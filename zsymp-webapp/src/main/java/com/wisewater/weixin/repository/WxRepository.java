package com.wisewater.weixin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.fans.pojo.CFan;
import com.wisewater.weixin.pojo.Wx;

public interface WxRepository  extends BaseRepository<Wx, Integer>{

	@Query(" select u from Wx u where u.wxAccount=:wx_account")
	public Wx selectBywx(@Param("wx_account") String wx_account);
	
	@Query(" select cf from CFan cf where cf.openID=:openID")
	public CFan selectCFanByopenID(@Param("openID") String openID);
}
