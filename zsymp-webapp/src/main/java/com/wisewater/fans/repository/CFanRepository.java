package com.wisewater.fans.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.fans.pojo.CFan;

public interface CFanRepository extends BaseRepository<CFan, String> {
	
	@Query("select u from CFan u where u.id=:id and u.isLogicDel=0")
	public CFan findById(@Param("id")String id);
	
	@Query("select u from CFan u where u.nickName=:nickName and u.isLogicDel=0")
	public List<CFan> findBynickName(@Param("nickName")String nickName);
	
	@Query("select u from CFan u where u.openID=:openID and u.token=:token  and u.isLogicDel=0")
	public CFan findByOpenIDAndToken(@Param("openID")String openID,@Param("token")String token);
	
	@Query("select u from CFan u where u.openID=:openID and u.isLogicDel=0")
	public CFan findByOpenID(@Param("openID")String openID);
	
	@Query("select u from CFan u where u.isSubscribe=1 and u.token=:token and  u.isLogicDel=0")
	public List<CFan> findByAllSubscribe(@Param("token")String token);
	
	@Query("select count(u) from CFan u where u.isSubscribe=1 and u.token=:token and  u.isLogicDel=0")
    public int findSubscribeCount(@Param("token")String token);
	
	@Query("select count(u) from CFan u where u.subscribeDate=:subscribeDate and u.token=:token and  u.isLogicDel=0")
    public int findSubCountByTokenAndDate(@Param("token")String token, @Param("subscribeDate")Date subscribeDate);
	
	@Query("select count(u) from CFan u where u.unsubscribeDate=:unsubscribeDate and u.token=:token and  u.isLogicDel=0")
    public int findUnsubCountByTokenAndDate(@Param("token")String token, @Param("unsubscribeDate")Date unsubscribeDate);
	
	@Query("select u.openID from CFan u where u.isSubscribe=1 and u.token=:token and  u.isLogicDel=0")
    public List<String> findOpenIDByAllSubscribe(@Param("token")String token);
	
	@Query("select u from CFan u where u.token=:token and u.isLogicDel=0")
    public List<CFan> findByAllByToken(@Param("token")String token);
	
	@Query("select u from CFan u where u.openID in :OpenIDs and u.token=:token and u.isLogicDel=0 ")
    public List<CFan> findByOpenIDIn(@Param("OpenIDs")List<String> OpenIDs, @Param("token")String token);
}