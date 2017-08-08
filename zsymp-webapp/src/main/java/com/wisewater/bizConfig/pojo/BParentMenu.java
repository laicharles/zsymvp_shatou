package com.wisewater.bizConfig.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;


/**
 * The persistent class for the b_parent_menu database table.
 * 
 */
@Entity
@Table(name="b_parent_menu")
@NamedQuery(name="BParentMenu.findAll", query="SELECT b FROM BParentMenu b")
public class BParentMenu extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private int isLogicDel;

	private int location;

	private String name;

	private String remarks;

	/*//bi-directional many-to-one association to BMenuConfigure
	@OneToMany(mappedBy="BParentMenu")
	private List<BMenuConfigure> BMenuConfigures;*/

	public BParentMenu() {
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

	public int getLocation() {
		return this.location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/*
	public List<BMenuConfigure> getBMenuConfigures() {
		return this.BMenuConfigures;
	}

	public void setBMenuConfigures(List<BMenuConfigure> BMenuConfigures) {
		this.BMenuConfigures = BMenuConfigures;
	}

	public BMenuConfigure addBMenuConfigure(BMenuConfigure BMenuConfigure) {
		getBMenuConfigures().add(BMenuConfigure);
		BMenuConfigure.setBParentMenu(this);

		return BMenuConfigure;
	}

	public BMenuConfigure removeBMenuConfigure(BMenuConfigure BMenuConfigure) {
		getBMenuConfigures().remove(BMenuConfigure);
		BMenuConfigure.setBParentMenu(null);

		return BMenuConfigure;
	}*/

}