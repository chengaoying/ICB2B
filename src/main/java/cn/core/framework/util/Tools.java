package cn.core.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 工具类
 * @author JC
 * @date 2015-9-7
 */
public class Tools {

	protected static Log log = LogFactory.getLog(Tools.class);
	
	/**
	 * 生成随机的字符串
	 * @param len 字符串长度
	 * @return string
	 */
	public static String getRandomString(int len){
		Random random = new Random(); 
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<len;i++) {
			int itmp = random.nextInt(26) + 65 ;  
			sb.append(String.valueOf((char)itmp));
		}
		return sb.toString();
	}
	
	/**
	 * url编码：
	 * @param url
	 * @return
	 */
	public static String urlEncoder(String url){
		try {
			return URLEncoder.encode((url), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * utf8解码
	 * @param str
	 * @return
	 */
	public static String decodeUTF8(String str){
		try {
			byte bytes[] = str.getBytes("ISO-8859-1");
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 生成唯一的编号(订单、询价单、报价单的编号)
	 * 格式：4位随机码+年月日时分秒+用户id
	 * @param id 用户帐号id
	 * @return
	 */
	public static String generateUUId(int id){
		return Tools.getRandomString(4) + DateUtil.formatDate(new Date(),DateUtil.pattern_1) + id;
	}
	
	/**
	 * 使用正在表达式删除字符串中的html代码
	 * @param str
	 * @return
	 */
	public static String regexFilterHtmlCode(String str){
		String expression = "<(?!ul|li|/li|/ul|strong|/strong|dd|/dd)[^>]*>";
		Pattern p = Pattern.compile(expression);
	    Matcher m = p.matcher(str);
	    return m.replaceAll("");
	}
	
}
