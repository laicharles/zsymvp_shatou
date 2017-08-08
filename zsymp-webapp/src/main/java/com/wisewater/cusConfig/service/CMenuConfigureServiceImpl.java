package com.wisewater.cusConfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.pojo.BParentMenu;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.bizConfig.service.BParentMenuService;
import com.wisewater.cusConfig.controller.CMenuConfigureForm;
import com.wisewater.cusConfig.pojo.CMenuConfigure;
import com.wisewater.cusConfig.repository.CMenuConfigureRepository;
import com.wisewater.wechatpublic.menu.Button;
import com.wisewater.wechatpublic.menu.ClickButton;
import com.wisewater.wechatpublic.menu.ComplexButton;
import com.wisewater.wechatpublic.menu.Menu;
import com.wisewater.wechatpublic.menu.ViewButton;
import com.wisewater.wechatpublic.util.MenuUtil;


@Service
public class CMenuConfigureServiceImpl extends BaseService  
    implements CMenuConfigureService{


	@Autowired
	private CMenuConfigureRepository cmenuconfigureRepository;
	
	@Autowired
	private BParentMenuService bParentMenuService;
	
	@Autowired
	private BAccessTokenService baccessTokenService;
	
	
	
	/**
	 * 公众号菜单生成
	 * @param appId
	 * @param appSecret
	 * @param token
	 */
	public boolean createWXMenu(String token){
		List<BParentMenu> bParentMenuList = bParentMenuService.findAllMenuForC();
		Menu menu = new Menu();
		List<Button> buttonList = new ArrayList<Button>();
		for(BParentMenu bParentMenu : bParentMenuList)
		{
			ComplexButton mainBtn = new ComplexButton();
			List<Button> subButtonList = new ArrayList<Button>();
			mainBtn.setName(bParentMenu.getName());
			List<CMenuConfigure> cMenuConfigureList = cmenuconfigureRepository.findOpenCMenuByParent(token,bParentMenu.getId());
			for(CMenuConfigure cMenuConfigure : cMenuConfigureList)
			{
				//view
				if("view".equals(cMenuConfigure.getType()))
				{
					ViewButton btn = new ViewButton();
					btn.setName(cMenuConfigure.getName());
					btn.setType(cMenuConfigure.getType());
					if(cMenuConfigure.getUrl().startsWith("http"))
					{
						btn.setUrl(cMenuConfigure.getUrl());
					}
					else
					{
						btn.setUrl(loadConstant.getWebSitePath()+cMenuConfigure.getUrl());
					}
					
					subButtonList.add(btn);
				}
				//click
				if("click".equals(cMenuConfigure.getType()))
				{
					ClickButton btn = new ClickButton();
					btn.setName(cMenuConfigure.getName());
					btn.setType(cMenuConfigure.getType());
					btn.setKey(cMenuConfigure.getKeyType());
					subButtonList.add(btn);
				}
			}
			mainBtn.setSub_button((Button[])subButtonList.toArray(new Button[subButtonList.size()]));
			buttonList.add(mainBtn);
		}
		menu.setButton((Button[])buttonList.toArray(new Button[buttonList.size()]));
		
		BAccessTokenForm  baccessTokenForm = baccessTokenService.checkUpdateAccessTokenByToken(token);
		boolean result = false;
		if(null!=baccessTokenForm)
		{
			MenuUtil.deleteMenu(baccessTokenForm.getAccessToken());
			//创建菜单
			result = MenuUtil.createMenu(menu, baccessTokenForm.getAccessToken());
		}
		return result;
	}
	
	/**
	 * 查出客户所有菜单
	 * @param token
	 * @return
	 */
	public List<CMenuConfigureForm> findAllCMenu(String token){
		List<CMenuConfigureForm> cMenuConfigureFormList = new ArrayList<CMenuConfigureForm>();
		
		List<BParentMenu> bParentMenuList = bParentMenuService.findAllMenuForC();
		for(BParentMenu bParentMenu : bParentMenuList)
		{
			List<CMenuConfigure> cMenuConfigureList = cmenuconfigureRepository.findOpenCMenuByParent(token,bParentMenu.getId());
			for(CMenuConfigure cMenuConfigure : cMenuConfigureList)
			{
				CMenuConfigureForm cMenuConfigureForm = new CMenuConfigureForm();
				cMenuConfigureForm.setId(cMenuConfigure.getId());
				cMenuConfigureForm.setName(cMenuConfigure.getName());
				cMenuConfigureForm.setParentName(cMenuConfigure.getBParentMenu().getName());
				cMenuConfigureForm.setIsDisabled(cMenuConfigure.getIsDisabled());
				cMenuConfigureFormList.add(cMenuConfigureForm);
			}
		}
		
		return cMenuConfigureFormList;
		
	}
	
	/**
	 * 更新客户菜单
	 * @param cMenuConfigureForm
	 */
	public void updateMenu(CMenuConfigureForm cMenuConfigureForm){
		CMenuConfigure cMenuConfigure = cmenuconfigureRepository.getOne(cMenuConfigureForm.getId());
		cMenuConfigure.setName(cMenuConfigureForm.getName());
		cMenuConfigure.setIsDisabled(cMenuConfigureForm.getIsDisabled());
		cmenuconfigureRepository.saveAndFlush(cMenuConfigure);
	}
	
	/**
	 * 查询微官网下的子功能
	 */
	@Override
	public Map<String, List<CMenuConfigure>> findAllChildrenMenu(String token,String menuLable) {

		List<CMenuConfigure> cMenuConfigureList = cmenuconfigureRepository.findMenuByMenuLable(token,menuLable);
		
		Map<String, List<CMenuConfigure>> configMap = new HashMap<String, List<CMenuConfigure>>();
		List<CMenuConfigure> configList = null;
				
		int count = 3;
		String str = null;
		for(int i = 1; i <= cMenuConfigureList.size(); i++){
			if(i > count){
				count = count + 3;
			}
			str = "menu"+count;
			if(configMap.containsKey(str)){
				configList = configMap.get(str);
				configList.add(cMenuConfigureList.get(i-1));
			}else{
				configList = new ArrayList<CMenuConfigure>();
				configList.add(cMenuConfigureList.get(i-1));
				configMap.put(str, configList);
			}
		}
		
		return configMap;
		
	}
}