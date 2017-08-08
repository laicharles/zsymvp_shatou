package com.wisewater.system.pojo;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import com.wisewater.base.BasePojo;

import java.util.List;


/**
 * The persistent class for the resources database table.
 * 
 */
@Entity
@Table(name="s_resources")
@NamedQuery(name="SResource.findAll", query="SELECT s FROM SResource s ")
public class SResource extends BasePojo {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String remarks;

	@ManyToOne
	@JoinColumn(name="parentResID")
	private SResource parentResource;

	private String picName;

	private String resCode;

	private String resName;

	//资源类型
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="resType", referencedColumnName="logicID")
	private SDictionary resType;

	private String reqUrl;
	
	private String authUrl;
	
	@Max(1)
	@Min(0)
	@Column(name="isLogicDel",columnDefinition="int(1) default 0")
	private int isLogicDel;
	
	@Max(1)
	@Min(0)
	@Column(name="isShowMenu",columnDefinition="int(1) default 1")
	private int isShowMenu;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="resources")
	private List<SRole> roles;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="parentResource")
	@Where(clause="isLogicDel=0")
	private List<SResource> subResources;
	

	public SResource() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicName() {
		return this.picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getResCode() {
		return this.resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResName() {
		return this.resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public List<SRole> getRoles() {
		return this.roles;
	}

	public void setRoles(List<SRole> roles) {
		this.roles = roles;
	}

	public SResource getParentResource() {
		return parentResource;
	}

	public void setParentResource(SResource parentResource) {
		this.parentResource = parentResource;
	}

	public List<SResource> getSubResources() {
		return subResources;
	}

	public void setSubResources(List<SResource> subResources) {
		this.subResources = subResources;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public int getIsShowMenu() {
		return isShowMenu;
	}

	public void setIsShowMenu(int isShowMenu) {
		this.isShowMenu = isShowMenu;
	}

	public SDictionary getResType() {
		return resType;
	}

	public void setResType(SDictionary resType) {
		this.resType = resType;
	}

}