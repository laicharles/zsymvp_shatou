package com.wisewater.function.service;

import java.util.List;

import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.controller.CFeedBackPersonForm;
import com.wisewater.function.pojo.CFeedBackPerson;

public interface CFeedBackPersonService {
	
	
	public JqgridListForm showAllPerson(int pageNo,String sidx,String sord,String token);
	
	public CFeedBackPersonForm findById(String id);
	
	public CFeedBackPerson updateCFeedBackPerson(CFeedBackPersonForm cFeedBackPersonForm);
	
	public String deleteCFeedBackPersonBatch(String ids);
	
	public boolean saveCFeedBackPerson(CFeedBackPersonForm cFeedBackPersonForm);
}
