package com.wisewater.system.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


/**
 * The persistent class for the s_operate_log database table.
 * 
 */
@Entity
@Table(name="s_fans_operate_log")
@NamedQuery(name="SFansOperateLog.findAll", query="SELECT s FROM SFansOperateLog s")
public class SFansOperateLog extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String descript;

	private int isLogicDel;

	private String moduleName;

	private String openId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date visitTime;
	
	public SFansOperateLog() {
		super();
	}

	public SFansOperateLog(String descript, String moduleName, String openId,
			Date visitTime) {
		super();
		this.descript = descript;
		this.isLogicDel = 0;
		this.moduleName = moduleName;
		this.openId = openId;
		this.visitTime = visitTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	
	

}