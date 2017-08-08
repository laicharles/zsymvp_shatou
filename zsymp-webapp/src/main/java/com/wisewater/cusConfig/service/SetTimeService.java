package com.wisewater.cusConfig.service;

import java.util.List;

import com.wisewater.cusConfig.pojo.TimeSet;

public interface SetTimeService {
	
	public boolean save(TimeSet timeSet);
	
	public List<TimeSet> count() ;

}
