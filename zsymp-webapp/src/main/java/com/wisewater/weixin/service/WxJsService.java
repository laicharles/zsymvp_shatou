package com.wisewater.weixin.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.cusConfig.controller.CMpForm;
import com.wisewater.cusConfig.pojo.CMp;
import com.wisewater.cusConfig.service.CMpService;
import com.wisewater.util.tools.WxJsUtil;
import com.wisewater.weixin.pojo.WxJsBean;




@Transactional
@Service
public class WxJsService extends BaseService{
	
	@Autowired
	private BAccessTokenService bAccessTokenService;
	
	@Autowired
	private CMpService cMpService;
	
	public WxJsBean findWxJsBean(String token,String url){
		BAccessTokenForm accessToken = bAccessTokenService.checkUpdateAccessTokenByToken(token);
				
		CMpForm mp = cMpService.findByToken(token);
		
		Map<String, String> map = WxJsUtil.sign(
				accessToken.getJsapiTicket(),  loadConstant.getWebSitePath() + url);
		
		/*System.out.println("url:"+map.get("url"));
		System.out.println("jsapi_ticket:"+map.get("jsapi_ticket"));
		System.out.println("nonceStr:"+map.get("nonceStr"));
		System.out.println("timestamp:"+map.get("timestamp"));
		System.out.println("signature:"+map.get("signature"));
		System.out.println("appId:"+mp.getAppID());
		System.out.println("accessToken:"+accessToken.getAccessToken());*/
		
		WxJsBean wxJsBean = new WxJsBean();
		wxJsBean.setAccessToken(accessToken.getAccessToken());
		wxJsBean.setAppId(mp.getAppID());
		
		wxJsBean.setSignature(map.get("signature"));
		wxJsBean.setAccessToken(accessToken.getAccessToken());
		
		
		wxJsBean.setNonceStr(map.get("nonceStr"));
		wxJsBean.setTimestamp(map.get("timestamp"));
		wxJsBean.setToken(token);
		return wxJsBean;
	}
}
