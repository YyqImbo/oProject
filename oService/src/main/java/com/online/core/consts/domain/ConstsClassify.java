package com.online.core.consts.domain;

import com.online.common.base.Baseentity;

public class ConstsClassify extends Baseentity {

	private static final long serialVersionUID = -971157257559067890L;

	/**
	*名称
	**/
	private String name;
	
	/**
	*编码
	**/
	private String code;
	
	/**
	*父级别id
	**/
	private String parentCode;
	
	/**
	*排序
	**/
	private Long sort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	
	
}
