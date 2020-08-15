package com.online.web.auth;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.online.common.util.JsonUtil;
import com.online.common.web.HttpHelper;
import com.online.common.web.JsonView;

public class AuthFilter extends FormAuthenticationFilter {
	private static final Integer SHIRO_TIME_OUT = 1001;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		return super.isAccessAllowed(request, response, mappedValue);
	}
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		//获取请求路径
		String loginUrl=httpServletRequest.getServletPath();
		
		//判断请求路径
		if(loginUrl.equals("/index.html")) {
			return true;
		}
		//是否有授权
		Subject subject=SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			return true;
		}
		
		// 判断是否为ajax请求
		if(HttpHelper.isAjaxRequest(httpServletRequest)) {
			JsonView jsonView=new JsonView();
			jsonView.setErrorCode(SHIRO_TIME_OUT);
			jsonView.setMessage("登录超时");
			HttpServletResponse httpServletResponse=(HttpServletResponse)response;
			PrintWriter printWriter=httpServletResponse.getWriter();
			httpServletResponse.setContentType("application/json");
			printWriter.write(JsonUtil.toJson(jsonView));
			printWriter.flush();
			printWriter.close();
		}else {
			saveRequestAndRedirectToLogin(request, response);
		}
		
		// 如果没有授权则跳转到登录页面
		return false;
	}
}
