package com.wisewater.wechatpublic.kf;

import java.util.List;

public class Kflist {

	public List<Kf> kf_list;
	
	public static class Kf {
	
		public String kf_account;
	
		public String kf_headimgurl;
	
		public String kf_id;
	
		public String kf_nick;

		public String getKf_account() {
			return kf_account;
		}

		public void setKf_account(String kf_account) {
			this.kf_account = kf_account;
		}

		public String getKf_headimgurl() {
			return kf_headimgurl;
		}

		public void setKf_headimgurl(String kf_headimgurl) {
			this.kf_headimgurl = kf_headimgurl;
		}

		public String getKf_id() {
			return kf_id;
		}

		public void setKf_id(String kf_id) {
			this.kf_id = kf_id;
		}

		public String getKf_nick() {
			return kf_nick;
		}

		public void setKf_nick(String kf_nick) {
			this.kf_nick = kf_nick;
		}
	}

	public List<Kf> getKf_list() {
		return kf_list;
	}

	public void setKf_list(List<Kf> kf_list) {
		this.kf_list = kf_list;
	}
	
}
