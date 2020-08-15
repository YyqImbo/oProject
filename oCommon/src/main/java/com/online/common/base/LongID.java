package com.online.common.base;

import java.io.Serializable;

public class LongID implements Identifier<Long>,Serializable {
	
	private static final long serialVersionUID = 6157257193603194900L;
	private Long id;
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
	
		return id;
	}

	
}
