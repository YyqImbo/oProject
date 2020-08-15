package com.online.core.user.dao;

import java.util.List;

import com.online.common.page.TailPage;
import com.online.core.user.domain.UserCollections;


public interface UserCollectionsDao {

	/**
	*根据id获取
	**/
	public UserCollections getById(Long id);

	/**
	*获取所有
	**/
	public List<UserCollections> queryAll(UserCollections queryEntity);

	/**
	*获取总数量
	**/
	public Integer getItemsTotalCount(UserCollections queryEntity);

	/**
	*分页获取
	**/
	public List<UserCollections> queryUserCollectionsPage(UserCollections queryEntity , TailPage<UserCollections> page);

	
	/**
	*创建新记录
	**/
	public void createSelectivity(UserCollections entity);
	

	/**
	*根据id选择性更新自动
	**/
	public void updateSelectivity(UserCollections entity);

	/**
	*物理删除
	**/
	public void delete(UserCollections entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(UserCollections entity);



}

