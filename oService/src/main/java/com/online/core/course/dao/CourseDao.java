package com.online.core.course.dao;

import java.util.List;

import com.online.common.page.TailPage;
import com.online.core.course.domain.Course;
import com.online.core.course.domain.CourseQueryDto;

public interface CourseDao {

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public Course getById(Long id);
	
	/**
	*根据条件获取一定数量数据
	**/
	public List<Course> queryCourseList(CourseQueryDto queryEntity);

	/**
	*获取总数量
	**/
	public Integer getItemsTotalCount(Course queryEntity);

	/**
	*分页获取
	**/
	public List<Course> queryCoursePage(Course queryEntity , TailPage<Course> page);

	/**
	*创建新记录
	**/
	public void createSelectivity(Course entity);


	/**
	*根据id选择性更新自动
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
