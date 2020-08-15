package com.online.core.course.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.common.page.TailPage;
import com.online.common.storage.QiniuStorage;
import com.online.core.course.dao.CourseCommentDao;
import com.online.core.course.domain.CourseComment;
import com.online.core.course.service.ICourseCommentService;


@Service
public class CourseCommentServiceImpl implements ICourseCommentService {
	
	@Autowired
	private CourseCommentDao entityDao;

	@Override
	public CourseComment getById(Long id) {
		CourseComment comment=entityDao.getById(id);
		//设置user头像图片
		if(comment.getHeader() != null) {
			comment.setHeader(QiniuStorage.getUrl(comment.getHeader()));
		}
		return comment;
	}

	@Override
	public List<CourseComment> queryAll(CourseComment queryEntity) {
		List<CourseComment> list=entityDao.queryAll(queryEntity);
		//设置user头像图片
		if(CollectionUtils.isNotEmpty(list)) {
			for (CourseComment item : list) {
				if(item.getHeader() != null) {
					item.setHeader(QiniuStorage.getUrl(item.getHeader()));
				}
			}
		}
		return list;
	}

	@Override
	public TailPage<CourseComment> queryCourseCommentPage(CourseComment queryEntity, TailPage<CourseComment> page) {
		int itemsTotalCount = entityDao.getItemsTotalCount(queryEntity);
		List<CourseComment> list = entityDao.queryCourseCommentPage(queryEntity, page);
		//设置user头像图片
		if(CollectionUtils.isNotEmpty(list)) {
			for (CourseComment item : list) {
				if(item.getHeader() != null) {
					item.setHeader(QiniuStorage.getUrl(item.getHeader()));
				}
			}
		}
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(list);
		return page;
	}

	@Override
	public TailPage<CourseComment> queryMyQAItemsPage(CourseComment queryEntity, TailPage<CourseComment> page) {
		int itemsTotalCount = entityDao.getMyQAItemsTotalCount(queryEntity);
		List<CourseComment> list = entityDao.queryMyQAItemsPage(queryEntity, page);
		//设置user头像图片
		if(CollectionUtils.isNotEmpty(list)) {
			for (CourseComment item : list) {
				if(item.getHeader() != null) {
					item.setHeader(QiniuStorage.getUrl(item.getHeader()));
				}
			}
		}
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(list);
		return page;
	}

	@Override
	public void createSelectivity(CourseComment entity) {
		entityDao.createSelectivity(entity);
		
	}

	@Override
	public void updateSelectivity(CourseComment entity) {
		entityDao.updateSelectivity(entity);
		
	}

	@Override
	public void delete(CourseComment entity) {
		entityDao.delete(entity);
		
	}

	@Override
	public void deleteLogic(CourseComment entity) {
		entityDao.deleteLogic(entity);
		
	}

}
