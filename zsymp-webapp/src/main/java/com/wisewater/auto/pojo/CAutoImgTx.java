package com.wisewater.auto.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the c_auto_img_tx database table.
 * 
 */
@Entity
@Table(name="c_auto_img_tx")
@NamedQuery(name="CAutoImgTx.findAll", query="SELECT c FROM CAutoImgTx c")
public class CAutoImgTx extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String description;

	private int isLogicDel;

	private String pageUrl;

	private String picName;

	private String remarks;

	private String title;

	private String token;

	//bi-directional one-to-one association to CAuto
	@OneToOne(mappedBy="CAutoImgTx", fetch=FetchType.LAZY)
	private CAuto CAuto;

	//bi-directional one-to-one association to CAutoAr
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="articleID")
	private CAutoAr CAutoAr;

	public CAutoImgTx() {
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

	public String getPageUrl() {
		return this.pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
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

	public CAuto getCAuto() {
		return this.CAuto;
	}

	public void setCAuto(CAuto CAuto) {
		this.CAuto = CAuto;
	}

	public CAutoAr getCAutoAr() {
		return this.CAutoAr;
	}

	public void setCAutoAr(CAutoAr CAutoAr) {
		this.CAutoAr = CAutoAr;
	}

}