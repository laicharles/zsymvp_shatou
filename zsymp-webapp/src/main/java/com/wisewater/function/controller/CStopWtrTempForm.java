package com.wisewater.function.controller;

import com.wisewater.base.BaseForm;
import com.wisewater.system.controller.SDictionaryForm;

public class CStopWtrTempForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    private String id;

    private String area;

    private String stopDuration;

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

    private String url;

    private String why;
    
    private int isPrototype;
    
    private String sendDateTime;

    private SDictionaryForm sendStatus;

    public CStopWtrTempForm() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStopDuration() {
        return stopDuration;
    }

    public void setStopDuration(String stopDuration) {
        this.stopDuration = stopDuration;
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWhy() {
        return this.why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    public int getIsPrototype() {
        return isPrototype;
    }

    public void setIsPrototype(int isPrototype) {
        this.isPrototype = isPrototype;
    }
    
    public String getSendDateTime() {
        return this.sendDateTime;
    }

    public void setSendDateTime(String sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public SDictionaryForm getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(SDictionaryForm sendStatus) {
        this.sendStatus = sendStatus;
    }

}