package com.wisewater.function.service;

import java.net.URL;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.function.pojo.CTemplateId;
import com.wisewater.function.repository.CTemplateIdRepository;
import com.wisewater.wechatpublic.message.out.TemplateIndustry;
import com.wisewater.wechatpublic.message.out.TemplateShortID;
import com.wisewater.wechatpublic.util.TemplateUtil;

@Service
public class SReviewPermissionsTempServiceImpl extends BaseService implements SReviewPermissionsTempService{

	@Autowired
	private BAccessTokenService  accessTokenService;
	
	@Autowired
	private CTemplateIdRepository cTemplateIdRepository;
	
	
	@Override
	public boolean sendReviewConsentTemp(String token, String remark,List<String> openIdList,String level) {
		boolean flag = false;
		
		BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
		if (accessTokenForm == null) {
			return false;
		}

		String templateId = findTemplateId(accessTokenForm.getAccessToken(), token);
		if ("".equals(templateId)) {
			return false;
		}
		
		if(openIdList.size()<=0){
			return false;
		}
		//String token,String accessToken,List<String> openIdList, String templateId, String remark
		Client client;
		try {
			JSONArray obj = JSONArray.fromObject(openIdList);
			client = new Client(new URL(loadConstant.getWebServicePath()));
			Object[] results = client.invoke("sendReviewConsentTemp", new Object[] {token , accessTokenForm.getAccessToken(), obj, templateId, remark ,level});
			flag = (boolean) results[0];
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public boolean sendReviewDisagreeTemp(String token, String remark,List<String> openIdList,String level) {

		boolean flag = false;
		
		BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
		if (accessTokenForm == null) {
			return false;
		}

		String templateId = findTemplateId(accessTokenForm.getAccessToken(), token);
		if ("".equals(templateId)) {
			return false;
		}
		
		if(openIdList.size()<=0){
			return false;
		}
		//String token,String accessToken,List<String> openIdList, String templateId, String remark
		Client client;
		try {
			JSONArray obj = JSONArray.fromObject(openIdList);
			client = new Client(new URL(loadConstant.getWebServicePath()));
			Object[] results = client.invoke("sendReviewDisagreeTemp", new Object[] {token , accessTokenForm.getAccessToken(), obj, templateId, remark ,level});
			flag = (boolean) results[0];
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	
	/**
	 * 获取模版ID，如果获取不成功则从新设置行业再去获取
	 * 
	 * @param tempCode
	 * @param accessToken
	 * @return XingXingLvCha 2015年9月8日 下午5:47:33
	 */
	public String findTemplateId(String accessToken, String token) {
		CTemplateId cTemplateId = cTemplateIdRepository.findByToken(token);
		if (cTemplateId == null) {
			cTemplateId = new CTemplateId();

		}
		if (StringUtils.isEmpty(cTemplateId.getReviewId())) {
			String tempCode = loadConstant.getReviewTempCode();
			TemplateShortID shortId = new TemplateShortID();
			shortId.setTemplate_id_short(tempCode);
			String templateId = TemplateUtil.getTemplateID(shortId, accessToken);
			if ("".equals(templateId)) {
				// 设置行业；
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
			cTemplateId.setReviewId(templateId);
			cTemplateId.setToken(token);
			cTemplateIdRepository.save(cTemplateId);
		}
		return cTemplateId.getReviewId();

	}
}
