package com.wisewater.cusConfig.service;

import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.cusConfig.controller.CMpForm;

 
public interface CMpService {

	public CMpForm findByToken(String token);

	public CMpForm saveAndGetCMpForm(CMpForm cMpForm, BAccessTokenForm token, String cusId);

}