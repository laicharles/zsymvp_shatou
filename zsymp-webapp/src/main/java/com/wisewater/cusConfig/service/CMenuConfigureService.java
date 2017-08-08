package com.wisewater.cusConfig.service;

import java.util.List;
import java.util.Map;

import com.wisewater.cusConfig.controller.CMenuConfigureForm;
import com.wisewater.cusConfig.pojo.CMenuConfigure;

 
public interface CMenuConfigureService {


//	/**
//	 * 客户初始化菜单
//	 * @param appId
//	 * @param appSecret
//	 * @param token
//	 */
//	public boolean saveAndCreateInitMenu(String appId,String appSecret,String token);
	
	/**
	 * 查出客户所有菜单
	 * @param token
	 * @return
	 */
	public List<CMenuConfigureForm> findAllCMenu(String token);
	
	/**
	 * 更新客户菜单
	 * @param cMenuConfigureForm
	 */
	public void updateMenu(CMenuConfigureForm cMenuConfigureForm);
	
	/**
	 * 公众号菜单生成
	 * @param appId
	 * @param appSecret
	 * @param token
	 */
	public boolean createWXMenu(String token);
	
	/**
	 * 查询所有微官网 下的子菜单
	 */
	public Map<String, List<CMenuConfigure>> findAllChildrenMenu(String token,String menuLable);
}