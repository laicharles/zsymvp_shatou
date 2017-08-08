package com.wisewater.wechatpublic.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.wisewater.wechatpublic.pojo.Token;


public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	// 凭证获取（GET）
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?"
			+ "grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	// 凭证获取（GET）
	public final static String apiTicket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
			+ "access_token=ACCESS_TOKEN&type=wx_card";
	
	// 凭证获取（GET）
	public final static String jsapiTicket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
			+ "access_token=ACCESS_TOKEN&type=jsapi";
	
	
	
	

	public static JSONObject httpsRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们制定的新人管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);

			// 当outputStr不为null时，向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("链接超时：{}", ce);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static Token getToken(String appid, String appsecret) {
		Token token = null;
		String requestUrl = token_url.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				token = null;
				// 获取token失败
				log.error("获取token失败errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return token;
	}
	
	public static String getAPITicket(String accessToken) {
		String result = "";
		String url = apiTicket_url.replace("ACCESS_TOKEN", accessToken);
		
		JSONObject jsonObject = httpsRequest(url, "GET", null);

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = jsonObject.getString("ticket");
			} else {
				log.error("删除客服帐号 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}
	
	
	public static String getJSAPITicket(String accessToken) {
		String result = "";
		String url = jsapiTicket_url.replace("ACCESS_TOKEN", accessToken);
		
		JSONObject jsonObject = httpsRequest(url, "GET", null);

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = jsonObject.getString("ticket");
			} else {
				log.error("删除客服帐号 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}
	
	
	
}
