package com.wisewater.cusConfig.controller;

import com.wisewater.base.BaseForm;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.controller.BCustomerForm;

public class CMpForm extends BaseForm {
	private static final long serialVersionUID = 1L;

	private String id;

	private String appID;

	private String appSecret;

	private String email;

	private int isLogicDel;

	private String name;

	private String origID;

	private String remarks;

	private BAccessTokenForm accessToken;

	private String wcAccount;

	private BCustomerForm BCusotmer;

	private boolean tempInitFlag;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppID() {
		return this.appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppSecret() {
		return this.appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigID() {
		return this.origID;
	}

	public void setOrigID(String origID) {
		this.origID = origID;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BAccessTokenForm getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(BAccessTokenForm accessToken) {
		this.accessToken = accessToken;
	}

	public String getWcAccount() {
		return this.wcAccount;
	}

	public void setWcAccount(String wcAccount) {
		this.wcAccount = wcAccount;
	}

	public BCustomerForm getBCusotmer() {
		return BCusotmer;
	}

	public void setBCusotmer(BCustomerForm bCusotmer) {
		BCusotmer = bCusotmer;
	}

	public boolean isTempInitFlag() {
		return tempInitFlag;
	}

	public void setTempInitFlag(boolean tempInitFlag) {
		this.tempInitFlag = tempInitFlag;
	}

}