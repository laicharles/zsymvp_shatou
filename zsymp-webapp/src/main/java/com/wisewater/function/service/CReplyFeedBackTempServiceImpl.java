package com.wisewater.function.service;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.feedback.controller.CFeedBackManageForm;
import com.wisewater.feedback.pojo.CFeedBackManage;
import com.wisewater.feedback.repository.CFeedBackRepository;
import com.wisewater.feedback.service.CFeedBackManageService;
import com.wisewater.function.pojo.CTemplateId;
import com.wisewater.function.repository.CTemplateIdRepository;
import com.wisewater.leavewords.pojo.CLeaveWordsManage;
import com.wisewater.wechatpublic.message.out.TemplateIndustry;
import com.wisewater.wechatpublic.message.out.TemplateShortID;
import com.wisewater.wechatpublic.util.TemplateUtil;

@Service
public class CReplyFeedBackTempServiceImpl extends BaseService implements CReplyFeedBackTempService{
	
	@Autowired
	private CTemplateIdRepository cTemplateIdRepository;
	
	@Autowired
	private CTemplateIdService templateIdService;
	
	@Autowired
	private BAccessTokenService accessTokenService;
	
	@Autowired
	private CFeedBackRepository cFeedBackRepository;
	
	@Autowired
	private CFeedBackManageService cFeedBackManageService;
	
	@Override
	public boolean sendToOne(String id, String token,String openId) {
		boolean flag = false;
		
		if(id == null){
			
			return false;
			
		}	
		CFeedBackManageForm cFeedBackManageForm = cFeedBackManageService.findById(id);
		String name = "";
		String content = "";
		String replyContent = "";
		String createTime = "";
		if (cFeedBackManageForm != null) {
			name = cFeedBackManageForm.getName();//获取姓名
			createTime = cFeedBackManageForm.getCreateTime();//获取创建时间
			content = cFeedBackManageForm.getContent();//内容
			replyContent = cFeedBackManageForm.getReplyContent();//回复内容
		}
		
		BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
		
		if (accessTokenForm == null) {
			return false;
		}
		String templateId = loadConstant.getReplyfeedbackTempCode();
		if("".equals(templateId)){
			return false;
		}
		Client client;
		try{
			client = new Client(new URL(loadConstant.getWebServicePath()));
			System.out.println(loadConstant.getWebServicePath());
			Object[] results = client.invoke("replyFeedBackForOne", new Object[] {accessTokenForm.getAccessToken(),id,templateId,token,openId,name,createTime,content,replyContent});
			flag = (boolean) results[0];
			System.out.println(flag);
			if(flag){
				CFeedBackManage feedback = cFeedBackRepository.findById(id);
				if(feedback != null){
					feedback.setIsSend("1");
					feedback = cFeedBackRepository.save(feedback);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
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
		if(cTemplateId == null || "".equals(cTemplateId.getReplyfeedbackId())){
			cTemplateId = new CTemplateId();
		}
		if(StringUtils.isEmpty(cTemplateId.getReplyfeedbackId())){
			String tempCode = "OPENTM203574543";
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
			cTemplateId.setReplyfeedbackId(templateId);
			cTemplateId.setToken(token);
			cTemplateId = templateIdService.updateTemplateId(cTemplateId);
			return cTemplateId.getReplyfeedbackId();
		}
		
		return cTemplateId.getReplyfeedbackId();
	}
}
