package com.wisewater.system.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.wisewater.base.BaseRepository;
import com.wisewater.system.pojo.SLoginLog;

public interface SLoginLogRepository extends BaseRepository<SLoginLog, String> {
	
	public List<SLoginLog> findByVisitorAndLogoutTimeIsNull(String visitor);
	
	@Query("select y from SLoginLog y where y.isLogicDel=0")
	public Page<SLoginLog> findSLoginLog(Pageable pager);
}