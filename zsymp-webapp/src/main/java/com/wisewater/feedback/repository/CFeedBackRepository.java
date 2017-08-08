package com.wisewater.feedback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cms.pojo.CCms;
import com.wisewater.feedback.pojo.CFeedBackManage;

public interface CFeedBackRepository extends BaseRepository<CFeedBackManage, String> {
	
	
	@Query("select y from CFeedBackManage y where y.feedbackType.logicID=:feedbackType and y.isDelete=0")
	public Page<CFeedBackManage> findFeedBackByFeedbackType(@Param("feedbackType") String feedbackType, Pageable pager);
	
	@Query("select f from CFeedBackManage f where f.id=:id and  f.isDelete=0 ")
	public CFeedBackManage findById(@Param("id") String id);
	
	@Query("select y from CFeedBackManage y where y.openId=:openId and y.isDelete=0 and y.feedbackType.logicID=:feedbackType")
	public Page<CFeedBackManage> showFeedbackList(@Param("openId") String openId,@Param("feedbackType") String feedbackType,@Param("pager") Pageable pager);
	
	@Query("select count(*) from CFeedBackManage y where y.openId=:openId and y.isDelete=0")
	public Long findAllCount(@Param("openId") String openId);
}
