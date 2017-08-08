package com.wisewater.wechatpublic.util;


import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisewater.wechatpublic.kf.KfAccount;
import com.wisewater.wechatpublic.kf.Kflist;
import com.wisewater.wechatpublic.kf.Kfonlinelist;

public class KfUtil {

	private static Logger log = LoggerFactory.getLogger(KfUtil.class);
	
	
	//添加客服（POST）
	public final static String kfaccount_add_url = "https://api.weixin.qq.com/customservice/kfaccount/add?"
			+ "access_token=ACCESS_TOKEN";
	
	//更新客服（POST）
	public final static String kfaccount_update_url = "https://api.weixin.qq.com/customservice/kfaccount/update?"
			+ "access_token=ACCESS_TOKEN";
	
	//删除客服（GET）
	public final static String kfaccount_delete_url = "https://api.weixin.qq.com/customservice/kfaccount/del?"
			+ "access_token=ACCESS_TOKEN&kf_account=KFACCOUNT";
	
	//所有客服（GET）
	public final static String getkflist_url = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?"
			+ "access_token=ACCESS_TOKEN";
	
	//所有在线客服（GET）
	public final static String getkfonlinelist_url = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?"
			+ "access_token=ACCESS_TOKEN";
	
	
	/**
	 * 添加客服帐号
	 * @param kfAccount
	 * @param accessToken
	 * @return
	 * XingXingLvCha
	 * 2015年7月7日 下午3:04:28
	 */
	public static boolean addKfAccount(KfAccount kfAccount, String accessToken){
		boolean result = false;
		String url = kfaccount_add_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转成JSON字符串
		String jsonMenu = JSONObject.fromObject(kfAccount).toString();
		System.out.println(jsonMenu);
		// 发起POST请求创建菜单
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				log.error("添加客服帐号 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}
	
	
	/**
	 * 更新客服帐号
	 * @param kfAccount
	 * @param accessToken
	 * @return
	 * XingXingLvCha
	 * 2015年7月7日 下午3:04:28
	 */
	public static boolean updateKfAccount(KfAccount kfAccount, String accessToken){
		boolean result = false;
		String url = kfaccount_update_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转成JSON字符串
		String jsonMenu = JSONObject.fromObject(kfAccount).toString();
		System.out.println(jsonMenu);
		// 发起POST请求创建菜单
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				log.error("更新客服帐号 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}
	
	/**
	 * 删除客服帐号
	 * @param kfAccount
	 * @param accessToken
	 * @return
	 * XingXingLvCha
	 * 2015年7月7日 下午3:04:28
	 */
	public static boolean deleteKfAccount(String kfAccount, String accessToken){
		boolean result = false;
		String url = kfaccount_delete_url.replace("ACCESS_TOKEN", accessToken).replace("KFACCOUNT", kfAccount);
		
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "GET", null);

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				log.error("删除客服帐号 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}
	
	/**
	 * 获取所有客服信息
	 * @param accessToken
	 * @return
	 * XingXingLvCha
	 * 2015年7月10日 下午4:22:27
	 */
	public static Kflist findKfList(String accessToken) {
		Kflist kflist = null;
		String requestUrl = getkflist_url.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求查询菜单
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			Gson gson = new Gson();
			kflist = gson.fromJson(jsonObject.toString(), new TypeToken<Kflist>(){}.getType());
		}
		
		return kflist;
	}
	
	
	/**
	 * 获取所有在线客服信息
	 * @param accessToken
	 * @return
	 * XingXingLvCha
	 * 2015年7月10日 下午4:22:27
	 */
	public static Kfonlinelist findKfonlineList(String accessToken) {
		Kfonlinelist kfonlinelist = null;
		String requestUrl = getkfonlinelist_url.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求查询菜单
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			Gson gson = new Gson();
			kfonlinelist = gson.fromJson(jsonObject.toString(), new TypeToken<Kfonlinelist>(){}.getType());
		}
		
		return kfonlinelist;
	}
}
