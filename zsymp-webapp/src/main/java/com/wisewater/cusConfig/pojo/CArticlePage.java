package com.wisewater.cusConfig.pojo;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * The persistent class for the c_article_page database table.
 * 
 */
@Entity
@Table(name="c_article_page")
@NamedQuery(name="CArticlePage.findAll", query="SELECT c FROM CArticlePage c")
public class CArticlePage extends com.wisewater.base.BasePojo  {
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

	//bi-directional one-to-one association to CArticleMaterial
	@OneToOne(mappedBy="CArticlePage", fetch=FetchType.LAZY)
	private CArticleMaterial CArticleMaterial;

	public CArticlePage() {
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

	public CArticleMaterial getCArticleMaterial() {
		return this.CArticleMaterial;
	}

	public void setCArticleMaterial(CArticleMaterial CArticleMaterial) {
		this.CArticleMaterial = CArticleMaterial;
	}

}