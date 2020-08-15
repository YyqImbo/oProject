package com.online.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.core.auth.domain.AuthUser;
import com.online.core.auth.service.IAuthUserService;
import com.online.core.business.IPortalBusiness;
import com.online.core.consts.domain.ConstsClassifyVO;
import com.online.core.consts.domain.ConstsSiteCarousel;
import com.online.core.consts.service.IConstsSiteCarouselService;
import com.online.core.course.domain.Course;
import com.online.core.course.domain.CourseEnum;
import com.online.core.course.domain.CourseQueryDto;
import com.online.core.course.service.ICourseService;


/**
 * 首页index
 * @author yyq
 *
 */
@Controller
@RequestMapping()
public class PortalController {

	@Autowired
	private IConstsSiteCarouselService carouselService;
	
	@Autowired
	private IPortalBusiness portalBusiness;
	
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IAuthUserService authUserService;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv =new ModelAndView();
		mv.setViewName("index");
		
		//加载轮播
		List<ConstsSiteCarousel> carouselList = carouselService.queryCarousel(4);//数量为4
		mv.addObject("carouselList", carouselList);
		
		//课程分类
		List<ConstsClassifyVO> classifys= portalBusiness.queryAllClassify();
		//加载推荐课程
		portalBusiness.prepareRecomdCourses(classifys);
		mv.addObject("classifys", classifys);
		
		
		//获取5门实战课程（收费）推荐，根据权重（weight）降序排序
		CourseQueryDto cDto= new CourseQueryDto();
		cDto.setCount(5);
		cDto.setDescSortField("weight");
		cDto.setFree(CourseEnum.FREE_NOT.value());//收费
		List<Course> actionCourseList = this.courseService.queryCourseList(cDto);
		mv.addObject("actionCourseList", actionCourseList);
		
		//获取5门免费课程推荐，根据权重（weight）降序排序
		cDto.setFree(CourseEnum.FREE.value());//收费
		List<Course> freeCourseList = this.courseService.queryCourseList(cDto);
		mv.addObject("freeCourseList", freeCourseList);
		
		//获取7门java课程，根据权重（学习数量studyCount）进行排序
		cDto.setCount(7);
		cDto.setDescSortField("studyCount");
		cDto.setSubClassify("java");
		cDto.setFree(null);
		List<Course> javaCourseList = this.courseService.queryCourseList(cDto);
		mv.addObject("javaCourseList", javaCourseList);
		
		//加载讲师
		List<AuthUser> recomdTeacherList = this.authUserService.queryRecommendAuthUser();
		mv.addObject("recomdTeacherList", recomdTeacherList);
		
		return mv;
	}
	
}
