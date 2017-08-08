package com.wisewater.function.service;

import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.controller.CNoticeTempForm;
import com.wisewater.function.pojo.CNoticeTemp;

public interface CNoticeTempService {

	public JqgridListForm findAll(int pageNo, String sidx, String sord, String token);
	
	public CNoticeTemp save(CNoticeTempForm noticeTempForm);
	
	boolean deleteByIds(String ids);
	
	public CNoticeTempForm findById(String id);
	
	boolean update(CNoticeTempForm noticeTempForm);
	
	boolean sendNoticeTempById(String id, String token);
	
	boolean sendNoticeTempByIdForBindUser(String id, String token);

	boolean previewNoticeTempById(String id,BCustomerUserForm user, String auditorOpenId ,String submitBinOpendId,String token);
	
}
