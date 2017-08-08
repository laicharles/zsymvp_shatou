/**
 * 
 */
package com.wisewater.bill.service;

import java.math.BigDecimal;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisewater.bill.pojo.Mp;
import com.wisewater.bill.pojo.PayConfig;
import com.wisewater.bill.pojo.WxPayPrepayForm;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.weixin.utils.GetWxOrderno;
import com.wisewater.weixin.utils.OrderNumber;
import com.wisewater.weixin.utils.RequestHandler;
import com.wisewater.weixin.utils.Sha1Util;
import com.wisewater.weixin.utils.TenpayUtil;

/**
 * @author XingXinglvcha
 * 2016年1月5日 下午4:33:16
 */
@Transactional
@Service
public class WxPayService {

	@Autowired
	private payConfigService payConfigService;
	
	@Autowired
	private MpService mpService;

	@Autowired
	private LoadConstant loadConstant;

	
	@SuppressWarnings("static-access")
	public WxPayPrepayForm getWXPayPrepayid(String openId,String totalFee,String token,String tradno,HttpServletRequest request,HttpServletResponse response){
		WxPayPrepayForm wxPayPrepayForm = new WxPayPrepayForm();
		if(StringUtils.isEmpty(token) || StringUtils.isEmpty(openId) || StringUtils.isEmpty(totalFee))
		{
			wxPayPrepayForm.setResult("fail");
			return wxPayPrepayForm;
		}
		Mp mp = mpService.findMpByToken(token);
		if(mp == null || StringUtils.isEmpty(mp.getAppID()))
		{
			wxPayPrepayForm.setResult("fail");
			return wxPayPrepayForm;
		}
		
		PayConfig payConfig = payConfigService.fingPayConfigByToken(token);
		if(payConfig == null || 0 == payConfig.getCanPay() || StringUtils.isEmpty(payConfig.getApiKey()) || StringUtils.isEmpty(payConfig.getMchId()))
		{	wxPayPrepayForm.setResult("fail");
			return wxPayPrepayForm;
		}
		
		//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		//8位日期
		String strTime = currTime.substring(8, currTime.length());
		//四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		//10位序列号,可以自行调整。
		String strReq = strTime + strRandom;
		
		//随机数 
		String nonce_str = strReq;
		
		//总金额以分为单位，不带小数点
		BigDecimal bd=new BigDecimal(totalFee);   
		//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)   
		bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP);  
		String total_fee = bd.toString().replace(".", "").replaceFirst("^0*", "");
		
		//String notify_url = systemService.findSystem().getMobiePath() + "/m/" + token + "/WXPaySuccess.do";
		String notify_url = loadConstant.getWebSitePath()+"/m/"+token+"/"+tradno+"/WXPaySuccess.do";//通知的地址 必填
		//回调附加数据,原样返回
		String attach = token;
		
		String appid = mp.getAppID();           //公众账号ID  必填
		String mch_id = payConfig.getMchId();   //商户号           必填
		//String appid = "wx8d9c5d382ad17261";
		String body = "水费";
		String out_trade_no = OrderNumber.getNumber();  //订单编号
		String spbill_create_ip = request.getRemoteAddr();//终端IP
		String trade_type = "JSAPI";
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);  
		packageParams.put("mch_id", mch_id);  
		packageParams.put("nonce_str", nonce_str);  
		packageParams.put("body", body);  
		packageParams.put("attach", attach);  
		packageParams.put("out_trade_no", out_trade_no);  
		packageParams.put("total_fee", total_fee);  
		packageParams.put("spbill_create_ip", spbill_create_ip);  
		packageParams.put("notify_url", notify_url);  
		packageParams.put("trade_type", trade_type);  
		packageParams.put("openid", openId);  
		//packageParams.put("key", "ugjnmki5p96pwz4b7g5m1i5k4g1l6o7t");  
		packageParams.put("key", payConfig.getApiKey());  
		
		RequestHandler reqHandler = new RequestHandler(request, response);
		String sign = reqHandler.createSign(packageParams);
		String xml="<xml>"+
				"<appid>"+appid+"</appid>"+
				"<mch_id>"+mch_id+"</mch_id>"+
				"<nonce_str>"+nonce_str+"</nonce_str>"+
				"<body><![CDATA["+body+"]]></body>"+         //标记信息
				"<attach><![CDATA["+attach+"]]></attach>"+   //附加数据
				"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
				"<total_fee>"+total_fee+"</total_fee>"+
				"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
				"<notify_url>"+notify_url+"</notify_url>"+
				"<trade_type>"+trade_type+"</trade_type>"+
				"<openid>"+openId+"</openid>"+
				"<sign><![CDATA["+sign+"]]></sign>"+
				"</xml>";
		try {
			 reqHandler.genPackage(packageParams);
		} catch (Exception e) {
			e.printStackTrace();
			wxPayPrepayForm.setResult("fail");
			return wxPayPrepayForm;
		}
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一支付接口
		
		String prepay_id="";
		try {
			prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);//准备的统一支付接口   与xml数据  最终为了得到预支付ID参数
			if(prepay_id.equals("")){
				System.out.println("统一支付接口获取预支付订单出错");
				wxPayPrepayForm.setResult("fail");
				return wxPayPrepayForm;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			wxPayPrepayForm.setResult("fail");
			return wxPayPrepayForm;
		}
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String timestamp = Sha1Util.getTimeStamp();
		String prepay_id2 = "prepay_id="+prepay_id;
		String packages = prepay_id2;
		finalpackage.put("appId", appid);  
		finalpackage.put("timeStamp", timestamp);  
		finalpackage.put("nonceStr", nonce_str);  
		finalpackage.put("package", packages);  
		finalpackage.put("signType", "MD5");
		//finalpackage.put("key","ugjnmki5p96pwz4b7g5m1i5k4g1l6o7t");
		finalpackage.put("key",payConfig.getApiKey());
		
		String finalsign = reqHandler.createSign(finalpackage);
		
		wxPayPrepayForm.setResult("success");
		wxPayPrepayForm.setAppId(appid);
		wxPayPrepayForm.setFinalsign(finalsign);
		wxPayPrepayForm.setNonceStr(nonce_str);
		wxPayPrepayForm.setPackages(packages);
		wxPayPrepayForm.setTimestamp(timestamp);
		wxPayPrepayForm.setOut_trade_no(out_trade_no);
		return wxPayPrepayForm;
	}
}
