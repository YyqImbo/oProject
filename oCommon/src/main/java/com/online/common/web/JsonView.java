package com.online.common.web;

import net.sf.json.JSONObject;

public class JsonView {

	private Integer errorCode =0;//错误代码 0：成功
	private String message; //消息
	private Object data; //数据
	
	
	
	public JsonView(Integer errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public JsonView(Integer errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public JsonView(Integer errorCode, String message, Object data) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.data = data;
	}

	public JsonView() {}

	public static String render(Integer errorcode, String message, Object data) {
		JsonView tmp=new JsonView(errorcode,message, data);
		return JSONObject.fromObject(tmp).toString();
	}
	
	public static String  render(Integer errorcode,String message) {
		JsonView tmp=new JsonView(errorcode,message);
		return JSONObject.fromObject(tmp).toString();
	}
	
	public static String  render(Integer errorcode) {
		JsonView tmp=new JsonView(errorcode,"");
		return JSONObject.fromObject(tmp).toString();
	}
	
	public static String render(Object data) {
		JsonView tmp= new JsonView(0,"success",data);
		return JSONObject.fromObject(tmp).toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public String toString(){
		return JSONObject.fromObject(this).toString();
	}
	
}
