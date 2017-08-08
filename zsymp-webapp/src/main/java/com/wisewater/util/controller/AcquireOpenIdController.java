package com.wisewater.util.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wisewater.cusConfig.service.CMpService;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.wechatpublic.oauth2.Oauth2Util;

/**
 * 获取当前用户openid
 * @author Xiong
 * @date 2017年4月1日 下午3:50:25
 */
@Controller
public class AcquireOpenIdController {
	
	@Autowired
	private CMpService cMpService;
	
	@Autowired
	private LoadConstant loadConstantsss;
	
	/**
	 * 请求微信获取code
	 * @param token  相对应的token
	 * @param redirectUrl 获取后想要跳转的url
	 * @return
	 */
	@RequestMapping(value="/u/acquireCode.do")
	public String acquireCode(String token,String redirectUrl,String typeCode,String logicID){
		
		String state = redirectUrl+","+token+","+typeCode+","+logicID;
		
		String url = Oauth2Util.buildAuthorizationUrl(cMpService.findByToken(token).getAppID(),
				loadConstantsss.getWebSitePath()+"/u/acquireOpenId.do", state);
		
		return "redirect:" + url;
	}
	
	/**
	 * 获取openId
	 * @param attr 重定向后带上的参数
	 * @param code 
	 * @param state 获取token和url(获取openID后需要跳转的url)
	 * @return
	 */
	@RequestMapping("/u/acquireOpenId.do")
	public String acquireOpenId(RedirectAttributes attr, @RequestParam(value="code") String code,@RequestParam(value = "state") String state){
		
		String[] str = state.split(",");
		String url = str[0];
		String token = str[1];
		String typeCode = "";
		String logicID = "";
		if(str[2] != null && !str[2].equals("")){
			typeCode = str[2];
			attr.addAttribute("typeCode", typeCode);
		}else{
			attr.addAttribute("typeCode", "typeCode");
		}
		if(str[3] != null && !str[3].equals("")){
			logicID = str[3];
			attr.addAttribute("logicID", logicID);
		}else{
			attr.addAttribute("logicID", "logicID");
		}
		
		String openId = Oauth2Util.oauth2getOpenID(code, cMpService.findByToken(token).getAppID(), cMpService.findByToken(token).getAppSecret());
		
		attr.addAttribute("openId", openId);
		attr.addAttribute("token", token);
		return "redirect:"+url;
	}
	
	/**
	 * 用来测试
	 * @param openId
	 * @return
	 */
	/*@RequestMapping("/u/getOpenId.do")
	public String getOpenId(@RequestParam(value="openId") String openId){
		System.out.println(openId);
		return "test/index";
	}*/
}
