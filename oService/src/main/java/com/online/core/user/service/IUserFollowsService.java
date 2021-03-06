package com.online.core.user.service;

import java.util.List;

import com.online.common.page.TailPage;
import com.online.core.user.domain.UserFollowStudyRecord;
import com.online.core.user.domain.UserFollows;


public interface IUserFollowsService {

	/**
	*根据id获取
	**/
	public UserFollows getById(Long id);

	/**
	*获取所有
	**/
	public List<UserFollows> queryAll(UserFollows queryEntity);

	/**
	*分页获取
	**/
	public TailPage<UserFollows> queryUserFollowsPage(UserFollows queryEntity ,TailPage<UserFollows> page);

	/**
	*分页获取
	**/
	public TailPage<UserFollowStudyRecord> queryUserFollowStudyRecordPage(UserFollowStudyRecord queryEntity ,TailPage<UserFollowStudyRecord> page);
	
	/**
	*创建
	**/
	public void createSelectivity(UserFollows entity);

	/**
	*根据id 进行可选性更新
	**/
	public void updateSelectivity(UserFollows entity);

	/**
	*物理删除
	**/
	public void delete(UserFollows entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(UserFollows entity);



}

