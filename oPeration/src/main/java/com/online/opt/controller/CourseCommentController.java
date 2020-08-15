package com.online.opt.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.common.page.TailPage;
import com.online.common.storage.QiniuStorage;
import com.online.common.web.JsonView;
import com.online.core.course.domain.CourseComment;
import com.online.core.course.service.ICourseCommentService;

/**
 * 课程评论管理
 */
@Controller
@RequestMapping("/courseComment")
public class CourseCommentController {
	
	@Autowired
	private ICourseCommentService courseCommentService;
	
	/**
	 * 课程评论管理
	 */
	@RequestMapping("/pagelist")
	public ModelAndView commentSegment(CourseComment queryEntity , TailPage<CourseComment> page){
		ModelAndView mv = new ModelAndView("cms/course/readComment");
		TailPage<CourseComment> commentPage = this.courseCommentService.queryCourseCommentPage(queryEntity, page);
		
		mv.addObject("page", commentPage);
		mv.addObject("queryEntity", queryEntity);
		return mv;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(CourseComment entity){
		courseCommentService.delete(entity);
		return new JsonView().toString();
	}
	
}

