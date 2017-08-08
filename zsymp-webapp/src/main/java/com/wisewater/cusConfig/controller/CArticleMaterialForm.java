package com.wisewater.cusConfig.controller;

import org.springframework.web.multipart.MultipartFile;

import com.wisewater.base.BaseForm;

public class CArticleMaterialForm extends BaseForm {
    
    private static final long serialVersionUID = 1L;

    private String id;

    private String createdAt;

    private String createdBy;

    private String createdDateTime;

    private String description;

    private int isLogicDel;

    private String mediaID;

    private String pageUrl;

    private String picName;

    private String remarks;

    private String thumbPicName;

    private String title;

    private String token;
    
    private String inheritedToken;
    
    private MultipartFile picNameFile;

    private CArticlePageForm CArticlePage;

    private String tags;

    public CArticleMaterialForm() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsLogicDel() {
        return this.isLogicDel;
    }

    public void setIsLogicDel(int isLogicDel) {
        this.isLogicDel = isLogicDel;
    }

    public String getMediaID() {
        return this.mediaID;
    }

    public void setMediaID(String mediaID) {
        this.mediaID = mediaID;
    }

    public String getPageUrl() {
        return this.pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPicName() {
        return this.picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getThumbPicName() {
        return this.thumbPicName;
    }

    public void setThumbPicName(String thumbPicName) {
        this.thumbPicName = thumbPicName;
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

    public String getInheritedToken() {
        return inheritedToken;
    }

    public void setInheritedToken(String inheritedToken) {
        this.inheritedToken = inheritedToken;
    }

    public CArticlePageForm getCArticlePage() {
        return this.CArticlePage;
    }

    public void setCArticlePage(CArticlePageForm CArticlePage) {
        this.CArticlePage = CArticlePage;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public MultipartFile getPicNameFile() {
        return picNameFile;
    }

    public void setPicNameFile(MultipartFile picNameFile) {
        this.picNameFile = picNameFile;
    }

}