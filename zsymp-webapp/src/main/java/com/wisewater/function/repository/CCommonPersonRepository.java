package com.wisewater.function.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.function.pojo.CCommonPerson;


public interface CCommonPersonRepository extends BaseRepository<CCommonPerson, String>{

	/**
	 * 根据token查询列表
	 * @param token
	 * @param pager
	 * @return
	 * AlexFung
	 * 2015年8月28日 下午1:35:47
	 */
	@Query("select y from CCommonPerson y where y.token=:token and y.isLogicDel=0")
	public Page<CCommonPerson> findCCommonPersonByToken(@Param("token") String token,Pageable pager);
	
	/**
	 * 根据token列表
	 * @param token
	 * @return
	 * XingXingLvCha
	 * 2015年8月28日 下午3:43:09
	 */
	@Query("select y from CCommonPerson y where y.token=:token and y.isLogicDel=0")
	public List<CCommonPerson> findCCommonPersonByToken(@Param("token") String token);
}
