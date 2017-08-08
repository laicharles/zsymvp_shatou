package com.wisewater.bizConfig.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisewater.base.BaseRepository;
import com.wisewater.bizConfig.pojo.BParentMenu;

public interface BParentMenuRepository extends BaseRepository<BParentMenu, String> {
	
	@Query(" Select distinct p from BParentMenu p where p.isLogicDel=0 order by p.location")
	List<BParentMenu> fingByAllMenuForC();
}