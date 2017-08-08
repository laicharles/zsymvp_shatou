package com.wisewater.wechatpublic.kf;

import java.util.List;

public class Kfonlinelist {

	public List<Kf> kf_online_list;
	
	public static class Kf {
		
		//客服账号
		public String kf_account;
		
		//在线状态 1：pc在线，2：手机在线。若pc和手机同时在线则为 1+2=3
		public String status;
		
		//客服工号
		public String kf_id;
		
		//最大自动接入数
		public String auto_accept;
		
		//当前正在接待的会话数
		public String accepted_case;

		public String getKf_account() {
			return kf_account;
		}

		public void setKf_account(String kf_account) {
			this.kf_account = kf_account;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getKf_id() {
			return kf_id;
		}

		public void setKf_id(String kf_id) {
			this.kf_id = kf_id;
		}

		public String getAuto_accept() {
			return auto_accept;
		}

		public void setAuto_accept(String auto_accept) {
			this.auto_accept = auto_accept;
		}

		public String getAccepted_case() {
			return accepted_case;
		}

		public void setAccepted_case(String accepted_case) {
			this.accepted_case = accepted_case;
		}
	}

	public List<Kf> getKf_online_list() {
		return kf_online_list;
	}

	public void setKf_online_list(List<Kf> kf_online_list) {
		this.kf_online_list = kf_online_list;
	}
}
