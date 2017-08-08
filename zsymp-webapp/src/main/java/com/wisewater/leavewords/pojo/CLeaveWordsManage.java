package com.wisewater.leavewords.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.wisewater.system.pojo.SDictionary;

@Entity
@Table(name="c_leavewordsmanage")
@NamedQuery(name="CLeaveWordsManage.findAll", query="SELECT c FROM CLeaveWordsManage c")
public class CLeaveWordsManage extends com.wisewater.base.BasePojo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="leavewords", referencedColumnName="logicID")
	private SDictionary leavewords;
	
	private String name;
	
	private String nickName;
	
	private String cityName;
	
	private String tel;
	
	private String address;
	
	private String content;
	
	private String img;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	private int isDelete;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteTime;
	
	private String openId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="isReply", referencedColumnName="logicID")
	private SDictionary isReply;
	
	
	private String replyBy;
	
	private String replyContent;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyTime;
	
	private String token;
	/**
	 * isSend: 0:未发送  1：已发送
	 */
	private int isSend;
	
	
	public CLeaveWordsManage(){}
	public CLeaveWordsManage(String id, SDictionary leavewords, String name,
			String nickName, String cityName, String tel, String address,
			String content, String img, Date createTime, int isDelete,
			Date deleteTime, String openId, SDictionary isReply,
			String replyBy, String replyContent, Date replyTime, String token, int isSend ) {
		super();
		this.id = id;
		this.leavewords = leavewords;
		this.name = name;
		this.nickName = nickName;
		this.cityName = cityName;
		this.tel = tel;
		this.address = address;
		this.content = content;
		this.img = img;
		this.createTime = createTime;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
		this.openId = openId;
		this.isReply = isReply;
		this.replyBy = replyBy;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
		this.token = token;
		this.isSend = isSend;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SDictionary getLeavewords() {
		return leavewords;
	}

	public void setLeavewords(SDictionary leavewords) {
		this.leavewords = leavewords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public SDictionary getIsReply() {
		return isReply;
	}

	public void setIsReply(SDictionary isReply) {
		this.isReply = isReply;
	}

	public String getReplyBy() {
		return replyBy;
	}

	public void setReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getIsSend() {
		return isSend;
	}
	
	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}
}
