package com.wisewater.bill.controller;

import java.util.List;

import com.wisewater.base.BaseForm;

/**
 * 水费账单信息
 * JiaHui
 * 2016年9月7日 下午3:46:26
 */
public class BillForm extends BaseForm{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	/**
	 * 户号
	 */
	private String hno;
	
	/**
	 * 户名
	 */
	private String name;
	
	/**
	 * 用水地址
	 */
	private String addr;
	
	/**
	 * 新地址
	 */
	private String newaddr;
	
	/**
	 * 用水性质
	 */
	private String usort;
	
	/**
	 * 抄表月份
	 */
	private String month;
	
	/**
	 * 上月行码
	 */
	private String lnum;
	
	/**
	 * 本月行码
	 */
	private String pnum;
	
	/**
	 * 水量
	 */
	private String quanty;
	
	/**
	 * 校正水量
	 */
	private String xzsl;
	
	/**
	 * 水费
	 */
	private String price;
	
	/**
	 * 水费单价
	 */
	private String uprice;
	
	/**
	 * 污水费
	 */
	private String pwf;
	
	/**
	 * 污水费单价
	 */
	private String pwuprice;
	
	/**
	 * 违约金
	 */
	private String cnj;
	
	/**
	 * 污水费滞纳金
	 */
	private String pwcnj;
	
	/**
	 * 垃圾费
	 */
	private String ljprice;
	
	/**
	 * 应交金额
	 */
	private String totalMoney;
	
	/**
	 * 抄表日期
	 */
	private String cdate;
	
	/**
	 * 上期抄表日期
	 */
	private String lcdate;
	
	/**
	 * 销账日期，为空表示未缴费
	 */
	private String sdate;

	public BillForm(){}
	public BillForm(String id, String hno, String name, String addr,
			String newaddr, String usort, String month, String lnum,
			String pnum, String quanty, String xzsl, String price,
			String uprice, String pwf, String pwuprice, String cnj,
			String pwcnj, String ljprice, String totalMoney, String cdate,
			String lcdate, String sdate) {
		super();
		this.id = id;
		this.hno = hno;
		this.name = name;
		this.addr = addr;
		this.newaddr = newaddr;
		this.usort = usort;
		this.month = month;
		this.lnum = lnum;
		this.pnum = pnum;
		this.quanty = quanty;
		this.xzsl = xzsl;
		this.price = price;
		this.uprice = uprice;
		this.pwf = pwf;
		this.pwuprice = pwuprice;
		this.cnj = cnj;
		this.pwcnj = pwcnj;
		this.ljprice = ljprice;
		this.totalMoney = totalMoney;
		this.cdate = cdate;
		this.lcdate = lcdate;
		this.sdate = sdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHno() {
		return hno;
	}

	public void setHno(String hno) {
		this.hno = hno;
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

	public String getNewaddr() {
		return newaddr;
	}

	public void setNewaddr(String newaddr) {
		this.newaddr = newaddr;
	}

	public String getUsort() {
		return usort;
	}

	public void setUsort(String usort) {
		this.usort = usort;
	}

	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getLnum() {
		return lnum;
	}

	public void setLnum(String lnum) {
		this.lnum = lnum;
	}

	public String getPnum() {
		return pnum;
	}

	public void setPnum(String pnum) {
		this.pnum = pnum;
	}

	public String getQuanty() {
		return quanty;
	}
	
	public void setQuanty(String quanty) {
		this.quanty = quanty;
	}
	
	public String getXzsl() {
		return xzsl;
	}
	
	public void setXzsl(String xzsl) {
		this.xzsl = xzsl;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUprice() {
		return uprice;
	}

	public void setUprice(String uprice) {
		this.uprice = uprice;
	}

	public String getPwf() {
		return pwf;
	}

	public void setPwf(String pwf) {
		this.pwf = pwf;
	}

	public String getPwuprice() {
		return pwuprice;
	}

	public void setPwuprice(String pwuprice) {
		this.pwuprice = pwuprice;
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
	
	public String getTotalMoney() {
		return totalMoney;
	}
	
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getLcdate() {
		return lcdate;
	}

	public void setLcdate(String lcdate) {
		this.lcdate = lcdate;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	
}
