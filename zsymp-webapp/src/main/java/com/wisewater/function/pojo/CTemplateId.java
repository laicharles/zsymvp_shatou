package com.wisewater.function.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="c_template_id")
@NamedQuery(name="CTemplateId.findAll", query="SELECT c FROM CTemplateId c")
public class CTemplateId extends com.wisewater.base.BasePojo{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;
	
	private String token;
	
	private String stopWtrId;
	
	private String reminderId;
	
	private String noticeId;
	
	private String feedbackId;
	
	private String replyfeedbackId;
	
	private String replyleavewordsId;
	
	private String leavewordsReminderId;
	
	private String reviewId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getLeavewordsReminderId() {
		return leavewordsReminderId;
	}

	public void setLeavewordsReminderId(String leavewordsReminderId) {
		this.leavewordsReminderId = leavewordsReminderId;
	}


	public String getReplyleavewordsId() {
		return replyleavewordsId;
	}

	public void setReplyleavewordsId(String replyleavewordsId) {
		this.replyleavewordsId = replyleavewordsId;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStopWtrId() {
		return stopWtrId;
	}

	public void setStopWtrId(String stopWtrId) {
		this.stopWtrId = stopWtrId;
	}

	public String getReminderId() {
		return reminderId;
	}

	public void setReminderId(String reminderId) {
		this.reminderId = reminderId;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getReplyfeedbackId() {
		return replyfeedbackId;
	}

	public void setReplyfeedbackId(String replyfeedbackId) {
		this.replyfeedbackId = replyfeedbackId;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	
}
