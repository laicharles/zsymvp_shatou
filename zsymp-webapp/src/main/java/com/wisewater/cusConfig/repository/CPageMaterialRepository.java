package com.wisewater.cusConfig.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cusConfig.pojo.CPageMaterial;

public interface CPageMaterialRepository extends BaseRepository<CPageMaterial, String> {
    
    @Query("select u from CPageMaterial u where u.title like %:title% and u.tags like %:tag% and u.isLogicDel=0 and u.token=:token")
    public Page<CPageMaterial> findAll(Pageable page, @Param("tag")String tag, @Param("title")String title,  @Param("token")String token);

    @Query("select u from CPageMaterial u where u.id=:id and  u.isLogicDel=0 ")
    public CPageMaterial findById(@Param("id") String id);
    
    @Query("select u from CPageMaterial u where u.id in ?1 and  u.isLogicDel=0 ")
    public List<CPageMaterial> findByIdIn(List<String> ids);
    
}