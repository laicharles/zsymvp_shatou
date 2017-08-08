package com.wisewater.weixin.service;

import org.springframework.web.multipart.MultipartFile;

import com.wisewater.weixin.controller.CClickTwForm;

public interface CClickTwService {

	/**
	 * 
	 * @param token
	 * @return gawen.chen 2015年4月20日下午7:13:46 描述：
	 */
	CClickTwForm findByToken(String token,String twType);

	/**
	 * 
	 * @param clickTwForm
	 * @param file
	 * @param token
	 * @return gawen.chen 2015年4月20日下午7:13:49 描述：
	 */
	boolean saveClickTw(CClickTwForm clickTwForm, MultipartFile file, String token);
}