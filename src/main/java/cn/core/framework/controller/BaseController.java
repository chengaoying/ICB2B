package cn.core.framework.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.core.framework.common.Configuration;
import cn.core.framework.common.Constant;
import cn.core.framework.util.FTPUtil;

/**
 * 控制器基础类
 * @author CGY
 * @date 2015-10-14
 */
public class BaseController {

	protected static Log log = LogFactory.getLog(BaseController.class);
	
	
	/**
	 * 获取前端异步交互时后台响应的结果
	 * @param status 结果：1-成功，0-失败
	 * @param info	响应信息
	 * @param data	成功状态返回的数据
	 * @return map
	 */
	protected Map<String,Object> getResultMap(Object status,String info, Object data){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		map.put("info", info);
		map.put("data", data);
		return map;
	}
	
	/**
	 * 获取项目根路径
	 * @return
	 */
	public String getRootPath(){
		return System.getProperty("ICB2B.webapp.root").replace("\\", "/").replace("//", "/");
	}
	
	
	/**
	 * 文件上传
	 * @param name
	 * @param path
	 * @param file
	 * @return
	 */
	protected Map<String,Object> uploadFile(String name, String path,CommonsMultipartFile file){
		String deployModel = Configuration.getProperty("deploy_model");
		if(deployModel.equalsIgnoreCase("cluster")){
			return this.uploadFileFTP(name, path, file);
		}else{
			return this.uploadFileLocal(name, path, file);
		}
	}
	
	/**
	 * 文件本地上传
	 * @param name 用户名
	 * @param path	文件路径
	 * @param file	待上传的文件
	 * @return map 
	 */
	protected Map<String,Object> uploadFileLocal(String name, String path,CommonsMultipartFile file)
	{
		String fileName = "";
		try {
			String[] ss = file.getOriginalFilename().split("\\.");
			fileName = ss[0].hashCode() + "." + ss[1];
			String localPath = this.getRootPath().concat(path).replace("//", "/");
			if (!(new File(localPath).isDirectory())) {
				new File(localPath).mkdir();
			}
			FileCopyUtils.copy(file.getBytes(), new File(localPath + fileName));			
			return this.getResultMap(Constant.CONST_SUCCESS, "上传成功", path + fileName);
		} catch (Exception e) {
			log.error("文件上传失败，上传用户为：" + name + "，文件名：" + fileName + "，异常原因：" + e.getMessage());
			return this.getResultMap(Constant.CONST_ERROR, "文件上传错误，原因：" + e, null);
		} 
	}
	
	/**
	 * ftp文件上传
	 * @param account
	 * @param path
	 * @param file
	 * @return
	 */
	protected Map<String,Object> uploadFileFTP(String name, String path,CommonsMultipartFile file)
	{
		String fileName = "";
		try {
			String[] str = file.getOriginalFilename().split("\\.");
			fileName = str[0].hashCode() + "." + str[1];
			
			boolean result = FTPUtil.upload(fileName, path, file.getInputStream());
			if(result){
				return this.getResultMap(Constant.CONST_SUCCESS, "上传成功", path + fileName);
			}else{
				return this.getResultMap(Constant.CONST_ERROR, "上传失败", path + fileName);
			}
		} catch (IOException e) {
			log.error("文件上传失败，上传用户为：" + name + "，文件名：" + fileName + "，异常原因：" + e.getMessage());
			return this.getResultMap(Constant.CONST_ERROR, "文件上传错误，原因：" + e, null);
		}
	}
	
	/**
	 * 文件下载
	 * @param filePath 文件相对路径
	 */
	protected void downloadFile(HttpServletResponse response, String filePath){
		filePath = this.getRootPath().concat(filePath).replace("//", "/");
		File file = new File(filePath);
		if(!file.exists()) return;
		
		InputStream is = null;
		OutputStream os = null;
		try {
			//设置response的Header
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			
			// 以流的形式下载文件
			is = new BufferedInputStream(new FileInputStream(filePath));
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			is.close();

			os = new BufferedOutputStream(response.getOutputStream());
			os.write(buffer);
			os.flush();
			os.close();
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("文件下载失败，文件名："+filePath+"，异常原因："+e.getMessage());
		} finally{
			
		}
	}
}
