package com.wisewater.weixin.pojo;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import com.wisewater.system.pojo.SDictionary;


/**
 * The persistent class for the c_user_article database table.
 * 
 */
@Entity
@Table(name="c_click_tw")
@NamedQuery(name="CClickTw.findAll", query="SELECT c FROM CClickTw c")
public class CClickTw extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String description;

	private int isLogicDel;

	private String picName;

	private String remarks;

	private String title;

	private String token;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="twType", referencedColumnName="logicID")
	private SDictionary twType;

	public CClickTw() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getPicName() {
		return this.picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public SDictionary getTwType() {
		return twType;
	}

	public void setTwType(SDictionary twType) {
		this.twType = twType;
	}
	
}