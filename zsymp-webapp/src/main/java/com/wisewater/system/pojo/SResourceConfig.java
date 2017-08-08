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
@Table(name="s_resources_config")
@NamedQuery(name="SResourceConfig.findAll", query="SELECT s FROM SResourceConfig s ")
public class SResourceConfig extends BasePojo {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String remarks;

	private String resName;
	
	private String resID;
	
	private String token;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResID() {
		return resID;
	}

	public void setResID(String resID) {
		this.resID = resID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}