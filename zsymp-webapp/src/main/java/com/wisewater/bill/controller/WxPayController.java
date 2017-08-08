package com.wisewater.bill.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.util.tools.ControllerRunnable;
import com.wisewater.util.tools.LoadConstant;
import com.thoughtworks.xstream.XStream;
import com.wisewater.bill.pojo.ChargeInfo;
import com.wisewater.bill.pojo.PayCallBackData;
import com.wisewater.bill.pojo.PayOrder;
import com.wisewater.bill.pojo.WxPayPrepayForm;
import com.wisewater.bill.repository.PayOrderRepository;
import com.wisewater.bill.service.PayOrderService;
import com.wisewater.bill.service.WxPayService;
import com.wisewater.bill.service.payConfigService;
import com.wisewater.fans.controller.CFanUserForm;
import com.wisewater.fans.service.CFanUserService;
import com.wisewater.scheduled.pojo.OrderCheck;
import com.wisewater.scheduled.service.AutoCheckService;
import com.wisewater.system.pojo.SFansOperateLog;
import com.wisewater.system.repository.SFansOperateLogRepository;
import com.wisewater.wechatpublic.util.StringUtil;
import com.wisewater.weixin.service.InterfaceService;

@Controller
public class WxPayController {

	@Autowired
	private CFanUserService cfanuserService;

	@Autowired
	private SFansOperateLogRepository fansOperateLogRepository;

	@Autowired
	private InterfaceService interfaceService;

	@Autowired
	private LoadConstant loadConstant;

	@Autowired
	private WxPayService wxPayService;

	@Autowired
	private PayOrderService payOrderService;

	@Autowired
	private PayOrderRepository payOrderRepository;
	
	@Autowired
	private payConfigService payConfigService;

	@Autowired
	private TaskExecutor taskExecutor;
	
	@Autowired
	private AutoCheckService autoCheckService;
	
	private Logger logger = Logger.getLogger(WxPayController.class);

	/**
	 * 缴纳水费页面 JiaHui 2016年9月8日 上午9:57:36
	 * 
	 * @param model
	 * @param openID
	 * @param token
	 * @return
	 */
	@RequestMapping("/m/wxPayJudgement.do")
	public String getWxPay(Model model,@RequestParam(value = "openID") String openID,@RequestParam("token") String token) {
		List<CFanUserForm> fanUserFormList = cfanuserService.findByOpenIDAndToken(openID, token);
		if (fanUserFormList == null || fanUserFormList.size() <= 0) {
			// 如果没有绑定，则跳到绑定页面
			return "redirect:/m/cfanuser_toAdd.do?openID=" + openID + "&token="+ token;
		}
		else if(fanUserFormList != null && fanUserFormList.size() == 1){
			return "redirect:/m/getWXPayPrepayid.do?accountNo="
					+ fanUserFormList.get(0).getUserAccount()+ "&token=" +token + "&openID="+ openID;
		}
		List<CFanUserForm> fanUserFormList1 = new ArrayList<CFanUserForm>();
		if (fanUserFormList != null && fanUserFormList.size() > 0) {
			for (CFanUserForm f : fanUserFormList) {
				f.setUserName(StringUtil.hideUserName(f.getUserName()));
				f.setContactAddr(StringUtil.hideString(f.getContactAddr()));
				fanUserFormList1.add(f);
			}
		}
		model.addAttribute("openID", openID);
		model.addAttribute("token", token);
		model.addAttribute("fanUserFormList", fanUserFormList1);
		fansOperateLogRepository.save(new SFansOperateLog("用户访问缴纳水费页面", "点击菜单","", new Date()));
		return "m/bill/fanUserList";
	}

