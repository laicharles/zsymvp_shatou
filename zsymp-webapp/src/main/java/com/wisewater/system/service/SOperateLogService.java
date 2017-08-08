package com.wisewater.system.service;

import com.wisewater.form.utils.JqgridListForm;

public interface SOperateLogService {

	/**
	 * 
	 * 描述：查看后台操作日志
	 * @author AlexFung
	 * 2016年8月3日 下午1:52:36
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public JqgridListForm findSOperateLog(int pageNo, String sidx, String sord);

}