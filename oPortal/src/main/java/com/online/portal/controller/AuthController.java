package com.online.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.common.util.EncryptUtil;
import com.online.common.util.MD5Util;
import com.online.common.web.JsonView;
import com.online.common.web.SessionContext;
import com.online.core.auth.domain.AuthUser;
import com.online.core.auth.service.IAuthUserService;

/**
 * 用户登录 & 注册
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private IAuthUserService authUserService;
	
	/**
	 * 注册页面
	 */
	@RequestMapping(value = "/register")
	public  ModelAndView register(){
		if(SessionContext.isLogin()){
			return new ModelAndView("redirect:/index.html");
		}
		return new ModelAndView("auth/register");
	}
	
	
	/**
	 * 实现注册
	 */
	@RequestMapping(value = "/doRegister")
	@ResponseBody
	public String doRegister(AuthUser authUser, String identiryCode, HttpServletRequest request) {
		//验证码判断
		if(identiryCode!=null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
			return JsonView.render(2); //验证码错误
		}
		
		AuthUser tmpUser = authUserService.getByUsername(authUser.getUsername());
		if(tmpUser != null){
			return JsonView.render(1);//用户已存在
		}else{
			authUser.setPassword(MD5Util.MD5hashed(authUser.getPassword()));//MD5加密
			authUserService.createSelectivity(authUser);
			return JsonView.render(0);//注册成功
		}
	}
	
	
	/**
	 * 登录页面
	 */
	@RequestMapping(value = "/login")
	public  ModelAndView login(){
		if(SessionContext.isLogin()){
			return new ModelAndView("redirect:/index.html");
		}
		return new ModelAndView("auth/login");
	}
	
	/**
	 * ajax登录
	 */
	@RequestMapping(value = "/ajaxlogin")
	@ResponseBody
	public String ajaxLogin(AuthUser user, String identiryCode, Integer rememberMe, HttpServletRequest request){
		//验证码判断
		if(identiryCode!=null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
			return JsonView.render(2, "验证码不正确！");
		}
		Subject currentUser = SecurityUtils.getSubject();
		//MD5加密
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),MD5Util.MD5hashed(user.getPassword()));
		try {
			if(rememberMe != null && rememberMe == 1){
				token.setRememberMe(true);
			}
			currentUser.login(token);//shiro：不抛出异常，登陆成功
			return  JsonView.render(0);
		}catch(AuthenticationException e){ //登录失败
			return JsonView.render(1, "用户名或密码不正确");
		}
	}
	
	/**
	 * login页面登录
	 * @param user
	 * @param identiryCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/doLogin")
	public ModelAndView doLogin(AuthUser user, String identiryCode, HttpServletRequest request){
		
		//如果已经登录过
		if(SessionContext.getAuthUser() != null){
			return new ModelAndView("redirect:/user/home.html");
		}
		
		//验证码判断
		if(identiryCode!=null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
			ModelAndView mv = new ModelAndView("auth/login");
			mv.addObject("errcode", 1);//验证码不正确
			return mv;
		}
		//MD5加密
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),MD5Util.MD5hashed(user.getPassword()));
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);//shiro实现登录
			return new ModelAndView("redirect:/user/home.html");
		}catch(AuthenticationException e){ //登录失败
			ModelAndView mv = new ModelAndView("auth/login");
			mv.addObject("errcode", 2);//用户名密码错误
			return mv;
		}
	}
	
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		SessionContext.shiroLogout();
		return new ModelAndView("redirect:/index.html");
	}
	

}
