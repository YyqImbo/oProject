package com.online.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.support.StaticApplicationContext;

//shiro的加密封装
public class MD5Util {

	//加密算法类型
	private static final String ALGORITH_NAME="MD5";
	
	//加密次数
	private static final int HASH_ITERATIONS =1;
	
	public static String MD5hashed(String password) {
		SimpleHash simpleHash=new SimpleHash(ALGORITH_NAME, password,null,HASH_ITERATIONS);
		return simpleHash.toString().toUpperCase();
	}
	
	public static void main(String []args) {
		String u="111111";
		System.out.println(MD5hashed(u));
	}
	
}