	@RequestMapping("/m/getWXPayPrepayid.do")
	public String openWXPay(@RequestParam(value = "accountNo") String accountNo, 
			@RequestParam(value = "token") String token,@RequestParam(value = "openID") String openID,Model model) {
		String method = "getCharge";
		String accountno = accountNo;
		String chargeInfo;
		try {
			chargeInfo = interfaceService.getCharge(loadConstant.getChargeServicePath(), method, accountno);
			JSONObject jsonObject = JSONObject.fromObject(chargeInfo);
			if (!"0".equals(jsonObject.getString("retcode"))) {
				String info = jsonObject.getString("retmsg");
				model.addAttribute("info", info);
				return "m/bill/noRecord";
			}
			ChargeInfo chargeform = new ChargeInfo();
			if (!chargeInfo.equals("") && chargeInfo != null) {
				chargeform.setAccountno(jsonObject.getString("hno"));// 户号
				chargeform.setName(jsonObject.getString("name"));// 户名
				chargeform.setAddr(jsonObject.getString("addr"));// 地址
				chargeform.setNewAddr(jsonObject.getString("newaddr"));// 新地址
				JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("feeList").toString());// 费用列表
				int size = jsonArray.size();

				if (size <= 0) {
					chargeform.setCdate("");
					chargeform.setPrice("0.00");
					chargeform.setPwf("0.00");
					chargeform.setCnj("0.00");
					chargeform.setLjprice("0.00");
					chargeform.setTotal("0.00");
				}
				for (int i = 0; i < size; i++) {
					JSONObject object = (JSONObject) jsonArray.get(i);
					chargeform.setCdate(object.getString("cdate"));
					chargeform.setPrice(object.getString("price"));
					chargeform.setPwf(object.getString("pwf"));
					chargeform.setCnj(object.getString("cnj"));
					chargeform.setLjprice(object.getString("ljprice"));
					chargeform.setTotal(object.getString("sumprice"));
				}
				model.addAttribute("size", size);// 设置size的状态值来确定是否缴纳 >=1表示未缴纳
													// =0表示缴纳
			}
			model.addAttribute("chargeform", chargeform);
			model.addAttribute("token", token);
			model.addAttribute("openId", openID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/m/bill/wxPay";
	}

	/**
	 * 预支付js
	 * 
	 * @param openid
	 * @param totalFee
	 * @param body
	 * @param request
	 * @param response
	 * @return XingXingLvCha 2015年5月11日 上午11:11:52
	 */
	@RequestMapping(value = { "/wxpay/getWXPayPrepayid.do" })// @RequestParam(value = "accountNo")
	@ResponseBody
	public WxPayPrepayForm getWXPayPrepayid(String openId,String accountno, String token, HttpServletRequest request,HttpServletResponse response) {

		String method = "getChargeInfos";
		String chargeInfo;
		WxPayPrepayForm wxPayPrepayForm = new WxPayPrepayForm(); // 接下来进行微信支付
		try {
			chargeInfo = interfaceService.getPay(loadConstant.getChargeServicePath(), method, accountno);
			JSONObject jsonObject = JSONObject.fromObject(chargeInfo);
			String totalFee = jsonObject.getString("totalMoney");// 欠费金额6
			String tradno = jsonObject.getString("tradNo");// 水费编号
			wxPayPrepayForm = wxPayService.getWXPayPrepayid(openId,totalFee,token,tradno,request, response);
			logger.info("调取预支付js数据,openId:"+openId+";accountno:"+accountno+";token:"+token);
		} catch (Exception e) {
			logger.error("调取预支付js数据出现异常,errorMsg:"+e.getMessage()+";openId:"+openId+";accountno:"+accountno+";token:"+token);
			e.printStackTrace();
		}
		return wxPayPrepayForm;
	}

	/**
	 * 微信支付回调入口
	 * 
	 * @author XingXinglvcha 2016年2月15日 下午2:26:09
	 * @param token
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = { "/m/{token}/{tradno}/WXPaySuccess.do" }, method = RequestMethod.POST, produces = "application/xml;charset=UTF-8")
	public void wXPaySuccess(@PathVariable String token,@PathVariable String tradno,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// post 过来的xml
		PayCallBackData payCallBackData = null;
		// 转换微信post过来的xml内容
		ServletInputStream in = request.getInputStream();
		String xmlMsg = StringUtil.inputStream2String(in);
		payCallBackData = (PayCallBackData) getObjectFromXML(xmlMsg,PayCallBackData.class);//转换返回的数据为对象
		
		logger.info(payCallBackData.getOpenid()+"用户支付成功！支付金额:"+payCallBackData.getTotal_fee()+";reutrn_code"+payCallBackData.getResult_code());
		
		if (!payCallBackData.getReturn_code().equals("SUCCESS")) {
			System.out.println(payCallBackData.getReturn_msg());
			response.getWriter().write("SUCCESS");
			response.getWriter().flush();

		} else {
			// 支付成功开始销帐  销账的时候微信会返回一个交易码，与微信在交易时生成的是一样的
			PayOrder payOrder = payOrderService.findPayOrderByOrderNo(token,payCallBackData.getOut_trade_no());
			if (payOrder != null&& (payOrder.getOrderStatus() == 1 || payOrder.getOrderStatus() == 2)) {
				response.getWriter().write("SUCCESS");
				response.getWriter().flush();
				return;
			}

			if (payOrder != null && payOrder.getOrderStatus() != 1&& payOrder.getOrderStatus() != 2) // 去销帐
			{
				// 先把状态改成4
				payOrder.setOrderStatus(4);
				payOrder = payOrderRepository.save(payOrder);
				String controllerUrl = "http://localhost/zsymp-webapp"
						+ "/wxpay/WXPaySuccess.do?token="
						+ token
						+ "&orderNo="
						+ payCallBackData.getOut_trade_no()
						+ "&attach="
						+ payCallBackData.getAttach()
						+ "&transactionId="
						+ payCallBackData.getTransaction_id()
						+"&tradno="
						+ tradno
						+"&totalFee="
						+payCallBackData.getTotal_fee();
				  // 创建线程
				  logger.info("去销账的URL"+ controllerUrl);
				  try { 
					  OrderCheck orderCheck = autoCheckService.findByOrderNo(payOrder.getOrderNo());
					  logger.info("获取成功！orderCheck:"+JSONObject.fromObject(orderCheck));
					  if( orderCheck == null){
						  orderCheck = new OrderCheck();
					  }
						
					  orderCheck.setToken(payOrder.getToken());
					  orderCheck.setOrderNo(payOrder.getOrderNo());
					  orderCheck.setAccountNo(payOrder.getAccountNo());
					  orderCheck.setAccountName(payOrder.getAccountName());
					  orderCheck.setOrderStatus(payOrder.getOrderStatus());
					  orderCheck.setTransactionId(payOrder.getTransactionId());
					  orderCheck.setWxpaySuccessUrl(controllerUrl);  //销账地址
					  //销账的一些参数
					  orderCheck.setAttach(payCallBackData.getAttach()); 
					  orderCheck.setTradno(tradno);
					  orderCheck.setTotalFee(String.format("%.2f", payCallBackData.getTotal_fee()*0.01));
					  orderCheck.setIsLogicDel(0); //1删除    0未删除
					  orderCheck.setCreateTime(new Date());
					  autoCheckService.saveOrUpdateAutoCheck(orderCheck);
					  logger.info("更新完成!");
					  
					  taskExecutor.execute(new ControllerRunnable(controllerUrl) {}); 
					  logger.info("去执行销账ing......");
					  
				  } catch (Exception e){ 
					  logger.error("销账失败！errorMsg:"+e.getMessage());
					  e.printStackTrace(); 
			      }
				return;

			}

		}

	}

	/**
	 * 解析微信返回的xml数据
	 * 
	 * @author XingXinglvcha 2016年2月15日 下午2:26:35
	 * @param xml
	 * @param tClass
	 * @return
	 */
	public static Object getObjectFromXML(String xml, Class<?> tClass) {
		// 将从API返回的XML数据映射到Java对象
		XStream xStreamForResponseData = new XStream();
		xStreamForResponseData.alias("xml", tClass);
		xStreamForResponseData.ignoreUnknownElements();// 暂时忽略掉一些新增的字段
		return xStreamForResponseData.fromXML(xml);
	}
}
