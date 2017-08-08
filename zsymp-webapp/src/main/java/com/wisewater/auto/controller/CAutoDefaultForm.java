package com.wisewater.auto.controller;



import org.springframework.web.multipart.MultipartFile;

import com.wisewater.base.BaseForm;
import com.wisewater.system.pojo.SDictionary;

public class CAutoDefaultForm extends BaseForm {

	private static final long serialVersionUID = 1L;
	private String id;

	private String token;
	
	private String autoReply;
	
	private SDictionary sdictionary;
	
	private String textContent;
	
	private String title;

	private String description;

	private String picName;
	
	private MultipartFile picNameFile;
	
	private String pageUrl;

	private String pageContent;
	
	private String author;
	
	private String origUrl;

	private int isLogicDel;

	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAutoReply() {
		return autoReply;
	}

	public void setAutoReply(String autoReply) {
		this.autoReply = autoReply;
	}

	public SDictionary getSdictionary() {
		return sdictionary;
	}

	public void setSdictionary(SDictionary sdictionary) {
		this.sdictionary = sdictionary;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public MultipartFile getPicNameFile() {
		return picNameFile;
	}

	public void setPicNameFile(MultipartFile picNameFile) {
		this.picNameFile = picNameFile;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
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

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}