package com.wisewater.wechatpublic.model;

import java.util.List;

public class OpenidForUser {

	public List<OpenidList> user_list;
	
	
	public static class OpenidList {
		
		public String openid;
		
		public String lang;

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

		public String getLang() {
			return lang;
		}

		public void setLang(String lang) {
			this.lang = lang;
		}
	}


	public List<OpenidList> getUser_list() {
		return user_list;
	}


	public void setUser_list(List<OpenidList> user_list) {
		this.user_list = user_list;
	}
}
