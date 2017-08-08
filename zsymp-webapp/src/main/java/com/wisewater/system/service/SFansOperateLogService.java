package com.wisewater.system.service;

import com.wisewater.form.utils.JqgridListForm;

public interface SFansOperateLogService {

	/**
	 * 
	 * 描述：查看粉丝操作日志
	 * @author AlexFung
	 * 2016年8月3日 下午1:52:36
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public JqgridListForm findSFansOperateLog(int pageNo, String sidx, String sord);

}