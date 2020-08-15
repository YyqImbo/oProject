package com.online.common.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.online.common.util.BeanUtil;

public abstract class AbstractPage<E> implements Page<E> {

	public static final int DEFAULT_FIRST_PAGE_NUM=1;
	public static final int DEFAULT_PAGE_SIZE=10;
	
	protected int pageSize=DEFAULT_PAGE_SIZE;
	protected int pageNum=DEFAULT_FIRST_PAGE_NUM;
	
	protected List<E> items;
	protected boolean isFirstPage;//是否第一页
	protected boolean isLastPage;//是否最后一页
	protected int itemsTotalCount;//数据总数
	protected int pageTotalCount;//总页数
	protected int startIndex;//每页第一条数据在表中的位置，从0开始
	
	private String sortField="update_time";//排序字段
	private String sortDirection="DESC";//升序或者降序
	
	@Override
	public int getFirstPageNum() {
		return DEFAULT_FIRST_PAGE_NUM;
	}
	
	@Override
	public List<E> getItems() {
		return items;
	}
	
	@Override
	public int getItemsTotalCount() {
		return itemsTotalCount;
	}
	
	@Override
	public int getLastPageNum() {
		return pageTotalCount;
	}
	
	@Override
	public int getPageNum() {
		return pageNum;
	}
	
	@Override
	public int getPageSize() {
		return pageSize;
	}
	
	@Override
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	
	@Override
	public Iterator<E> iterator() {
		return this.items.iterator();
	}
	
	@Override
	public boolean isFirstPage() {
		isFirstPage=(getPageNum()<=getFirstPageNum());
		return isFirstPage;
	}
	
	@Override
	public boolean isLastPage() {
		isLastPage=(getPageNum()==getPageTotalCount());
		return isLastPage;
	}
	
	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNum(int pageNum) {
		if(pageNum<DEFAULT_FIRST_PAGE_NUM)
			pageNum=DEFAULT_FIRST_PAGE_NUM;
		this.pageNum=pageNum;
	}

	public void setItems(Collection<E> items) {
		if(items==null)
			items=Collections.emptyList();
		this.items=new ArrayList<E>(items);
	    this.isLastPage = this.pageNum == this.pageTotalCount;
	    this.isFirstPage = this.pageNum == DEFAULT_FIRST_PAGE_NUM;
	}

	public void setItemsTotalCount(int itemsTotalCount) {
		this.itemsTotalCount=itemsTotalCount;
		if(itemsTotalCount%this.pageSize==0) {
			this.pageTotalCount=itemsTotalCount/this.pageSize;
		}else {
			this.pageTotalCount=itemsTotalCount/this.pageSize+1;
		}
		if(this.pageNum > this.pageTotalCount){
			this.pageNum = DEFAULT_FIRST_PAGE_NUM;
		}
		if(this.itemsTotalCount <= this.pageSize){
			this.isFirstPage = true;
			this.isLastPage = true;
		}
	}
	
	public int getStartIndex() {
		this.startIndex=(this.pageNum-1)*pageSize;
		if(this.startIndex <= 0){
			this.startIndex = 0;
		}
		return startIndex;
	}
	
	/**
	 * 按照sortField升序
	 * @param sortField：指java bean中的属性
	 */
	public void ascSortField(String sortField) {
		if(StringUtils.isNotEmpty(sortField)){
			this.sortField = BeanUtil.fieldToColumn(sortField);
			this.sortDirection = " ASC ";
		}
	}
	
	/**
	 * 按照sortField降序
	 * @param sortField ：指java bean中的属性
	 */
	public void descSortField(String sortField) {
		if(StringUtils.isNotEmpty(sortField)){
			this.sortField = BeanUtil.fieldToColumn(sortField);
			this.sortDirection = " DESC ";
		}
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	
}
