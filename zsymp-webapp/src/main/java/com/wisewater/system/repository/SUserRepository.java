package com.wisewater.system.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.system.pojo.SUser;

public interface SUserRepository extends BaseRepository<SUser, String> {

	
	@Query("select u from SUser u where u.loginName=:loginName and u.password=:password and u.isLogicDel=0")
	public SUser findByLoginNameAndPassword(@Param("loginName") String loginName,@Param("password") String password);
	
	@Cacheable(value="userCache")
	@Query("select u from SUser u where u.loginName=:loginName  and u.isLogicDel=0")
	public SUser findByLoginName(@Param("loginName") String loginName);
	
	@Query("select u from SUser u where u.loginName=:loginName and u.isDisabled=0  and u.isLogicDel=0")
	public SUser findByLoginNameEnable(@Param("loginName") String loginName);
	
	@Query("select u from SUser u where u.mobile=:mobile and u.password=:password and u.isLogicDel=0")
	public SUser findByMobileAndPassword(@Param("mobile") String mobile,@Param("password") String password);
	
	@Query("select u from SUser u where u.mobile=:mobile and u.isLogicDel=0")
	public SUser findByMobile(@Param("mobile") String mobile);
	
	@Query("select u from SUser u where u.mobile=:mobile and u.isDisabled=0   and u.isLogicDel=0")
	public SUser findByMobileEnable(@Param("mobile") String mobile);
	
	@Query("select u from SUser u where u.email=:email and u.password=:password and u.isLogicDel=0")
	public SUser findByEmailAndPassword(@Param("email") String email,@Param("password") String password);
	
	@Query("select u from SUser u where u.email=:email and u.isLogicDel=0")
	public SUser findByEmail(@Param("email") String email);
	
	@Query("select u from SUser u where u.email=:email and u.isDisabled=0   and u.isLogicDel=0")
	public SUser findByEmailEnable(@Param("email") String email);
	
	@Query("select u from SUser u where u.id=:id and u.isLogicDel=0")
	public SUser findById(@Param("id")String id);
	
	
	@Query("select u from SUser u where u.id in ?1 and  u.isLogicDel=0 ")
	public List<SUser> findByIdsIn(List<String> ids);
	
	
}
