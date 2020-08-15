package com.online.core.consts.dao;

import java.util.List;

import com.online.common.page.TailPage;
import com.online.core.consts.domain.ConstsSiteCarousel;

public interface ConstsSiteCarouselDao {

	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public ConstsSiteCarousel getById(Long id);
	
	/**
	 * 获取首页的轮播
	 * @param count 要获取的数量
	 * @return
	 */
	public List<ConstsSiteCarousel> queryCarousel(Integer count);
	
	/**
	 * 获取数据总数
	 * @param carousel
	 * @return
	 */
	public Integer getItemsTotalCount(ConstsSiteCarousel carousel);
	
	/**
	 * 获取一页的数据
	 * @param carousel
	 * @param page
	 * @return
	 */
	public List<ConstsSiteCarousel> queryCarouselPage(ConstsSiteCarousel carousel, TailPage<ConstsSiteCarousel> page);
	
	/**
	 * 创建新记录
	 */
	public void createSelectivity(ConstsSiteCarousel entity);

	/**
	*根据id选择性更新自动
	**/
	public void updateSelectivity(ConstsSiteCarousel entity);

	/**
	*物理删除
	**/
	public void delete(ConstsSiteCarousel entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(ConstsSiteCarousel entity);
}
