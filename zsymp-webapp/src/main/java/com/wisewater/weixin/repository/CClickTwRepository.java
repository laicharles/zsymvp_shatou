package com.wisewater.weixin.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.weixin.pojo.CClickTw;

public interface CClickTwRepository extends BaseRepository<CClickTw, String> {
	
	
	@Query("select u from CClickTw u where u.token=:token and u.twType.logicID=:twType and u.isLogicDel=0")
	public CClickTw findByToken(@Param("token") String token,@Param("twType") String twType);
}