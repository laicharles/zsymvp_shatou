package com.wisewater.feedback.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.wisewater.fans.pojo.CFanUser;
import com.wisewater.system.pojo.SDictionary;

@Entity
@Table(name="c_feedbackmanage")
@NamedQuery(name="CFeedBackManage.findAll", query="SELECT c FROM CFeedBackManage c")
public class CFeedBackManage extends com.wisewater.base.BasePojo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	@NotFound(action=NotFoundAction.IGNORE)
	private String id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="feedbackType", referencedColumnName="logicID")
	private SDictionary feedbackType;
	
	private String name;
	
	private String tel;
	
	private String address;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customerType", referencedColumnName="logicID")
	private SDictionary customerType;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fanUserID")
	private CFanUser fanUser;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="feedbackOption", referencedColumnName="logicID")
	private SDictionary feedbackOption;
	
	
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
	
	private String title;
	
	private String isSend;

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CFeedBackManage() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SDictionary getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(SDictionary feedbackType) {
		this.feedbackType = feedbackType;
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

	public SDictionary getCustomerType() {
		return customerType;
	}

	public void setCustomerType(SDictionary customerType) {
		this.customerType = customerType;
	}

	public CFanUser getFanUser() {
		return fanUser;
	}

	public void setFanUser(CFanUser fanUser) {
		this.fanUser = fanUser;
	}

	public SDictionary getFeedbackOption() {
		return feedbackOption;
	}

	public void setFeedbackOption(SDictionary feedbackOption) {
		this.feedbackOption = feedbackOption;
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
}
