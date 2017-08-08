package com.wisewater.function.controller;
import com.wisewater.base.BaseForm;
import com.wisewater.system.pojo.SDictionary;

public class CNoticeTempForm extends BaseForm {

	private static final long serialVersionUID = 1L;

    private String id;
    
    private String first;
	
	private String token;
	
    private String sendDateTime;
	
	private String noticeType;
	
	private String remark;
	
	private String noticeContent;
	
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

	public String getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(String sendDateTime) {
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
