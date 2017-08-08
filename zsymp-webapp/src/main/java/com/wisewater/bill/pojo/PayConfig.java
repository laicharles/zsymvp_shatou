package com.wisewater.bill.pojo;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 支付配置表
 * @author TalonYeung
 * 2015年12月22日 上午11:23:12
 */
@Entity
@Table(name="cn_payconfig")
public class PayConfig implements Serializable{
	private static final long serialVersionUID = 10000L;
	
	/**
	 * 公众号token
	 */
	@Id
	private String token;
	
	/**
	 * 微信支付商户号
	 */
	private String mchId;
	
	/**
	 * API密钥
	 */
	private String apiKey;
	
	/**
	 * 支付开关
	 * 0:关闭
	 * 1:开启
	 */
	private int canPay;
	
	private String certPath;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public int getCanPay() {
		return canPay;
	}

	public void setCanPay(int canPay) {
		this.canPay = canPay;
	}

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}
	
	
	
}

