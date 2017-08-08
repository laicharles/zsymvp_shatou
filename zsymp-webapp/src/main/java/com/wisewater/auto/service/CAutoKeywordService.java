package com.wisewater.auto.service;

import java.util.List;

import com.wisewater.auto.pojo.CAutoKeyword;

 
public interface CAutoKeywordService {


	/**
	 * 通过关键字找自动回复 （完全匹配）
	 * @param keyName
	 * @return
	 * XingXingLvCha
	 * 2015年4月10日 上午10:39:23
	 */
	public List<CAutoKeyword> findCAutoByKeyName(String keyName,String token);

}