package com.online.core.consts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.common.page.TailPage;
import com.online.core.consts.dao.ConstsClassifyDao;
import com.online.core.consts.domain.ConstsClassify;
import com.online.core.consts.service.IConstsClassifyService;


@Service
public class ConstsClassifyServiceImpl implements IConstsClassifyService {
	
	@Autowired
	private ConstsClassifyDao entityDao;

	@Override
	public ConstsClassify getById(Long id) {
		return entityDao.getById(id);
	}

	@Override
	public List<ConstsClassify> queryAll() {
		return entityDao.queryAll();
	}

	@Override
	public ConstsClassify getByCode(String code) {
		if(code == null) {
			return null;
		}else {
			return entityDao.getByCode(code);
		}
	}

	@Override
	public List<ConstsClassify> queryByCondition(ConstsClassify queryEntity) {
		return entityDao.queryByCondition(queryEntity);
	}

	@Override
	public TailPage<ConstsClassify> queryConstsClassifyPage(ConstsClassify queryEntity, TailPage<ConstsClassify> page) {
		Integer itemsTotalCount = entityDao.getItemsTotalCount(queryEntity);
		List<ConstsClassify> items = entityDao.queryConstsClassifyPage(queryEntity, page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	@Override
	public void createSelectivity(ConstsClassify entity) {
		entityDao.createSelectivity(entity);
		
	}

	@Override
	public void updateSelectivity(ConstsClassify entity) {
		entityDao.updateSelectivity(entity);
		
	}

	@Override
	public void delete(ConstsClassify entity) {
		entityDao.delete(entity);
		
	}

	@Override
	public void deleteLogic(ConstsClassify entity) {
		entityDao.deleteLogic(entity);
		
	}

	
	
}
