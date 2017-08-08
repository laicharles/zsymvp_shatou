package com.wisewater.function.service;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.function.pojo.CTemplateId;
import com.wisewater.function.repository.CTemplateIdRepository;
import com.wisewater.leavewords.controller.CLeaveWordsManageForm;
import com.wisewater.leavewords.pojo.CLeaveWordsManage;
import com.wisewater.leavewords.repository.CLeaveWordsRepository;
import com.wisewater.leavewords.service.CLeaveWordsManageService;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.wechatpublic.message.out.TemplateIndustry;
import com.wisewater.wechatpublic.message.out.TemplateShortID;
import com.wisewater.wechatpublic.util.TemplateUtil;

@Service
public class CReplyLeaveWordsTempServiceImpl extends BaseService implements CReplyLeaveWordsTempService{
	
	@Autowired
	private CTemplateIdRepository cTemplateIdRepository;
	
	@Autowired
	private CTemplateIdService templateIdService;
	
	@Autowired
	private BAccessTokenService accessTokenService;
	
	@Autowired
	private CLeaveWordsManageService cLeaveWordsManageService;
	
	@Autowired
	private CLeaveWordsRepository cLeaveWordsRepository;
	
	
//	@Override
//	public boolean sendToOne(String id, String token) {
//		boolean flag = false;
//		
//		if(id == null){
//			
//			return false;
//			
//		}
//		BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
//		if (accessTokenForm == null) {
//			return false;
//		}
//		String templateId = findTemplateId(accessTokenForm.getAccessToken(),token);
//		if("".equals(templateId)){
//			return false;
//		}
//		String url = loadConstant.getWebSitePath();
//		Client client;
//		try{
//			client = new Client(new URL(loadConstant.getWebServicePath()));
//			System.out.println(loadConstant.getWebServicePath());
//			Object[] results = client.invoke("replyFeedBackForOne", new Object[] {accessTokenForm.getAccessToken(), id, templateId, token ,url});
//			flag = (boolean) results[0];
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
	
	@Override
	public boolean sendToOne(String id, String token, String openId) {
		boolean flag = false;
		
		if(id == null){
			
			return false;
		}
		
		CLeaveWordsManageForm form = cLeaveWordsManageService.findById(id);
//		CLeaveWordsManageForm toSendForm = null;
//		String strtoSendForm = null;
//		Map map = new HashMap();
		String nickName = null;
		String createTime = null;
		String content = null;
		String replyContent = null;
		if(form != null){
			
//			toSendForm = new CLeaveWordsManageForm();
//			
//			toSendForm.setContent(form.getContent());
//			toSendForm.setOpenId(form.getOpenId());
//			toSendForm.setReplyBy(form.getReplyBy());
//			toSendForm.setReplyContent(form.getReplyContent());
//			toSendForm.setReplyTime(form.getReplyTime());
//			toSendForm.setCreateTime(form.getCreateTime());
//			toSendForm.setNickName(form.getNickName());
//			//将Java对象转换为json对象
//			JSONObject jsontoSendForm = JSONObject.fromObject(toSendForm);
//			//将json对象转换为字符串
//			strtoSendForm = jsontoSendForm.toString();
			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date dcreateTime = form.getCreateTime();
//			String createTime = sdf.format(dcreateTime);
//			map.put("nickName", form.getNickName());
//			map.put("createTime", createTime);
//			map.put("content", form.getContent());
//			map.put("replyContent", form.getReplyContent());
			
			
			nickName = form.getNickName();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dcreateTime = form.getCreateTime();
			createTime = sdf.format(dcreateTime);
			content = form.getContent();
			replyContent = form.getReplyContent();
			
		}
		
		
		BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
		if (accessTokenForm == null) {
			return false;
		}
		//TODO 回复消息模本ID
		String templateId = loadConstant.getReplyleavewordsTempCode();
		if("".equals(templateId)){
			return false;
		}
		String url = loadConstant.getWebSitePath();
		Client client;
		try{
			client = new Client(new URL(loadConstant.getWebServicePath()));
			System.out.println(loadConstant.getWebServicePath());
			Object[] results = client.invoke("replyLeaveWordsForOne", new Object[] {accessTokenForm.getAccessToken(), id, templateId, token ,openId ,nickName , createTime,content,replyContent});
			flag = (boolean) results[0];
			System.out.println("调用webservice成功与否："+flag);
			//改变发送状态
			if(flag){
				CLeaveWordsManage leaveword = cLeaveWordsRepository.findById(id);
				if(leaveword!=null){
					leaveword.setIsSend(1);
					leaveword = cLeaveWordsRepository.save(leaveword);
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
