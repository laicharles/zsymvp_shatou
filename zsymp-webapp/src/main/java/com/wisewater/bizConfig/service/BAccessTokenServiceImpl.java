package com.wisewater.bizConfig.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.pojo.BAccessToken;
import com.wisewater.bizConfig.repository.BAccessTokenRepository;
import com.wisewater.cusConfig.controller.CMpForm;
import com.wisewater.cusConfig.service.CMpService;
import com.wisewater.wechatpublic.pojo.Token;
import com.wisewater.wechatpublic.util.CommonUtil;
import com.wisewater.wechatpublic.util.StringUtil;

@Service
public class BAccessTokenServiceImpl extends BaseService implements BAccessTokenService {

	@Autowired
	private BAccessTokenRepository accessTokenRepository;

	@Autowired
	private CMpService CMpService;
	
	private static final Logger logger = LoggerFactory.getLogger(BAccessTokenServiceImpl.class);
	
	@Override
	public BAccessTokenForm checkUpdateAccessTokenByToken(String token) {
		BAccessTokenForm bAccessTokenForm = new BAccessTokenForm();
		CMpForm mpForm = CMpService.findByToken(token);
		if (mpForm == null || StringUtil.isEmpty(mpForm.getAppID()) || StringUtil.isEmpty(mpForm.getAppSecret())) {
			logger.info(token+"-->Mp配置错误!当前accessToken:"+bAccessTokenForm.getAccessToken());
			return bAccessTokenForm;
		}
		Date now = new Date();
		BAccessToken bAccessToken = accessTokenRepository.findByToken(token);
		System.out.println(bAccessToken.getAccessToken());
		// 过时执行更新accessToken
		if (bAccessToken == null || StringUtil.isEmpty(bAccessToken.getAccessToken())
				|| bAccessToken.getExpiredDateTime().getTime() <= now.getTime()) {
			Token newAccessToken = CommonUtil.getToken(mpForm.getAppID(), mpForm.getAppSecret());
			if (newAccessToken == null) {
				return bAccessTokenForm;
			}
			// 数据库更新accessToken
			bAccessToken.setAccessToken(newAccessToken.getAccessToken());
			bAccessToken.setApiTicket(CommonUtil.getAPITicket(newAccessToken.getAccessToken()));
			bAccessToken.setJsapiTicket(CommonUtil.getJSAPITicket(newAccessToken.getAccessToken()));
			bAccessToken.setExpiredDateTime(new Date(now.getTime()+300000));
			bAccessToken = accessTokenRepository.saveAndFlush(bAccessToken);
			logger.info(token+"-->更新成功!当前accessToken:"+bAccessToken.getAccessToken()+";next update Time--->"+new Date(now.getTime()+300000));
		}
		bAccessTokenForm = mapper.map(bAccessToken, BAccessTokenForm.class);
		return bAccessTokenForm;
	}

}