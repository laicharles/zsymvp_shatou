package com.wisewater.function.service;

import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.controller.CStopWtrTempForm;
import com.wisewater.function.pojo.CStopWtrTemp;

 
public interface CStopWtrTempService {

    public JqgridListForm findAll(int pageNo, String sidx, String sord, String token);

    public CStopWtrTemp save(CStopWtrTempForm stopWtrTempForm);

    public CStopWtrTempForm findById(String id);

    boolean deleteByIds(String ids);
    
    boolean sendStopWtrTempById(String id, String token);
    
    boolean sendStopWtrTempByIdForBindUser(String id, String token);

    boolean previewStopWtrTempById(String id,BCustomerUserForm user, String auditorOpenId ,String submitBinOpendId,String token);
    
    boolean update(CStopWtrTempForm stopWtrTempForm);
    
    boolean initTempRelated(boolean updateApp, String appID, String appSecret, String token, String accessToken);

}