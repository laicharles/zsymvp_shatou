package com.wisewater.fans.pojo;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the c_fan_user database table.
 * 
 */
@Entity
@Table(name="c_fan_user")
@NamedQuery(name="CFanUser.findAll", query="SELECT c FROM CFanUser c")
public class CFanUser extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date bindDateTime;

	private String contactAddr;

	private int isLogicDel;

	private String mobile;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fanID")
    private CFan fan;

	private String remarks;

	private String token;

	private String userAccount;

	private String userName;
	
	private String openId;

	public CFanUser() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBindDateTime() {
		return bindDateTime;
	}

	public void setBindDateTime(Date bindDateTime) {
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

	public CFan getFan() {
		return fan;
	}

	public void setFan(CFan fan) {
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}