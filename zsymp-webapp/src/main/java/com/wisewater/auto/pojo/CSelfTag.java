package com.wisewater.auto.pojo;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;


/**
 * The persistent class for the c_self_tag database table.
 * 
 */
@Entity
@Table(name="c_self_tag")
@NamedQuery(name="CSelfTag.findAll", query="SELECT c FROM CSelfTag c")
public class CSelfTag extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private int isLogicDel;

	private String name;

	private String remarks;

	private String token;

	//bi-directional many-to-many association to CAuto
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="c_auto_and_tag"
			, joinColumns={
				@JoinColumn(name="tagID")
				}
			, inverseJoinColumns={
				@JoinColumn(name="autoID")
				}
			)
	private List<CAuto> CAutos;

	public CSelfTag() {
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

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<CAuto> getCAutos() {
		return this.CAutos;
	}

	public void setCAutos(List<CAuto> CAutos) {
		this.CAutos = CAutos;
	}

}