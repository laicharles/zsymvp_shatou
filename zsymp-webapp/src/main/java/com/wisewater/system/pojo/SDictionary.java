package com.wisewater.system.pojo;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the s_dictionary database table.
 * 
 */
@Entity
@Table(name="s_dictionary")
@NamedQuery(name="SDictionary.findAll", query="SELECT s FROM SDictionary s")
public class SDictionary extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String dicName;

	private String dicValue;

	private int isLogicDel;

	private String logicID;

	private String remarks;

	private String typeCode;

	private String typeName;

	public SDictionary() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDicName() {
		return this.dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public String getDicValue() {
		return this.dicValue;
	}

	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getLogicID() {
		return this.logicID;
	}

	public void setLogicID(String logicID) {
		this.logicID = logicID;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}