package com.wisewater.bill.controller;

import com.wisewater.base.BaseForm;

/**
 * 账单内容
 * JiaHui
 * 2016年9月7日 下午3:46:14
 */
public class BillContentForm extends BaseForm{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 账单日期
	 */
	private String bill_date;
	
	/**
	 * 水费/年阶梯费
	 */
	private String bill_type;
	
	/**
	 * 当期水量
	 */
	private String water_yield;
	
	/**
	 * 当期水费
	 */
	private String water_charge;
	
	/**
	 *  缴费状态
	 *  0:未缴；1:已缴待销；2:已缴
	 */
	private String status;
	
	/**
	 * 滞纳金
	 */
	private String penalbond;

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}

	public String getWater_yield() {
		return water_yield;
	}

	public void setWater_yield(String water_yield) {
		this.water_yield = water_yield;
	}

	public String getWater_charge() {
		return water_charge;
	}

	public void setWater_charge(String water_charge) {
		this.water_charge = water_charge;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPenalbond() {
		return penalbond;
	}

	public void setPenalbond(String penalbond) {
		this.penalbond = penalbond;
	}
	
}
