package com.wisewater.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.wisewater.base.BaseRepository;
import com.wisewater.system.pojo.SOperateLog;

public interface SOperateLogRepository extends BaseRepository<SOperateLog, String> {

	@Query("select y from SOperateLog y")
	public Page<SOperateLog> findSOperateLog(Pageable pager);

}