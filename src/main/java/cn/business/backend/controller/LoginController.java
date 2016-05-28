package cn.business.backend.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.SysUser;
import cn.business.backend.service.SysUserService;
import cn.core.framework.common.Constant;
import cn.core.framework.util.UrlUtil;

/**
 * 控制器:后台登入
 * @author JC
 * @date 2015-7-26
 */
@Controller("backendLoginController")
public class LoginController extends CommonController{

	protected static Log log = LogFactory.getLog(LoginController.class);
	
	@Resource
	private SysUserService sysUserService;
	
	/**
	 * 登入界面
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login(){
		return getModelView("login");
	}
	
	/**
	 * 登入验证
	 * @param	username  登入用户名
	 * @param	passowrd  登入密码
	 * @return	map 
	 */
	@RequestMapping(value="/loginValidate",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> loginValidate(@ModelAttribute("user")SysUser user){
		
		Map<String,Object> map = null;
		SysUser sysUser = sysUserService.loadByAccount(user.getAccount());
		
		//登入验证
		String status = sysUserService.loginValidate(sysUser,user.getPassword());
		if(status.equals(Constant.CONST_SUCCESS)){
			map = this.getResultMap(1, "success", request.getContextPath() + "/" + Constant.CONST_BACKEND +"/index");
			
			//登入成功更新用户登入信息
			sysUser.setLastIp(UrlUtil.getIpAddr(request));
			sysUser.setLastTime(new Date());
			sysUserService.update(sysUser);
			
			//保存用户信息至session中
			this.saveUserToSession(sysUser);
		}else{
	        map = this.getResultMap(0, "用户名或密码错误",null);
		}
        return map;
	}
	
	/**
	 * 退出登入
	 * @throws IOException 
	 */
	@RequestMapping("logout")
	public String logout(){
		this.removeUserFromSession();
		return "redirect:/" + Constant.CONST_BACKEND +"/login";
	}
	
}
