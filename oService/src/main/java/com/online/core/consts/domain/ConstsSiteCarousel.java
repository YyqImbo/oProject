package com.online.core.consts.domain;

import com.online.common.base.Baseentity;

public class ConstsSiteCarousel extends Baseentity {

	private static final long serialVersionUID = -3378532696463681770L;

	/**
	*名称
	**/
	private String name;
	
	/**
	*图片
	**/
	private String picture;
	
	/**
	*链接
	**/
	private String url;
	
	/**
	*权重
	**/
	private Integer weight;
	
	/**
	 * 是否可用,0:不可用；1：可用
	 */
	private Integer enable;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	
	
	
}
