package com.wisewater.system.controller;

import com.wisewater.base.BaseForm;

public class SUserForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String remarks;

	private String department;

	private String email;

	private int isDisabled;

	private String mobile;
	
	private String tel;

	private String password;

	private SDictionaryForm gender;
	
	private String userName;
	
	private String loginName;
	
	private int isLogicDel;

	//private List<RoleForm> roles;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(int isDisabled) {
		this.isDisabled = isDisabled;
	}


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public SDictionaryForm getGender() {
		return gender;
	}

	public void setGender(SDictionaryForm gender) {
		this.gender = gender;
	}

	
	
}
