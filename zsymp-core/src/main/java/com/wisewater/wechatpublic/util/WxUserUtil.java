package com.wisewater.wechatpublic.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisewater.wechatpublic.model.OpenidForUser;
import com.wisewater.wechatpublic.model.UserList;
import com.wisewater.wechatpublic.model.WxUser;
import com.wisewater.wechatpublic.model.WxUserList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WxUserUtil {
	// 发送模版消息
	public static final String QFURL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";

	// 获取用户信息的接口
	public static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	// 批量获取用户OPENID的接口
	public static final String BATCH_OPENIDS = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

	// 获取更多的粉丝信息（POST）
	public final static String getmoreUserInfo_url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?"
			+ "access_token=ACCESS_TOKEN";

	// 每次获取最大关注用户数
	private static final int MAX_COUNT = 10000;

	public static boolean sendMass(String accessToken, WxUserList qf) {

		// 拼装创建菜单的url
		String url = QFURL.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonWt = JSONObject.fromObject(qf).toString();
		System.out.println("jsonWt：" + jsonWt);
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonWt);
		System.out.println("jsonObject:" + jsonObject);

		if (null != jsonObject) {
			if (0 == jsonObject.getInt("errcode")) {
				return true;
			}
			System.out.println(
					"发送群发消息失败 errcode:" + jsonObject.getInt("errcode") + ",errmsg:" + jsonObject.getString("errmsg"));
		}
		return false;
	}

	/**
	 * 
	 * @param accessToken
	 * @param openID
	 * @return gawen.chen 2015年4月10日下午3:11:04 描述：根据openID取得用户信息，默认是简体中文zh_CN
	 */
	public static WxUser getUserInfo(String accessToken, String openID) {
		return getUserInfo(accessToken, openID, "zh_CN");
	}

	/**
	 * 
	 * @param accessToken
	 * @param openID
	 * @param lang
	 * @return gawen.chen 2015年4月10日下午2:53:54 描述：根据信息openID寻找用户信息
	 */
	public static WxUser getUserInfo(String accessToken, String openID, String lang) {
		WxUser wxUser = null;

		String url = USER_INFO.replace("ACCESS_TOKEN", accessToken);
		url = url.replace("OPENID", openID);
		url = url.replace("zh_CN", lang);

		JSONObject jsonObject = CommonUtil.httpsRequest(url, "GET", null);

		if (jsonObject != null) {
			if (!jsonObject.containsKey("errcode")) {
				wxUser = new WxUser();
				wxUser.setSubscribe(jsonObject.getInt("subscribe"));
				wxUser.setOpenid(jsonObject.getString("openid"));
				wxUser.setNickname(jsonObject.getString("nickname"));
				wxUser.setSex(jsonObject.getInt("sex"));
				wxUser.setLanguage(jsonObject.getString("language"));
				wxUser.setCity(jsonObject.getString("city"));
				wxUser.setProvince(jsonObject.getString("province"));
				wxUser.setCountry(jsonObject.getString("country"));
				wxUser.setHeadimgurl(jsonObject.getString("headimgurl"));
				wxUser.setSubscribe_time(jsonObject.getInt("subscribe_time"));
				if (jsonObject.containsKey("unionid")) {
					wxUser.setUnionid(jsonObject.getString("unionid"));
				}
			}
		}

		return wxUser;
	}

	/**
	 * 获取所有openID列表
	 * 
	 * @param accessToken
	 * @return XingXingLvCha 2015年10月26日 上午10:09:26
	 */
	public static List<String> getAllUserOpenIdList(String accessToken) {
		List<String> openIDList = null;
		// 第一次获取
		WxUserList wxUserList = getUserListFromWX(accessToken, "");
		if (wxUserList != null && wxUserList.getCount() < MAX_COUNT) {
			openIDList = wxUserList.getOpenidList();
			return openIDList;
		} else if (wxUserList != null && wxUserList.getCount() == MAX_COUNT) {
			openIDList = wxUserList.getOpenidList();
			// 第二次多次获取
			while (wxUserList.getCount() == MAX_COUNT) {
				wxUserList = getUserListFromWX(accessToken, wxUserList.getNext_openid());
				openIDList.addAll(wxUserList.getOpenidList());
			}
		}
		System.out.println("总得粉丝数量是：" + wxUserList.getTotal());
		System.out.println("列表数量是：" + openIDList.size());
		return openIDList;
	}

	/**
	 * 
	 * @param accessToken
	 * @param nextOpenID
	 * @return gawen.chen 2015年4月11日上午9:09:05 描述：获取用户列表
	 */
	public static WxUserList getUserListFromWX(String accessToken, String nextOpenID) {
		WxUserList userList = null;

		String url = BATCH_OPENIDS.replace("ACCESS_TOKEN", accessToken);
		url = url.replace("NEXT_OPENID", nextOpenID);

		JSONObject jsonObject = CommonUtil.httpsRequest(url, "GET", null);

		if (jsonObject != null) {
			if (!jsonObject.containsKey("errcode")) {
				userList = new WxUserList();

				int total = jsonObject.getInt("total");
				userList.setTotal(total);
				userList.setCount(jsonObject.getInt("count"));
				List<String> openIDList = new ArrayList<String>();
				JSONObject data = jsonObject.getJSONObject("data");
				if (!data.isNullObject()) {
					JSONArray jsonArray = data.getJSONArray("openid");
					for (int i = 0; i < jsonArray.size(); i++) {
						openIDList.add(jsonArray.getString(i));
					}
				}
				userList.setOpenidList(openIDList);
				userList.setNext_openid(jsonObject.getString("next_openid"));
			}
		}

		return userList;
	}

	/**
	 * 获取多个粉丝信息
	 * 
	 * @param accessToken
	 * @param openidList
	 * @return XingXingLvCha 2015年10月23日 下午3:56:12
	 */
	public static UserList getMoreUserInfo(String accessToken, OpenidForUser openidList) {
		UserList userList = null;
		String url = getmoreUserInfo_url.replace("ACCESS_TOKEN", accessToken);
		// 将对象转成JSON字符串
		String jsonForObject = JSONObject.fromObject(openidList).toString();
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonForObject);
		if (null != jsonObject) {
			if (!jsonObject.containsKey("errcode")) {
				Gson gson = new Gson();
				userList = gson.fromJson(jsonObject.toString(), new TypeToken<UserList>() {
				}.getType());
			} else {
				System.out.println(
						"失败 errcode:" + jsonObject.getInt("errcode") + ",errmsg:" + jsonObject.getString("errmsg"));
			}

		}

		return userList;
	}

}
