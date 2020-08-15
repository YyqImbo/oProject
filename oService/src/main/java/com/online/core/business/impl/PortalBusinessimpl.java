package com.online.core.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.core.business.IPortalBusiness;
import com.online.core.consts.domain.ConstsClassify;
import com.online.core.consts.domain.ConstsClassifyVO;
import com.online.core.consts.service.IConstsClassifyService;
import com.online.core.course.domain.Course;
import com.online.core.course.domain.CourseQueryDto;
import com.online.core.course.service.ICourseService;

@Service
public class PortalBusinessimpl implements IPortalBusiness {
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IConstsClassifyService classifyService;


	/**
	 * 设置分类集合并返回Map
	 */
	@Override
	public Map<String, ConstsClassifyVO> queryAllClassifyMap() {
		Map<String, ConstsClassifyVO> resultMap= new LinkedHashMap<String,ConstsClassifyVO>();
		Iterator<ConstsClassify> iterator= classifyService.queryAll().iterator();
		while (iterator.hasNext()) {
			ConstsClassify classify= iterator.next();
			//判断是否为一级分类
			if("0".equals(classify.getParentCode())) {
				ConstsClassifyVO classifyVO = new ConstsClassifyVO();
				BeanUtils.copyProperties(classify, classifyVO);
				//Map中key为一级分类的code，value是一级分类的对象
				resultMap.put(classifyVO.getCode(), classifyVO);
			}else {
				//如果在Map中已有自己的父分类，则父分类VO的list添加此二级分类对象
				if(resultMap.get(classify.getParentCode()) != null) {
					resultMap.get(classify.getParentCode()).getSubClassifyList().add(classify);
				}
			}
		}
		return resultMap;
	}

	/**
	 * 首页
	 * 获取所有的分类,返回一级分类的list，一级分类下有二级分类的list
	 */
	@Override
	public List<ConstsClassifyVO> queryAllClassify() {
		List<ConstsClassifyVO> resultList = new ArrayList<ConstsClassifyVO>();
		for (ConstsClassifyVO vo : this.queryAllClassifyMap().values()) {
			//将一级分类vo添加到list中
			resultList.add(vo);		
		}
		return resultList;
	}
	
	/**
	 * 首页
	 * 为ConstsClassifyVO分类设置课程推荐list
	 */
	@Override
	public void prepareRecomdCourses(List<ConstsClassifyVO> classifyVoList) {
		if(CollectionUtils.isNotEmpty(classifyVoList)) {
			for (ConstsClassifyVO  item : classifyVoList) {
				CourseQueryDto courseQueryDto = new CourseQueryDto();
				courseQueryDto.setCount(5); //推荐数量为5门
				courseQueryDto.setDescSortField("weight"); //设置排序字段，根据权重排序
				courseQueryDto.setClassify(item.getCode()); //设置分类条件
				
				List<Course> courseList= this.courseService.queryCourseList(courseQueryDto);
				if(CollectionUtils.isNotEmpty(courseList)) {
					item.setRecomdCourseList(courseList);
				}
			}
		}

	}

}
