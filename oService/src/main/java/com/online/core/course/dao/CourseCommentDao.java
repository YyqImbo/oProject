package com.online.core.course.dao;

import java.util.List;

import com.online.common.page.TailPage;
import com.online.core.course.domain.CourseComment;

public interface CourseCommentDao {

	
	/**
	*根据id获取
	**/
	public CourseComment getById(Long id);

	/**
	*获取所有
	**/
	public List<CourseComment> queryAll(CourseComment queryEntity);

	/**
	*获取总数量
	**/
	public Integer getItemsTotalCount(CourseComment queryEntity);

	/**
	*分页获取课程学习页面的所有评论和问答
	**/
	public List<CourseComment> queryCourseCommentPage(CourseComment queryEntity , TailPage<CourseComment> page);
	
	
	/**
	*获取总数量
	**/
	public Integer getMyQAItemsTotalCount(CourseComment queryEntity);

	/**
	*分页获取我的课程的评论和问答
	**/
	public List<CourseComment> queryMyQAItemsPage(CourseComment queryEntity , TailPage<CourseComment> page);
	
	/**
	 * 创建新记录
	 */
	public void createSelectivity(CourseComment entity);

	/**
	*根据id选择性更新自动
	**/
	public void updateSelectivity(CourseComment entity);

	/**
	*物理删除
	**/
	public void delete(CourseComment entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(CourseComment entity);
}
