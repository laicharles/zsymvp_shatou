package com.wisewater.system.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.system.pojo.SRole;

public interface SRoleRepository extends BaseRepository<SRole, String> {
	
	@Query(" select u from SRole u where u.isLogicDel=0")
	public Page<SRole> findAll(Pageable page);
	
	@Query(" select u from SRole u where u.isLogicDel=0")
	public List<SRole> findAll();
	
	
	@Query("select u from SRole u where u.id=:id and  u.isLogicDel=0 ")
	public SRole findById(@Param("id") String id);
	
	@Query("select u from SRole u where u.roleCode=:roleCode and  u.isLogicDel=0 ")
	public SRole findByRoleCode(@Param("roleCode") String roleCode);
	
	@Query("select u from SRole u where u.roleCode=:roleCode")
	public SRole findByRoleCodeInAll(@Param("roleCode") String roleCode);
	
	@Query("select u from SRole u where u.id in ?1 and  u.isLogicDel=0 ")
	public List<SRole> findByIdIn(List<String> ids);
}