package com.wisewater.bizConfig.pojo;

import java.io.Serializable;
import java.util.Date;


public class BCustomerUserForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String email;

	private String fromInvitedCode;

	private int isDisabled;

	private int isLogicDel;

	private String loginName;

	private String mobile;

	private String password;
	
	private Date registerDate;

	private String remarks;

	private String toInviteCode;

	private int toInviteCount;

	private String userName;
	
	private Date resetTime;
	
	
	private String authCode;
	
	private String binOpendId;
	
	private String higherCustomerUserId;
	
	private String currentPermissions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFromInvitedCode() {
		return fromInvitedCode;
	}

	public void setFromInvitedCode(String fromInvitedCode) {
		this.fromInvitedCode = fromInvitedCode;
	}

	public int getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(int isDisabled) {
		this.isDisabled = isDisabled;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getToInviteCode() {
		return toInviteCode;
	}

	public void setToInviteCode(String toInviteCode) {
		this.toInviteCode = toInviteCode;
	}

	public int getToInviteCount() {
		return toInviteCount;
	}

	public void setToInviteCount(int toInviteCount) {
		this.toInviteCount = toInviteCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getResetTime() {
		return resetTime;
	}

	public void setResetTime(Date resetTime) {
		this.resetTime = resetTime;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getBinOpendId() {
		return binOpendId;
	}

	public void setBinOpendId(String binOpendId) {
		this.binOpendId = binOpendId;
	}

	public String getHigherCustomerUserId() {
		return higherCustomerUserId;
	}

	public void setHigherCustomerUserId(String higherCustomerUserId) {
		this.higherCustomerUserId = higherCustomerUserId;
	}

	public String getCurrentPermissions() {
		return currentPermissions;
	}

	public void setCurrentPermissions(String currentPermissions) {
		this.currentPermissions = currentPermissions;
	}

}
