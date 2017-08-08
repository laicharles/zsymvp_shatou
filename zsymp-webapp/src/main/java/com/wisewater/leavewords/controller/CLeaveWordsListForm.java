package com.wisewater.leavewords.controller;

import java.util.Date;

import com.wisewater.base.BaseForm;
import com.wisewater.system.pojo.SDictionary;

public class CLeaveWordsListForm extends BaseForm{
	
	private static final long serialVersionUID = 1L;

	private String id;  
	
	private String leavewords; 
	
	private String isReply;
	
	private String createTime;
	
	private String name;
	
	private String nickName;
	
	private String cityName;
	
	private String openId;
	
	private String token;
	

	private String tel;
	
	private String address;
	
	private String replyTime;
	
	private String deleteTime;
	
	private String replyBy;
	
	public String getReplyBy() {
		return replyBy;
	}

	public void setReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLeavewords() {
		return leavewords;
	}

	public void setLeavewords(String leavewords) {
		this.leavewords = leavewords;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

