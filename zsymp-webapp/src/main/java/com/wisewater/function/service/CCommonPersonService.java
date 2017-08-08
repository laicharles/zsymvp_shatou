package com.wisewater.function.service;

import java.util.List;

import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.controller.CCommonPersonForm;
import com.wisewater.function.pojo.CCommonPerson;


public interface CCommonPersonService {

	public JqgridListForm findCCommonPersonByToken(String token, int pageNo,String sidx,String sord);

	public CCommonPersonForm findCCommonPersonById(String id);

	public CCommonPerson updateCCommonPerson(CCommonPersonForm cCommonPersonFrom);

	public String deleteCCommonPersonbatch(String idStr);

	public boolean saveCCommonPerson(CCommonPersonForm cCommonPersonFrom);
	
	public List<CCommonPersonForm> findCCommonPersonByToken(String token);
}
