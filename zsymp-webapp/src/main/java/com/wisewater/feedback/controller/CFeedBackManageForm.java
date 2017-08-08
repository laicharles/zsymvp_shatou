package com.wisewater.feedback.controller;

import java.util.Date;

import com.wisewater.base.BaseForm;
import com.wisewater.fans.pojo.CFanUser;
import com.wisewater.system.pojo.SDictionary;

public class CFeedBackManageForm extends BaseForm{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private SDictionary feedbackType;
	
	private String name;
	
	private String tel;
	
	private String address;
	
	private SDictionary customerType;
	
	private CFanUser fanUser;
	
	private SDictionary feedbackOption;
	
	private String content;
	
	private String img;
	
	private String createTime;
	
	private String deleteTime;
	
	private String openId;
	
	private SDictionary isReply;
	
	private String replyBy;
	
	private String replyContent;
	
	private String replyTime;
	
	private String token;
	
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SDictionary getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(SDictionary feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public SDictionary getCustomerType() {
		return customerType;
	}

	public void setCustomerType(SDictionary customerType) {
		this.customerType = customerType;
	}

	public CFanUser getFanUser() {
		return fanUser;
	}

	public void setFanUser(CFanUser fanUser) {
		this.fanUser = fanUser;
	}

	public SDictionary getFeedbackOption() {
		return feedbackOption;
	}

	public void setFeedbackOption(SDictionary feedbackOption) {
		this.feedbackOption = feedbackOption;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public SDictionary getIsReply() {
		return isReply;
	}

	public void setIsReply(SDictionary isReply) {
		this.isReply = isReply;
	}

	public String getReplyBy() {
		return replyBy;
	}

	public void setReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
}
