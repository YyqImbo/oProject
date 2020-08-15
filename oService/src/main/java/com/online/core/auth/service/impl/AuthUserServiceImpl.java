package com.online.core.auth.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.common.page.TailPage;
import com.online.common.storage.QiniuStorage;
import com.online.core.auth.dao.AuthUserDao;
import com.online.core.auth.domain.AuthUser;
import com.online.core.auth.service.IAuthUserService;

@Service
public class AuthUserServiceImpl implements IAuthUserService {
	
	@Autowired
	private AuthUserDao entityDao;

	@Override
	public AuthUser getById(Long id) {
		return entityDao.getById(id);
	}

	@Override
	public AuthUser getByUsername(String username) {
		return entityDao.getByUsername(username);
	}

	@Override
	public AuthUser getByUsernameAndPassword(AuthUser authUser) {
		return entityDao.getByUsernameAndPassword(authUser);
	}

	@Override
	public TailPage<AuthUser> queryAuthUserPage(AuthUser authUser, TailPage<AuthUser> page) {
		int itemsTotalCount=entityDao.getItemsTotalCount(authUser);
		page.setItemsTotalCount(itemsTotalCount);
		List<AuthUser> list=entityDao.queryAuthUserPage(authUser, page);
		page.setItems(list);
		return page;
		
	}

	@Override
	public List<AuthUser> queryRecommendAuthUser() {
		List<AuthUser> list=entityDao.queryRecommendAuthUser();
		if(CollectionUtils.isNotEmpty(list)) {
			for (AuthUser authUser : list) {
				//获取key，设置头像
				if(StringUtils.isNotEmpty(authUser.getHeader())) {
					authUser.setHeader(QiniuStorage.getUrl(authUser.getHeader()));
				}
			}
		}
		return list;
	}

	@Override
	public void createSelectivity(AuthUser authUser) {
		entityDao.createSelectivity(authUser);

	}

	@Override
	public void updateSelectivity(AuthUser authUser) {
		entityDao.updateSelectivity(authUser);

	}

	@Override
	public void delete(AuthUser entity) {
		entityDao.delete(entity);

	}

	@Override
	public void deleteLogic(AuthUser entity) {
		entityDao.deleteLogic(entity);

	}

}
