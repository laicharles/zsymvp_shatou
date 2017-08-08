package com.wisewater.scheduled.service;

import java.util.List;

import com.wisewater.scheduled.pojo.OrderCheck;

/**
 * @author Xiong
 * @date 2017年7月10日 下午2:35:45
 */
public interface AutoCheckService {
	
	boolean saveOrUpdateAutoCheck(OrderCheck orderCheck);
	
	OrderCheck findByOrderNo(String orderNo);
	
	boolean deleteAutoCheck(OrderCheck orderCheck);
	
	List<OrderCheck> findByCreateTime(String token ,String createTime);
	
	List<OrderCheck> findByNotAccount(String token,String createTime,int orderStatus);
}
