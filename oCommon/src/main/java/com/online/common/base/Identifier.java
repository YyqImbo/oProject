package com.online.common.base;

import java.io.Serializable;

public interface Identifier<KEY extends Serializable> {
	
	public KEY getId();

}
