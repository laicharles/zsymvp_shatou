package com.wisewater.bizConfig.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.bizConfig.pojo.BCustomer;

public interface BCustomerRepository extends BaseRepository<BCustomer, String> {
	
	@Query("select u from BCustomer u where u.id=:id and u.isLogicDel=0")
	public BCustomer findById(@Param("id")String id);
	
}