package com.wisewater.cusConfig.controller;

import com.wisewater.base.BaseForm;

public class CMaterialTagForm extends BaseForm {


    private static final long serialVersionUID = 1L;

    private String id;

    private int isLogicDel;

    private String remarks;

    private String token;

    private String value;

    public CMaterialTagForm() {
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

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}