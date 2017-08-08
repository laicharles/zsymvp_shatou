package com.wisewater.feedback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cms.pojo.CCms;
import com.wisewater.feedback.pojo.CFeedBackManage;

public interface FeedBackRepository extends BaseRepository<CFeedBackManage, String> {
	
	
	@Query("select y from CFeedBackManage y where y.token=:token and y.feedbackType=:feedbackType and y.isDelete=0")
	public Page<CFeedBackManage> findCmsByToken(@Param("token") String token, @Param("feedbackType") int feedbackType, Pageable pager);
}
