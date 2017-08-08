package com.wisewater.system.repository;


import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.system.pojo.SResource;

public interface SResourceRepository extends BaseRepository<SResource, String> {
	
	@Query("select u from SResource u where u.parentResource.id=:parentReourceID ")
	public SResource findResource(@Param("parentReourceID") String parentReourceID);
	
	
	public SResource findByParentResourceIsNull();
	
	//@Cacheable(value="resourceCache")
	@Query("select u from SResource u where u.resCode=:resCode and u.isLogicDel=0 order by u.resCode")
	public SResource findByResCode(@Param("resCode") String resCode);
	
	@Query("select u from SResource u where u.id=:id and u.isLogicDel=0")
	public SResource findById(@Param("id") String id);
	
	@Query("select u from SResource u where u.resType.dicValue!='2' and u.isLogicDel=0")
	public List<SResource> findAllMenus();
	
	@Query("select u from SResource u where u.id in ?1 and  u.isLogicDel=0 ")
	public List<SResource> findByIdsIn(List<String> ids);
	
	@Cacheable(value="resourceCache")
	@Query("select u from SResource u where u.isLogicDel=0 ")
	public List<SResource> findAll();
	
}