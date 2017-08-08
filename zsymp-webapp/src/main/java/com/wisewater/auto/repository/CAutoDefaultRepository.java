package com.wisewater.auto.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.auto.pojo.CAutoDefault;

public interface CAutoDefaultRepository extends BaseRepository<CAutoDefault, String> {
	
	/**
	 * 通过token查
	 * @param token
	 * @return
	 * AlexFung
	 * 2015年6月11日 下午12:05:07
	 */
	@Query("select c from CAutoDefault c where c.token=:token and c.isLogicDel=0")
	public List<CAutoDefault> findDefaultByToken(@Param("token")String token);
	
}