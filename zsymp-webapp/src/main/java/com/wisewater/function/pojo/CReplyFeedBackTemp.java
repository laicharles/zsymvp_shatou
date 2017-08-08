package com.wisewater.function.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="c_replyfeedback_temp")
@NamedQuery(name="CReplyFeedBackTemp.findAll", query="SELECT c FROM CReplyFeedBackTemp c")
public class CReplyFeedBackTemp extends com.wisewater.base.BasePojo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;
	
	private int isLoginDel;
	
	private String remarks;
	
	private String templateCode;
	
	private String templateName;
	
	private String templateId;
	
	private String headColor;
	
	private String textColor;
	
	private String url;
	
	private String first;
	
	private String replyBy;
	
	private String replyTime;
	
	private String replyContent;
	
	private String replyRemark;
	
	private int isPrototype;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendDateTime;
	
	private String sendStatus;
	
	private String token;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsLoginDel() {
		return isLoginDel;
	}

	public void setIsLoginDel(int isLoginDel) {
		this.isLoginDel = isLoginDel;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getHeadColor() {
		return headColor;
	}

	public void setHeadColor(String headColor) {
		this.headColor = headColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getReplyBy() {
		return replyBy;
	}

	public void setReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyRemark() {
		return replyRemark;
	}

	public void setReplyRemark(String replyRemark) {
		this.replyRemark = replyRemark;
	}

	public int getIsPrototype() {
		return isPrototype;
	}

	public void setIsPrototype(int isPrototype) {
		this.isPrototype = isPrototype;
	}

	public Date getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(Date sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
