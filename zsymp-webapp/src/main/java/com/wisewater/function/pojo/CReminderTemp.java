package com.wisewater.function.pojo;


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

import com.wisewater.system.pojo.SDictionary;


/**
 * The persistent class for the c_stop_wtr_temp database table.
 * 
 */
@Entity
@Table(name="c_reminder_temp")
@NamedQuery(name="CReminderTemp.findAll", query="SELECT c FROM CReminderTemp c")
public class CReminderTemp extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;
	
	private int isLogicDel;
	
	private String remarks;
	
	private String templateCode;
	
	private String templateName;

	private String templateID;
	
	private String headColor;
	
	private String textColor;
	
	private String url;

	private String first;

	private String serviceUnits;
	
	private String tel;

	private String tempRemark;

	private String token;
		
	private int isPrototype;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date sendDateTime;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sendStatus", referencedColumnName="logicID")
    private SDictionary sendStatus;

	public CReminderTemp() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	public String getHeadColor() {
		return headColor;
	}

	public void setHeadColor(String headColor) {
		this.headColor = headColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getServiceUnits() {
		return serviceUnits;
	}

	public void setServiceUnits(String serviceUnits) {
		this.serviceUnits = serviceUnits;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTempRemark() {
		return tempRemark;
	}

	public void setTempRemark(String tempRemark) {
		this.tempRemark = tempRemark;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getIsPrototype() {
		return isPrototype;
	}

	public void setIsPrototype(int isPrototype) {
		this.isPrototype = isPrototype;
	}

	public Date getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(Date sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	public SDictionary getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(SDictionary sendStatus) {
		this.sendStatus = sendStatus;
	}

	


}