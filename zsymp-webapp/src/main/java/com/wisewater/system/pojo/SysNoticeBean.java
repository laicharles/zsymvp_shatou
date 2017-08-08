package com.wisewater.system.pojo;

public class SysNoticeBean {
	
	// 通知消息的内容
	private String msg;
	
	// 通知消息的链接（直接跳转至配置页面）
	private String url;
	
	public SysNoticeBean(String msg, String url) {
		super();
		this.msg = msg;
		this.url = url;
	}


	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
}

