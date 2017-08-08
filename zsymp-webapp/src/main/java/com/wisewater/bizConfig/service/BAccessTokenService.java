package com.wisewater.bizConfig.service;

import com.wisewater.bizConfig.controller.BAccessTokenForm;

 
public interface BAccessTokenService {


	/**
	 * 
	 * @param token
	 * @return
	 * gawen.chen
	 * 2015年4月10日下午6:55:21
	 * 描述：检查并更新accessToken通过智水云的token
	 */
	BAccessTokenForm checkUpdateAccessTokenByToken(String token);

}