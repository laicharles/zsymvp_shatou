package com.wisewater.cusConfig.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cusConfig.pojo.CArticlePage;

public interface CArticlePageRepository extends BaseRepository<CArticlePage, String> {
	
    @Query("select u from CArticlePage u where u.id=:id and  u.isLogicDel=0 ")
    public CArticlePage findById(@Param("id") String id);
    
}