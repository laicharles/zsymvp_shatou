package com.wisewater.auto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.auto.pojo.CAutoKeyword;
import com.wisewater.auto.repository.CAutoKeywordRepository;


@Service
public class CAutoKeywordServiceImpl extends BaseService  
    implements CAutoKeywordService{


	@Autowired
	private CAutoKeywordRepository cautokeywordRepository;

	/**
	 * 通过关键字找自动回复 （完全匹配）
	 * @param keyName
	 * @return
	 * XingXingLvCha
	 * 2015年4月10日 上午10:39:23
	 */
	@Override
	public List<CAutoKeyword> findCAutoByKeyName(String keyName,String token){
		List<CAutoKeyword> cautoKeywordList = cautokeywordRepository.findByKeyName(keyName,token);
		
		return cautoKeywordList;
	}
}