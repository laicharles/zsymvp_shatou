package com.wisewater.base;


import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.util.tools.LoadConstant;

@Service
public class BaseService {

	@Autowired
	protected DozerBeanMapper mapper;
	
	@Autowired
	protected LoadConstant loadConstant;
}
