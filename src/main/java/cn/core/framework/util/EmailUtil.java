package cn.core.framework.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.impl.util.Base64;

import cn.core.framework.common.Configuration;

/**
 * 邮件工具类
 * @author CGY
 * @date 2015-9-9
 */
public class EmailUtil {

	protected static Log log = LogFactory.getLog(EmailUtil.class);
	
	/**
	 * 发送邮件
	 * @param toMail 收件人
	 * @param subject 主题
	 * @param context 内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void sendMail(String toMail, String subject, String context){
		Properties properties = new Properties();
		//设置传输协议
		properties.setProperty("mail.transport.protocol", "smtp");
		//设置需要鉴权
		properties.setProperty("mail.smtp.auth", "true");
		
		//一次会话
		Session session = Session.getInstance(properties);
		session.setDebug(true);
		
		//会话中的消息
		Message message = new MimeMessage(session);
		try { 
			//发件人
			message.setFrom(new InternetAddress(Configuration.getProperty("mail_sender"),"屏芯网","utf-8"));

			//将中文转化为GB2312编码，通过Base64后设置邮件名称
			subject = new String(Base64.encode((subject).getBytes("GB2312"))); 
			message.setSubject("=?GB2312?B?" + subject + "?="); 

			//内容
			message.setContent(context, "text/html;charset=utf-8");
			
			//发送邮件
			Transport tran = session.getTransport();
			//使用帐号和密码连接服务器
			tran.connect(Configuration.getProperty("mail_server_host"), 
						 Configuration.getProperty("user"),
						 Configuration.getProperty("password"));
			tran.sendMessage(message, new Address[] { new InternetAddress(toMail) });
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("邮件发送异常：" + e.getMessage());
		}
	}
	
	/**
	 * 根据邮箱获取对应的邮箱登入地址
	 * @param email
	 * @return 
	 */
	public static String getMailLoginUrl(String email){
		String[][] urlMap = {
				{"qq.com","http://mail.qq.com"},{"gmail.com","http://mail.google.com"},
				{"sina.com","http://mail.sina.com.cn"},{"163.com","http://mail.163.com"},
				{"126.com","http://mail.126.com"},{"sohu.com","http://mail.sohu.com/"},
				{"sogou.com","http://mail.sogou.com/"},{"139.com","http://mail.10086.cn/"},
				{"foxmail.com","http://www.foxmail.com"},{"yahoo.cn","http://mail.cn.yahoo.com/"},
				{"yahoo.com.cn","http://mail.cn.yahoo.com/"},{"hotmail.com","http://www.hotmail.com"},
		};
		
		for (String[] str : urlMap) {
			if(email.indexOf(str[0]) != -1)
				return str[1];
		}
		return null;
	}
}
