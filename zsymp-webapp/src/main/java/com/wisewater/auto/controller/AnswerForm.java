package com.wisewater.auto.controller;

public class AnswerForm {

	private int answerType;
	
	private String content;
	
	private String title;
	
	private String description;
	
	private String url;
	
	private String picUrl;

	public AnswerForm(){
		this.answerType=0;
		this.content="";
		this.description="";
		this.picUrl="";
		this.title="";
		this.url="";
	}
	
	public int getAnswerType() {
		return answerType;
	}

	public void setAnswerType(int answerType) {
		this.answerType = answerType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
