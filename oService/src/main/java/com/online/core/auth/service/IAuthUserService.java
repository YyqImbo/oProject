package com.online.core.auth.service;

import java.util.List;

import com.online.common.page.TailPage;
import com.online.core.auth.domain.AuthUser;

public interface IAuthUserService {

	/**
	 * 根据id获取
	 * @return
	 */
	public AuthUser getById(Long id);
	
	/**
	 * 根据username获取
	 * @param authUser
	 * @return
	 */
	public AuthUser getByUsername(String username);
	
	/**
	 * 根据username和password获取
	 * @param authUser
	 * @return
	 */
	public AuthUser getByUsernameAndPassword(AuthUser authUser);
	
	/**
	 * 返回包含数据的一页
	 * @param authUser
	 * @param page
	 * @return
	 */
	public TailPage<AuthUser> queryAuthUserPage(AuthUser authUser, TailPage<AuthUser> page);
	
	/**
	 * 推荐教师
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
