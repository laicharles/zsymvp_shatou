package com.wisewater.playStatement.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.bill.pojo.PayOrder;
import com.wisewater.bill.service.PayOrderService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.playStatement.pojo.PayStatement;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.weixin.service.InterfaceService;

/**
 * 报表服务类
 * @author Xiong
 * @date 2017年4月6日 上午9:37:44
 */
@Service
public class PayStatementService {
	
	@Autowired
	private InterfaceService interfaceService;
	
	@Autowired
	private PayOrderService payOrderService;
	
	@Autowired
	private LoadConstant loadConstant;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JqgridListForm weChatCommWebService(int pageNo,String date,String userType,String type){
		
		JqgridListForm jqgridListForm = new JqgridListForm();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			//TODO  测试 只有2017-03-02有数据
			String result = interfaceService.weChatDayCount(loadConstant.getPayStatementServicePath(), type, date,userType);

			Date fromDate = sdf.parse(date+" 00:00:00");
			Date endDate = sdf.parse(date+" 23:59:59");
			String totalMoney = payOrderService.findPayOrderByTime(fromDate, endDate);
			List<PayStatement> list = (List)JSONArray.toCollection(JSONArray.fromObject(result), PayStatement.class);
			if (list!=null && list.size()>0) {
				double totalShantou = 0.00;
				for (PayStatement payStatement : list) {
					PayOrder payOrder = payOrderService.findpayOrderByAccountNo("225fb8d5394a44e983e7f6354954a21e", payStatement.getUserno(),date);//汕头token
					if (payOrder != null) {
						payStatement.setOrderNo(payOrder.getTransactionId()==null ? "":payOrder.getTransactionId());
						payStatement.setOrderSatus(payOrder.getOrderStatus() == 1 ?"已销账" : "未销账");
					}else {
						payStatement.setOrderNo("");
						payStatement.setOrderSatus("未销账");
					}
					totalShantou+=Double.parseDouble(payStatement.getTotalfee());
				}
				PayStatement payStatement = new PayStatement();
				payStatement.setUsort("微信合计 / 收入网合计");
				payStatement.setUsercnt("     /");
				payStatement.setWaternum("     /");
				payStatement.setWaterfee("     /");
				payStatement.setPenalty("     /");
				payStatement.setSewagefee("     /");
				payStatement.setLatefee("     /");
				payStatement.setGarbagefee("     /");
				payStatement.setTotalfee(totalMoney+" / "+totalShantou);
				list.add(payStatement);
			}

			int totalSize = list.size();
			int pageSize = loadConstant.getPageSize();
			//获取指定页码的条数
			List<PayStatement> listStatement = getAssign(pageNo, pageSize, list);
			
			jqgridListForm.setPage(pageNo);
			jqgridListForm.setFormList(listStatement);
			jqgridListForm.setTotal(totalSize%pageSize > 0 ? totalSize/pageSize+1 : totalSize/pageSize);
			jqgridListForm.setRecords(totalSize);
			
			return jqgridListForm;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JqgridListForm weChatMonthWebService(int pageNo,String date1,String date2,String userType,String type){
		
		JqgridListForm jqgridListForm = new JqgridListForm();
		
		try {
			//TODO  测试 只有2017-03-02有数据
			String result = interfaceService.weChatMonthReport(loadConstant.getPayStatementServicePath(), type, date1,date2,userType);
			
			List<PayStatement> list = (List)JSONArray.toCollection(JSONArray.fromObject(result), PayStatement.class);
			
			int totalSize = list.size();
			int pageSize = loadConstant.getPageSize();
			//获取指定页码的条数
			List<PayStatement> listStatement = getAssign(pageNo, pageSize, list);
			
			jqgridListForm.setPage(pageNo);
			jqgridListForm.setFormList(listStatement);
			jqgridListForm.setTotal(totalSize%pageSize > 0 ? totalSize/pageSize+1 : totalSize/pageSize);
			jqgridListForm.setRecords(totalSize);
			
			return jqgridListForm;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String weChatDayCountDownload(String date,String userType){
		StringBuffer sb = new StringBuffer();
		
		//创建表格标题
		sb.append("微信缴费日统计("+date+")").append("\r\r");

		sb.append("用水性质").append(",");
		sb.append("户数").append(",");
		sb.append("水量").append(",");
		sb.append("水费").append(",");
		sb.append("水费违约金").append(",");
		sb.append("污水费").append(",");
		sb.append("污水费滞纳金").append(",");
		sb.append("垃圾费").append(",");
		sb.append("总费用").append(",");
		sb.append("\r");
		
		String method = "weChatDayCount";
		try {
			String result = interfaceService.weChatDayCount(loadConstant.getPayStatementServicePath(), method, date,userType);
			
			List<PayStatement> list = (List)JSONArray.toCollection(JSONArray.fromObject(result), PayStatement.class);
		
			for (PayStatement payStatement : list) {
				sb.append(payStatement.getUsort()).append(",");
				sb.append(payStatement.getUsercnt()).append(",");
				sb.append(payStatement.getWaternum()).append(",");
				sb.append(payStatement.getWaterfee()).append(",");
				sb.append(payStatement.getPenalty()).append(",");
				sb.append(payStatement.getSewagefee()).append(",");
				sb.append(payStatement.getLatefee()).append(",");
				sb.append(payStatement.getGarbagefee()).append(",");
				sb.append(payStatement.getTotalfee()).append(",");
				sb.append("\r");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	
	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	public JqgridListForm weChatDayReport(int pageNo,String date){
		
		JqgridListForm jqgridListForm = new JqgridListForm();
		
		String method = "weChatDayReport";
		try {
			//TODO  测试 只有2017-03-02有数据
			String result = interfaceService.weChatDayCount(URL, method, date);
			
			List<PayStatement> list = (List)JSONArray.toCollection(JSONArray.fromObject(result), PayStatement.class);
			
			int totalSize = list.size();
			int pageSize = loadConstant.getPageSize();
			//获取指定页码的条数
			List<PayStatement> listStatement = getAssign(pageNo, pageSize, list);
			
			jqgridListForm.setPage(pageNo);
			jqgridListForm.setFormList(listStatement);
			jqgridListForm.setTotal(totalSize%pageSize > 0 ? totalSize/pageSize+1 : totalSize/pageSize);
			jqgridListForm.setRecords(totalSize);
			
			return jqgridListForm;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String weChatDayReportDownload(String date,String userType){
		StringBuffer sb = new StringBuffer();
		
		//创建表格标题
		sb.append("微信缴费日统计明细("+date+")").append("\r\r");
		
		sb.append("用户号").append(",");
		sb.append("抄表月份").append(",");
		sb.append("水量").append(",");
		sb.append("水费").append(",");
		sb.append("水费违约金").append(",");
		sb.append("污水费").append(",");
		sb.append("污水费滞纳金").append(",");
		sb.append("垃圾费").append(",");
		sb.append("总费用").append(",");
		sb.append("\r");
		
		String method = "weChatDayReport";
		try {
			String result = interfaceService.weChatDayCount(loadConstant.getPayStatementServicePath(), method, date,userType);
			
			List<PayStatement> list = (List)JSONArray.toCollection(JSONArray.fromObject(result), PayStatement.class);
		
			for (PayStatement payStatement : list) {
				sb.append(payStatement.getUserno()).append(",");
				sb.append(payStatement.getCmonth()).append(",");
				sb.append(payStatement.getWaternum()).append(",");
				sb.append(payStatement.getWaterfee()).append(",");
				sb.append(payStatement.getPenalty()).append(",");
				sb.append(payStatement.getSewagefee()).append(",");
				sb.append(payStatement.getLatefee()).append(",");
				sb.append(payStatement.getGarbagefee()).append(",");
				sb.append(payStatement.getTotalfee()).append(",");
				sb.append("\r");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String weChatMonthReportDownload(String date1,String date2,String userType){
		StringBuffer sb = new StringBuffer();
		
		//创建表格标题
		sb.append("微信缴费月统计("+date1+" 至  "+date2+")").append("\r\r");
		
		sb.append("收费日期").append(",");
		sb.append("缴费户数").append(",");
		sb.append("水量").append(",");
		sb.append("水费").append(",");
		sb.append("水费违约金").append(",");
		sb.append("污水费").append(",");
		sb.append("污水费滞纳金").append(",");
		sb.append("垃圾费").append(",");
		sb.append("总费用").append(",");
		sb.append("\r");
		
		String method = "weChatMonthReport";
		try {
			String result = interfaceService.weChatMonthReport(loadConstant.getPayStatementServicePath(), method, date1,date2,userType);
			
			List<PayStatement> list = (List)JSONArray.toCollection(JSONArray.fromObject(result), PayStatement.class);
		
			for (PayStatement payStatement : list) {
				sb.append(payStatement.getSdate()).append(",");
				sb.append(payStatement.getUsercnt()).append(",");
				sb.append(payStatement.getWaternum()).append(",");
				sb.append(payStatement.getWaterfee()).append(",");
				sb.append(payStatement.getPenalty()).append(",");
				sb.append(payStatement.getSewagefee()).append(",");
				sb.append(payStatement.getLatefee()).append(",");
				sb.append(payStatement.getGarbagefee()).append(",");
				sb.append(payStatement.getTotalfee()).append(",");
				sb.append("\r");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	/**
	 * 用来获取指定的数据
	 * @param pageNo
	 * @param pageSize
	 * @param payList
	 * @return
	 */
	public List<PayStatement> getAssign(int pageNo,int pageSize,List<PayStatement> payList){
		List<PayStatement> listStatement = new ArrayList<PayStatement>(); 
		int count = pageNo*pageSize;
		int start = count-pageSize;
		int maxCount = payList.size();
		for(int i= start; i < count;i++){
			if(maxCount==i){
				break;
			}
			listStatement.add(payList.get(i));
		}
		return listStatement;
	}
	
	
	
	
}
