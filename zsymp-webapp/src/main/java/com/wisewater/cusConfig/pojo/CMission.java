package com.wisewater.cusConfig.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;



/**
 * The persistent class for the c_mission database table.
 * 
 */
@Entity
@Table(name="c_mission")
@NamedQuery(name="CMission.findAll", query="SELECT c FROM CMission c")
public class CMission extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private int isConfiged;

	private int isLogicDel;

	private String remarks;

	private String token;

	//bi-directional many-to-one association to CMissionConf
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="missionID")
	private CMissionConf CMissionConf;

	public CMission() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsConfiged() {
		return this.isConfiged;
	}

	public void setIsConfiged(int isConfiged) {
		this.isConfiged = isConfiged;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public CMissionConf getCMissionConf() {
		return this.CMissionConf;
	}

	public void setCMissionConf(CMissionConf CMissionConf) {
		this.CMissionConf = CMissionConf;
	}

}