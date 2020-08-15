package com.online.core.course.service;

import java.util.List;

import com.online.common.page.TailPage;
import com.online.core.course.domain.Course;
import com.online.core.course.domain.CourseQueryDto;

public interface ICourseService {

	/**
	*根据id获取
	**/
	public Course getById(Long id);

	/**  -
	*获取一定数量数据
	**/
	public List<Course> queryCourseList(CourseQueryDto queryEntity);

	/**
	*分页获取
	**/
	public TailPage<Course> queryCoursePage(Course queryEntity ,TailPage<Course> page);

	/**
	*创建
	**/
	public void createSelectivity(Course entity);

	/**
	*根据id 进行可选性更新
	**/
	public void updateSelectivity(Course entity);

	/**
	*物理删除
	**/
	public void delete(Course entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(Course entity);
}
