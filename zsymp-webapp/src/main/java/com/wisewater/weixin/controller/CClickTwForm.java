package com.wisewater.weixin.controller;

import org.springframework.web.multipart.MultipartFile;

import com.wisewater.base.BaseForm;
import com.wisewater.system.pojo.SDictionary;

public class CClickTwForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String description;

	private int isLogicDel;

	private String picName;

	private String remarks;

	private String title;

	private String token;
	
	private SDictionary twType;
	
	private MultipartFile imgFile;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public SDictionary getTwType() {
		return twType;
	}

	public void setTwType(SDictionary twType) {
		this.twType = twType;
	}

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
}