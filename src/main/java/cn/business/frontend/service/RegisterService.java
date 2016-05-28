package cn.business.frontend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import cn.business.backend.model.User;
import cn.business.backend.service.UserService;
import cn.core.framework.common.Constant;
import cn.core.framework.util.DateUtil;
import cn.core.framework.util.EmailUtil;
import cn.core.framework.util.MD5Util;
import cn.core.framework.util.Tools;

/**
 * 注册服务类
 * @author JC
 * @date 2016-5-28
 * @since v1.0
 */
@Service("registerService")
public class RegisterService {

	protected static Log log = LogFactory.getLog(RegisterService.class);
	
	@Resource
	private UserService userService;
	
	/**
	 * 手机注册
	 * @param usesr
	 * @param checkCode
	 * @param sessionCheckCode
	 * @return
	 */
	public Map<String,String> reisterByPhone(User usesr, String checkCode, String sessionCheckCode){
		Map<String,String> map = new HashMap<>();
		
		return map;
	}
	
	/**
	 * 用户邮箱注册
	 * @param url	激活地址
	 * @param user	注册用户
	 * @param checkCode	验证码
	 * @param sessionCheckCode session中保存的验证码
	 */
	public Map<String,String> reisterByEmail(String url, User user, String checkCode
			,String sessionCheckCode)
	{
		Map<String,String> map = new HashMap<>();
		
		//判断用户是否已注册
		if(userService.loadById(user.getId()) != null){
			map.put("status", Constant.CONST_ERROR);
			map.put("info", "该邮箱已注册！");
			return map;
		}
		
		if(checkCode.equalsIgnoreCase(sessionCheckCode)){
			String activateCode = MD5Util.encodeByMD5(Tools.getRandomString(16));
			user.setName(Tools.getRandomString(6));
			user.setActivateCode(activateCode);
			user.setPassword(MD5Util.encodeByMD5(user.getPassword()));
			user.setStatus(Constant.CONST_MEMBER_STATUS_INACTIVE);
			user.setAddTime(DateUtil.formatDate(new Date()));
			userService.save(user);
			
			//发送激活邮件
			this.sendEmail(url, user);
			
			map.put("status", Constant.CONST_SUCCESS);
		}else{
			map.put("status", Constant.CONST_ERROR);
			map.put("info", "验证码错误！");
		}
		return map;
	}
	
	/**
	 * 发送激活邮件
	 * @param url 地址
	 * @param account 用户帐号
	 */
	public void sendEmail(String url, User user){
		//过滤80端口
		url = url.replace(":80/", "/");
		try {
			String params = "?userId=" +  Base64.encodeBase64String(String.valueOf(user.getId()).getBytes())
						  + "&activateCode=" + user.getActivateCode();
			url += params;
		} catch (Exception e) {
			log.error("激活邮件地址编码异常，原因：" + e.getMessage());
		}
		String subject = "伙拼网会员激活邮件";
		StringBuffer context = new StringBuffer("请点击下面链接激活账号，48小时内有效，链接只能使用一次，请尽快激活！</br>");
		context.append("Please click the following link to activate the account, within 48 hours, the link can only be used once, please activate as soon as possible!<br>");
		context.append("<a href=\"" + url + "\">" + url + "</a>");
		EmailUtil.sendMail(user.getEmail(), subject, context.toString());
	}
	
	/**
	 * 用户邮件激活操作处理
	 * @param userId 用户id
	 * @param activateCode	激活码
	 * @return
	 */
	public Map<String,String> processActivate(int userId,String activateCode)
	{
		Map<String,String> map = new HashMap<>();
		User u = userService.loadById(userId);
		
		//判断帐号是否存在
		if(null != u){
			//判断用户是否已激活
			if(u.getStatus() != Constant.CONST_MEMBER_STATUS_INACTIVE){
				map.put("status", Constant.CONST_ERROR);
				map.put("info", "激活失败，帐号已激活！");
			}else{ 
				//判断激活时间是否超过48小时
				long nowHours = DateUtil.formatDateToHour(new Date());
				Date addTime = DateUtil.convertDate(u.getAddTime(), DateUtil.pattern_default);
				long registerHours = DateUtil.formatDateToHour(addTime);
				if((nowHours-registerHours) > 48){
					//超过激活时长
					map.put("status", Constant.CONST_ERROR);
					map.put("info", "激活失败，超过激活时长，请重新激活或重新注册帐号再激活！");
				}else{
					//判断激活码是否正确
					if(activateCode.equals(u.getActivateCode())){
						map.put("status", Constant.CONST_SUCCESS);
						map.put("info", "恭喜您激活成功！");
						
						//修改用户状态为待完善信息
						u.setStatus(Constant.CONST_MEMBER_STATUS_INCOMPLETE);
						userService.update(u);
					}else{
						map.put("status", Constant.CONST_ERROR);
						map.put("info", "激活失败，激活码不正确！");
					}
				}
			}
		}else{
			map.put("status", Constant.CONST_ERROR);
			map.put("info", "激活失败，帐号不存在！");
		}
		return map;
	}
}
