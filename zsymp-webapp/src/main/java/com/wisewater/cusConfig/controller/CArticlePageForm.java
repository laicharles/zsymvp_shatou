package com.wisewater.cusConfig.controller;

import com.wisewater.base.BaseForm;

public class CArticlePageForm extends BaseForm {
    
    private static final long serialVersionUID = 1L;

    private String id;

    private String author;

    private int isLogicDel;

    private String origUrl;

    private String pageContent;

    private String remarks;

    private String token;

    public CArticlePageForm() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIsLogicDel() {
        return this.isLogicDel;
    }

    public void setIsLogicDel(int isLogicDel) {
        this.isLogicDel = isLogicDel;
    }

    public String getOrigUrl() {
        return this.origUrl;
    }

    public void setOrigUrl(String origUrl) {
        this.origUrl = origUrl;
    }

    public String getPageContent() {
        return this.pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
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

}