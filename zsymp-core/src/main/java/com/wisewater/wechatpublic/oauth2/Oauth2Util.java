package com.wisewater.wechatpublic.oauth2;


import net.sf.json.JSONObject;

import com.wisewater.wechatpublic.util.CommonUtil;

public class Oauth2Util {

	public final static String GET_OAUTH2_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	public final static String GET_OAUTH2_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	public final static String GET_OPENID = "snsapi_base";

	public final static String GET_USERINFO = "snsapi_userinfo";

	/**
	 * 
	 * 描述：构造OAUTH2.0 URL
	 * @author AlexFung
	 * 2016年1月25日 下午4:34:04
	 * @param appid
	 * @param redirectUri
	 * @param state
	 * @return
	 */
	public static String buildAuthorizationUrl(String appid, String redirectUri, String state) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?";
		url += "appid=" + appid;
		url += "&redirect_uri=" + URIUtil.encodeURIComponent(redirectUri);
		url += "&response_type=code";
		url += "&scope=snsapi_base";
		if (state != null) {
			url += "&state=" + state;
		}
		url += "#wechat_redirect";
		return url;
	}
	
	/**
	 * 
	 * 描述：获取openID
	 * @author AlexFung
	 * 2016年1月25日 下午4:57:26
	 * @param code
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static String oauth2getOpenID(String code,String appid,String appsecret){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?";
		url += "appid="+appid;
		url += "&secret="+appsecret;
		url += "&code="+code;
		url += "&grant_type=authorization_code";
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "GET", "");
		System.out.println("jsonObject="+jsonObject);
		return jsonObject.getString("openid");
	}
	
	public static void main(String[] args) {
		String url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=lEsVlcJdWmSpmgwWSgl7NQ6ePE9ORauMgabmeSqrrw_Olde74Nen-h2Aoh2bZnYqbQSODkui6hr8sidw_hQa4BlM6KQjMgSwA1PbIrI6dLEYBOhABACFS";
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "GET", "");
		System.out.println(jsonObject.getString("groups"));
	}

}
