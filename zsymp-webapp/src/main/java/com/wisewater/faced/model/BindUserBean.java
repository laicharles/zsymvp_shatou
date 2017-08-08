package com.wisewater.faced.model;

/**
 * 
 * 描述：绑定户号
 * @author AlexFung
 * 2016年9月8日 下午5:01:34
 */
public class BindUserBean{

	/**
	 *  户名 
	 */
	private String account_name;
	
	/**
	 *  户号 
	 */
	private String account_no;
	
	/**
	 *  联系地址
	 */
	private String address;
	
	/**
	 *  返回结果 （0，成功；1，未查询到用户；2，包含非法字符）
	 */
	private String return_code;
	
	/**
	 *  0:正常；1:停水；－1:拆表
	 */
	private String status;
	
	/**
	 * 滞纳金
	 */
	private String station;

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}
	
}
