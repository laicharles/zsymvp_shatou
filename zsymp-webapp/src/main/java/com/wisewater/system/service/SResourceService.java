
package com.wisewater.system.service;

import java.util.List;

import com.wisewater.system.controller.SResourceForm;
import com.wisewater.system.controller.SResourceJqgridForm;
import com.wisewater.util.tools.ZtreeForm;

 
public interface SResourceService {

	public SResourceForm findRootResource();
	
	
	public SResourceForm findAuthMenu(List<SResourceForm> menus,String rootCode,String token);
	
	
	public List<SResourceJqgridForm> findAllResources();
	
	
	public boolean saveResource(SResourceForm resourceForm);
	
	
	public SResourceForm findById(String id);

	
	public List<ZtreeForm> findAllMenus();
	
	
	public boolean deleteResource(String id);
	
}