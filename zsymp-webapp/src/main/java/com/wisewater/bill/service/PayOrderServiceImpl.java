package com.wisewater.bill.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bill.pojo.PayOrder;
import com.wisewater.bill.repository.PayOrderRepository;

@Service
public class PayOrderServiceImpl extends BaseService implements PayOrderService{
	
	@Autowired
	private PayOrderRepository payOrderRepository;

	@Override
	public PayOrder findPayOrderByOrderNo(String token, String orderNo) {
		List<PayOrder> list = payOrderRepository.findPayOrderByOrderNo(token, orderNo);
		
		if (list!=null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public String findPayOrderByTime(Date fromDate, Date endDate) {
		String sumTotal = payOrderRepository.findPayOrderByTime(fromDate, endDate);
		if (!"".equals(sumTotal) && sumTotal != null) {
			return sumTotal;
		}
		return "0.00";
	}
	
	@Override
	public PayOrder findpayOrderByAccountNo(String token, String accountNo,String date) {
		List<PayOrder> list = payOrderRepository.findpayOrderByAccountNo(token, accountNo,date);
		if (list!=null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<PayOrder> findByCreateTimeAndOrderStatus(String token,
			String createTime, int orderStatus) {

		return payOrderRepository.findByCreateTimeAndOrderStatus(token, createTime, orderStatus);
	}
}
