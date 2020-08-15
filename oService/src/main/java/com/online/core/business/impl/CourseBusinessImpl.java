package com.online.core.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.core.business.ICourseBusiness;
import com.online.core.course.domain.CourseEnum;
import com.online.core.course.domain.CourseSection;
import com.online.core.course.domain.CourseSectionVO;
import com.online.core.course.service.ICourseSectionService;

@Service
public class CourseBusinessImpl implements ICourseBusiness {
	
	@Autowired
	private ICourseSectionService courseSectionService;

	/**
	 * 获取课程章节
	 */
	@Override
	public List<CourseSectionVO> queryCourseSection(Long courseId) {
		List<CourseSectionVO> resultList= new ArrayList<CourseSectionVO>();
		CourseSection courseSection =new CourseSection();
		courseSection.setCourseId(courseId);
		courseSection.setOnsale(CourseEnum.ONSALE.value());
		
		Map<Long, CourseSectionVO> tmpMap = new LinkedHashMap<Long, CourseSectionVO>();
		Iterator<CourseSection> iterator= courseSectionService.queryAll(courseSection).iterator();
		while(iterator.hasNext()) {
			CourseSection item=iterator.next();
			if(Long.valueOf(0).equals(item.getParentId())){//是章
				CourseSectionVO vo =new CourseSectionVO();
				BeanUtils.copyProperties(item, vo);
				//将章添加到map中，key为章id，value为章VO对象
				tmpMap.put(vo.getId(), vo);
			}else {
				//将一小节添加到章的list中
				tmpMap.get(item.getParentId()).getSections().add(item);
			}
		}
		for (CourseSectionVO vo : tmpMap.values()) {
			//将map中的章对象添加到list中
			resultList.add(vo);
		}
		return resultList;
	}

}
