package com.wisewater.bizConfig.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the b_menu_configure database table.
 * 
 */
@Entity
@Table(name="c_menu_configure")
@NamedQuery(name="BMenuConfigure.findAll", query="SELECT b FROM BMenuConfigure b")
public class BMenuConfigure extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private int isDisabled;

	private int isLogicDel;

	private String keyType;

	private String name;

	private String remarks;

	private int sequence;

	private String type;

	private String url;

	//bi-directional many-to-one association to BParentMenu
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parentMenuID")
	private BParentMenu BParentMenu;

	public BMenuConfigure() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsDisabled() {
		return this.isDisabled;
	}

	public void setIsDisabled(int isDisabled) {
		this.isDisabled = isDisabled;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getKeyType() {
		return this.keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
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

	public int getSequence() {
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BParentMenu getBParentMenu() {
		return this.BParentMenu;
	}

	public void setBParentMenu(BParentMenu BParentMenu) {
		this.BParentMenu = BParentMenu;
	}

}