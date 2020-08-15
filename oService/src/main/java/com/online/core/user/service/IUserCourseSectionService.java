package com.online.core.user.service;

import java.util.List;

import com.online.common.page.TailPage;
import com.online.core.user.domain.UserCourseSection;
import com.online.core.user.domain.UserCourseSectionDto;


public interface IUserCourseSectionService {

	/**
	*根据id获取
	**/
	public UserCourseSection getById(Long id);

	/**
	*获取所有
	**/
	public List<UserCourseSection> queryAll(UserCourseSection queryEntity);

	/**
	 * 获取最新的
	 */
	public UserCourseSection queryLatest(UserCourseSection queryEntity);
	
	/**
	*分页获取
	**/
	public TailPage<UserCourseSectionDto> queryUserCourseSectionPage(UserCourseSection queryEntity , TailPage<UserCourseSectionDto> page);

	/**
	*创建
	**/
	public void createSelectivity(UserCourseSection entity);


	/**
	*根据id 进行可选性更新
	**/
	public void updateSelectivity(UserCourseSection entity);

	/**
	*物理删除
	**/
	public void delete(UserCourseSection entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(UserCourseSection entity);



}

