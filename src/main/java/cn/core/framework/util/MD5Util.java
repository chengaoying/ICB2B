package cn.core.framework.util;

import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 采用MD5加密解密
 * @author jc
 * @date 2015-8-1
 */
public class MD5Util {

	protected static Log log = LogFactory.getLog(MD5Util.class);
	
	/**
	 * md5加密
	 * @param inStr
	 * @return
	 */
	public static String encodeByMD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			log.error("md5 encode error:"+e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * md5解密：一次加密需要執行兩次解密
	 * @param str
	 * @return
	 */
	public static String decodeByMD5(String str){
		return convertMD5(convertMD5(str));
	}
	
	private static String convertMD5(String inStr){
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		return new String(a);
	}
}