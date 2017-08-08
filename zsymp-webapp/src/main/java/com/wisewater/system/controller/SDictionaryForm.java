package com.wisewater.system.controller;

import com.wisewater.base.BaseForm;

public class SDictionaryForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String dicName;

	private String dicValue;

	private int isLogicDel;

	private String logicID;

	private String remarks;

	private String typeCode;

	private String typeName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public String getDicValue() {
		return dicValue;
	}

	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getLogicID() {
		return logicID;
	}

	public void setLogicID(String logicID) {
		this.logicID = logicID;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}