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
import com.wisewater.function.controller.CStopWtrTempForm;
import com.wisewater.function.pojo.CStopWtrTemp;
import com.wisewater.function.pojo.CTemplateId;
import com.wisewater.function.repository.CStopWtrTempRepository;
import com.wisewater.function.repository.CTemplateIdRepository;
import com.wisewater.review.repository.SReviewPermissionsRepository;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.wechatpublic.message.out.TemplateIndustry;
import com.wisewater.wechatpublic.message.out.TemplateShortID;
import com.wisewater.wechatpublic.util.TemplateUtil;

@Service
public class CStopWtrTempServiceImpl extends BaseService implements CStopWtrTempService {

	@Autowired
	private CStopWtrTempRepository cstopwtrtempRepository;

	@Autowired
	private BAccessTokenService accessTokenService;

	@Autowired
	private SDictionaryRepository dictionaryRepository;

	@Autowired
	private CTemplateIdRepository cTemplateIdRepository;

	@Autowired
	private SReviewPermissionsRepository reviewRepository; 
	
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

		Page<CStopWtrTemp> stopWtrTempPage = cstopwtrtempRepository.findAll(page, token);

		List<CStopWtrTempForm> pageList = new ArrayList<CStopWtrTempForm>();
		if (stopWtrTempPage != null && stopWtrTempPage.getContent() != null) {
			for (CStopWtrTemp txtMtrl : stopWtrTempPage.getContent()) {
				pageList.add(mapper.map(txtMtrl, CStopWtrTempForm.class));
			}
		}

		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList);
		jqgridListForm.setTotal(stopWtrTempPage.getTotalPages());
		jqgridListForm.setRecords(stopWtrTempPage.getTotalElements());

		return jqgridListForm;

	}

	/**
	 * 新增
	 */
	@Override
	public CStopWtrTemp save(CStopWtrTempForm stopWtrTempForm) {
		if (stopWtrTempForm == null)
			return null;
		CStopWtrTemp stopWtrTemp = mapper.map(stopWtrTempForm, CStopWtrTemp.class);

		SDictionary sendStusEnt = dictionaryRepository.findByTypeCodeAndDicValue("reviewStatus", "0");

		stopWtrTemp.setSendStatus(sendStusEnt);

		CStopWtrTemp protoType = cstopwtrtempRepository.findPrototypeByToken(stopWtrTemp.getToken());
		if (protoType != null) {
			stopWtrTemp.setTemplateID(protoType.getTemplateID());
		}

		CStopWtrTemp temp = cstopwtrtempRepository.save(stopWtrTemp);
		if (temp != null) {
			return temp;
		}
		return null;
	}

	@Override
	public CStopWtrTempForm findById(String id) {
		if (id == null)
			return null;

		CStopWtrTemp temp = cstopwtrtempRepository.findById(id);
		if (temp == null)
			return null;

		CStopWtrTempForm form = mapper.map(temp, CStopWtrTempForm.class);

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

		List<CStopWtrTemp> tempList = cstopwtrtempRepository.findByIdIn(idList);
		if (tempList == null || tempList.isEmpty())
			return false;

		for (CStopWtrTemp temp : tempList) {
			temp.setIsLogicDel(1);
		}

		List<CStopWtrTemp> tempListRtn = cstopwtrtempRepository.save(tempList);
		if (tempListRtn == null || tempListRtn.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 发送预览
	 * BCustomerUserForm user  当前用户
	 * String customerOpenId,  当前用户绑定OpenId
	 * user 用户  包括上级id  当前用户openId 当前权限id
	 * auditorOpenId  审核人的openId  [如果是第一级提交，审核人则为空]
	 * submitBinOpendId  提交人
	 */
	@Override
	public boolean previewStopWtrTempById(String id,BCustomerUserForm user, String auditorOpenId,String submitBinOpendId,String token) {

		boolean flag = false;
		
		if(user == null)
			return false;
		
		if(user.getBinOpendId() == null || user.getBinOpendId().equals("") ||
		   user.getHigherCustomerUserId() == null || user.getHigherCustomerUserId().equals("") ||
		   user.getCurrentPermissions() == null || user.getCurrentPermissions().equals(""))
			return false;
		
		//获取上级用户
		BCustomerUser higherBCustomerUser = bCustomerUserRepository.findById(user.getHigherCustomerUserId());
		//CReviewPermissions review = reviewRepository.findById(user.getHigherPermissions()); 
		
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
			Object[] results = client.invoke("sendStopWtrForOne",
					new Object[] { accessTokenForm.getAccessToken(), id, templateId, token, higherBCustomerUser.getBinOpendId() ,
					               user.getBinOpendId(),auditorOpenId,submitBinOpendId,user.getHigherCustomerUserId(),user.getCurrentPermissions(),loadConstant.getWebSitePath() });
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
	public boolean sendStopWtrTempById(String id, String token) {

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
			Object[] results = client.invoke("sendStopWtr", new Object[] {accessTokenForm.getAccessToken(), id, templateId, token });
			flag = (boolean) results[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		updateSendDateTimeAndStatus(id, "2");
		return flag;
	}
	
	/**
	 * 发送给所有绑定用户
	 */
	@Override
	public boolean sendStopWtrTempByIdForBindUser(String id, String token) {

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
			Object[] results = client.invoke("sendStopWtrForBindUser", new Object[] {accessTokenForm.getAccessToken(), id, templateId, token });
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
		if (StringUtils.isEmpty(cTemplateId.getStopWtrId())) {
			String tempCode = loadConstant.getStopWtrTempCode();
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
			cTemplateId.setStopWtrId(templateId);
			cTemplateId.setToken(token);
			cTemplateIdRepository.save(cTemplateId);
		}
		return cTemplateId.getStopWtrId();

	}

	/**
	 * 修改发送时间和状态
	 * 
	 * @param temp
	 * @param flag
	 *            XingXingLvCha 2015年9月11日 下午1:21:03
	 */
	private void updateSendDateTimeAndStatus(String cStopWtrTempId, String flag) {
		CStopWtrTemp cStopWtrTemp = cstopwtrtempRepository.findOne(cStopWtrTempId);
		cStopWtrTemp.setSendDateTime(new Date());

		SDictionary sendStusEnt = dictionaryRepository.findByTypeCodeAndDicValue("reviewStatus", flag);

		cStopWtrTemp.setSendStatus(sendStusEnt);

		cstopwtrtempRepository.save(cStopWtrTemp);
	}

	/**
	 * 更新
	 */
	@Override
	public boolean update(CStopWtrTempForm stopWtrTempForm) {
		if (stopWtrTempForm == null)
			return false;
		CStopWtrTemp temp = cstopwtrtempRepository.findById(stopWtrTempForm.getId());
		if (temp == null) {
			return false;
		}
		SDictionary sendStusEnt = dictionaryRepository.findByTypeCodeAndDicValue("reviewStatus", "0");
		
		temp.setUrl(stopWtrTempForm.getUrl());
		temp.setFirst(stopWtrTempForm.getFirst());
		temp.setStopDuration(stopWtrTempForm.getStopDuration());
		temp.setArea(stopWtrTempForm.getArea());
		temp.setWhy(stopWtrTempForm.getWhy());
		temp.setTempRemark(stopWtrTempForm.getTempRemark());
		temp.setSendStatus(sendStusEnt);
		if (cstopwtrtempRepository.save(temp) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean initTempRelated(boolean updateApp, String appID, String appSecret, String token,
			String accessToken) {
		boolean initFlg = true;
		CStopWtrTempForm stopWtrForm = new CStopWtrTempForm();
		stopWtrForm.setArea("安源区步行街、北桥、万龙湾");
		stopWtrForm.setFirst("江西省萍乡市城区停水通知");
		stopWtrForm.setHeadColor("#FF0000");
		stopWtrForm.setStopDuration("2014年8月29日8:00-18:00");
		stopWtrForm.setTemplateCode("");
		// stopWtrForm.setTemplateID(templateID);
		stopWtrForm.setTemplateName("停水通知");
		stopWtrForm.setTempRemark("因为城区道路改造需要对自来水供水管路进行停水履行，给您带来不便敬请谅解。市政公告。");
		stopWtrForm.setTextColor("#173177");
		stopWtrForm.setToken(token);
		stopWtrForm.setUrl("www.baidu.com");
		stopWtrForm.setWhy("计划检修");
		stopWtrForm.setIsPrototype(1);
		CStopWtrTemp stopWtr = null;
		if (!updateApp) {
			stopWtr = save(stopWtrForm);
		} else {
			stopWtr = cstopwtrtempRepository.findPrototypeByToken(token);

			stopWtr.setTemplateID("");
			stopWtr = cstopwtrtempRepository.save(stopWtr);
		}

		if (stopWtr == null) {
			System.out.println("--------->待初始化的模板创建失败或是找不到");
			initFlg = false;
		}
		return initFlg;

	}

}