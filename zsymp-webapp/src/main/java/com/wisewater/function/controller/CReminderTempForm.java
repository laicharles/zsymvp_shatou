package com.wisewater.function.controller;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.wisewater.base.BaseForm;
import com.wisewater.system.controller.SDictionaryForm;

public class CReminderTempForm extends BaseForm{
	
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
	
	private String sendDateTime;
	
	private SDictionaryForm sendStatus;
	
	public SDictionaryForm getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(SDictionaryForm sendStatus) {
		this.sendStatus = sendStatus;
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

	public String getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(String sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	
}
