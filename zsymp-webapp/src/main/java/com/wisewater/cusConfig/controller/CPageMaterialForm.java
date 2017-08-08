package com.wisewater.cusConfig.controller;

import com.wisewater.base.BaseForm;

public class CPageMaterialForm extends BaseForm {
    
    private static final long serialVersionUID = 1L;

    private String id;

    private String createdBy;

    private String createdDateTime;

    private int isLogicDel;

    private String remarks;

    private String pageContent;

    private String title;
    
    private String tags;
    
    private String token;

    public CPageMaterialForm() {
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

    public String getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
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

    public String getPageContent() {
        return this.pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}