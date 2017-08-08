/**
 * 
 */
package com.wisewater.bill.pojo;

/**
 * @author zxx
 * 2016年1月5日 下午5:59:27
 */
public class WxPayPrepayForm {

	private String result;
	
	private String appId;
	
	private String timestamp;
	
	private String nonceStr;
	
	private String packages;
	
	private String finalsign;
	
	private String out_trade_no;
	
	private String code;

	

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public String getFinalsign() {
		return finalsign;
	}

	public void setFinalsign(String finalsign) {
		this.finalsign = finalsign;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
