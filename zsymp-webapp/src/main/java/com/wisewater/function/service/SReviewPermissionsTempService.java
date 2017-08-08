package com.wisewater.function.service;

import java.util.List;

public interface SReviewPermissionsTempService {
	
	/**
	 * 审核通过的时候推送的消息
	 * @param token
	 * @param remark
	 * @return
	 */
	boolean sendReviewConsentTemp(String token, String remark,List<String> openIdList,String level);
	
	/**
	 * 审核不同过的时候推送的消息
	 * @param token
	 * @param remark
	 * @return
	 */
	boolean sendReviewDisagreeTemp(String token, String remark,List<String> openIdList,String level);
	
}
