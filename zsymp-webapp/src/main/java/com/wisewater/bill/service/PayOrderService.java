package com.wisewater.bill.service;



import java.util.Date;
import java.util.List;

import com.wisewater.bill.pojo.PayOrder;


public interface PayOrderService {

	PayOrder findPayOrderByOrderNo(String token, String orderNo);
	
	String findPayOrderByTime(Date fromDate,Date endDate);
	
	PayOrder findpayOrderByAccountNo(String token,String accountNo,String date);

	List<PayOrder> findByCreateTimeAndOrderStatus(String token,String createTime,int orderStatus);
	
}
