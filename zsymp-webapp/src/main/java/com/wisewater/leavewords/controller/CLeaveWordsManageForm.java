package com.wisewater.leavewords.controller;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wisewater.base.BaseForm;
import com.wisewater.fans.pojo.CFan;
import com.wisewater.system.pojo.SDictionary;

public class CLeaveWordsManageForm extends BaseForm{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	@JsonIgnore
	private SDictionary leavewords;
	
	private String name;
	
	private String nickName;
	
	private String cityName;
	
	private String tel;
	
	private String address;
	
	@JsonIgnore
	private CFan fan;
	
	private String content;
	
	private String img;
	
	private Date createTime;
	
	private Date deleteTime;
	
	private String openId;
	
	@JsonIgnore
	private SDictionary isReply;
	
	private String replyBy;
	
	private String replyContent;
	
	private Date replyTime;
	
	private String token;

	
	@JsonIgnore
	public CFan getFan() {
		return fan;
	}
	
	public void setFan(CFan fan) {
		this.fan = fan;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonIgnore
	public SDictionary getLeavewords() {
		return leavewords;
	}
	
	public void setLeavewords(SDictionary leavewords) {
		this.leavewords = leavewords;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
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

	@JsonIgnore
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

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
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
