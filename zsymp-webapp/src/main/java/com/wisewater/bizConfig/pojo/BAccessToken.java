package com.wisewater.bizConfig.pojo;


import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wisewater.cusConfig.pojo.CMp;


/**
 * The persistent class for the b_access_token database table.
 * 
 */
@Entity
@Table(name="b_access_token")
@NamedQuery(name="BAccessToken.findAll", query="SELECT b FROM BAccessToken b")
public class BAccessToken extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="token")
	//@GeneratedValue(generator="uuidgenerator")
	//@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String token;

	private String accessToken;
	
	private String apiTicket;
	
	private String jsapiTicket;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredDateTime;
	
	@OneToOne(mappedBy="accessToken",fetch=FetchType.LAZY)
	private CMp cmp;
	

	public BAccessToken() {
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getExpiredDateTime() {
		return expiredDateTime;
	}

	public void setExpiredDateTime(Date expiredDateTime) {
		this.expiredDateTime = expiredDateTime;
	}

	public CMp getCmp() {
		return cmp;
	}

	public void setCmp(CMp cmp) {
		this.cmp = cmp;
	}

	public String getApiTicket() {
		return apiTicket;
	}

	public void setApiTicket(String apiTicket) {
		this.apiTicket = apiTicket;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

}