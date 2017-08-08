package com.wisewater.function.service;

import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.controller.CMassSendForm;

 
public interface CMassSendService {

    public JqgridListForm findAll(int pageNo, String sidx, String sord, String title, String token);

    public boolean saveSendMass(CMassSendForm massSendForm);

    public boolean deleteByIds(String ids);

    public String sendMass(String id, String token);
    
    public String previewSendMass(String id, String token, String openID);
    
    public boolean updateSendMassStatus(String id, String msgId, String sendStatus);
    


}