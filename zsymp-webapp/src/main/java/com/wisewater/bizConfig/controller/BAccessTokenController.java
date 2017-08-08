package com.wisewater.bizConfig.controller;

import com.wisewater.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.wisewater.bizConfig.service.BAccessTokenService;

@Controller
public class BAccessTokenController extends BaseController {

	@Autowired
	private BAccessTokenService baccesstokenService;
   
   
}