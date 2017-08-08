package com.wisewater.wechatpublic.util;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisewater.wechatpublic.message.out.Template;
import com.wisewater.wechatpublic.message.out.TemplateIndustry;
import com.wisewater.wechatpublic.message.out.TemplateResult;
import com.wisewater.wechatpublic.message.out.TemplateShortID;

public class TemplateUtil {

	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	public final static String send_templeate_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	public final static String get_templeateID_url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";

	public final static String set_industry_url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";

	public static TemplateResult sendTemplate(Template temp, String accessToken) {
		TemplateResult templateResult = new TemplateResult();
		boolean result = false;
		String url = send_templeate_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转成JSON字符串
		String jsonTemp = JSONObject.fromObject(temp).toString();
		// 发起POST请求创建菜单
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonTemp);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			System.out.println("---->" + jsonObject);
			if (0 == errorCode) {
				result = true;
				String msgid = jsonObject.getString("msgid");
				templateResult.setMsgid(msgid);
			} else {
				result = false;
				templateResult.setMsgid("");
				log.error("发送模板消息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		templateResult.setResult(result);
		return templateResult;
	}

	public static String getTemplateID(TemplateShortID templateShortID, String accessToken) {
		String templateId = "";
		String url = get_templeateID_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转成JSON字符串
		String jsonMenu = JSONObject.fromObject(templateShortID).toString();
		System.out.println(jsonMenu);
		// 发起POST请求创建菜单
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			// System.out.println("----the template_id is
			// "+jsonObject.getString("template_id"));
			System.out.println(errorCode);
			System.out.println(errorMsg);
			// System.out.println(jsonObject.getString("type"));
			if (0 == errorCode) {
				templateId = jsonObject.getString("template_id");
			} else {
				templateId = "";
				log.error("获取模板ID失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return templateId;
	}

	public static boolean setIndustry(TemplateIndustry templateIndustry, String accessToken) {
		boolean result = false;

		String url = set_industry_url.replace("ACCESS_TOKEN", accessToken);

		// 将菜单对象转成JSON字符串
		String jsonTemp = JSONObject.fromObject(templateIndustry).toString();

		// 发起POST请求创建菜单
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonTemp);

		if (null != jsonObject) {

			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");

			System.out.println("---->" + jsonObject);

			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				log.error("设置所属行业失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}

		return result;
	}
}
