/**
 * 
 */
package com.wisewater.faced.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.wisewater.faced.model.BillBean;
import com.wisewater.faced.model.BillContentBean;
import com.wisewater.faced.model.BindUserBean;

import net.sf.json.JSONObject;

/**
 * 
 * 描述：读取socket通用方法
 * 
 * @author AlexFung 2016年9月8日 下午4:54:19
 */
public class MethodClient {

	public BindUserBean bindUser(String account_no) throws UnsupportedEncodingException {
		WebSocketClient webSocketClient = new WebSocketClient();
		String jsonString = webSocketClient.SocketClient(11, account_no);
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		try {
			return (BindUserBean) JSONObject.toBean(jsonObject, BindUserBean.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		MethodClient MethodClient = new MethodClient();
		System.out.println(MethodClient.waterBill("").getBill_content());
	}

	/**
	 * 
	 * 描述：获取水费账单
	 * 
	 * @author AlexFung 2016年9月8日 下午4:54:40
	 * @param account_no
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	public BillBean waterBill(String account_no) throws UnsupportedEncodingException {
		WebSocketClient webSocketClient = new WebSocketClient();
		String jsonString = webSocketClient.SocketClient(13, account_no);
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		try {
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("bill_content", BillContentBean.class); 
			return (BillBean) JSONObject.toBean(jsonObject, BillBean.class,classMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// 字符串位数不够补零
	public static String normsStr(String src, int num) throws UnsupportedEncodingException {

		StringBuffer sbf = new StringBuffer(src);
		for (int i = 0; i < num - src.getBytes("gbk").length; i++) {
			sbf.append(" ");
		}
		return new String(sbf);
	}

}
