package com.wisewater.cusConfig.pojo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wisewater.bizConfig.pojo.BAccessToken;
import com.wisewater.bizConfig.pojo.BCustomer;

/**
 * The persistent class for the c_mp database table.
 * 
 */
@Entity
@Table(name = "c_mp")
@NamedQuery(name = "CMp.findAll", query = "SELECT c FROM CMp c")
public class CMp extends com.wisewater.base.BasePojo {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuidgenerator")
	@GenericGenerator(name = "uuidgenerator", strategy = "uuid2")
	private String id;

	private String appID;

	private String appSecret;

	private String email;

	private int isLogicDel;

	private String name;

	private String origID;

	private String remarks;

	private String wcAccount;

	// bi-directional many-to-one association to BCusotmer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerID")
	private BCustomer BCusotmer;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "token")
	private BAccessToken accessToken;

	public CMp() {
	}

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

	public String getWcAccount() {
		return this.wcAccount;
	}

	public void setWcAccount(String wcAccount) {
		this.wcAccount = wcAccount;
	}

	public BCustomer getBCusotmer() {
		return this.BCusotmer;
	}

	public void setBCusotmer(BCustomer BCusotmer) {
		this.BCusotmer = BCusotmer;
	}

	public BAccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(BAccessToken accessToken) {
		this.accessToken = accessToken;
	}

}