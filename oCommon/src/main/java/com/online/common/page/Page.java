package com.online.common.page;

import java.util.List;

//page接口
public interface Page<E> extends Iterable<E>  {

	/**
	 * 返回第一页的页码
	 * @return
	 */
	public int getFirstPageNum();
	
	/**
	 * 返回最后一页的页码
	 * @return
	 */
	public int getLastPageNum();
	
	/**
	 * 返回当前页码
	 * @return
	 */
	public int getPageNum();
	
	/**
	 * 返回页面大小
	 * @return
	 */
	public int  getPageSize();
	
	/**
	 * 返回页面数据集合
	 * @return
	 */
	public List<E> getItems();
	
	/**
	 * 返回所有数据总数
	 * @return
	 */
	public int getItemsTotalCount();
	
	/**
	 * 返回页面的总数
	 * @return
	 */
	public int getPageTotalCount();
	
	/**
	 * 是否第一页
	 * @return
	 */
	public boolean isFirstPage();
	
	/**
	 * 是否最后一页
	 * @return
	 */
	public boolean isLastPage();
	
	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty();
	
	
}
