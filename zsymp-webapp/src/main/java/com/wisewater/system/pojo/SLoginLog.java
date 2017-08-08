package com.wisewater.system.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


/**
 * The persistent class for the s_login_log database table.
 * 
 */
@Entity
@Table(name="s_login_log")
@NamedQuery(name="SLoginLog.findAll", query="SELECT s FROM SLoginLog s")
public class SLoginLog extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String ipAddress;

	private int isLogicDel;

	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date logoutTime;

	private String sessionID;

	private String visitor;

	public SLoginLog() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getSessionID() {
		return this.sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getVisitor() {
		return this.visitor;
	}

	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}

}