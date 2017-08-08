package com.wisewater.system.service;

import java.util.List;

import com.wisewater.system.controller.SDictionaryForm;
import com.wisewater.system.pojo.SDictionary;

 
public interface SDictionaryService {


	public List<SDictionaryForm> findByTypeCode(String typeCode);
	
	
	public SDictionaryForm findByTypeCodeAndDicValue(String typeCode ,String dicValue);
	
	public SDictionary findByLogicID(String logicID);

}