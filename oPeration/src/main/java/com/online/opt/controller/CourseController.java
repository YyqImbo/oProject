package com.online.opt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.online.common.page.TailPage;
import com.online.common.storage.QiniuStorage;
import com.online.common.util.CalendarUtil;
import com.online.common.util.JsonUtil;
import com.online.common.web.JsonView;
import com.online.core.auth.domain.AuthUser;
import com.online.core.auth.service.IAuthUserService;
import com.online.core.business.ICourseBusiness;
import com.online.core.business.IPortalBusiness;
import com.online.core.consts.domain.ConstsClassify;
import com.online.core.consts.domain.ConstsClassifyVO;
import com.online.core.consts.service.IConstsClassifyService;
import com.online.core.course.domain.Course;
import com.online.core.course.domain.CourseSectionVO;
import com.online.core.course.service.ICourseService;
import com.online.core.statics.domain.CourseStudyStaticsDto;
import com.online.core.statics.domain.StaticsVO;
import com.online.core.statics.service.IStaticsService;

/**
 * 课程管理
 * @author yyq
 *
 */
@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IPortalBusiness portalBusiness;
	
	@Autowired
	private ICourseBusiness courseBusiness;
	
	@Autowired
	private IConstsClassifyService constsClassifyService;
	
	@Autowired
	private IAuthUserService authUserService;
	
	@Autowired
	private IStaticsService staticsService;
	
	@RequestMapping("/pagelist")
	public ModelAndView pagelist(Course queryEntity,TailPage<Course> page) {
		ModelAndView mv = new ModelAndView("cms/course/pagelist");
		
		if(StringUtils.isNotEmpty(queryEntity.getName())){
			queryEntity.setName(queryEntity.getName().trim());
		}else{
			queryEntity.setName(null);
		}
		
		page.setPageSize(5);
		page = courseService.queryCoursePage(queryEntity, page);
		mv.addObject("page", page);
		mv.addObject("queryEntity", queryEntity);
		mv.addObject("curNav", "course");
		return mv;
	}
	
	/**
	 * 根据id获取
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public String getById(Long id){
		return JsonView.render(courseService.getById(id));
	}
	
	/**
	 * 课程上下架
	 */
	@RequestMapping("/doSale")
	@ResponseBody
	public String doSale(Course entity){
		courseService.updateSelectivity(entity);
		//更新课程总时长
		
		return new JsonView().toString();
	}
	
	/**
	 * 课程删除
	 */
	@RequestMapping("/doDelete")
	@ResponseBody
	public String doDelete(Course entity){
		courseService.delete(entity);
		return new JsonView().toString();
	}
	
	/**
	 * 课程详情
	 */
	@RequestMapping("/read")
	public ModelAndView courseRead(Long id){
		Course course = courseService.getById(id);
		if(null == course){
			return new ModelAndView("error/404"); 
		} 
		
		ModelAndView mv = new ModelAndView("cms/course/read");
		mv.addObject("curNav", "course");
		mv.addObject("course", course);
		
		//课程章节
		List<CourseSectionVO> chaptSections = this.courseBusiness.queryCourseSection(id);
		mv.addObject("chaptSections", chaptSections);
		
		//课程分类
		Map<String,ConstsClassifyVO> classifyMap = portalBusiness.queryAllClassifyMap();
		//所有一级分类
		List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();
		for(ConstsClassifyVO vo : classifyMap.values()){
			classifysList.add(vo);
		}
		mv.addObject("classifys", classifysList);
		
		List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();
		for(ConstsClassifyVO vo : classifyMap.values()){
			subClassifys.addAll(vo.getSubClassifyList());
		}
		mv.addObject("subClassifys", subClassifys);//所有二级分类
		
		//获取报表统计信息
		/*CourseStudyStaticsDto staticsDto = new CourseStudyStaticsDto();
		staticsDto.setCourseId(course.getId());
		staticsDto.setEndDate(new Date());
		staticsDto.setStartDate(CalendarUtil.getPreNDay(new Date(), 7));//前面7天的学习人数
		
		StaticsVO staticsVo = staticsService.queryCourseStudyStatistics(staticsDto);
		if(null != staticsVo){
			try {
				mv.addObject("staticsVo", JsonUtil.toJson(staticsVo));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		return mv;
	}
	
	/**
	 * 添加课程，先获取所有分类
	 */
	@RequestMapping("/add")
	public ModelAndView add(){
		ModelAndView mv = new ModelAndView("cms/course/add");
		mv.addObject("curNav", "course");
		Map<String,ConstsClassifyVO> classifyMap = portalBusiness.queryAllClassifyMap();
		//所有一级分类
		List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();
		for(ConstsClassifyVO vo : classifyMap.values()){
			classifysList.add(vo);
		}
		mv.addObject("classifys", classifysList);
		
		List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();
		for(ConstsClassifyVO vo : classifyMap.values()){
			subClassifys.addAll(vo.getSubClassifyList());
		}
		mv.addObject("subClassifys", subClassifys);//所有二级分类
		return mv; 
	}
	
	/**
	 * 添加、修改课程
	 */
	@RequestMapping("/doMerge")
	@ResponseBody
	public String doMerge(Course entity,@RequestParam MultipartFile pictureImg){
		String key = null;
		try {
			if (null != pictureImg && pictureImg.getBytes().length > 0) {
				key = QiniuStorage.uploadImage(pictureImg.getBytes());//七牛上传图片
				entity.setPicture(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//判断教师
		if(StringUtils.isNotEmpty(entity.getUsername())){
			AuthUser user = authUserService.getByUsername(entity.getUsername());
			if(null == user){
				return JsonView.render(1).toString();
			}
		}else{
			return JsonView.render(1).toString();//用户名输入错误
		}

		if(null != entity.getId()){
			courseService.updateSelectivity(entity);
		}else{
			//判断获取分类
			if(StringUtils.isNotEmpty(entity.getClassify())){
				ConstsClassify classify = this.constsClassifyService.getByCode(entity.getClassify());
				if(null != classify){
					entity.setClassifyName(classify.getName());
				}
			}
			if(StringUtils.isNotEmpty(entity.getSubClassify())){
				ConstsClassify subClassify = this.constsClassifyService.getByCode(entity.getSubClassify());
				if(null != subClassify){
					entity.setSubClassifyName(subClassify.getName());
				}
			}
			courseService.createSelectivity(entity);
		}
		return JsonView.render(entity).toString();
	}
	
	//继续添加章节
		@RequestMapping("/append")
		public ModelAndView appendSection(Long courseId){
			Course course = courseService.getById(courseId);
			if(null == course)
				return new ModelAndView("error/404"); 
			
			ModelAndView mv = new ModelAndView("cms/course/append");
			mv.addObject("curNav", "course");
			mv.addObject("course", course);
			
			List<CourseSectionVO> chaptSections = this.courseBusiness.queryCourseSection(courseId);
			mv.addObject("chaptSections", chaptSections);
			
			return mv;
		}

}
