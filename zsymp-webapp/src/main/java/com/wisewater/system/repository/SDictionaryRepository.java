package com.wisewater.system.repository;


import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.wisewater.base.BaseRepository;
import com.wisewater.system.pojo.SDictionary;

public interface SDictionaryRepository extends BaseRepository<SDictionary, String> {
	
	@Cacheable(value="dictionaryCache")
	public SDictionary findByLogicID(String logicID);
	
	public List<SDictionary> findByTypeCode(String typeCode);
	
	public SDictionary findByTypeCodeAndDicValue(String typeCode,String dicValue) ;
}