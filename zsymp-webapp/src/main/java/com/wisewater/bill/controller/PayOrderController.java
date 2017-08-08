/**
 * 
 */
package com.wisewater.bill.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.ReturnMessage;
import com.wisewater.bill.pojo.PayOrder;
import com.wisewater.bill.repository.PayOrderRepository;
import com.wisewater.bill.service.PayOrderService;
import com.wisewater.bill.service.payConfigService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.scheduled.pojo.OrderCheck;
import com.wisewater.scheduled.service.AutoCheckService;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.util.tools.SecurityMD5;
import com.wisewater.weixin.service.InterfaceService;

/**
 * @author XingXinglvcha
 * 2016年2月5日 下午1:46:39
 */
@Controller
public class PayOrderController {

	@Autowired
	private PayOrderService payOrderService;
	
	@Autowired
	private PayOrderRepository payOrderRepository;
	
	@Autowired
	private InterfaceService interfaceService;
	
	@Autowired
	private payConfigService payConfigService;
	
	@Autowired
	private LoadConstant loadConstant;
	
	@Autowired
	private BAccessTokenService accessTokenService;
	
	@Autowired
	private AutoCheckService autoCheckService;

	
	private Logger logger = Logger.getLogger(PayOrderController.class);
	
    /**
	 * 创建未支付订单
	 * @author XingXinglvcha
	 * 2016年2月5日 下午2:16:44
	 * @param openId
	 * @param token
	 * @param accountNo
	 * @param accountName
	 * @param orderNo
	 * @param totalCharge
	 * @param billNo
	 * @param tollboothCode
	 * @return
	 */
	@RequestMapping(value = { "/wxpay/inserOrder.do" })
	@ResponseBody
	public ReturnMessage inserOrder(String openId,String token,String accountNo,String accountName,String address,String orderNo,
			String totalCharge){
		ReturnMessage msg = new ReturnMessage();
		if(StringUtils.isEmpty(openId) || StringUtils.isEmpty(token) || StringUtils.isEmpty(accountNo) || StringUtils.isEmpty(orderNo) || StringUtils.isEmpty(totalCharge) )
		{
			msg.setMsg("FAIL");
		}
		PayOrder payOrder = new PayOrder();
		payOrder.setAccountName(accountName);
		payOrder.setAccountNo(accountNo);
		payOrder.setAddress(address);
		payOrder.setCreateTime(new Date());
		payOrder.setOpenId(openId);
		payOrder.setOrderNo(orderNo);
		payOrder.setPayType("native");
		payOrder.setToken(token);
		payOrder.setTotalCharge(totalCharge);
		payOrder.setOrderStatus(3);
		payOrder = payOrderRepository.save(payOrder);
		if(payOrder != null)
		{
			msg.setMsg("SECCESS");
		}
		else
		{
			msg.setMsg("FAIL");
		}	
		return msg;
	}
	
	/**
	 * 销帐订单
	 * @author XingXinglvcha
	 * 2016年2月15日 下午5:22:56
	 * @param token
	 * @param orderNo
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = { "/wxpay/WXPaySuccess.do" })
	public void wXPaySuccess(String token,String orderNo,String attach,String transactionId,String tradno,String totalFee) throws Exception{
		logger.info("开始销账,token"+token+";orderNo"+orderNo);
		PayOrder payOrder = payOrderService.findPayOrderByOrderNo(token, orderNo);
		//订单的状态值 0  已支付单但是没有销账成功   1 已支付       2   预留    3    没有支付的单子
		logger.info("获取到要销账订单:"+JSONObject.fromObject(payOrder));
		if(payOrder != null && payOrder.getOrderStatus() != 1)
		{
			//销账部分代码
			String method = "postUsersFee";
			String accountNo = payOrder.getAccountNo();
			String checkNo = SecurityMD5.makeMD5("daxin" + tradno).toUpperCase();
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			Date time = new Date();
			String sfrq = formatter.format(time);
			int result = interfaceService.wxPayReturn(loadConstant.getChargeServicePath(), method, accountNo, totalFee, checkNo, sfrq);
			if(result == 1)
			{
				logger.info("销账成功");
				//销帐成功，发送模版，并开启支付功能
				payOrder.setBisBackTime(new Date());
				payOrder.setOrderStatus(1);
				payOrder.setAttach(attach);
				payOrder.setTransactionId(transactionId);
				payOrder = payOrderRepository.save(payOrder);
				
				OrderCheck orderCheck = autoCheckService.findByOrderNo(payOrder.getOrderNo());
				if(orderCheck != null){
					orderCheck.setIsLogicDel(1);
					orderCheck.setOrderStatus(1);
					autoCheckService.saveOrUpdateAutoCheck(orderCheck);
				}
				
				BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
				
				if(payOrder != null) //发送支付成功模版消息
				{
					try {
							Client client = new Client(new URL(loadConstant.getWebServicePath()));
							Object[] results = client.invoke("sendPaySuccess", new Object[] { token,loadConstant.getPaySuccessTempCode(),payOrder.getAccountNo(),payOrder.getAccountName(),payOrder.getOrderNo(),payOrder.getTotalCharge(),payOrder.getOpenId(),accessTokenForm.getAccessToken() });
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				//operateLogForm.setOutParam("OK");
				
			}
			else
			{
				//销帐失败，发送错误模版
				logger.info("销账失败");
				payOrder.setOrderStatus(0);
				payOrder.setAttach(attach);
				payOrder.setTransactionId(transactionId);
				payOrderRepository.save(payOrder);
				
				OrderCheck orderCheck = autoCheckService.findByOrderNo(payOrder.getOrderNo());
				if(orderCheck != null){
					orderCheck.setIsLogicDel(0);
					orderCheck.setOrderStatus(0);
					autoCheckService.saveOrUpdateAutoCheck(orderCheck);
				}
//				operateLogForm.setOutParam("FAIL");
			}
		
			
		}
		
//		operateLogForm.setOperateContent("销帐订单");
//		logManage.saveOperateLog(operateLogForm);
		
	}
	
}
