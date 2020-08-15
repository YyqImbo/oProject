package com.online.core.user.domain;

import com.online.common.base.Baseentity;


public class UserFollows extends Baseentity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5901591164056139595L;

	/**
	*用户id
	**/
	private Long userId;

	/**
	*关注的用户id
	**/
	private Long followId;

	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getFollowId(){
		return followId;
	}
	public void setFollowId(Long followId){
		this.followId = followId;
	}



}

