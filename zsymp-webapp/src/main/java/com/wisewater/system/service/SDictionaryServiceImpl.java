package com.wisewater.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.system.controller.SDictionaryForm;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;


@Service
public class SDictionaryServiceImpl extends BaseService  
    implements SDictionaryService{


	@Autowired
	private SDictionaryRepository sdictionaryRepository;

	@Override
	public List<SDictionaryForm> findByTypeCode(String typeCode) {
		
		List<SDictionary> dictionaries = sdictionaryRepository.findByTypeCode(typeCode);
		List<SDictionaryForm> dictionaryForms = new ArrayList<SDictionaryForm>();
		
		for(SDictionary dictionary : dictionaries){
			dictionaryForms.add(mapper.map(dictionary, SDictionaryForm.class));
		}
		
		return dictionaryForms;
	}

	@Override
	public SDictionaryForm findByTypeCodeAndDicValue(String typeCode,
			String dicValue) {
		SDictionary dictionary = sdictionaryRepository.findByTypeCodeAndDicValue(typeCode, dicValue);
		
		SDictionaryForm dictionaryForm = null;
		if(dictionary!=null){
			dictionaryForm = mapper.map(dictionary, SDictionaryForm.class);
		}
		
		return dictionaryForm;
	}
	
	@Override
	public SDictionary findByLogicID(String logicID){
		SDictionary dictionary = sdictionaryRepository.findByLogicID(logicID);
		return dictionary;
	}
	

}