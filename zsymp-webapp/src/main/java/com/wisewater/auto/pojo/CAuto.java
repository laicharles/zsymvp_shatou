package com.wisewater.auto.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

import com.wisewater.system.pojo.SDictionary;


/**
 * The persistent class for the c_auto database table.
 * 
 */
@Entity
@Table(name="c_auto")
@NamedQuery(name="CAuto.findAll", query="SELECT c FROM CAuto c")
public class CAuto extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private int isLogicDel;

	private String question;

	private String remarks;

	private String textContent;

	private String token;

	//bi-directional many-to-one association to SDictionary
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="answerType", referencedColumnName="logicID")
	private SDictionary SDictionary;

	//bi-directional one-to-one association to CAutoImgTx
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="knowITID")
	private CAutoImgTx CAutoImgTx;

	//bi-directional many-to-one association to CAutoKeyword
	@OneToMany(mappedBy="CAuto")
	private List<CAutoKeyword> CAutoKeywords;

	//bi-directional many-to-many association to CSelfTag
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="c_auto_and_tag"
			, joinColumns={
				@JoinColumn(name="autoID")
				}
			, inverseJoinColumns={
				@JoinColumn(name="tagID")
				}
			)
	private List<CSelfTag> CSelfTags;

	public CAuto() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTextContent() {
		return this.textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public SDictionary getSDictionary() {
		return this.SDictionary;
	}

	public void setSDictionary(SDictionary SDictionary) {
		this.SDictionary = SDictionary;
	}

	public CAutoImgTx getCAutoImgTx() {
		return this.CAutoImgTx;
	}

	public void setCAutoImgTx(CAutoImgTx CAutoImgTx) {
		this.CAutoImgTx = CAutoImgTx;
	}

	public List<CAutoKeyword> getCAutoKeywords() {
		return this.CAutoKeywords;
	}

	public void setCAutoKeywords(List<CAutoKeyword> CAutoKeywords) {
		this.CAutoKeywords = CAutoKeywords;
	}

	public CAutoKeyword addCAutoKeyword(CAutoKeyword CAutoKeyword) {
		getCAutoKeywords().add(CAutoKeyword);
		CAutoKeyword.setCAuto(this);

		return CAutoKeyword;
	}

	public CAutoKeyword removeCAutoKeyword(CAutoKeyword CAutoKeyword) {
		getCAutoKeywords().remove(CAutoKeyword);
		CAutoKeyword.setCAuto(null);

		return CAutoKeyword;
	}

	public List<CSelfTag> getCSelfTags() {
		return this.CSelfTags;
	}

	public void setCSelfTags(List<CSelfTag> CSelfTags) {
		this.CSelfTags = CSelfTags;
	}

}