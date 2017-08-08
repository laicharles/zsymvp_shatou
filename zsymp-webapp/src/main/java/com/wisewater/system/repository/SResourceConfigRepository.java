package com.wisewater.system.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.system.pojo.SResource;

public interface SResourceConfigRepository extends BaseRepository<SResource, String> {
	
	/**
	 * 确认该查单是否是特殊菜单
	 * @param resID
	 * @return
	 */
	@Query("select count(id) from SResourceConfig u where u.resID=:resID ")
	public int findResourceByResID(@Param("resID") String resID);
	
	
	/**
	 * 确认该用户是否能显示该菜单
	 * @param resID
	 * @param token
	 * @return
	 */
	@Query("select count(id) from SResourceConfig u where u.resID=:resID and u.token=:token")
	public int findResourceByResIDNToken(@Param("resID") String resID,@Param("token") String token);
	
	
}