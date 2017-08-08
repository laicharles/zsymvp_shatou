package com.wisewater.system.controller;

import java.util.List;
import com.wisewater.base.BaseForm;

public class SResourceForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String remarks;

	private String resName;

	private String reqUrl;
	
	private String authUrl;
	
	private int isLogicDel;
	
	private int isShowMenu;
	
	private SResourceForm parentResource;

	private String picName;

	private String resCode;

	private SDictionaryForm resType;

	private List<SResourceForm> subResources;


	//private List<Role> roles;

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

	public SResourceForm getParentResource() {
		return parentResource;
	}

	public void setParentResource(SResourceForm parentResource) {
		this.parentResource = parentResource;
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


	public List<SResourceForm> getSubResources() {
		return subResources;
	}

	public void setSubResources(List<SResourceForm> subResources) {
		this.subResources = subResources;
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