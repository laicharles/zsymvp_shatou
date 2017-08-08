package com.wisewater.bizConfig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wisewater.base.BaseService;

import com.wisewater.bizConfig.repository.BCustomerRepository;


@Service
public class BCustomerServiceImpl extends BaseService  
    implements BCustomerService{


	@Autowired
	private BCustomerRepository bcustomerRepository;

}