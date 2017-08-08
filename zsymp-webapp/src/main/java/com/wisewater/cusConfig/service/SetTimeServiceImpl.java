package com.wisewater.cusConfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.cusConfig.pojo.TimeSet;
import com.wisewater.cusConfig.repository.SetTimeRepository;
@Service
public class SetTimeServiceImpl extends BaseService implements SetTimeService{

	@Autowired
	private SetTimeRepository setTimeRepository;
	
	@Override
	public boolean save(TimeSet timeSet) {
		TimeSet timeset = setTimeRepository.save(timeSet);
		
		if (timeset != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<TimeSet> count() {
		List<TimeSet> list = setTimeRepository.findTimeSet();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
