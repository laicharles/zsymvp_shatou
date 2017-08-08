package com.wisewater.cusConfig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.pojo.BCustomer;
import com.wisewater.bizConfig.repository.BCustomerRepository;
import com.wisewater.cusConfig.controller.CMpForm;
import com.wisewater.cusConfig.pojo.CMp;
import com.wisewater.cusConfig.repository.CMpRepository;
import com.wisewater.function.service.CStopWtrTempService;

@Service
public class CMpServiceImpl extends BaseService implements CMpService {

	@Autowired
	private CMpRepository cMpRepository;

	@Autowired
	private BCustomerRepository cusRepository;

	@Autowired
	CStopWtrTempService cStopWtrTempService;

	@Override
	public CMpForm saveAndGetCMpForm(CMpForm cMpForm, BAccessTokenForm token, String cusId) {
		if (cMpForm == null)
			return null;
		boolean isNew = true;
		boolean updateApp = false;
		CMp cmp = cMpRepository.findByToken(token.getToken());
		if (cmp.getAppID() != null && !cmp.getAppID().equalsIgnoreCase("")) {
			isNew = false;
			if (cmp.getAppID() != null && !cmp.getAppID().equalsIgnoreCase("")
					&& !cmp.getAppID().equalsIgnoreCase(cMpForm.getAppID()) && cmp.getAppSecret() != null
					&& !cmp.getAppSecret().equalsIgnoreCase("")
					&& !cmp.getAppSecret().equalsIgnoreCase(cMpForm.getAppSecret())) {
				updateApp = true;
			}
		}
		BCustomer cus = cusRepository.findById(cusId);
		CMp cMp = mapper.map(cMpForm, CMp.class);
		cMp.setBCusotmer(cus);
		CMp cmprtn = cMpRepository.saveAndFlush(cMp);
		if (cmprtn != null) {
			CMpForm mpFormRtn = mapper.map(cmprtn, CMpForm.class);
			// Init Template related
			if (isNew || updateApp) {
				boolean initTemp = cStopWtrTempService.initTempRelated(updateApp, mpFormRtn.getAppID(),
						mpFormRtn.getAppSecret(), token.getToken(), token.getAccessToken());
				if (!initTemp) {
					System.out.println("-------------->初始化模板相关设置失败");
				}
				mpFormRtn.setTempInitFlag(initTemp);
			}
			return mpFormRtn;
		}
		return null;
	}

	/**
	 * 
	 * 描述：根据token查找mp
	 * @author AlexFung
	 * 2016年7月26日 下午3:31:56
	 * @param token
	 * @return
	 */
	@Override
	public CMpForm findByToken(String token) {
		CMp mp = cMpRepository.findByToken(token);
		CMpForm mpForm = null;
		if (mp != null) {
			mpForm = mapper.map(mp, CMpForm.class);
		}
		return mpForm;
	}

}
