package com.wisewater.system.pojo;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import com.wisewater.base.BasePojo;
import com.wisewater.bizConfig.pojo.BCustomerUser;

import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="s_role")
public class SRole extends BasePojo {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String remarks;
	
	@Max(1)
	@Min(0)
	private int isLogicDel;


	private String roleCode;

	private String roleName;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="roles")
	private List<SUser> users;

	//bi-directional many-to-many association to Resource
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="s_role_resources"
			, joinColumns={
				@JoinColumn(name="roleID")
				}
			, inverseJoinColumns={
				@JoinColumn(name="resID")
				}
			)
	@Where(clause="isLogicDel=0")
	private List<SResource> resources;

	//bi-directional many-to-one association to BCusotmerUser
	@OneToMany(mappedBy="role")
	@Where(clause="isLogicDel=0")
	private List<BCustomerUser> bCusotmerUsers;
	
	public SRole() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<SUser> getUsers() {
		return this.users;
	}

	public void setUsers(List<SUser> users) {
		this.users = users;
	}

	public List<SResource> getResources() {
		return this.resources;
	}

	public void setResources(List<SResource> resources) {
		this.resources = resources;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public List<BCustomerUser> getbCusotmerUsers() {
		return bCusotmerUsers;
	}

	public void setbCusotmerUsers(List<BCustomerUser> bCusotmerUsers) {
		this.bCusotmerUsers = bCusotmerUsers;
	}

}