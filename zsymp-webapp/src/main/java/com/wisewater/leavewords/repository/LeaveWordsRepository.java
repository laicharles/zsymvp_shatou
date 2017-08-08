package com.wisewater.leavewords.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cms.pojo.CCms;
import com.wisewater.feedback.pojo.CFeedBackManage;
import com.wisewater.leavewords.pojo.CLeaveWordsManage;

public interface LeaveWordsRepository extends BaseRepository<CLeaveWordsManage, String> {
	
	
	@Query("select y from CLeaveWordsManage y where y.token=:token and y.leavewords=:leavewords and y.isDelete=0")
	public Page<CLeaveWordsManage> findCmsByToken(@Param("token") String token, @Param("leavewords") int leavewords, Pageable pager);
}
