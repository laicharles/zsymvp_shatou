package com.wisewater.fans.controller;

import com.wisewater.base.BaseForm;
import com.wisewater.system.controller.SDictionaryForm;

public class CFanForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String cityName;

	private String country;

	private SDictionaryForm gender;

	private int isLogicDel;

	private int isSubscribe;

	private String nickName;

	private String openID;

	private String provinceName;

	private String remarks;

	private String subscribeDate;

	private String token;

	private String unsubscribeDate;
	
	private String headimgurl;
	
	/**
	 * 是否有绑定*/
	private String hasBind;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public SDictionaryForm getGender() {
		return gender;
	}

	public void setGender(SDictionaryForm gender) {
		this.gender = gender;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public int getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(int isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSubscribeDate() {
		return subscribeDate;
	}

	public void setSubscribeDate(String subscribeDate) {
		this.subscribeDate = subscribeDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUnsubscribeDate() {
		return unsubscribeDate;
	}

	public void setUnsubscribeDate(String unsubscribeDate) {
		this.unsubscribeDate = unsubscribeDate;
	}

    public String getHasBind() {
        return hasBind;
    }

    public void setHasBind(String hasBind) {
        this.hasBind = hasBind;
    }

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
    
}