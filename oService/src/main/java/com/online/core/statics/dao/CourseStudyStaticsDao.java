package com.online.core.statics.dao;

import java.util.List;

import com.online.core.statics.domain.CourseStudyStaticsDto;

public interface CourseStudyStaticsDao {
	
	/**
	*统计课程学习情况
	*在一定时间范围内该课程的学习人数
	**/
	public List<CourseStudyStaticsDto> queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity);
	
}

