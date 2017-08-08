package com.wisewater.cusConfig.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;


/**
 * The persistent class for the c_mission_conf database table.
 * 
 */
@Entity
@Table(name="c_mission_conf")
@NamedQuery(name="CMissionConf.findAll", query="SELECT c FROM CMissionConf c")
public class CMissionConf extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private int isLogicDel;

	private String missionName;

	private String missionUrl;

	private String remarks;

	//bi-directional many-to-one association to CMission
	@OneToMany(mappedBy="CMissionConf")
	private List<CMission> CMissions;

	public CMissionConf() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getMissionName() {
		return this.missionName;
	}

	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}

	public String getMissionUrl() {
		return this.missionUrl;
	}

	public void setMissionUrl(String missionUrl) {
		this.missionUrl = missionUrl;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<CMission> getCMissions() {
		return this.CMissions;
	}

	public void setCMissions(List<CMission> CMissions) {
		this.CMissions = CMissions;
	}

	public CMission addCMission(CMission CMission) {
		getCMissions().add(CMission);
		CMission.setCMissionConf(this);

		return CMission;
	}

	public CMission removeCMission(CMission CMission) {
		getCMissions().remove(CMission);
		CMission.setCMissionConf(null);

		return CMission;
	}

}