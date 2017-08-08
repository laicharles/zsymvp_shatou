package com.wisewater.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.wisewater.base.BaseRepository;
import com.wisewater.system.pojo.SFansOperateLog;

public interface SFansOperateLogRepository extends BaseRepository<SFansOperateLog, String> {

	@Query("select y from SFansOperateLog y")
	public Page<SFansOperateLog> findSFansOperateLog(Pageable pager);

}