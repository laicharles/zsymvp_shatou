package com.wisewater.function.service;

import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctc.wstx.util.StringUtil;
import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.function.pojo.CTemplateId;
import com.wisewater.function.repository.CTemplateIdRepository;
import com.wisewater.wechatpublic.message.out.TemplateIndustry;
import com.wisewater.wechatpublic.message.out.TemplateShortID;
import com.wisewater.wechatpublic.util.TemplateUtil;
@Service
public class CFeedBackTempServiceImpl extends BaseService implements CFeedBackTempService{

	@Autowired
	private CTemplateIdRepository cTemplateIdRepository;
	
	@Autowired
	private CTemplateIdService templateIdService;
	
	@Autowired
	private BAccessTokenService accessTokenService;
	
	
	/**
	 * 创建模板id
	 * @author TalonYeung
	 * 2016年12月1日 上午10:08:03
	 * @param accessToken
	 * @param token
	 * @return
	 */
	public String findTemplateId(String accessToken, String token) {
		CTemplateId cTemplateId = cTemplateIdRepository.findByToken(token);
		if(cTemplateId == null || "".equals(cTemplateId.getFeedbackId())){
			cTemplateId = new CTemplateId();
		}
		if(StringUtils.isEmpty(cTemplateId.getFeedbackId())){
			String tempCode = "OPENTM400926115";
			TemplateShortID shortId = new TemplateShortID();
			shortId.setTemplate_id_short(tempCode);
			
			String templateId = TemplateUtil.getTemplateID(shortId, accessToken);
			if("".equals(templateId)){
				TemplateIndustry templateIndustry = new TemplateIndustry();
				templateIndustry.setIndustry_id1(loadConstant.getIndustry1());
				templateIndustry.setIndustry_id2(loadConstant.getIndustry2());
				boolean setindustryflag = TemplateUtil.setIndustry(templateIndustry, accessToken);
				if (setindustryflag) {
					templateId = TemplateUtil.getTemplateID(shortId, accessToken);
				} else {
					System.out.println("--------->模板相关设置：公众号行业设置失败");
				}
			}
			cTemplateId.setFeedbackId(templateId);
			cTemplateId.setToken(token);
			cTemplateId = templateIdService.updateTemplateId(cTemplateId);
			return cTemplateId.getFeedbackId();
		}
		
		return cTemplateId.getFeedbackId();
	}



	@Override
	public boolean sendToFeedBackPerson(String id, String token) {
		
		boolean flag = false;
		
		if(id == null){
			
			return false;
			
		}
		
		BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
		if (accessTokenForm == null) {
			return false;
		}
		
		String templateId = findTemplateId(accessTokenForm.getAccessToken(),token);
		if("".equals(templateId)){
			return false;
		}
		
		Client client;
		try{
			client = new Client(new URL(loadConstant.getWebServicePath()));
			System.out.println(loadConstant.getWebServicePath());
			Object[] results = client.invoke("sendFeedBackForFeedBackPerson", new Object[] {accessTokenForm.getAccessToken(), id, templateId, token });
			flag = (boolean) results[0];
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return false;
	}



	
	
	
	
}
