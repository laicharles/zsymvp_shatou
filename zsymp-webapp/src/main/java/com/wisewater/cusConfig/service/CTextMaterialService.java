package com.wisewater.cusConfig.service;

import com.wisewater.cusConfig.controller.CTextMaterialForm;
import com.wisewater.form.utils.JqgridListForm;

 
public interface CTextMaterialService {

    public JqgridListForm findAll(int pageNo, String sidx, String sord, String tag, String title, String token);

    public boolean save(CTextMaterialForm textMtrlForm);

    public CTextMaterialForm findById(String id);

    boolean deleteByIds(String ids);

    boolean update(CTextMaterialForm textMtrlForm);



}