package com.wisewater.auto.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the c_auto_keyword database table.
 * 
 */
@Entity
@Table(name="c_auto_keyword")
@NamedQuery(name="CAutoKeyword.findAll", query="SELECT c FROM CAutoKeyword c")
public class CAutoKeyword extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private int isLogicDel;

	private String keyName;

	private String remarks;

	private String token;

	//bi-directional many-to-one association to CAuto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="autoID")
	private CAuto CAuto;

	public CAutoKeyword() {
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

	public String getKeyName() {
		return this.keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
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

	public CAuto getCAuto() {
		return this.CAuto;
	}

	public void setCAuto(CAuto CAuto) {
		this.CAuto = CAuto;
	}

}