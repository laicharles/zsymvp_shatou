package com.wisewater.weixin.pojo;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * 微信实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tbl_wxs")
public class Wx implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//自增长编号
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="wx_id")
	private int wxId;

	//是否关注
	@Column(name="focus_state")
	@Max(1)
	@Min(0)
	private int focusState;

	//微信账户名
	@Column(name="wx_account")
	private String wxAccount;

	//微信所在组id
	@Column(name="wx_group_id")
	private int wxGroupId;

	public int getWxId() {
		return this.wxId;
	}

	public void setWxId(int wxId) {
		this.wxId = wxId;
	}


	public String getWxAccount() {
		return this.wxAccount;
	}

	public void setWxAccount(String wxAccount) {
		this.wxAccount = wxAccount;
	}

	public int getWxGroupId() {
		return this.wxGroupId;
	}

	public void setWxGroupId(int wxGroupId) {
		this.wxGroupId = wxGroupId;
	}

	public int getFocusState() {
		return focusState;
	}

	public void setFocusState(int focusState) {
		this.focusState = focusState;
	}
}
