package com.wisewater.function.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.wisewater.system.pojo.SDictionary;

import java.util.Date;


/**
 * The persistent class for the c_mass_send database table.
 * 
 */
@Entity
@Table(name="c_mass_send")
@NamedQuery(name="CMassSend.findAll", query="SELECT c FROM CMassSend c")
public class CMassSend extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDatetime;

	private int isLogicDel;

	private String materialID;

	private String materialID2;

	private String materialID3;

	private String materialID4;

	private String materialID5;

	private String materialID6;

	private String materialID7;

	private String materialID8;

	private String materialType;

	private String mpMsgID;

	private String remarks;

	@Temporal(TemporalType.TIMESTAMP)
	private Date sendDateTime;

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sendStatus", referencedColumnName="logicID")
    private SDictionary sendStatus;

	private String title;

	private String token;

	public CMassSend() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getMaterialID() {
		return this.materialID;
	}

	public void setMaterialID(String materialID) {
		this.materialID = materialID;
	}

	public String getMaterialID2() {
		return this.materialID2;
	}

	public void setMaterialID2(String materialID2) {
		this.materialID2 = materialID2;
	}

	public String getMaterialID3() {
		return this.materialID3;
	}

	public void setMaterialID3(String materialID3) {
		this.materialID3 = materialID3;
	}

	public String getMaterialID4() {
		return this.materialID4;
	}

	public void setMaterialID4(String materialID4) {
		this.materialID4 = materialID4;
	}

	public String getMaterialID5() {
		return this.materialID5;
	}

	public void setMaterialID5(String materialID5) {
		this.materialID5 = materialID5;
	}

	public String getMaterialID6() {
		return this.materialID6;
	}

	public void setMaterialID6(String materialID6) {
		this.materialID6 = materialID6;
	}

	public String getMaterialID7() {
		return this.materialID7;
	}

	public void setMaterialID7(String materialID7) {
		this.materialID7 = materialID7;
	}

	public String getMaterialID8() {
		return this.materialID8;
	}

	public void setMaterialID8(String materialID8) {
		this.materialID8 = materialID8;
	}

	public String getMaterialType() {
		return this.materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMpMsgID() {
		return this.mpMsgID;
	}

	public void setMpMsgID(String mpMsgID) {
		this.mpMsgID = mpMsgID;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getSendDateTime() {
		return this.sendDateTime;
	}

	public void setSendDateTime(Date sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	public SDictionary getSendStatus() {
		return this.sendStatus;
	}

	public void setSendStatus(SDictionary sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}