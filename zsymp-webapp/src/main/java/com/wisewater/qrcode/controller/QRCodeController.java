package com.wisewater.qrcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 展示二维码
 * @author Xiong
 * @date 2017年5月28日 下午3:05:32
 */
@Controller
public class QRCodeController {
	
	@RequestMapping("/m/showQRCode.do")
	public String showQRCode(){
		
		return "m/qrcode/qrcode_show";
	}
	
}
