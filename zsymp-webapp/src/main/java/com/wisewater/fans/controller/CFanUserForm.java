package com.wisewater.fans.controller;

import com.wisewater.base.BaseForm;
//绑定用户的信息
public class CFanUserForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String bindDateTime;

	private String contactAddr;

	private int isLogicDel;

	private String mobile;

	private CFanForm fan;

	private String remarks;

	private String token;

	private String userAccount;

	private String userName;
	//状态值 repeat over success fail notcity
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBindDateTime() {
		return bindDateTime;
	}

	public void setBindDateTime(String bindDateTime) {
		this.bindDateTime = bindDateTime;
	}

	public String getContactAddr() {
		return contactAddr;
	}

	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public CFanForm getFan() {
		return fan;
	}

	public void setFan(CFanForm fan) {
		this.fan = fan;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}