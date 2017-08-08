package com.wisewater.bill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bill.pojo.PayConfig;
import com.wisewater.bill.repository.PayConfigRepository;

@Service
public class payConfigServiceImpl extends BaseService implements payConfigService{

	@Autowired
	private PayConfigRepository payConfigRepository;
	
	@Override
	public PayConfig fingPayConfigByToken(String token) {
		List<PayConfig> list = payConfigRepository.findPayOrderByOrderNo(token);
		
		if (list!=null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
}
