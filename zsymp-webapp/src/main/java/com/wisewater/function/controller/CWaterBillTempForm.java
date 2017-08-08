package com.wisewater.function.controller;

import com.wisewater.base.BaseForm;

public class CWaterBillTempForm extends BaseForm {
    
    private static final long serialVersionUID = 1L;

    private String id;

    private String first;

    private String headColor;

    private int isLogicDel;

    private String remarks;

    private String templateCode;

    private String templateID;

    private String templateName;

    private String tempRemark;

    private String textColor;

    private String token;
    
    private String sendStatus;
    
    private String sendDateTime;

    public CWaterBillTempForm() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst() {
        return this.first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getHeadColor() {
        return this.headColor;
    }

    public void setHeadColor(String headColor) {
        this.headColor = headColor;
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

    public String getTemplateCode() {
        return this.templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateID() {
        return this.templateID;
    }

    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTempRemark() {
        return this.tempRemark;
    }

    public void setTempRemark(String tempRemark) {
        this.tempRemark = tempRemark;
    }

    public String getTextColor() {
        return this.textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(String sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

}