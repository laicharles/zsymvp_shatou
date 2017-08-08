package com.wisewater.function.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.bizConfig.pojo.BCustomerUser;
import com.wisewater.bizConfig.repository.BCustomerUserRepository;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.controller.CReminderTempForm;
import com.wisewater.function.pojo.CReminderTemp;
import com.wisewater.function.pojo.CTemplateId;
import com.wisewater.function.repository.CReminderTempRepository;
import com.wisewater.function.repository.CTemplateIdRepository;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.wechatpublic.message.out.TemplateIndustry;
import com.wisewater.wechatpublic.message.out.TemplateShortID;
import com.wisewater.wechatpublic.util.TemplateUtil;

@Service
public class CReminderTempServiceImpl  extends BaseService implements CReminderTempService{
	
	@Autowired
	private CReminderTempRepository cReminderTempRepository;
	
	@Autowired
	private BAccessTokenService accessTokenService;

	@Autowired
	private SDictionaryRepository dictionaryRepository;

	@Autowired
	private CTemplateIdRepository cTemplateIdRepository;
	
	@Autowired
	private BCustomerUserRepository bCustomerUserRepository;

	/**
	 * 查询列表
	 */
	@Override
	public JqgridListForm findAll(int pageNo, String sidx, String sord, String token) {

		int pageSize = loadConstant.getPageSize();
		if (pageNo < 1)
			pageNo = 1;

		// 默认不排序
		PageRequest page = new PageRequest(pageNo - 1, pageSize);

		// 设置排序方式
		if (sidx != null && sidx.length() > 0) {
			if (sord != null && sord.equalsIgnoreCase("desc")) {
				page = new PageRequest(pageNo - 1, pageSize, Direction.DESC, sidx);
			} else {
				page = new PageRequest(pageNo - 1, pageSize, Direction.ASC, sidx);
			}
		}

		Page<CReminderTemp> reminderTempPage = cReminderTempRepository.findAll(page, token);

		List<CReminderTempForm> pageList = new ArrayList<CReminderTempForm>();
		if (reminderTempPage != null && reminderTempPage.getContent() != null) {
			for (CReminderTemp txtMtrl : reminderTempPage.getContent()) {
				pageList.add(mapper.map(txtMtrl, CReminderTempForm.class));
			}
		}

		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList);
		jqgridListForm.setTotal(reminderTempPage.getTotalPages());
		jqgridListForm.setRecords(reminderTempPage.getTotalElements());

