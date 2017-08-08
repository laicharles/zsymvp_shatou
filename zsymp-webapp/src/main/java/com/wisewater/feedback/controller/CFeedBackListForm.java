package com.wisewater.feedback.controller;

import com.wisewater.base.BaseForm;
import com.wisewater.system.pojo.SDictionary;

public class CFeedBackListForm extends BaseForm{
	
	private String openId;
	
	private String token;

	private String id;  
	
	private String feedbackType; 
	
	private String isReply;
	
	private String feedbackOption;
	
	private String customerType;
	
	private String createTime;
	
	private String fanUser;
	
	private String fanUserName;
	
	private String fanUserTel;
	
	private String title;
	
	private String replyBy;
	
	private String addr;
	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	private String replyTime;
	

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getReplyBy() {
		return replyBy;
	}

	public void setReplyBy(String replyBy) {
		this.replyBy = replyBy;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFanUserName() {
		return fanUserName;
	}

	public void setFanUserName(String fanUserName) {
		this.fanUserName = fanUserName;
	}

	public String getFanUserTel() {
		return fanUserTel;
	}

	public void setFanUserTel(String fanUserTel) {
		this.fanUserTel = fanUserTel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getIsReply() {
		return isReply;
	}

	public void setIsReply(String isReply) {
		this.isReply = isReply;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFeedbackOption() {
		return feedbackOption;
	}

	public void setFeedbackOption(String feedbackOption) {
		this.feedbackOption = feedbackOption;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getFanUser() {
		return fanUser;
	}

	public void setFanUser(String fanUser) {
		this.fanUser = fanUser;
	}
	
	
}
