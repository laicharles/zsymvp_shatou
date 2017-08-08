package com.wisewater.wechatpublic.qrcode;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class QrcodeUtil {
	private static Logger log = Logger.getLogger(QrcodeUtil.class);
	
	// 创建永久二维码
	public final static String qrcode_create_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	
	// 创建临时二维码
	public final static String qrcode_temp_create_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 
	 * 描述：创建永久二维码
	 * @author AlexFung
	 * 2016年4月27日 下午4:13:36
	 * @param qrcode
	 * @param accessToken
	 * @return
	 */
	public static String createQrcode(Qrcode qrcode, String accessToken) {
		String url = qrcode_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转成JSON字符串
		String json = JSONObject.fromObject(qrcode).toString();
		System.out.println(json);
		// 发起POST请求创建菜单
		JSONObject jsonObject = JsonUtil.httpsRequest(url, "POST", json);
		return jsonObject.toString();
	}
	
	/**
	 * 
	 * 描述：创建临时二维码
	 * @author AlexFung
	 * 2016年4月27日 下午4:13:36
	 * @param qrcode
	 * @param accessToken
	 * @return
	 */
	public static String createQrcodeTemp(QrcodeTemp qrcodeTemp, String accessToken) {
		String url = qrcode_temp_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转成JSON字符串
		String json = JSONObject.fromObject(qrcodeTemp).toString();
		System.out.println(json);
		// 发起POST请求创建菜单
		JSONObject jsonObject = JsonUtil.httpsRequest(url, "POST", json);
		return jsonObject.toString();
	}

}
