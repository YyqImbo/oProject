package com.online.opt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.common.web.SessionContext;

/**
 * 后台首页
 * @author yyq
 *
 */
@Controller
@RequestMapping()
public class CmsController {
	
	@RequestMapping("/index")
	public ModelAndView index() {
		if(SessionContext.isLogin()){
			ModelAndView mv = new ModelAndView("cms/index");
			mv.addObject("curNav", "home");
			return mv;
		}else{
			return new ModelAndView("auth/login");
		}
	}

}
