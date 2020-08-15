package com.online.portal.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.common.storage.QiniuStorage;
import com.online.common.web.JsonView;
import com.online.common.web.SessionContext;
import com.online.core.auth.domain.AuthUser;
import com.online.core.auth.service.IAuthUserService;
import com.online.core.business.ICourseBusiness;
import com.online.core.course.domain.Course;
import com.online.core.course.domain.CourseQueryDto;
import com.online.core.course.domain.CourseSection;
import com.online.core.course.domain.CourseSectionVO;
import com.online.core.course.service.ICourseSectionService;
import com.online.core.course.service.ICourseService;
import com.online.core.user.domain.UserCourseSection;
import com.online.core.user.service.IUserCourseSectionService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private ICourseService courseService;

	@Autowired
	private ICourseSectionService courseSectionService;

	@Autowired
	private IAuthUserService authUserService;

	@Autowired
	private IUserCourseSectionService userCourseSectionService;

	@Autowired
	private ICourseBusiness courseBusiness;

	/**
	 * 课程章节页面
	 * 
	 * @return
	 */
	@RequestMapping("/learn/{courseId}")
	public ModelAndView learn(@PathVariable Long courseId) {
		if (courseId == null) {
			return new ModelAndView("error/404");
		}

		// 获取课程
		Course course = courseService.getById(courseId);
		if (course == null) {
			return new ModelAndView("error/404");
		}

		// 获取课程章节
		ModelAndView mv = new ModelAndView("learn");
		List<CourseSectionVO> chaptSections = this.courseBusiness.queryCourseSection(courseId);
		mv.addObject("course", course);
		mv.addObject("chaptSections", chaptSections);

		// 获取讲师
		AuthUser courseTeacher = this.authUserService.getByUsername(course.getUsername());
		if (courseTeacher != null) {
			if (courseTeacher.getHeader() != null) {// 设置讲师头像
				courseTeacher.setHeader(QiniuStorage.getUrl(courseTeacher.getHeader()));
			}
		}
		mv.addObject("courseTeacher", courseTeacher);

		// 获取推荐课程
		CourseQueryDto courseQueryDto = new CourseQueryDto();
		courseQueryDto.setCount(5);
		courseQueryDto.setDescSortField("weight");
		courseQueryDto.setSubClassify(course.getSubClassify());
		List<Course> recomdCourseList = this.courseService.queryCourseList(courseQueryDto);
		mv.addObject("recomdCourseList", recomdCourseList);

		// 当前最新学习的章节
		UserCourseSection userCourseSection = new UserCourseSection();
		userCourseSection.setCourseId(course.getId());
		userCourseSection.setUserId(SessionContext.getUserId());
		userCourseSection = this.userCourseSectionService.queryLatest(userCourseSection);
		if (userCourseSection != null) {
			CourseSection curCourseSection = this.courseSectionService.getById(userCourseSection.getSectionId());
			mv.addObject("curCourseSection", curCourseSection);
		}

		return mv;

	}

	@RequestMapping("/video/{sectionId}")
	public ModelAndView video(@PathVariable Long sectionId) {
		if (null == sectionId)
			return new ModelAndView("error/404");

		CourseSection courseSection = courseSectionService.getById(sectionId);
		if (null == courseSection)
			return new ModelAndView("error/404");

		// 课程章节
		ModelAndView mv = new ModelAndView("video");
		List<CourseSectionVO> chaptSections = this.courseBusiness.queryCourseSection(courseSection.getCourseId());
		mv.addObject("courseSection", courseSection);
		mv.addObject("chaptSections", chaptSections);

		// 插入或更新当前用户学习记录
		UserCourseSection userCourseSection = new UserCourseSection();
		userCourseSection.setUserId(SessionContext.getUserId());
		userCourseSection.setCourseId(courseSection.getCourseId());
		userCourseSection.setSectionId(courseSection.getId());
		UserCourseSection result = userCourseSectionService.queryLatest(userCourseSection);

		if (null == result) {// 如果没有，插入
			userCourseSection.setCreateTime(new Date());
			userCourseSection.setCreateUser(SessionContext.getUsername());
			userCourseSection.setUpdateTime(new Date());
			userCourseSection.setUpdateUser(SessionContext.getUsername());

			userCourseSectionService.createSelectivity(userCourseSection);
		} else {
			result.setUpdateTime(new Date());
			userCourseSectionService.updateSelectivity(result);
		}
		return mv;

	}
	
	@RequestMapping(value = "/getCurLeanInfo")
	@ResponseBody
	public String getCurLeanInfo(){
		JsonView jv = new JsonView();
		//加载当前用户学习最新课程
		if(SessionContext.isLogin()){
			UserCourseSection userCourseSection = new UserCourseSection();
			userCourseSection.setUserId(SessionContext.getUserId());
			userCourseSection = this.userCourseSectionService.queryLatest(userCourseSection);
			if(null != userCourseSection){
				JSONObject jsObj = new JSONObject();
				CourseSection curCourseSection = this.courseSectionService.getById(userCourseSection.getSectionId());
				jsObj.put("curCourseSection", curCourseSection);
				Course curCourse = courseService.getById(userCourseSection.getCourseId());
				jsObj.put("curCourse", curCourse);
				jv.setData(jsObj);
			}
		}
		return jv.toString();
	}
	
	

}
