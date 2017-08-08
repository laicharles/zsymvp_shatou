package com.wisewater.function.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.function.pojo.CTemplateId;

public interface CTemplateIdRepository extends BaseRepository<CTemplateId, String>{

	@Query("select u from CTemplateId u where u.token=:token ")
    public CTemplateId findByToken(@Param("token") String token);
}
