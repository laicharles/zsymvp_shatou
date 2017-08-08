package com.wisewater.bizConfig.service;


import com.wisewater.bizConfig.pojo.BMenuConfigure;

 
public interface BMenuConfigureService {
	
	/**
	 * 查出所有启用的菜单名称
	 * @return
	 */
	public BMenuConfigure findMenuById(String id);
	

}
