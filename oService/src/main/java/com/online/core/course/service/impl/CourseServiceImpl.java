package com.online.core.course.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.common.page.TailPage;
import com.online.common.storage.QiniuStorage;
import com.online.core.course.dao.CourseDao;
import com.online.core.course.domain.Course;
import com.online.core.course.domain.CourseEnum;
import com.online.core.course.domain.CourseQueryDto;
import com.online.core.course.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {
	
	@Autowired
	private CourseDao entityDao;
	
	private void setCoursePicture(Course course) {
		if(null != course && StringUtils.isNotEmpty(course.getPicture())) {
			course.setPicture(QiniuStorage.getUrl(course.getPicture()));
		}
	}

	@Override
	public Course getById(Long id) {
		Course course = entityDao.getById(id);
		setCoursePicture(course);
		return course;
	}

	@Override
	public List<Course> queryCourseList(CourseQueryDto queryEntity) {
		if(null == queryEntity.getOnsale()){//是否上架
			queryEntity.setOnsale(CourseEnum.ONSALE.value());
		}
		List<Course> items=entityDao.queryCourseList(queryEntity);
		//设置图片
		if(CollectionUtils.isNotEmpty(items)){
			for(Course item : items){
				setCoursePicture(item);
			}
		}
		return items;
	}

	@Override
	public TailPage<Course> queryCoursePage(Course queryEntity, TailPage<Course> page) {
		int itemsTotalCount = entityDao.getItemsTotalCount(queryEntity);
		List<Course> items = entityDao.queryCoursePage(queryEntity, page);
		//设置图片
		if(CollectionUtils.isNotEmpty(items)){
			for(Course item : items){
				setCoursePicture(item);
			}
		}
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	@Override
	public void createSelectivity(Course entity) {
		entityDao.createSelectivity(entity);
		
	}

	@Override
	public void updateSelectivity(Course entity) {
		entityDao.updateSelectivity(entity);
		
	}

	@Override
	public void delete(Course entity) {
		entityDao.delete(entity);
		
	}

	@Override
	public void deleteLogic(Course entity) {
		entityDao.deleteLogic(entity);
		
	}

}
