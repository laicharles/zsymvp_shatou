package com.wisewater.cusConfig.service;

import com.wisewater.cusConfig.controller.CPicMaterialForm;
import com.wisewater.form.utils.JqgridListForm;

 
public interface CPicMaterialService {

    public JqgridListForm findAll(int pageNo, String sidx, String sord, String tag, String title, String token);

    public boolean save(CPicMaterialForm mtrlForm, String token);

    public CPicMaterialForm findById(String id);

    boolean deleteByIds(String ids);

    boolean update(CPicMaterialForm mtrlForm, String token);

}