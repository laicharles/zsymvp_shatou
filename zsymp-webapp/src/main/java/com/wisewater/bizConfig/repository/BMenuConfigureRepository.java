package com.wisewater.bizConfig.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.bizConfig.pojo.BMenuConfigure;

public interface BMenuConfigureRepository extends BaseRepository<BMenuConfigure, String> {
	
	@Query("select u from BMenuConfigure u where u.id=:id")
	public List<BMenuConfigure> findById(@Param("id")String id);
	
}