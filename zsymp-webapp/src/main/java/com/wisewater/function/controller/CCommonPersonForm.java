package com.wisewater.function.controller;

import com.wisewater.base.BaseForm;

public class CCommonPersonForm extends BaseForm{

	private static final long serialVersionUID = 1L;
	
	private String id;

	private String openID;
	
	private String token;
	
	private String name;

	private int isLogicDel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}
}
