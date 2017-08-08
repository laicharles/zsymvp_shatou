package com.wisewater.cusConfig.controller;

import com.wisewater.base.BaseForm;

public class CMenuConfigureForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private int isDisabled;

	private String name;

	private String parentName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(int isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}