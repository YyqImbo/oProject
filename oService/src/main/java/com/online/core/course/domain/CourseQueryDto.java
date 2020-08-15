package com.online.core.course.domain;

import org.apache.commons.lang.StringUtils;

import com.online.common.util.BeanUtil;

public class CourseQueryDto extends Course {

	private static final long serialVersionUID = -1023823137516009474L;

	//排序字段
	private String sortField;
	
	//排序方向,升序或降序
	private String sortDirection = "DESC";
	
	//query的数量
	private Integer count;
	
	//limit开始
	private Integer start=0;
	
	//limit结束
	private Integer end;

	public String getSortField() {
		return sortField;
	}

	/**
	 * 按照sortField升序排列
	 * @param sortField
	 */
	public void setAscSortField(String sortField) {
		if(StringUtils.isNotEmpty(sortField)) {
			this.sortField=BeanUtil.fieldToColumn(sortField);
			this.sortDirection="ASC";
		}
	}
	
	/**
	 * 按照sortField降序排列
	 * @param sortField
	 */
	public void setDescSortField(String sortField) {
		if(StringUtils.isNotEmpty(sortField)) {
			this.sortField=BeanUtil.fieldToColumn(sortField);
			this.sortDirection="DESC";
		}
	}
	
	public String getSortDirection() {
		return sortDirection;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		if(this.count != null) {
			if(this.start != null) {
				this.end=this.start+this.count;
				return end;
			}
		}
		return end;
	}

	
	
	
}
