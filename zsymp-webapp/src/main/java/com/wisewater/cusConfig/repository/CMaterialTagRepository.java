package com.wisewater.cusConfig.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cusConfig.pojo.CMaterialTag;

public interface CMaterialTagRepository extends BaseRepository<CMaterialTag, String> {
    
    @Query("select u from CMaterialTag u where u.value=:value and u.token=:token and u.isLogicDel=0 ")
    public CMaterialTag findByValueAndToken(@Param("value") String value, @Param("token") String token);
	
}