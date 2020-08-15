package com.online.core.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.common.page.TailPage;
import com.online.core.user.dao.UserCourseSectionDao;
import com.online.core.user.domain.UserCourseSection;
import com.online.core.user.domain.UserCourseSectionDto;
import com.online.core.user.service.IUserCourseSectionService;

@Service
public class UserCourseSectionServiceImpl implements IUserCourseSectionService {
	
	@Autowired
	private UserCourseSectionDao entityDao;

	@Override
	public UserCourseSection getById(Long id) {
		return entityDao.getById(id);
	}

	@Override
	public List<UserCourseSection> queryAll(UserCourseSection queryEntity) {
		return entityDao.queryAll(queryEntity);
	}

	@Override
	public UserCourseSection queryLatest(UserCourseSection queryEntity) {
		return entityDao.queryLatest(queryEntity);
	}

	@Override
	public TailPage<UserCourseSectionDto> queryUserCourseSectionPage(UserCourseSection queryEntity,
			TailPage<UserCourseSectionDto> page) {
		Integer itemsTotalCount = entityDao.getItemsTotalCount(queryEntity);
		List<UserCourseSectionDto> items = entityDao.queryUserCourseSectionPage(queryEntity, page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	@Override
	public void createSelectivity(UserCourseSection entity) {
		entityDao.createSelectivity(entity);

	}

	@Override
	public void updateSelectivity(UserCourseSection entity) {
		entityDao.updateSelectivity(entity);

	}

	@Override
	public void delete(UserCourseSection entity) {
		entityDao.delete(entity);

	}

	@Override
	public void deleteLogic(UserCourseSection entity) {
		entityDao.deleteLogic(entity);

	}

}
