package com.wisewater.function.pojo;

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
@Table(name="c_notice_temp")
@NamedQuery(name="CNoticeTemp.findAll", query="SELECT c FROM CNoticeTemp c")
public class CNoticeTemp extends com.wisewater.base.BasePojo {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;
	
	private String first;
	
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date sendDateTime;
	
	private String noticeType;
	
	private String remark;
	
	private String noticeContent;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sendStatus", referencedColumnName="logicID")
	private SDictionary sendStatus;
	
	private int isLogicDel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(Date sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	
	public SDictionary getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(SDictionary sendStatus) {
		this.sendStatus = sendStatus;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}
	
	
}
