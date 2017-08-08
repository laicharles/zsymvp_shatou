package com.wisewater.function.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.function.pojo.CStopWtrTemp;

public interface CStopWtrTempRepository extends BaseRepository<CStopWtrTemp, String> {
    
    @Query("select u from CStopWtrTemp u where u.token=:token and u.isLogicDel=0 and u.isPrototype=0")
    public Page<CStopWtrTemp> findAll(Pageable page, @Param("token")String token);

    @Query("select u from CStopWtrTemp u where u.id=:id and  u.isLogicDel=0 ")
    public CStopWtrTemp findById(@Param("id") String id);
    
    @Query("select u from CStopWtrTemp u where u.id in ?1 and  u.isLogicDel=0 ")
    public List<CStopWtrTemp> findByIdIn(List<String> ids);
    
    @Query("select u from CStopWtrTemp u where u.token=:token and  u.isLogicDel=0 ")
    public CStopWtrTemp findByToken(@Param("token") String token);
    
    @Query("select u from CStopWtrTemp u where u.token=:token and u.isLogicDel=0 and u.isPrototype=1")
    public CStopWtrTemp findPrototypeByToken(@Param("token") String token);
    
}