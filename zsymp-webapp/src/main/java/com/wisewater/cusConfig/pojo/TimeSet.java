package com.wisewater.cusConfig.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "c_limit")
@NamedQuery(name = "TimeSet.findAll", query = "SELECT c FROM TimeSet c")
public class TimeSet extends com.wisewater.base.BasePojo {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private int flag;

	private String firstHour;//日结的开始时间

	private String secondHour;//日结的结束时间

	private String firstDay1;//月结的开始时间（居民）

	private String secondDay1;//月结的开始时间（居民）
	
	private String firstDay2;//日结的开始时间（企业）

	private String secondDay2;//日结的开始时间（企业）
	
	private String hourSatus;//日结时间的状态值
	
	private String daySatus1;//月结时间的状态值(居民)
	
	private String daySatus2;//月结时间的状态值（企业）

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getFirstHour() {
		return firstHour;
	}

	public void setFirstHour(String firstHour) {
		this.firstHour = firstHour;
	}

	public String getSecondHour() {
		return secondHour;
	}

	public void setSecondHour(String secondHour) {
		this.secondHour = secondHour;
	}

	public String getFirstDay1() {
		return firstDay1;
	}

	public void setFirstDay1(String firstDay1) {
		this.firstDay1 = firstDay1;
	}

	public String getSecondDay1() {
		return secondDay1;
	}

	public void setSecondDay1(String secondDay1) {
		this.secondDay1 = secondDay1;
	}

	public String getFirstDay2() {
		return firstDay2;
	}

	public void setFirstDay2(String firstDay2) {
		this.firstDay2 = firstDay2;
	}

	public String getSecondDay2() {
		return secondDay2;
	}

	public void setSecondDay2(String secondDay2) {
		this.secondDay2 = secondDay2;
	}

	public String getHourSatus() {
		return hourSatus;
	}

	public void setHourSatus(String hourSatus) {
		this.hourSatus = hourSatus;
	}

	public String getDaySatus1() {
		return daySatus1;
	}

	public void setDaySatus1(String daySatus1) {
		this.daySatus1 = daySatus1;
	}

	public String getDaySatus2() {
		return daySatus2;
	}

	public void setDaySatus2(String daySatus2) {
		this.daySatus2 = daySatus2;
	}
	
}
