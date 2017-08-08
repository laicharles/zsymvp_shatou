package com.wisewater.bizConfig.repository;


import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.bizConfig.pojo.BCustomerUser;

public interface BCustomerUserRepository extends BaseRepository<BCustomerUser, String> {
	
	
	@Query("select u from BCustomerUser u where u.loginName=:loginName and u.isLogicDel=0")
	public BCustomerUser findByLoginName(@Param("loginName") String loginName);
	
	//@Cacheable(value="customerUserCache")
	@Query("select u from BCustomerUser u where u.loginName=:loginName and u.isDisabled=0 and u.isLogicDel=0")
	public BCustomerUser findByLoginNameEnable(@Param("loginName") String loginName);
	
	
	@Query("select u from BCustomerUser u where u.loginName=:loginName and u.password=:password and u.isLogicDel=0")
	public BCustomerUser findByLoginNameAndPassword(@Param("loginName") String loginName,@Param("password") String password);
	
	@Query("select u from BCustomerUser u where u.mobile=:mobile and u.password=:password and u.isLogicDel=0")
	public BCustomerUser findByMobileAndPassword(@Param("mobile") String mobile,@Param("password") String password);
	
	@Query("select u from BCustomerUser u where u.mobile=:mobile and u.isLogicDel=0")
	public BCustomerUser findByMobile(@Param("mobile") String mobile);
	
	//@Cacheable(value="customerUserCache")
	@Query("select u from BCustomerUser u where u.mobile=:mobile and u.isDisabled=0 and u.isLogicDel=0")
	public BCustomerUser findByMobileEnable(@Param("mobile") String mobile);
	
	@Query("select u from BCustomerUser u where u.email=:email and u.password=:password and u.isLogicDel=0")
	public BCustomerUser findByEmailAndPassword(@Param("email") String email,@Param("password") String password);
	
	@Query("select u from BCustomerUser u where u.email=:email and u.isLogicDel=0")
	public BCustomerUser findByEmail(@Param("email") String email);
	
	//@Cacheable(value="customerUserCache")
	@Query("select u from BCustomerUser u where u.email=:email and u.isDisabled=0 and u.isLogicDel=0")
	public BCustomerUser findByEmailEnable(@Param("email") String email);
	
	@Query("select u from BCustomerUser u where u.id=:id and u.isLogicDel=0")
	public BCustomerUser findById(@Param("id")String id);
	
	@Query("select u from BCustomerUser u where u.id in ?1 and  u.isLogicDel=0 ")
	public List<BCustomerUser> findByIdsIn(List<String> ids);
	
	
	@Query("select u from BCustomerUser u where u.id in ?1 and u.role.roleCode='CUS_001' and  u.isLogicDel=0 ")
	public List<BCustomerUser> findByIdsforAdmin(List<String> ids);
	
	
	@Query("select u from BCustomerUser u where u.toInviteCode=:toInviteCode and  u.isLogicDel=0 ")
	public  BCustomerUser findByToInviteCode(@Param("toInviteCode")String toInviteCode);
	
	//@Query("select u from BCustomerUser u where u.loginName like %:loginName%")
	@Query("select u from BCustomerUser u where u.loginName =:loginName")
	public List<BCustomerUser> findByBossLoginName(@Param("loginName")String loginName);
	
	@Query("select u from BCustomerUser u where u.id =:higherCustomerUserId")
	public BCustomerUser findByfindByHigherCustomerUserId(@Param("higherCustomerUserId")String higherCustomerUserId);
}