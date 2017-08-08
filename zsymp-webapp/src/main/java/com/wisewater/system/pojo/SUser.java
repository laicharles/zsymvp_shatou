package com.wisewater.system.pojo;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;


import org.hibernate.annotations.Where;

import com.wisewater.base.BasePojo;

import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="s_user")
public class SUser extends BasePojo {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String remarks;

	private String department;

	private String email;

	@Max(1)
	@Min(0)
	private int isDisabled;

	private String loginName;

	private String mobile;

	private String password;

	private String tel;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gender", referencedColumnName="logicID")
	private SDictionary gender;
	

	private String userName;
	
	@Max(1)
	@Min(0)
	private int isLogicDel;


	//bi-directional many-to-many association to Role
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="s_user_role"
			, joinColumns={
				@JoinColumn(name="userID")
				}
			, inverseJoinColumns={
				@JoinColumn(name="roleID")
				}
			)
	@Where(clause="isLogicDel=0")
	private List<SRole> roles;

	public SUser() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<SRole> getRoles() {
		return this.roles;
	}

	public void setRoles(List<SRole> roles) {
		this.roles = roles;
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

	public SDictionary getGender() {
		return gender;
	}

	public void setGender(SDictionary gender) {
		this.gender = gender;
	}

}