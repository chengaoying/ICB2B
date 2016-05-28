/**
 * 
 */
package cn.core.framework.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import cn.core.framework.common.Configuration;

/**
 * 工具类
 * @author JC
 * @date 2015-11-7
 */
public class FTPUtil {
	
	protected static Log log = LogFactory.getLog(FTPUtil.class);
	
	/**
	* Description: 向FTP服务器上传文件
	* @param filename 上传到FTP服务器上的文件名
	* @param input 输入流
	* @return 成功返回true，否则返回false
	*/ 
	public static boolean upload(String filename, String path, InputStream input){ 
	    String url = Configuration.getProperty("ftp_server_host");
	    int port = Integer.parseInt(Configuration.getProperty("ftp_server_port"));
	    String username = Configuration.getProperty("ftp_user");
	    String password = Configuration.getProperty("ftp_password");
	    
	    boolean success = false; 
	    FTPClient ftp = new FTPClient(); 
	    try { 
	        int reply; 
	        ftp.connect(url, port);//如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	        ftp.login(username, password);
	        reply = ftp.getReplyCode(); 
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	            ftp.disconnect(); 
	            return success; 
	        } 
	        
	        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	        boolean rst = ftp.changeWorkingDirectory(path);
	        if(!rst){
	        	ftp.makeDirectory(path);
	        	ftp.changeWorkingDirectory(path);
	        }
	        ftp.storeFile(filename, input);
	        
	        input.close(); 
	        ftp.logout(); 
	        success = true; 
	    } catch (IOException e) {
	    	log.error("ftp上传文件异常：" + e.getMessage());
	    } finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	            } catch (IOException ioe) { 
	            	log.error("ftp连接关闭异常：" + ioe.getMessage());
	            } 
	        } 
	    } 
	    return success; 
	}
	
}

