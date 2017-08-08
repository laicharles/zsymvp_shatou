package com.wisewater.bizConfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.pojo.BParentMenu;
import com.wisewater.bizConfig.repository.BParentMenuRepository;


@Service
public class BParentMenuServiceImpl extends BaseService  
    implements BParentMenuService{


	@Autowired
	private BParentMenuRepository bparentmenuRepository;
	
	
	
	/**
	 * 查出所有启用的菜单给客户后台
	 * @return
	 */
	public List<BParentMenu> findAllMenuForC(){
		List<BParentMenu> bParentMenuList = bparentmenuRepository.fingByAllMenuForC();
		return bParentMenuList;
	}
	

}