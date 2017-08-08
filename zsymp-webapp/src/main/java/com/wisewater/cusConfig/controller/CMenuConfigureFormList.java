package com.wisewater.cusConfig.controller;

import java.util.List;

import com.wisewater.base.BaseForm;

public class CMenuConfigureFormList extends BaseForm{

	private static final long serialVersionUID = 1L;
	
	private List<CMenuConfigureForm> cMenuConfigureFormList;

	public List<CMenuConfigureForm> getcMenuConfigureFormList() {
		return cMenuConfigureFormList;
	}

	public void setcMenuConfigureFormList(
			List<CMenuConfigureForm> cMenuConfigureFormList) {
		this.cMenuConfigureFormList = cMenuConfigureFormList;
	}

}
