package com.wisewater.cms.controller;


import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import com.wisewater.base.BaseForm;
import com.wisewater.system.pojo.SDictionary;

public class CCmsForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String createdDateTime;

	private int isLogicDel;

	private String pageContent;

	private String pageUrl;

	private String remarks;

	private String title;

	private String token;
	
	private String author;
	
	private String origUrl;
	
	private SDictionary cmsType;
	
	private String image;
	
	private MultipartFile picNameFile;
	
	private String picName;
	
	private String sortNumber;
	
	private String submitOpenId;
	
	private String auditorOpenId;
	
	private SDictionary auditorStatus;
	
	private String auditorLevel;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOrigUrl() {
		return origUrl;
	}

	public void setOrigUrl(String origUrl) {
		this.origUrl = origUrl;
	}

	public SDictionary getCmsType() {
		return cmsType;
	}

	public void setCmsType(SDictionary cmsType) {
		this.cmsType = cmsType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public MultipartFile getPicNameFile() {
		return picNameFile;
	}

	public void setPicNameFile(MultipartFile picNameFile) {
		this.picNameFile = picNameFile;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(String sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getSubmitOpenId() {
		return submitOpenId;
	}

	public void setSubmitOpenId(String submitOpenId) {
		this.submitOpenId = submitOpenId;
	}

	public String getAuditorOpenId() {
		return auditorOpenId;
	}

	public void setAuditorOpenId(String auditorOpenId) {
		this.auditorOpenId = auditorOpenId;
	}

	public SDictionary getAuditorStatus() {
		return auditorStatus;
	}

	public void setAuditorStatus(SDictionary auditorStatus) {
		this.auditorStatus = auditorStatus;
	}

	public String getAuditorLevel() {
		return auditorLevel;
	}

	public void setAuditorLevel(String auditorLevel) {
		this.auditorLevel = auditorLevel;
	}

}