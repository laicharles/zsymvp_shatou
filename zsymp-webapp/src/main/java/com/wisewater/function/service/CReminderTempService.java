package com.wisewater.function.service;



import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.controller.CReminderTempForm;


import com.wisewater.function.pojo.CReminderTemp;


public interface CReminderTempService {
	
	public JqgridListForm findAll(int pageNo, String sidx, String sord, String token);

    public CReminderTemp save(CReminderTempForm reminderTempForm);

    public CReminderTempForm findById(String id);

    boolean deleteByIds(String ids);
    
    boolean sendReminderTempById(String id, String token);
    
    public boolean sendReminderTempByIdForBindUser(String id, String token) ;

    boolean previewReminderTempById(String id,BCustomerUserForm user, String auditorOpenId ,String submitBinOpendId,String token);
    
    boolean update(CReminderTempForm reminderTempForm);
    
    boolean initTempRelated(boolean updateApp, String appID, String appSecret, String token, String accessToken);
    
}
