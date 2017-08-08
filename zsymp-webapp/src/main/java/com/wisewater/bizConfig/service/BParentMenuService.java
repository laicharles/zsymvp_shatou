package com.wisewater.bizConfig.service;

import java.util.List;

import com.wisewater.bizConfig.pojo.BParentMenu;

 
public interface BParentMenuService {
	
	/**
	 * 查出所有启用的菜单给客户后台
	 * @return
	 */
	public List<BParentMenu> findAllMenuForC();
	

}