package com.wisewater.cusConfig.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cusConfig.pojo.CMenuConfigure;

public interface CMenuConfigureRepository extends BaseRepository<CMenuConfigure, String> {
	
	@Query("Select cm from CMenuConfigure cm where cm.token=:token and cm.isLogicDel=0 and cm.menuLable is null order by cm.BParentMenu.location,cm.sequence")
	List<CMenuConfigure> findAllCMenu(@Param("token") String token);
	
	@Query("Select cm from CMenuConfigure cm where cm.token=:token and cm.BParentMenu.id=:parentID and cm.menuLable is null and cm.isLogicDel=0 and cm.isDisabled=0 order by cm.BParentMenu.location,cm.sequence")
	List<CMenuConfigure> findOpenCMenuByParent(@Param("token") String token,@Param("parentID") String parentID);
	
	@Query("Select cm from CMenuConfigure cm where cm.token=:token and cm.menuLable = :menuLable and cm.isLogicDel=0 and cm.isDisabled=0 order by sequence")
	List<CMenuConfigure> findMenuByMenuLable(@Param("token") String token,@Param("menuLable") String menuLable);
}