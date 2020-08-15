package com.online.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.online.common.page.TailPage;
import com.online.core.business.IPortalBusiness;
import com.online.core.consts.domain.ConstsClassify;
import com.online.core.consts.domain.ConstsClassifyVO;
import com.online.core.consts.service.IConstsClassifyService;
import com.online.core.course.domain.Course;
import com.online.core.course.domain.CourseEnum;
import com.online.core.course.service.ICourseService;

@Controller
@RequestMapping("/course")
public class CourseListController {

	@Autowired
	private IConstsClassifyService constsClassifyService;
	
	@Autowired
	private IPortalBusiness portalBusiness;
	
	@Autowired
	private ICourseService courseService;
	
	/**
	 * 课程分类页
	 * @param c 分类
	 * @param sort  排序
	 * @param page  分页
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(String c, String sort,TailPage<Course> page) {
		ModelAndView mv =new ModelAndView("list");
		String curCode= "-1"; //当前一级分类code
		String curSubCode = "-2"; //当前二级分类的code
		
		//加载所有的课程分类
		Map<String, ConstsClassifyVO> classifyMap =portalBusiness.queryAllClassifyMap();
		List<ConstsClassifyVO> classifyList =new ArrayList<ConstsClassifyVO>();
		//将一级分类对象装入list
		for (ConstsClassifyVO vo : classifyMap.values()) {
			classifyList.add(vo);
		}
		mv.addObject("classifys", classifyList);
		
		//当前分类
		//根据前端出传入的c（code）查询
		ConstsClassify curClassify = constsClassifyService.getByCode(c);
		if(curClassify == null) {
			//没有此分类，加载所有一级分类的二级分类
			List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();
			for (ConstsClassifyVO vo : classifyMap.values()) {
				subClassifys.addAll(vo.getSubClassifyList());
			}
			mv.addObject("subClassifys", subClassifys);
		}else {
			if(!"0".equals(curClassify.getParentCode())) {//是二级分类
				curCode =curClassify.getParentCode();
				curSubCode =curClassify.getCode();
				//同父类的所有二级分类
				mv.addObject("subClassifys" , classifyMap.get(curClassify.getParentCode()).getSubClassifyList());
			}else { //是一级分类
				curCode= curClassify.getCode();
				//此一级分类下的所有二级分类
				mv.addObject("subClassifys", classifyMap.get(curClassify.getCode()).getSubClassifyList());
			}
		}
		mv.addObject("curCode", curCode);
		mv.addObject("curSubCode", curSubCode);
		
		//分页排序

		//设置课程分类
		Course course=new Course();
		if(!"-1".equals(curCode)) {
			course.setClassify(curCode);
		}
		if (!"-2".equals(curSubCode)) {
			course.setSubClassify(curSubCode);
		}
		//设置排序参数
		if("pop".equals(sort)) {
			page.descSortField("studyCount");
		}else {
			sort="last";
			page.descSortField("updateTime");
		}
		mv.addObject("sort", sort);
		
		//分页参数
		course.setOnsale(CourseEnum.ONSALE.value());
		page = this.courseService.queryCoursePage(course, page);
		mv.addObject("page", page);
		return mv;
		
		
	}
	
}
