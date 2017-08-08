package com.wisewater.auto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.auto.pojo.CAutoImgTx;
import com.wisewater.auto.repository.CAutoImgTxRepository;


@Service
public class CAutoImgTxServiceImpl extends BaseService  
    implements CAutoImgTxService{


	@Autowired
	private CAutoImgTxRepository cautoimgtxRepository;
	
	public CAutoImgTx findCAutoImgTxById(String ID){
		return cautoimgtxRepository.getOne(ID);
	}
	

}