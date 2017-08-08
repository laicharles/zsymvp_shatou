package com.wisewater.auto.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the c_auto_ar database table.
 * 
 */
@Entity
@Table(name="c_auto_ar")
@NamedQuery(name="CAutoAr.findAll", query="SELECT c FROM CAutoAr c")
public class CAutoAr extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String author;

	private int isLogicDel;

	private String origUrl;

	@Lob
	private String pageContent;

	private String remarks;

	private String token;

	//bi-directional one-to-one association to CAutoImgTx
	@OneToOne(mappedBy="CAutoAr", fetch=FetchType.LAZY)
	private CAutoImgTx CAutoImgTx;

	public CAutoAr() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getOrigUrl() {
		return this.origUrl;
	}

	public void setOrigUrl(String origUrl) {
		this.origUrl = origUrl;
	}

	public String getPageContent() {
		return this.pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public CAutoImgTx getCAutoImgTx() {
		return this.CAutoImgTx;
	}

	public void setCAutoImgTx(CAutoImgTx CAutoImgTx) {
		this.CAutoImgTx = CAutoImgTx;
	}

}