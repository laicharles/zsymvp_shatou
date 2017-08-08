package com.wisewater.cusConfig.service;

import com.wisewater.cusConfig.controller.CPageMaterialForm;
import com.wisewater.form.utils.JqgridListForm;

 
public interface CPageMaterialService {

    public JqgridListForm findAll(int pageNo, String sidx, String sord, String tag, String title, String token);

    public boolean save(CPageMaterialForm mtrlForm, String token);

    public CPageMaterialForm findById(String id);

    boolean deleteByIds(String ids);

    boolean update(CPageMaterialForm mtrlForm, String token);

}