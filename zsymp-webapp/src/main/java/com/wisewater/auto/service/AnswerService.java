package com.wisewater.auto.service;

import com.wisewater.auto.controller.AnswerForm;

public interface AnswerService {

	/**
	 * 被动回复
	 * @param content
	 * @param fromUserName
	 * @param token
	 * @return
	 * XingXingLvCha
	 * 2015年4月9日 下午8:04:21
	 */
	public AnswerForm answerMessage(String content,String fromUserName,String token,String createTime);
}
