package com.wisewater.fans.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.wisewater.system.pojo.SDictionary;


/**
 * The persistent class for the c_fan database table.
 * 
 */
@Entity
@Table(name="c_fan")
@NamedQuery(name="CFan.findAll", query="SELECT c FROM CFan c")
public class CFan extends com.wisewater.base.BasePojo  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	@NotFound(action=NotFoundAction.IGNORE)
	private String id;

	private String cityName;

	private String country;

	//private String gender;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gender", referencedColumnName="logicID")
	private SDictionary gender;

	private int isLogicDel;

	private int isSubscribe;

	private String nickName;

	@OneToMany(mappedBy="fan",fetch=FetchType.LAZY)
	private List<CFanUser> fanUsers;
	
	private String openID;

	private String provinceName;

	private String remarks;

	@Temporal(TemporalType.TIMESTAMP)
	private Date subscribeDate;

	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	private Date unsubscribeDate;

	public CFan() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getIsLogicDel() {
		return this.isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public int getIsSubscribe() {
		return this.isSubscribe;
	}

	public void setIsSubscribe(int isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<CFanUser> getFanUsers() {
        return fanUsers;
    }

    public void setFanUsers(List<CFanUser> fanUsers) {
        this.fanUsers = fanUsers;
    }

    public String getOpenID() {
		return this.openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getSubscribeDate() {
		return this.subscribeDate;
	}

	public void setSubscribeDate(Date subscribeDate) {
		this.subscribeDate = subscribeDate;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getUnsubscribeDate() {
		return this.unsubscribeDate;
	}

	public void setUnsubscribeDate(Date unsubscribeDate) {
		this.unsubscribeDate = unsubscribeDate;
	}

	public SDictionary getGender() {
		return gender;
	}

	public void setGender(SDictionary gender) {
		this.gender = gender;
	}




}