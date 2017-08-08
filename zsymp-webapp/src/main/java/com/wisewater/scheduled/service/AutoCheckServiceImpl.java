package com.wisewater.scheduled.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.scheduled.pojo.OrderCheck;
import com.wisewater.scheduled.repository.AutoCheckRepository;

/**
 * @author Xiong
 * @date 2017年7月10日 下午2:37:00
 */
@Service
public class AutoCheckServiceImpl implements AutoCheckService{

	@Autowired
	private AutoCheckRepository autoCheckRepository;
	
	@Override
	public boolean saveOrUpdateAutoCheck(OrderCheck orderCheck) {
		orderCheck = autoCheckRepository.saveAndFlush(orderCheck);
		if(orderCheck != null){
			return true;
		}
		return false;
	}

	@Override
	public OrderCheck findByOrderNo(String orderNo) {
		
		SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return autoCheckRepository.findByOrderNo(orderNo,sdfDateFormat.format(new Date()));
	}

	@Override
	public boolean deleteAutoCheck(OrderCheck orderCheck) {
		try {
			autoCheckRepository.delete(orderCheck);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<OrderCheck> findByCreateTime(String token,String createTime) {
		
		return autoCheckRepository.findByCreateTime(token,createTime);
	}
	
	@Override
	public List<OrderCheck> findByNotAccount(String token, String createTime,
			int orderStatus) {
		
		return autoCheckRepository.findByNotAccount(token, createTime, orderStatus);
	}

}
