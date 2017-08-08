package com.wisewater.auto.controller;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wisewater.base.BaseForm;
import com.wisewater.system.pojo.SDictionary;

public class CAutoForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	    //自动id
		private String id;
		
		//自动创建时间
		private long createTime;
		
		//自动回复问题
		private String question;
		
		//自动文本
		private String textContent;
		
		//自动回复类型
		private String answerType;
		
		//图文简介
		private String description;
		
		//图文外链地址
		private String pageUrl;
		
		//图文图片名称
		private MultipartFile picName;
		
		//图文图片地址
		private String picUrl;
		
		//图文标题
		private String title;
		
		//文章作者
		private String author;
		
		//文章原文链接
		private String origUrl;
		
		//文章内容
		private String pageContent;
		
		//关键字
		private List<CAutoKeywordForm> CAutoKeywords;
		private String cautoKeywordStr;
		
		//标签
		private List<CSelfTagForm> CSelfTags;
		private String cselfTagStr;
		
		//数据字典 回复类型
		private SDictionary SDictionary;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getTextContent() {
			return textContent;
		}

		public void setTextContent(String textContent) {
			this.textContent = textContent;
		}

		public String getAnswerType() {
			return answerType;
		}

		public void setAnswerType(String answerType) {
			this.answerType = answerType;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getPageUrl() {
			return pageUrl;
		}

		public void setPageUrl(String pageUrl) {
			this.pageUrl = pageUrl;
		}

		public MultipartFile getPicName() {
			return picName;
		}

		public void setPicName(MultipartFile picName) {
			this.picName = picName;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getOrigUrl() {
			return origUrl;
		}

		public void setOrigUrl(String origUrl) {
			this.origUrl = origUrl;
		}

		public String getPageContent() {
			return pageContent;
		}

		public void setPageContent(String pageContent) {
			this.pageContent = pageContent;
		}

		public List<CAutoKeywordForm> getCAutoKeywords() {
			return CAutoKeywords;
		}

		public void setCAutoKeywords(List<CAutoKeywordForm> cAutoKeywords) {
			CAutoKeywords = cAutoKeywords;
		}

		public String getCautoKeywordStr() {
			return cautoKeywordStr;
		}

		public void setCautoKeywordStr(String cautoKeywordStr) {
			this.cautoKeywordStr = cautoKeywordStr;
		}

		public List<CSelfTagForm> getCSelfTags() {
			return CSelfTags;
		}

		public void setCSelfTags(List<CSelfTagForm> cSelfTags) {
			CSelfTags = cSelfTags;
		}

		public String getCselfTagStr() {
			return cselfTagStr;
		}

		public void setCselfTagStr(String cselfTagStr) {
			this.cselfTagStr = cselfTagStr;
		}

		public SDictionary getSDictionary() {
			return SDictionary;
		}

		public void setSDictionary(SDictionary sDictionary) {
			SDictionary = sDictionary;
		}

}