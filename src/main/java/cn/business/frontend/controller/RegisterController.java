package cn.business.frontend.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.User;
import cn.business.backend.service.UserService;
import cn.business.frontend.service.RegisterService;
import cn.core.framework.common.Constant;
import cn.core.framework.util.DateUtil;
import cn.core.framework.util.EmailUtil;
import cn.core.framework.util.Tools;

/**
 * 用户注册控制器
 * @author JC
 * @date 2016-5-28
 * @since v1.0
 */
@Controller
public class RegisterController extends CommonController{

	
	@Resource
	private UserService userService;
	
	@Resource
	private RegisterService registerService;
	
	/**
	 * 用户注册页面
	 * @return
	 */
	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public ModelAndView register() 
	{
		Map<String, Object> map = new HashMap<>();
		String checkCode = Tools.getRandomString(4);
		map.put("checkCode", checkCode);
		session.setAttribute("checkCode", checkCode);
		return this.getModelView("register",map);
	}
	
	/**
	 * 用户注册处理（异步调用）
	 * @param user 用户
	 * @param type 注册类型，email:邮箱注册，phone:手机注册
	 * @param checkCode 验证码
	 * @return
	 */
	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> register(@ModelAttribute("User") User user,
			@RequestParam String type, @RequestParam String checkCode) 
	{
		Map<String, String> map = null;
		
		if(type.equals("phone")){
			map = registerByPhone(user, checkCode);
		}else{
			map = registerByEmail(user, checkCode);
		}
		
		return map;
	}
	
	/**
	 * 手机注册
	 * @param user
	 * @return
	 */
	private Map<String,String> registerByPhone(User user, String checkCode){
		String sessionCheckCode = session.getAttribute("checkCode").toString();
		Map<String, String> map = registerService.reisterByPhone(user, checkCode, sessionCheckCode);
		
		return map;
	}
	
	/**
	 * 邮箱注册
	 * @param user
	 * @param checkCode
	 * @return
	 */
	private Map<String,String> registerByEmail(User user, String checkCode){
		String url = this.getFrontendUrl()+"/processActivate.html";
		String sessionCheckCode = session.getAttribute("checkCode").toString();
		Map<String, String> map = registerService.reisterByEmail(url, user, checkCode, sessionCheckCode);
		log.info("user reister info:" + map.get("info"));
		
		String status = map.get("status");
		if (status.equals(Constant.CONST_SUCCESS)) {
			// 保存用户信息至session中
			this.saveUserToSession(user);
			// 跳转至激活页面
			map.put("data", "/activate.html");
		}
		return map;
	}
	
	/**
	 * 重新发送激活邮件（异步调用）
	 * @param account 用户帐号
	 * @return
	 */
	@RequestMapping(value = "/sendActiveEmail.html")
	public String sendActiveEmail()
	{
		//更新用户注册时间
		User _user = this.getUserFromSession();
		_user.setAddTime(DateUtil.formatDate(new Date()));
		this.userService.update(_user);
		
		String url = this.getFrontendUrl()+"/processActivate.html";
		registerService.sendEmail(url, this.getUserFromSession());
		return "redirect:/" + Constant.CONST_FRONTEND + "/activate.html";
	}

	/**
	 * 激活页面
	 * 
	 * @param status 激活状态：0为 未进行激活，-1为激活失败，1为激活成功（用户界面显示对应的信息）
	 * @return
	 */
	@RequestMapping(value = "/activate.html")
	public ModelAndView mailActivate(@RequestParam(required = false) String status,
			@RequestParam(required = false) String info) 
	{
		Map<String, Object> map = new HashMap<>();
		if (null == status || status.equals("0")) {
			String email = this.getUserFromSession().getEmail();
			map.put("status", 0);
			map.put("email", email);
			map.put("mailUrl", EmailUtil.getMailLoginUrl(email));
		} else if (status.equals("-1")) {
			map.put("status", -1);
			map.put("info", info);
		} else if (status.equals("1")) {
			map.put("status", 1);
			map.put("info", info);
		}

		return this.getModelView("activate", map);
	}

	/**
	 * 处理用户激活操作
	 * 
	 * @param user  用户帐号
	 * @param activateCode 激活码
	 * @return
	 */
	@RequestMapping(value = "/processActivate.html", method = RequestMethod.GET)
	public String processActivate(@RequestParam String userId, @RequestParam String activateCode)
	{
		int userId64 = Integer.parseInt(new String(Base64.decodeBase64(userId.getBytes())));
		Map<String, String> map = registerService.processActivate(userId64, activateCode);
		String status = map.get("status").toString();
		String info = map.get("info");
		
		// 更新用户session
		this.updateUserInSession(userService.loadById(userId64));

		if (Constant.CONST_SUCCESS.equals(status)) {
			// 激活成功
			return "redirect:/" + Constant.CONST_FRONTEND + "/activate.html?status=1&info="+Tools.urlEncoder(info);
		} else {
			// 激活失败
			return "redirect:/" + Constant.CONST_FRONTEND + "/activate.html?status=-1&info="+Tools.urlEncoder(info);
		}
	}

}
