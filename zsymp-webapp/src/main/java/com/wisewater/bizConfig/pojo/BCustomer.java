package com.wisewater.bizConfig.pojo;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;

import com.wisewater.cusConfig.pojo.CMp;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the b_cusotmer database table.
 * 
 */
@Entity
@Table(name="b_customer")
@NamedQuery(name="BCustomer.findAll", query="SELECT b FROM BCustomer b")
public class BCustomer extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	private String addr;

	private String companyName;

	@Max(1)
	@Min(0)
	private int isDisabled;

	@Max(1)
	@Min(0)
	private int isLogicDel;

	@Temporal(TemporalType.DATE)
	private Date registerDate;

	private String remarks;

	private String tel;

	//bi-directional many-to-one association to BCusotmerUser
	@OneToMany(mappedBy="BCusotmer",fetch=FetchType.LAZY)
	private List<BCustomerUser> BCusotmerUsers;

	@OneToMany(mappedBy="BCusotmer",fetch=FetchType.LAZY)
	private List<CMp> cmps;
	
	public BCustomer() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<BCustomerUser> getBCusotmerUsers() {
		return this.BCusotmerUsers;
	}

	public void setBCusotmerUsers(List<BCustomerUser> BCusotmerUsers) {
		this.BCusotmerUsers = BCusotmerUsers;
	}

	public BCustomerUser addBCusotmerUser(BCustomerUser BCusotmerUser) {
		getBCusotmerUsers().add(BCusotmerUser);
		BCusotmerUser.setBCusotmer(this);

		return BCusotmerUser;
	}

	public BCustomerUser removeBCusotmerUser(BCustomerUser BCusotmerUser) {
		getBCusotmerUsers().remove(BCusotmerUser);
		BCusotmerUser.setBCusotmer(null);

		return BCusotmerUser;
	}

	public List<CMp> getCmps() {
		return cmps;
	}

	public void setCmps(List<CMp> cmps) {
		this.cmps = cmps;
	}

}