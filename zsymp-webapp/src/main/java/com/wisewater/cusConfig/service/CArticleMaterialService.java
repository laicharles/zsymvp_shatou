package com.wisewater.cusConfig.service;

import com.wisewater.cusConfig.controller.CArticleMaterialForm;
import com.wisewater.form.utils.JqgridListForm;

 
public interface CArticleMaterialService {

    public JqgridListForm findAll(int pageNo, String sidx, String sord, String tag, String title, String token);

    public boolean save(CArticleMaterialForm articleMtrlForm, String token);

    public CArticleMaterialForm findById(String id);

    boolean deleteByIds(String ids);

    boolean update(CArticleMaterialForm articleMtrlForm, String token);

}