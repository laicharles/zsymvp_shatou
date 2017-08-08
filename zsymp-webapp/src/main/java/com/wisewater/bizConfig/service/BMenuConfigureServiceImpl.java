package com.wisewater.bizConfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.pojo.BMenuConfigure;
import com.wisewater.bizConfig.repository.BMenuConfigureRepository;
@Service
public class BMenuConfigureServiceImpl extends BaseService  implements BMenuConfigureService{
	
	@Autowired
    private BMenuConfigureRepository bMenuConfigureRepository;
	
	@Override
	public BMenuConfigure findMenuById(String id) {
		List<BMenuConfigure> bMenuConfigureList = bMenuConfigureRepository.findById(id);
		
        if (bMenuConfigureList.size()>0) {
			return bMenuConfigureList.get(0);
		}
        return null;
	}

}
