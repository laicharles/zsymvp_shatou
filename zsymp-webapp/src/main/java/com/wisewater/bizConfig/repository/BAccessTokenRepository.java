package com.wisewater.bizConfig.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.bizConfig.pojo.BAccessToken;

public interface BAccessTokenRepository extends BaseRepository<BAccessToken, String> {
	
	@Query("select u from BAccessToken u where u.token=:token")
	public BAccessToken findByToken(@Param("token")String token);
	
}