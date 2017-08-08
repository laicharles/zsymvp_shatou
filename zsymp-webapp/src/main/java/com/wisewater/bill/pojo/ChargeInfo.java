package com.wisewater.bill.pojo;

/**
 * 欠费账单信息列表
 * 显示在缴费表单中来确认是否需要缴费
 * */
public class ChargeInfo {
	/**
	 * 户号
	 * */
	private String accountno;
	/**
	 * 户名
	 * */
	private String name;
	/**
	 * 地址
	 * */
	private String addr;
	/**
	 * 新地址
	 * */
	private String newAddr;
	/**
	 * 抄表日期
	 * */
	private String cdate;
	/**
	 * 水费
	 * */
	private String price;
	/**
	 * 污水费
	 * */
	private String pwf;
	/**
	 * 违约金
	 * */
	private String cnj;
	/**
	 * 污水费滞纳金
	 * */
	private String pwcnj;
	/**
	 * 垃圾费
	 * */
	private String ljprice;
	/**
	 * 总计
	 * */
	private String total;
	
	public String getTotal() {
		
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getNewAddr() {
		return newAddr;
	}

	public void setNewAddr(String newAddr) {
		this.newAddr = newAddr;
	}
	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPwf() {
		return pwf;
	}

	public void setPwf(String pwf) {
		this.pwf = pwf;
	}

	public String getCnj() {
		return cnj;
	}

	public void setCnj(String cnj) {
		this.cnj = cnj;
	}

	public String getPwcnj() {
		return pwcnj;
	}

	public void setPwcnj(String pwcnj) {
		this.pwcnj = pwcnj;
	}

	public String getLjprice() {
		return ljprice;
	}

	public void setLjprice(String ljprice) {
		this.ljprice = ljprice;
	}
 
}
