package com.online.core.consts.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.common.page.TailPage;
import com.online.common.storage.QiniuStorage;
import com.online.core.consts.dao.ConstsSiteCarouselDao;
import com.online.core.consts.domain.ConstsSiteCarousel;
import com.online.core.consts.service.IConstsSiteCarouselService;


@Service
public class ConstsSiteCarouselServiceImpl implements IConstsSiteCarouselService {
	
	@Autowired
	private ConstsSiteCarouselDao consts;

	@Override
	public ConstsSiteCarousel getById(Long id) {
		ConstsSiteCarousel constsSiteCarousel=consts.getById(id);
		if(constsSiteCarousel.getPicture() != null) {
			constsSiteCarousel.setPicture(QiniuStorage.getUrl(constsSiteCarousel.getPicture()));
		}
		return constsSiteCarousel;
	}

	@Override
	public List<ConstsSiteCarousel> queryCarousel(Integer count) {
		List<ConstsSiteCarousel> list=consts.queryCarousel(count);
		if(CollectionUtils.isNotEmpty(list)) {
			//处理图片
			for (ConstsSiteCarousel constsSiteCarousel : list) {
				if(constsSiteCarousel.getPicture() != null) {
					constsSiteCarousel.setPicture(QiniuStorage.getUrl(constsSiteCarousel.getPicture()));
				}
			}
		}
		return list;
	}

	@Override
	public TailPage<ConstsSiteCarousel> queryCarouselPage(ConstsSiteCarousel carousel,
			TailPage<ConstsSiteCarousel> page) {
		int itemsTotalCount=consts.getItemsTotalCount(carousel);
		List<ConstsSiteCarousel> list= consts.queryCarouselPage(carousel, page);
		if(CollectionUtils.isNotEmpty(list)) {
			//处理图片
			for (ConstsSiteCarousel constsSiteCarousel : list) {
				if(constsSiteCarousel.getPicture() != null) {
					constsSiteCarousel.setPicture(QiniuStorage.getUrl(constsSiteCarousel.getPicture()));
				}
			}
		}
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(list);
		return page;
	}

	@Override
	public void createSelectivity(ConstsSiteCarousel entity) {
		consts.createSelectivity(entity);
		
	}

	@Override
	public void updateSelectivity(ConstsSiteCarousel entity) {
		consts.updateSelectivity(entity);
		
	}

	@Override
	public void delete(ConstsSiteCarousel entity) {
		consts.delete(entity);
		
	}

	@Override
	public void deleteLogic(ConstsSiteCarousel entity) {
		consts.deleteLogic(entity);
		
	}

}
