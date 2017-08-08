package com.wisewater.bizConfig.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.wisewater.system.pojo.SRole;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the b_cusotmer_user database table.
 * 
 */
@Entity
@Table(name="b_customer_user")
@NamedQuery(name="BCustomerUser.findAll", query="SELECT b FROM BCustomerUser b")
public class BCustomerUser extends com.wisewater.base.BasePojo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String email;

	private String fromInvitedCode;

	private int isDisabled;

	private int isLogicDel;

	private String loginName;

	private String mobile;

	private String password;

	@Temporal(TemporalType.DATE)
	private Date registerDate;

	private String remarks;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="roleID")
	private SRole role;

	private String toInviteCode;

	private int toInviteCount;

	private String userName;
	
	private Date resetTime;

	//bi-directional many-to-one association to BCusotmer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customerID")
	private BCustomer BCusotmer;
	
	
	private String authCode;
	
	private String binOpendId;
	
	private String higherCustomerUserId;
	
	private String currentPermissions;
	
	public BCustomerUser() {
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

	public String getFromInvitedCode() {
		return this.fromInvitedCode;
	}

	public void setFromInvitedCode(String fromInvitedCode) {
		this.fromInvitedCode = fromInvitedCode;
	}

	public int getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(int isDisabled) {
		this.isDisabled = isDisabled;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getToInviteCode() {
		return this.toInviteCode;
	}

	public void setToInviteCode(String toInviteCode) {
		this.toInviteCode = toInviteCode;
	}

	public int getToInviteCount() {
		return this.toInviteCount;
	}

	public void setToInviteCount(int toInviteCount) {
		this.toInviteCount = toInviteCount;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BCustomer getBCusotmer() {
		return this.BCusotmer;
	}

	public void setBCusotmer(BCustomer BCusotmer) {
		this.BCusotmer = BCusotmer;
	}

	public SRole getRole() {
		return role;
	}

	public void setRole(SRole role) {
		this.role = role;
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