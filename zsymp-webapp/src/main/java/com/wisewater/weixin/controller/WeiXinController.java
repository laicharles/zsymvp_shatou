package com.wisewater.weixin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.wechatpublic.util.SignUtil;
import com.wisewater.weixin.service.CoreService;


@Controller
@RequestMapping(value="/weixinServlet")
public class WeiXinController {

    //从官方获取
	//private static String token = "sam_weixin";

    //重复通知过滤  时效60秒
    //private static ExpireSet<String> expireSet = new ExpireSet<String>(60);
    
    @Autowired
    private CoreService coreService;
    
    @RequestMapping(method=RequestMethod.GET,produces = "application/xml;charset=UTF-8")
    @ResponseBody
    public String bind(HttpServletRequest request){
    	System.out.println("进入CoreServlet的doGet开始");
		//微信加密签名
		String signature = request.getParameter("signature");
		//时间戳
		String timestamp = request.getParameter("timestamp");
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");
		
		String token = request.getParameter("token");
		
		//请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if(SignUtil.checkSignature(token,signature, timestamp, nonce)){
			System.out.println("进入CoreServlet的doGet结束1");
			return echostr ; 
		}
		System.out.println("进入CoreServlet的doGet结束");
		
        return "";
    }
    
    
    
    @RequestMapping(method=RequestMethod.POST,produces = "application/xml;charset=UTF-8")
    @ResponseBody
	public String process(HttpServletRequest request) throws IOException{
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String token = request.getParameter("token");
		System.out.println("进入CoreServlet的doPost中间"); 
		if(SignUtil.checkSignature(token,signature, timestamp, nonce)){
			String respXml = coreService.processRequest(request,token);
			return respXml;
		}
		System.out.println("进入CoreServlet的doPost结束");
        return "";
	}
	
	
}
