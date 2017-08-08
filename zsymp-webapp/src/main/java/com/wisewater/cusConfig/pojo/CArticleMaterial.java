package com.wisewater.cusConfig.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

/**
 * The persistent class for the c_article_material database table.
 * 
 */
@Entity
@Table(name="c_article_material")
@NamedQuery(name="CArticleMaterial.findAll", query="SELECT c FROM CArticleMaterial c")
public class CArticleMaterial extends com.wisewater.base.BasePojo  {
	
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;

	private String description;

	private int isLogicDel;

	private String mediaID;

	private String pageUrl;

	private String picName;

	private String remarks;

	private String thumbPicName;

	private String title;

	private String token;

	//bi-directional one-to-one association to CArticlePage
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="articlePageID")
	private CArticlePage CArticlePage;

	//bi-directional many-to-many association to CMaterialTag
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="c_article_mtrl_tag"
            , joinColumns={
                @JoinColumn(name="mtrlID")
                }
            , inverseJoinColumns={
                @JoinColumn(name="mtrlTagID")
                }
            )
    @Where(clause="isLogicDel=0")
    private List<CMaterialTag> CMaterialTags;
    
    private String tags;

	public CArticleMaterial() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
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

	public String getMediaID() {
		return this.mediaID;
	}

	public void setMediaID(String mediaID) {
		this.mediaID = mediaID;
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

	public String getThumbPicName() {
		return this.thumbPicName;
	}

	public void setThumbPicName(String thumbPicName) {
		this.thumbPicName = thumbPicName;
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

	public CArticlePage getCArticlePage() {
		return this.CArticlePage;
	}

	public void setCArticlePage(CArticlePage CArticlePage) {
		this.CArticlePage = CArticlePage;
	}

	public List<CMaterialTag> getCMaterialTags() {
		return this.CMaterialTags;
	}

	public void setCMaterialTags(List<CMaterialTag> CMaterialTags) {
		this.CMaterialTags = CMaterialTags;
	}

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

}