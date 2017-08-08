package com.wisewater.auto.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.wisewater.system.pojo.SDictionary;


/**
 * The persistent class for the c_auto_img_tx database table.
 * 
 */
@Entity
@Table(name="c_auto_default")
@NamedQuery(name="CAutoDefault.findAll", query="SELECT c FROM CAutoDefault c")
public class CAutoDefault extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String token;
	
	private String autoReply;
	
	//bi-directional many-to-one association to SDictionary
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="answerType", referencedColumnName="logicID")
	private SDictionary sdictionary;
	
	private String textContent;
	
	private String title;

	private String description;

	private String picName;
	
	private String pageUrl;

	@Lob
	private String pageContent;
	
	private String author;
	
	private String origUrl;

	private int isLogicDel;

	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAutoReply() {
		return autoReply;
	}

	public void setAutoReply(String autoReply) {
		this.autoReply = autoReply;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOrigUrl() {
		return origUrl;
	}

	public void setOrigUrl(String origUrl) {
		this.origUrl = origUrl;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public SDictionary getSdictionary() {
		return sdictionary;
	}

	public void setSdictionary(SDictionary sdictionary) {
		this.sdictionary = sdictionary;
	}
}