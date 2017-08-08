package com.wisewater.auto.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.auto.pojo.CAuto;

public interface CAutoRepository extends BaseRepository<CAuto, String> {
	
	/**
	 * 知识库列表
	 * @param pager
	 * @return
	 * XingXingLvCha
	 * 2015年4月7日 上午10:00:50
	 */
	@Query("Select distinct CA from CAuto CA left join  CA.CAutoKeywords Know left join  CA.CSelfTags Tag"
	 +" where CA.token=:token and CA.isLogicDel=0 "
	 +" and (:question is null or CHAR_LENGTH(:question)=0 or CA.question like :question)"
	 +" and (:cautoKeyword is null or CHAR_LENGTH(:cautoKeyword)=0 or Know.keyName = :cautoKeyword)"
	 +" and (:cautoTag is null or CHAR_LENGTH(:cautoTag)=0 or Tag.id = :cautoTag)")
	public Page<CAuto> findAllCAuto(@Param("question") String question,@Param("cautoKeyword") String cautoKeyword,@Param("cautoTag") String cautoTag,@Param("token") String token,Pageable pager);
}