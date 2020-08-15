package com.online.core.statics.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.core.statics.dao.CourseStudyStaticsDao;
import com.online.core.statics.domain.CourseStudyStaticsDto;
import com.online.core.statics.domain.StaticsVO;
import com.online.core.statics.service.IStaticsService;

@Service
public class StaticsServiceImpl implements IStaticsService {
	
	@Autowired
	private CourseStudyStaticsDao entityDao;

	@Override
	public StaticsVO queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity) {
		List<CourseStudyStaticsDto> list =entityDao.queryCourseStudyStatistics(queryEntity);
		
		StaticsVO staticsVO =new StaticsVO();
		List<Integer> data =new ArrayList<Integer>();//存放学习人数
		List<String> categories = new ArrayList<String>(); //存放日期
		
		if(CollectionUtils.isNotEmpty(list)) {
			for (CourseStudyStaticsDto item : list) {
				//将学习人数和日期分别存入到VO的两个list中
				categories.add(item.getDateStr());
				data.add(item.getTotalCount());
			}
			staticsVO.setCategories(categories);
			staticsVO.setData(data);
		}
		
		return staticsVO;
		
	}

}
