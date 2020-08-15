package com.online.core.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.common.page.TailPage;
import com.online.core.course.dao.CourseSectionDao;
import com.online.core.course.domain.CourseSection;
import com.online.core.course.service.ICourseSectionService;

@Service
public class CourseSectionServiceImpl implements ICourseSectionService {
	
	@Autowired
	private CourseSectionDao entityDao;

	@Override
	public CourseSection getById(Long id) {
		return entityDao.getById(id);
	}

	@Override
	public List<CourseSection> queryAll(CourseSection queryEntity) {
		return entityDao.queryAll(queryEntity);
	}

	@Override
	public Integer getMaxSort(Long courseId) {
		return entityDao.getMaxSort(courseId);
	}

	@Override
	public TailPage<CourseSection> queryCourseSectionPage(CourseSection queryEntity, TailPage<CourseSection> page) {
		int itemsTotalCount = entityDao.getItemsTotalCount(queryEntity);
		List<CourseSection> items = entityDao.queryCourseSectionPage(queryEntity, page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	@Override
	public void createSelectivity(CourseSection entity) {
		entityDao.createSelectivity(entity);
		
	}

	@Override
	public void createList(List<CourseSection> entityList) {
		entityDao.createList(entityList);
		
	}

	@Override
	public void updateSelectivity(CourseSection entity) {
		entityDao.updateSelectivity(entity);
		
	}

	@Override
	public void delete(CourseSection entity) {
		entityDao.delete(entity);
		
	}

	@Override
	public void deleteLogic(CourseSection entity) {
		entityDao.deleteLogic(entity);
		
	}

	@Override
	public CourseSection getSortSectionMax(CourseSection curCourseSection) {
		return entityDao.getSortSectionMax(curCourseSection);
	}

	@Override
	public CourseSection getSortSectionMin(CourseSection curCourseSection) {
		return entityDao.getSortSectionMin(curCourseSection);
	}

}
