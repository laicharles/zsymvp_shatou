package com.wisewater.auto.controller;
/**
 * 
 * 描述：自动回复列表
 * @author AlexFung
 * 2016年8月17日 下午4:28:02
 */
public class AnswerContext {

	private int index;

	private String question;

	// 1：自动 2:客户知识库
	private int type;

	private Object classT;

	private Object classM;

	private String objectID;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getClassT() {
		return classT;
	}

	public void setClassT(Object classT) {
		this.classT = classT;
	}

	public Object getClassM() {
		return classM;
	}

	public void setClassM(Object classM) {
		this.classM = classM;
	}

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

}
