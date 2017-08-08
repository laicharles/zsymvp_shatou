package com.wisewater.leavewords.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cms.pojo.CCms;
import com.wisewater.leavewords.pojo.CLeaveWordsManage;

public interface CLeaveWordsRepository extends BaseRepository<CLeaveWordsManage, String> {
	
	
	@Query("select y from CLeaveWordsManage y where y.leavewords.logicID=:leavewords and y.isDelete=0")
	public Page<CLeaveWordsManage> findLeaveWordsByLeavewords(@Param("leavewords") String leavewords, Pageable pager);
	
	@Query("select f from CLeaveWordsManage f where f.id=:id and  f.isDelete=0 ")
	public CLeaveWordsManage findById(@Param("id") String id);
	
	@Query("select y from CLeaveWordsManage y where y.openId=:openId and y.isDelete=0")
	public Page<CLeaveWordsManage> showLeavewordsList(@Param("openId") String openId, Pageable pager);
	
	@Query("select count(*) from CLeaveWordsManage y where y.openId=:openId and y.isDelete=0")
	public Long findAllCount(@Param("openId") String openId);
}
