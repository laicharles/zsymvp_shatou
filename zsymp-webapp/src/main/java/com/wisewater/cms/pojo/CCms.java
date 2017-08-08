package com.wisewater.cms.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.wisewater.system.pojo.SDictionary;

import java.util.Date;


/**
 * The persistent class for the c_cms database table.
 * 
 */
@Entity
@Table(name="c_cms")
@NamedQuery(name="CCms.findAll", query="SELECT c FROM CCms c")
public class CCms extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;

	private int isLogicDel;

	@Lob
	private String pageContent;

	private String pageUrl;

	private String remarks;

	private String title;

	private String token;
	
	private String author;
	
	private String origUrl;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cmsType", referencedColumnName="logicID")
	private SDictionary cmsType;
	
	private String image;
	
	private String sortNumber;
	
	private String submitOpenId;
	
	private String auditorOpenId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="auditorStatus", referencedColumnName="logicID")
	private SDictionary auditorStatus;
	
	private String auditorLevel;
	
	public CCms() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getPageContent() {
		return this.pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public String getPageUrl() {
		return this.pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
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

	public SDictionary getCmsType() {
		return cmsType;
	}

	public void setCmsType(SDictionary cmsType) {
		this.cmsType = cmsType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(String sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getSubmitOpenId() {
		return submitOpenId;
	}

	public void setSubmitOpenId(String submitOpenId) {
		this.submitOpenId = submitOpenId;
	}

	public String getAuditorOpenId() {
		return auditorOpenId;
	}

	public void setAuditorOpenId(String auditorOpenId) {
		this.auditorOpenId = auditorOpenId;
	}

	public SDictionary getAuditorStatus() {
		return auditorStatus;
	}

	public void setAuditorStatus(SDictionary auditorStatus) {
		this.auditorStatus = auditorStatus;
	}

	public String getAuditorLevel() {
		return auditorLevel;
	}

	public void setAuditorLevel(String auditorLevel) {
		this.auditorLevel = auditorLevel;
	}
	
}