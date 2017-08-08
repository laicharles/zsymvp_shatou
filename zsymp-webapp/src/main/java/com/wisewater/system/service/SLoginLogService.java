package com.wisewater.system.service;

import com.wisewater.form.utils.JqgridListForm;

public interface SLoginLogService {

	/**
	 * 
	 * 描述：后台登录日志
	 * @author AlexFung
	 * 2016年8月3日 下午2:50:12
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public JqgridListForm findSLoginLog(int pageNo, String sidx, String sord);

}