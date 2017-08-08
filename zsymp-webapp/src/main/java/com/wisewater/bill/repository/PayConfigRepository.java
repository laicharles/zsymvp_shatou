package com.wisewater.bill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.bill.pojo.PayConfig;


public interface PayConfigRepository extends BaseRepository<PayConfig, String>{
	
	@Query("select y from PayConfig y where y.token=:token")
	public List<PayConfig> findPayOrderByOrderNo(@Param("token") String token);
}
