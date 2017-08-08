package com.wisewater.cusConfig.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;


/**
 * The persistent class for the c_material_tag database table.
 * 
 */
@Entity
@Table(name="c_material_tag")
@NamedQuery(name="CMaterialTag.findAll", query="SELECT c FROM CMaterialTag c")
public class CMaterialTag extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private int isLogicDel;

	private String remarks;

	private String token;

	private String value;

	//bi-directional many-to-many association to CArticleMaterial
	@ManyToMany(mappedBy="CMaterialTags")
	private List<CArticleMaterial> CArticleMaterials;

	//bi-directional many-to-many association to CPageMaterial
	@ManyToMany(mappedBy="CMaterialTags")
	private List<CPageMaterial> CPageMaterials;

	//bi-directional many-to-many association to CPicMaterial
	@ManyToMany(mappedBy="CMaterialTags")
	private List<CPicMaterial> CPicMaterials;

	//bi-directional many-to-many association to CTextMaterial
	@ManyToMany(mappedBy="CMaterialTags")
	private List<CTextMaterial> CTextMaterials;

	public CMaterialTag() {
	}
	
	public CMaterialTag(String value, String token) {
	    this.value=value;
	    this.token=token;
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<CArticleMaterial> getCArticleMaterials() {
		return this.CArticleMaterials;
	}

	public void setCArticleMaterials(List<CArticleMaterial> CArticleMaterials) {
		this.CArticleMaterials = CArticleMaterials;
	}

	public List<CPageMaterial> getCPageMaterials() {
		return this.CPageMaterials;
	}

	public void setCPageMaterials(List<CPageMaterial> CPageMaterials) {
		this.CPageMaterials = CPageMaterials;
	}

	public List<CPicMaterial> getCPicMaterials() {
		return this.CPicMaterials;
	}

	public void setCPicMaterials(List<CPicMaterial> CPicMaterials) {
		this.CPicMaterials = CPicMaterials;
	}

	public List<CTextMaterial> getCTextMaterials() {
		return this.CTextMaterials;
	}

	public void setCTextMaterials(List<CTextMaterial> CTextMaterials) {
		this.CTextMaterials = CTextMaterials;
	}

}