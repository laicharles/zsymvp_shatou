package com.wisewater.auto.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.auto.pojo.CSelfTag;

public interface CSelfTagRepository extends BaseRepository<CSelfTag, String> {
	
	@Query("Select CSF from CSelfTag CSF where CSF.isLogicDel=0 and CSF.token=:token and CSF.name=:name")
	public CSelfTag findByNameByToken(@Param("name") String name,@Param("token") String token);
	
	@Query("Select CSF from CSelfTag CSF where CSF.isLogicDel=0 and CSF.token=:token")
	public List<CSelfTag> findAllCSelfTag(@Param("token") String token);
}