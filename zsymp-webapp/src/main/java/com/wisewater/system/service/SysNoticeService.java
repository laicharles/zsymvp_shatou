package com.wisewater.system.service;

import java.util.List;

import com.wisewater.system.pojo.SysNoticeBean;

public interface SysNoticeService {

	/**
	 * 系统通知
	 * @param token
	 * @return
	 */
	public List<SysNoticeBean> findNotices(String token);

}