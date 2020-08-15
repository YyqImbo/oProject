package com.online.core.consts.dao;

import java.util.List;

import com.online.common.page.TailPage;
import com.online.core.consts.domain.ConstsClassify;

public interface ConstsClassifyDao {

	
	/**
	*根据id获取
	**/
	public ConstsClassify getById(Long id);
	
	/**
	 * 根据code获取
	 */
	public ConstsClassify getByCode(String code);

	/**
	*获取所有
	**/
	public List<ConstsClassify> queryAll();
	
	/**
	 * 根据条件动态获取
	 * @param queryEntity
	 * @return
	 */
	public List<ConstsClassify> queryByCondition(ConstsClassify queryEntity);

	/**
	*获取总数量
	**/
	public Integer getItemsTotalCount(ConstsClassify queryEntity);

	/**
	*分页获取
	**/
	public List<ConstsClassify> queryConstsClassifyPage(ConstsClassify queryEntity , TailPage<ConstsClassify> page);

	
	/**
	 * 创建新记录
	 */
	public void createSelectivity(ConstsClassify entity);


	/**
	*根据id选择性更新自动
	**/
	public void updateSelectivity(ConstsClassify entity);

	/**
	*物理删除
	**/
	public void delete(ConstsClassify entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(ConstsClassify entity);

}
