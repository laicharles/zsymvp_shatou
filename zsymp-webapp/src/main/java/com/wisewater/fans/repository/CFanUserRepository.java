package com.wisewater.fans.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.fans.pojo.CFanUser;

public interface CFanUserRepository extends BaseRepository<CFanUser, String> {
	
    @Query("select u from CFanUser u where u.id=:id and u.isLogicDel=0")
    public CFanUser findById(@Param("id")String id);
    
    @Query("select u from CFanUser u where u.openId=:openId and u.isLogicDel=0")
    public List<CFanUser> findByOpenId(@Param("openId")String openId);
	
	@Query("select u  from CFanUser u where u.token=:token and u.fan.openID=:openID and u.isLogicDel=0")
	public List<CFanUser> findByTokenAndOpenID(@Param("token")String token,@Param("openID")String openID);
	
	@Query("select u from CFanUser u where u.userAccount like %:userAccount% and u.isLogicDel=0 and u.token=:token")
    public Page<CFanUser> findAll(Pageable page, @Param("userAccount")String userAccount, @Param("token")String token);
	
	@Query("select u from CFanUser u where u.token=:token and u.userAccount=:accountNo and  u.isLogicDel=0 ")
    public List<CFanUser> findAllBindUserByTokenAndAccountNo(@Param("token") String token, @Param("accountNo") String accountNo);
	
	@Query("select count(u) from CFanUser u where u.fan.openID=:openID and  u.isLogicDel=0 ")
    public int countBindUserByOpenID(@Param("openID") String openID);
	
	@Query("select count(u) from CFanUser u where u.fan.openID=:openID and u.userAccount=:userAccount and u.isLogicDel=0 ")
	public int countBindUserByOpenIDNUserAccount(@Param("openID") String openID,@Param("userAccount") String userAccount);
	
}