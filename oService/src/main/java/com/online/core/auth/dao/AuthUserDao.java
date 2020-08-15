package com.online.core.auth.dao;

import java.util.List;

import com.online.common.page.TailPage;
import com.online.core.auth.domain.AuthUser;

public interface AuthUserDao {

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public AuthUser getById(Long id);
	
	/**
	 * 根据username获取
	 * @param username
	 * @return
	 */
	public AuthUser getByUsername(String username);
	
	/**
	 * 根据username和password获取
	 * @param username
	 * @param password
	 * @return
	 */
	public AuthUser getByUsernameAndPassword(AuthUser authUser);
	
	/**
	 * 获取一页的数据
	 * @param authUser
	 * @param page
	 * @return
	 */
	public List<AuthUser> queryAuthUserPage(AuthUser authUser, TailPage<AuthUser> page);
	
	/**
	 * 获取数据总数
	 * @param authUser
	 * @return
	 */
	public Integer getItemsTotalCount(AuthUser authUser);
	
	/**
	 * 获取推荐的教师数据
	 * @param authUser
	 * @return
	 */
	public List<AuthUser> queryRecommendAuthUser();
	
	/**
	 * 创建新数据
	 * @param authUser
	 */
	public void createSelectivity(AuthUser authUser);
	
	/**
	 * 更新数据
	 * @param authUser
	 */
	public void updateSelectivity(AuthUser authUser);
	
	/**
	*物理删除
	**/
	public void delete(AuthUser entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(AuthUser entity);
	
	
	
	
}
