package cn.core.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author CGY
 * @date 2015-9-8
 */
public class DateUtil {
	
	public static String pattern_default = "yyyy-MM-dd HH:mm:ss";
	public static String pattern_1 = "yyyyMMddHHmmss";
	public static String pattern_2 = "yyyy-MM-dd";
	
	/**
	 * 日期格式化
	 * @param date 日期
	 * @return
	 */
	public static String formatDate(Date date){
		if(null == date || "".equals(date)) date = new Date();
		return formatDate(date, null);
	}
	
	/**
	 * 日期格式化
	 * @param date 日期
	 * @param pattern 日期模式
	 * @return
	 */
	public static String formatDate(Date date,String pattern){
		if(null == date || "".equals(date)) 
			date = new Date();
		if(null == pattern || "".equals(pattern)) 
			pattern = DateUtil.pattern_default;
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	} 
	
	/**
	 * 将时间字符串转换成日期类型
	 * @param date
	 * @param pattern
	 * @return date
	 */
	public static Date convertDate(String date,String pattern){
		Date _date = null;
		if(null == pattern || "".equals(pattern)) pattern = DateUtil.pattern_2;
		try {
			_date = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return _date;
	}
	
	/**
	 * 获取当前时间的小时数
	 * @param date
	 * @return
	 */
	public static long formatDateToHour(Date date){
		Long time = date.getTime();
		return time/1000/60/60;
	}
}
