package com.wisewater.bizConfig.controller;


import com.wisewater.base.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.wisewater.bizConfig.service.BCustomerService;
import com.wisewater.cusConfig.service.CMpService;

@Controller
public class BCustomerController extends BaseController {

	@Autowired
	private BCustomerService bcustomerService;
   
	@Autowired
	private CMpService mpService;
	
   
}