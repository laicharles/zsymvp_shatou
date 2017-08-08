package com.wisewater.auto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.auto.pojo.CAutoAr;
import com.wisewater.auto.repository.CAutoArRepository;


@Service
public class CAutoArServiceImpl extends BaseService  
    implements CAutoArService{


	@Autowired
	private CAutoArRepository cautoarRepository;

	/**
	 * 获取文章
	 * @param ID
	 * @return
	 * XingXingLvCha
	 * 2015年4月10日 下午3:15:45
	 */
	@Override
	public CAutoAr findCAutoArById(String ID){
		return cautoarRepository.getOne(ID);
	}
}