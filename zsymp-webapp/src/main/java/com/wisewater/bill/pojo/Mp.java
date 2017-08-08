package com.wisewater.bill.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 公众号基础信息
 * 
 * JiaHui
 * 2015年12月21日 下午6:00:22
 */
@Entity
@Table(name="c_mp")
public class Mp implements Serializable{

	private static final long serialVersionUID = 10000L;
	
	/**
	 * 二维码（保存图片名称）
	 */
	private int isLogicDel;
	
	/**
	 * 类型
	 *值1:服务号
     *值2:订阅号
	 */
	private String remarks;
	
	/**
	 * 微信号
	 */
	private String name;
	
	/**
	 * 原始ID
	 */
	private String origID;
	
	/**
	 * 客服电话
	 */
	private String wcAccount;
	
	/**
	 * 公司全称
	 */
	private String appID;
	
	/**
	 * 微信公众号平台登录账号
	 */
	private String appSecret;
	
	/**
	 * 微信公众号平台登录密码
	 */
	private String msgEncodeType;
	
	/**
	 * 应用ID
	 */
	private String wcType;
	
	/**
	 * 应用密钥
	 */
	private String provinceID;
	
	/**
	 * 与token相对应，且能公开
	 */
	
	
	/**
	 * 备注
	 */
	private String cityID;
	
	/**
	 * 电子邮箱
	 */
	private String email;
	
	/**
	 * 官网地址
	 */
	@Id
	private String token;

	/**
	 * 网站一体化-域名
	 */
	private String customerID;
	
	/**
	 * 用户信息是否加密（用户绑定、水费账单、缴纳水费）
	 * 0：加密
	 * 1：不加密
	 */
	private int i_flag;


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigID() {
		return origID;
	}

	public void setOrigID(String origID) {
		this.origID = origID;
	}

	public String getWcAccount() {
		return wcAccount;
	}

	public void setWcAccount(String wcAccount) {
		this.wcAccount = wcAccount;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getMsgEncodeType() {
		return msgEncodeType;
	}

	public void setMsgEncodeType(String msgEncodeType) {
		this.msgEncodeType = msgEncodeType;
	}

	public String getWcType() {
		return wcType;
	}

	public void setWcType(String wcType) {
		this.wcType = wcType;
	}

	public String getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	public String getCityID() {
		return cityID;
	}

	public void setCityID(String cityID) {
		this.cityID = cityID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public int getI_flag() {
		return i_flag;
	}

	public void setI_flag(int i_flag) {
		this.i_flag = i_flag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
