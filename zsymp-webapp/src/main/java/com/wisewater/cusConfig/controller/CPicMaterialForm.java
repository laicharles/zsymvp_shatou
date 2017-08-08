package com.wisewater.cusConfig.controller;

import org.springframework.web.multipart.MultipartFile;

import com.wisewater.base.BaseForm;

public class CPicMaterialForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    private String id;

    private String createdAt;

    private String createdBy;

    private String createdDateTime;

    private int isLogicDel;

    private String mediaID;

    private String picName;
    
    private MultipartFile picNameFile;

    private String remarks;

    private String thumbPicName;
    
    private String title;

    private String tags;
    
    private String token;
    
    private String inheritedToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public int getIsLogicDel() {
        return isLogicDel;
    }

    public void setIsLogicDel(int isLogicDel) {
        this.isLogicDel = isLogicDel;
    }

    public String getMediaID() {
        return mediaID;
    }

    public void setMediaID(String mediaID) {
        this.mediaID = mediaID;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public MultipartFile getPicNameFile() {
        return picNameFile;
    }

    public void setPicNameFile(MultipartFile picNameFile) {
        this.picNameFile = picNameFile;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getThumbPicName() {
        return thumbPicName;
    }

    public void setThumbPicName(String thumbPicName) {
        this.thumbPicName = thumbPicName;
    }

    public String getTitle() {
        return title;
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

    public String getInheritedToken() {
        return inheritedToken;
    }

    public void setInheritedToken(String inheritedToken) {
        this.inheritedToken = inheritedToken;
    }
   
}