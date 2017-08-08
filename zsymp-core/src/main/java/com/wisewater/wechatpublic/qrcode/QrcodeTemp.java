package com.wisewater.wechatpublic.qrcode;

import com.wisewater.wechatpublic.qrcode.Qrcode.Action_info;

public class QrcodeTemp {
	
	private int expire_seconds;

	private String action_name = "QR_SCENE";

	private Action_info action_info;

	public String getAction_name() {
		return action_name;
	}

	public Action_info getAction_info() {
		return action_info;
	}

	public void setAction_info(Action_info action_info) {
		this.action_info = action_info;
	}
	
	public int getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

}
