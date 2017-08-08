package com.wisewater.cusConfig.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cusConfig.pojo.CMp;

public interface CMpRepository extends BaseRepository<CMp, String> {

	@Query("select u from CMp u where u.accessToken.token=:token and u.isLogicDel=0 ")
	public CMp findByToken(@Param("token") String token);

}