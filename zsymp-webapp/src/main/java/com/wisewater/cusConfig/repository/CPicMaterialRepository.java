package com.wisewater.cusConfig.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cusConfig.pojo.CPicMaterial;

public interface CPicMaterialRepository extends BaseRepository<CPicMaterial, String> {
    
    @Query("select u from CPicMaterial u where u.title like %:title% and u.tags like %:tag% and u.isLogicDel=0 and u.token=:token")
    public Page<CPicMaterial> findAll(Pageable page, @Param("tag")String tag, @Param("title")String title, @Param("token")String token);

    @Query("select u from CPicMaterial u where u.id=:id and  u.isLogicDel=0 ")
    public CPicMaterial findById(@Param("id") String id);
    
    @Query("select u from CPicMaterial u where u.id in ?1 and  u.isLogicDel=0 ")
    public List<CPicMaterial> findByIdIn(List<String> ids);
}