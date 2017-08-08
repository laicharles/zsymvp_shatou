package com.wisewater.auto.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.auto.pojo.CAutoKeyword;

public interface CAutoKeywordRepository extends BaseRepository<CAutoKeyword, String> {
	
	@Query("Select CAK from CAutoKeyword CAK where CAK.token=:token and CAK.keyName=:keyName")
	List<CAutoKeyword> findByKeyName(@Param("keyName") String keyName,@Param("token") String token);
}