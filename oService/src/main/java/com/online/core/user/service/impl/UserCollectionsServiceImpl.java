package com.online.core.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.common.page.TailPage;
import com.online.core.user.dao.UserCollectionsDao;
import com.online.core.user.domain.UserCollections;
import com.online.core.user.service.IUserCollectionsService;

@Service
public class UserCollectionsServiceImpl implements IUserCollectionsService {
	
	@Autowired
	private UserCollectionsDao entityDao;

	@Override
	public UserCollections getById(Long id) {
		return entityDao.getById(id);
	}

	@Override
	public List<UserCollections> queryAll(UserCollections queryEntity) {
		return entityDao.queryAll(queryEntity);
	}

	@Override
	public TailPage<UserCollections> queryUserCollectionsPage(UserCollections queryEntity,
			TailPage<UserCollections> page) {
		Integer itemsTotalCount = entityDao.getItemsTotalCount(queryEntity);
		List<UserCollections> items = entityDao.queryUserCollectionsPage(queryEntity, page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	@Override
	public void createSelectivity(UserCollections entity) {
		entityDao.createSelectivity(entity);

	}

	@Override
	public void updateSelectivity(UserCollections entity) {
		entityDao.updateSelectivity(entity);

	}

	@Override
	public void delete(UserCollections entity) {
		entityDao.delete(entity);

	}

	@Override
	public void deleteLogic(UserCollections entity) {
		entityDao.deleteLogic(entity);

	}

}
