package com.wisewater.system.controller;

import com.wisewater.util.tools.JqgridTreeForm;

public class SResourceJqgridForm extends JqgridTreeForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String remarks;

	private String resName;

	private String reqUrl;
	
	private String authUrl;
	
	private int isLogicDel;
	
	private int isShowMenu;
	
	private String picName;

	private String resCode;

	private SDictionaryForm resType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}


	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public int getIsShowMenu() {
		return isShowMenu;
	}

	public void setIsShowMenu(int isShowMenu) {
		this.isShowMenu = isShowMenu;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public SDictionaryForm getResType() {
		return resType;
	}

	public void setResType(SDictionaryForm resType) {
		this.resType = resType;
	}


}