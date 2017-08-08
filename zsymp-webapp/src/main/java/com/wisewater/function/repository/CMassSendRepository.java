package com.wisewater.function.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.function.pojo.CMassSend;

public interface CMassSendRepository extends BaseRepository<CMassSend, String> {
    
    @Query("select u from CMassSend u where u.title like %:title% and u.isLogicDel=0 and u.token=:token")
    public Page<CMassSend> findAll(Pageable page, @Param("title")String title, @Param("token") String token);

    @Query("select u from CMassSend u where u.id=:id and  u.isLogicDel=0 ")
    public CMassSend findById(@Param("id") String id);
    
    @Query("select u from CMassSend u where u.id in ?1 and  u.isLogicDel=0 ")
    public List<CMassSend> findByIdIn(List<String> ids);
}