		return jqgridListForm;

	}

	/**
	 * 新增
	 */
	@Override
	public CReminderTemp save(CReminderTempForm reminderTempForm) {
		if (reminderTempForm == null)
			return null;
		CReminderTemp reminderTemp = mapper.map(reminderTempForm, CReminderTemp.class);

		SDictionary sendStusEnt = dictionaryRepository.findByLogicID("reviewStatus0");

		reminderTemp.setSendStatus(sendStusEnt);
		reminderTemp.setHeadColor("#FF0000");
		reminderTemp.setTextColor("#173177");

		CReminderTemp protoType = cReminderTempRepository.findPrototypeByToken(reminderTemp.getToken());
		if (protoType != null) {
			reminderTemp.setTemplateID(protoType.getTemplateID());
		}

		CReminderTemp temp = cReminderTempRepository.save(reminderTemp);
		if (temp != null) {
			return temp;
		}
		return null;
	}

	@Override
	public CReminderTempForm findById(String id) {
		if (id == null)
			return null;

		CReminderTemp temp = cReminderTempRepository.findById(id);
		if (temp == null)
			return null;

		CReminderTempForm form = mapper.map(temp, CReminderTempForm.class);

		return form;
	}

	/**
	 * 删除
	 */
	@Override
	public boolean deleteByIds(String ids) {
		if (ids == null)
			return false;

		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);

		List<CReminderTemp> tempList = cReminderTempRepository.findByIdIn(idList);
		if (tempList == null || tempList.isEmpty())
			return false;

		for (CReminderTemp temp : tempList) {
			temp.setIsLogicDel(1);
		}

		List<CReminderTemp> tempListRtn = cReminderTempRepository.save(tempList);
		if (tempListRtn == null || tempListRtn.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 发送预览
	 */
	@Override
	public boolean previewReminderTempById(String id,BCustomerUserForm user, String auditorOpenId ,String submitBinOpendId,String token) {

		boolean flag = false;
		
		if(user == null)
			return false;
		
		if(user.getBinOpendId() == null || user.getBinOpendId().equals("") ||
		   user.getHigherCustomerUserId() == null || user.getHigherCustomerUserId().equals("") ||
		   user.getCurrentPermissions() == null || user.getCurrentPermissions().equals(""))
			return false;
		
		//获取上级用户
		BCustomerUser higherBCustomerUser = bCustomerUserRepository.findById(user.getHigherCustomerUserId());
		if(higherBCustomerUser == null)  return false;
		
		if (id == null)
			return false;

		BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
		if (accessTokenForm == null) {
			return false;
		}

		String templateId = findTemplateId(accessTokenForm.getAccessToken(), token);
		if ("".equals(templateId)) {
			return false;
		}

		Client client;
		try {
			client = new Client(new URL(loadConstant.getWebServicePath()));
			Object[] results = client.invoke("sendReminderForOne", new Object[] {accessTokenForm.getAccessToken(), id, templateId, token, higherBCustomerUser.getBinOpendId() ,
		               user.getBinOpendId(),auditorOpenId,submitBinOpendId,user.getHigherCustomerUserId(),user.getCurrentPermissions(),loadConstant.getWebSitePath()});
			flag = (boolean) results[0];
			
			if(flag){
				updateSendDateTimeAndStatus(id,"1");
			}else{
				updateSendDateTimeAndStatus(id,"3");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 发送
	 */
	@Override
	public boolean sendReminderTempById(String id, String token) {

		boolean flag = false;

		if (id == null)
			return false;

		BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
		if (accessTokenForm == null) {
			return false;
		}

		String templateId = findTemplateId(accessTokenForm.getAccessToken(), token);
		if ("".equals(templateId)) {
			return false;
		}

		Client client;
		try {
			client = new Client(new URL(loadConstant.getWebServicePath()));
			Object[] results = client.invoke("sendReminder", new Object[] {accessTokenForm.getAccessToken(), id, templateId, token });
			flag = (boolean) results[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateSendDateTimeAndStatus(id, "2");
		return flag;
	}
	
	/**
	 * 发送所有绑定用户
	 */
	@Override
	public boolean sendReminderTempByIdForBindUser(String id, String token) {

		boolean flag = false;

		if (id == null)
			return false;

		BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
		if (accessTokenForm == null) {
			return false;
		}

		String templateId = findTemplateId(accessTokenForm.getAccessToken(), token);
		if ("".equals(templateId)) {
			return false;
		}

		Client client;
		try {
			client = new Client(new URL(loadConstant.getWebServicePath()));
			Object[] results = client.invoke("sendReminderForBindUser", new Object[] {accessTokenForm.getAccessToken(), id, templateId, token });
			flag = (boolean) results[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateSendDateTimeAndStatus(id, "2");
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
		if (StringUtils.isEmpty(cTemplateId.getReminderId())) {
			String tempCode = loadConstant.getReminderTempCode();
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
			cTemplateId.setReminderId(templateId);
			cTemplateId.setToken(token);
			cTemplateId = cTemplateIdRepository.save(cTemplateId);
		}

		return cTemplateId.getReminderId();

	}

	/**
	 * 修改发送时间和状态
	 * 
	 * @param temp
	 * @param flag
	 *            XingXingLvCha 2015年9月11日 下午1:21:03
	 */
	private void updateSendDateTimeAndStatus(String cStopWtrTempId, String flag) {
		CReminderTemp cReminderTemp = cReminderTempRepository.findOne(cStopWtrTempId);
		cReminderTemp.setSendDateTime(new Date());
		SDictionary sendStusEnt = dictionaryRepository.findByTypeCodeAndDicValue("reviewStatus", flag);
		cReminderTemp.setSendStatus(sendStusEnt);
		
		cReminderTempRepository.save(cReminderTemp);
	}

	/**
	 * 更新
	 */
	@Override
	public boolean update(CReminderTempForm reminderTempForm) {
		if (reminderTempForm == null)
			return false;
		CReminderTemp temp = cReminderTempRepository.findById(reminderTempForm.getId());
		if (temp == null) {
			return false;
		}
		
		SDictionary sendStusEnt = dictionaryRepository.findByTypeCodeAndDicValue("reviewStatus", "0");
		
		temp.setUrl(reminderTempForm.getUrl());
		temp.setFirst(reminderTempForm.getFirst());
		temp.setTel(reminderTempForm.getTel());
		temp.setServiceUnits(reminderTempForm.getServiceUnits());
		temp.setTempRemark(reminderTempForm.getTempRemark());
		temp.setSendStatus(sendStusEnt);
		if (cReminderTempRepository.save(temp) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean initTempRelated(boolean updateApp, String appID, String appSecret, String token,
			String accessToken) {
		boolean initFlg = true;
		CReminderTempForm reminderTempForm = new CReminderTempForm();
	
		reminderTempForm.setFirst("江西省萍乡市城区停水通知");
		reminderTempForm.setHeadColor("#FF0000");
	
		reminderTempForm.setTemplateCode("");
		// stopWtrForm.setTemplateID(templateID);
		reminderTempForm.setTemplateName("停水通知");
		reminderTempForm.setTempRemark("因为城区道路改造需要对自来水供水管路进行停水履行，给您带来不便敬请谅解。市政公告。");
		reminderTempForm.setTextColor("#173177");
		reminderTempForm.setToken(token);
		reminderTempForm.setUrl("www.baidu.com");
		reminderTempForm.setIsPrototype(1);
		CReminderTemp reminder = null;
		if (!updateApp) {
			reminder = save(reminderTempForm);
		} else {
			reminder = cReminderTempRepository.findPrototypeByToken(token);

			reminder.setTemplateID("");
			reminder = cReminderTempRepository.save(reminder);
		}

		if (reminder == null) {
			System.out.println("--------->待初始化的模板创建失败或是找不到");
			initFlg = false;
		}
		return initFlg;

	}



	
	
	
	
	
	
}
