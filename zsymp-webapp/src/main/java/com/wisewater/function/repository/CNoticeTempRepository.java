package com.wisewater.function.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.function.pojo.CNoticeTemp;


public interface CNoticeTempRepository extends BaseRepository<CNoticeTemp, String>{

	@Query("select u from CNoticeTemp u where u.token=:token and u.isLogicDel=0")
    public Page<CNoticeTemp> findAll(Pageable page, @Param("token")String token);
	
	
	@Query("select u from CNoticeTemp u where u.id in ?1 and  u.isLogicDel=0 ")
    public List<CNoticeTemp> findByIdIn(List<String> ids);
	
	
}
