package com.online.core.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.common.page.TailPage;
import com.online.core.user.dao.UserFollowsDao;
import com.online.core.user.domain.UserFollowStudyRecord;
import com.online.core.user.domain.UserFollows;
import com.online.core.user.service.IUserFollowsService;


@Service
public class UserFollowServiceImpl implements IUserFollowsService {
	
	@Autowired
	private UserFollowsDao entityDao;

	@Override
	public UserFollows getById(Long id) {
		return entityDao.getById(id);
	}

	@Override
	public List<UserFollows> queryAll(UserFollows queryEntity) {
		return entityDao.queryAll(queryEntity);
	}

	@Override
	public TailPage<UserFollows> queryUserFollowsPage(UserFollows queryEntity, TailPage<UserFollows> page) {
		Integer itemsTotalCount = entityDao.getItemsTotalCount(queryEntity);
		List<UserFollows> items = entityDao.queryUserFollowsPage(queryEntity, page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	@Override
	public TailPage<UserFollowStudyRecord> queryUserFollowStudyRecordPage(UserFollowStudyRecord queryEntity,
			TailPage<UserFollowStudyRecord> page) {
		Integer itemsTotalCount = entityDao.getFollowStudyRecordCount(queryEntity);
		List<UserFollowStudyRecord> items = entityDao.queryFollowStudyRecord(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	@Override
	public void createSelectivity(UserFollows entity) {
		entityDao.createSelectivity(entity);

	}

	@Override
	public void updateSelectivity(UserFollows entity) {
		entityDao.updateSelectivity(entity);

	}

	@Override
	public void delete(UserFollows entity) {
		entityDao.delete(entity);

	}

	@Override
	public void deleteLogic(UserFollows entity) {
		entityDao.deleteLogic(entity);

	}

}
