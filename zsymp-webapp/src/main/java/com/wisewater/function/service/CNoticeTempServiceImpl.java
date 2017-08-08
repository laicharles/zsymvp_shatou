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
import com.wisewater.function.controller.CNoticeTempForm;
import com.wisewater.function.pojo.CNoticeTemp;
import com.wisewater.function.pojo.CTemplateId;
import com.wisewater.function.repository.CNoticeTempRepository;
import com.wisewater.function.repository.CTemplateIdRepository;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.wechatpublic.message.out.TemplateIndustry;
import com.wisewater.wechatpublic.message.out.TemplateShortID;
import com.wisewater.wechatpublic.util.TemplateUtil;

@Service
public class CNoticeTempServiceImpl extends BaseService implements CNoticeTempService {

	@Autowired
	private CNoticeTempRepository noticeTempRepository;

	@Autowired
	private CTemplateIdRepository cTemplateIdRepository;

	@Autowired
	private BAccessTokenService accessTokenService;

	@Autowired
	private CTemplateIdService templateIdService;
	
	@Autowired
	private SDictionaryRepository dictionaryRepository;
	
	@Autowired
	private BCustomerUserRepository bCustomerUserRepository;

	/**
	 * 查询列表
	 */
	@Override
	public JqgridListForm findAll(int pageNo, String sidx, String sord, String token) {

		int pageSize = loadConstant.getPageSize();
		if (pageNo < 1){
			pageNo = 1;
		}
			

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

		Page<CNoticeTemp> noticeTempPage = noticeTempRepository.findAll(page, token);

		List<CNoticeTempForm> pageList = new ArrayList<CNoticeTempForm>();
		if (noticeTempPage != null && noticeTempPage.getContent() != null) {
			for (CNoticeTemp txtMtrl : noticeTempPage.getContent()) {
				pageList.add(mapper.map(txtMtrl, CNoticeTempForm.class));
			}
		}

		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList);
		jqgridListForm.setTotal(noticeTempPage.getTotalPages());
		jqgridListForm.setRecords(noticeTempPage.getTotalElements());

		return jqgridListForm;

	}

	/**
	 * 保存
	 */
	@Override
	public CNoticeTemp save(CNoticeTempForm noticeTempForm) {
		if (noticeTempForm == null)
			return null;
		CNoticeTemp noticeTemp = mapper.map(noticeTempForm, CNoticeTemp.class);
		
		SDictionary sendStusEnt = dictionaryRepository.findByTypeCodeAndDicValue("reviewStatus", "0");
		noticeTemp.setSendStatus(sendStusEnt);

		CNoticeTemp temp = noticeTempRepository.save(noticeTemp);
		if (temp != null) {
			return temp;
		}
		return null;
	}

	/**
	 * 查询一个实例
	 */
	@Override
	public CNoticeTempForm findById(String id) {
		if (id == null)
			return null;

		CNoticeTemp temp = noticeTempRepository.getOne(id);
		if (temp == null)
			return null;

		CNoticeTempForm form = mapper.map(temp, CNoticeTempForm.class);

		return form;
	}

	/**
	 * 更新
	 */
	@Override
	public boolean update(CNoticeTempForm noticeTempForm) {
		if (noticeTempForm == null)
			return false;
		CNoticeTemp temp = noticeTempRepository.getOne(noticeTempForm.getId());

		if (temp == null) {
			return false;
		}
		
		SDictionary sendStusEnt = dictionaryRepository.findByTypeCodeAndDicValue("reviewStatus", "0");
		
		temp.setFirst(noticeTempForm.getFirst());
		temp.setNoticeType(noticeTempForm.getNoticeType());
		temp.setNoticeContent(noticeTempForm.getNoticeContent());
		temp.setRemark(noticeTempForm.getRemark());
		temp.setSendStatus(sendStusEnt);

		if (noticeTempRepository.save(temp) != null) {
			return true;
		}
		return false;
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

		List<CNoticeTemp> tempList = noticeTempRepository.findByIdIn(idList);
		if (tempList == null || tempList.isEmpty())
			return false;

		for (CNoticeTemp temp : tempList) {
			temp.setIsLogicDel(1);
		}

		List<CNoticeTemp> tempListRtn = noticeTempRepository.save(tempList);
		if (tempListRtn == null || tempListRtn.isEmpty()) {
			return false;
		} else {
			return true;
		}
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
		if (StringUtils.isEmpty(cTemplateId.getNoticeId())) {
			String tempCode = loadConstant.getNoticeTempCode();
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
			cTemplateId.setNoticeId(templateId);
			cTemplateId.setToken(token);
			cTemplateId = templateIdService.updateTemplateId(cTemplateId);
			return cTemplateId.getNoticeId();
		}
		return cTemplateId.getNoticeId();

	}

	/**
	 * 发送预览
	 */
	@Override
	public boolean previewNoticeTempById(String id,BCustomerUserForm user, String auditorOpenId ,String submitBinOpendId,String token) {

		boolean flag = false;

		if (id == null)
			return false;
		
		if(user == null) return false;
		if(user.getBinOpendId() == null || user.getBinOpendId().equals("") ||
		   user.getHigherCustomerUserId() == null || user.getHigherCustomerUserId().equals("") ||
		   user.getCurrentPermissions() == null || user.getCurrentPermissions().equals(""))
			return false;
		
		//获取上级用户
		BCustomerUser higherBCustomerUser = bCustomerUserRepository.findById(user.getHigherCustomerUserId());
		if(higherBCustomerUser == null)  return false;
		
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
			Object[] results = client.invoke("sendNoticeForOne", new Object[] { accessTokenForm.getAccessToken(), id, templateId, token, higherBCustomerUser.getBinOpendId() ,
		               user.getBinOpendId(),auditorOpenId,submitBinOpendId,user.getHigherCustomerUserId(),user.getCurrentPermissions(),loadConstant.getWebSitePath()});
			flag = (boolean) results[0];
			
			if(flag){
				updateSendDateTimeAndStatus(id,"1");
			}else{
				updateSendDateTimeAndStatus(id,"3");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 发送
	 */
	@Override
	public boolean sendNoticeTempById(String id, String token) {

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
			Object[] results = client.invoke("sendNotice", new Object[] {accessTokenForm.getAccessToken(), id, templateId, token });
			flag = (boolean) results[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateSendDateTimeAndStatus(id, "2");
		return flag;
	}
	
	/**
	 * 发送给绑定用户
	 */
	@Override
	public boolean sendNoticeTempByIdForBindUser(String id, String token) {

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
			Object[] results = client.invoke("sendNoticeForBindUser", new Object[] {accessTokenForm.getAccessToken(), id, templateId, token });
			flag = (boolean) results[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateSendDateTimeAndStatus(id, "2");
		return flag;
	}

	/**
	 * 修改发送时间和状态
	 * 
	 * @param temp
	 * @param flag
	 *            XingXingLvCha 2015年9月11日 下午1:21:03
	 */
	private void updateSendDateTimeAndStatus(String noticeTempId, String flag) {
		CNoticeTemp noticeTemp = noticeTempRepository.findOne(noticeTempId);
		SDictionary sendStusEnt = dictionaryRepository.findByTypeCodeAndDicValue("reviewStatus", flag);
		noticeTemp.setSendStatus(sendStusEnt);
		noticeTemp.setSendDateTime(new Date());
		noticeTempRepository.save(noticeTemp);
	}
}
