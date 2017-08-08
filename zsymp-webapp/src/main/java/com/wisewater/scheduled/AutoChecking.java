package com.wisewater.scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wisewater.bill.pojo.PayOrder;
import com.wisewater.bill.service.PayOrderService;
import com.wisewater.playStatement.pojo.PayStatement;
import com.wisewater.scheduled.pojo.OrderCheck;
import com.wisewater.scheduled.service.AutoCheckService;
import com.wisewater.util.tools.ControllerRunnable;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.weixin.service.InterfaceService;

/**
 * 自动对账（每天晚上十一点）
 * @author Xiong
 * @date 2017年7月10日 上午9:15:43
 */
@Component
public class AutoChecking{

	@Autowired
	private InterfaceService interfaceService;
	
	@Autowired
	private LoadConstant loadConstant;
	
	@Autowired
	private PayOrderService payOrderService;
	
	@Autowired
	private AutoCheckService autoCheckService;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	private Logger logger = Logger.getLogger(AutoChecking.class);
	
	@Scheduled(cron = "0 10 23 * * ? ")
	public void autoCheck() {
		
		double waterBillSum  = getWaterBill();
		double weChatBillSum = getWeChatBill();
		//System.out.println("测试程序:--->"+waterBillSum+"------>"+weChatBillSum);
		logger.info("waterBillSum:"+waterBillSum+";---->weChatBillSum:"+weChatBillSum);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		
		//获取今天所有成功的payorder订单
		List<PayOrder> payOrderList = payOrderService.findByCreateTimeAndOrderStatus("225fb8d5394a44e983e7f6354954a21e", date, 1);
		//获取今天所有的订单
		List<OrderCheck> orderCheckList = autoCheckService.findByCreateTime("225fb8d5394a44e983e7f6354954a21e", date);
		//对比订单，从所有的订单中去除已经成功的
		for (PayOrder payOrder : payOrderList) {
			Iterator<OrderCheck> orderCheckIter = orderCheckList.iterator();
			while(orderCheckIter.hasNext()){
				OrderCheck orderNext = orderCheckIter.next();
				if(orderNext.getOrderNo().equals(payOrder.getOrderNo())){
					
					orderNext.setIsLogicDel(1);
					orderNext.setOrderStatus(payOrder.getOrderStatus());
					if(autoCheckService.saveOrUpdateAutoCheck(orderNext)){
						orderCheckIter.remove();
						break;
					}
				}
			}
		}
		
		logger.info("对账完成!今天有"+orderCheckList.size()+"单没销账！");
		
		//判断两个金额是否一致
		if(waterBillSum != weChatBillSum || orderCheckList.size() > 0){ //不一致，进行对账， 平账单
			
			for (OrderCheck orderCheck : orderCheckList) {
				
				logger.info("自动平账:"+orderCheck.getWxpaySuccessUrl());
				
				taskExecutor.execute(new ControllerRunnable(orderCheck.getWxpaySuccessUrl()) {}); 
			}
			
		}
	}
	
	/**
	 * 获取水司收到费用总和
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private double getWaterBill(){
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			String date = sdf.format(new Date());
			String result = interfaceService.weChatDayCount(loadConstant.getPayStatementServicePath(), "weChatDayCount", date,"0");
			List<PayStatement> list = (List)JSONArray.toCollection(JSONArray.fromObject(result), PayStatement.class);
			
			if (list!=null && list.size()>0) {
				double totalShantou = 0.00;
				for (PayStatement payStatement : list) {
					totalShantou+=Double.parseDouble(payStatement.getTotalfee());
				}
				
				return Double.parseDouble(String.format("%.2f",totalShantou));
			}
		} catch (Exception e) {
			logger.error("获取水司收到费用总和失败：errorMsg:" + e.getMessage());
			e.printStackTrace();
		}
		return 0.00;
	}
	
	/**
	 * 获取微信收的钱，既我们收的
	 */
	private double getWeChatBill(){
		
		try {
			SimpleDateFormat sdfSimple = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfComplex = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdfSimple.format(new Date());
			
			Date fromDate = sdfComplex.parse(date + " 00:00:00");
			Date endDate = sdfComplex.parse(date + " 23:59:59");
			
			String totalMoney = payOrderService.findPayOrderByTime(fromDate, endDate);
			
			return Double.parseDouble(totalMoney);
		} catch (ParseException e) {
			logger.error("获取微信收到费用总和失败：errorMsg:" + e.getMessage());
			e.printStackTrace();
		}
		
		return 0.00;
	}

}
