package com.wisewater.system.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.controller.SRoleForm;
import com.wisewater.system.pojo.SResource;
import com.wisewater.system.pojo.SRole;
import com.wisewater.system.repository.SResourceRepository;
import com.wisewater.system.repository.SRoleRepository;
import com.wisewater.util.tools.ZtreeForm;


@Service
public class SRoleServiceImpl extends BaseService  
    implements SRoleService{


	@Autowired
	private SRoleRepository roleRepository;
	
	@Autowired
	private SResourceRepository resourcesRepository;

	@Override
	public JqgridListForm findAll(int pageNo,String sidx,String sord) {
		
		
		int pageSize =  loadConstant.getPageSize();
		if(pageNo<1)pageNo=1;
		
		//默认不排序
    	PageRequest page = new PageRequest(pageNo-1, pageSize);
    	
    	//设置排序方式
    	if(sidx!=null&&sidx.length()>0){
    		if(sord!=null&&sord.equalsIgnoreCase("desc")){
    			 page = new PageRequest(pageNo-1, pageSize,Direction.DESC,sidx);
    		}else{
   			 page = new PageRequest(pageNo-1, pageSize,Direction.ASC,sidx);
    		}
    	}
    	
		Page<SRole> rolePage = roleRepository.findAll(page);
		
		List<SRoleForm> pageList = new ArrayList<SRoleForm>();
		if(rolePage!=null&&rolePage.getContent()!=null){
			for (SRole role : rolePage.getContent()) {
				pageList.add(mapper.map(role, SRoleForm.class));
			}
		}
		
		JqgridListForm jqgridListForm = new JqgridListForm();
    	jqgridListForm.setPage(pageNo);
    	jqgridListForm.setFormList(pageList);
    	jqgridListForm.setTotal(rolePage.getTotalPages());
    	jqgridListForm.setRecords(rolePage.getTotalElements());
		
		
		return jqgridListForm;
	}
	
	@Override
	public List<SRoleForm> findAll() {
		List<SRole> list = roleRepository.findAll();
		List<SRoleForm> listForm = new ArrayList<SRoleForm>();
		for(SRole role:list){
			SRoleForm roleForm = mapper.map(role, SRoleForm.class);
			listForm.add(roleForm);
		}
		return listForm;
	}

	@Override
	public boolean save(SRoleForm roleForm) {
		if(roleForm==null) return false;
		SRole role = mapper.map(roleForm, SRole.class);
		if(roleRepository.save(role)!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean update(SRoleForm roleForm) {
		if(roleForm==null) return false;
		SRole role = roleRepository.findById(roleForm.getId());
		
		role.setRemarks(roleForm.getRemarks());
		role.setRoleCode(roleForm.getRoleCode());
		role.setRoleName(roleForm.getRoleName());
		
		if(roleRepository.save(role)!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteByIds(String ids) {
		if(ids==null) return false;
		
		String[] idArray= ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		
		List<SRole> roleList = roleRepository.findByIdIn(idList);
		if(roleList==null||roleList.isEmpty())return false;
		
		for (SRole role :roleList) {
			role.setIsLogicDel(1);
			role.setbCusotmerUsers(null);
			role.setResources(null);
			role.setUsers(null);
		}
		roleRepository.save(roleList);
		return true;
	}

	@Override
	public SRoleForm findById(String id) {
		if(id==null) return null;
		
		SRole role = roleRepository.findById(id);
		if(role==null) return null;
		
		return mapper.map(role, SRoleForm.class);
	}

	@Override
	public SRoleForm findByRoleCode(String roleCode) {
		SRole role = roleRepository.findByRoleCode(roleCode);
		SRoleForm roleForm = null;
		if(role!=null){
			roleForm = mapper.map(role, SRoleForm.class);
		}
		return roleForm;
	}

	@CacheEvict(value={"resourceCache","customerUserCache","userCache"},allEntries=true)
	@Override
	public boolean saveAuthorityRole(String roleId, String resourceIds) {
		
		SRole role = roleRepository.findById(roleId);
		if(role==null)return false;
		
		if(StringUtils.isEmpty(resourceIds)){
			role.setResources(null);
		}else{
		
			String[] arrayIds = resourceIds.split(",");
			List<String> listResourceIds = Arrays.asList(arrayIds);
			List<SResource> resources = resourcesRepository.findByIdsIn(listResourceIds);
			role.setResources(resources);
		}
		
		if(roleRepository.save(role)!=null){
			return true;
		}
		
		return false;
	}

	@Override
	public List<ZtreeForm> findAllMenusByRoleId(String roleId) {
		SRole role = roleRepository.findById(roleId);
		List<SResource> authResources = role.getResources();  //当前角色所拥有的资原权限
			
		List<SResource> allResources = resourcesRepository.findAll();
		
		List<ZtreeForm> treeMenus = new  ArrayList<ZtreeForm>();
		for(SResource resource :allResources){
			ZtreeForm ztreeForm = new ZtreeForm();
			ztreeForm.setId(resource.getId());
			ztreeForm.setName(resource.getResName());
			if(resource.getParentResource()==null){
				ztreeForm.setpId("0");
			}else{
				ztreeForm.setpId(resource.getParentResource().getId());
			}
			
			ztreeForm.setChecked(false);
			ztreeForm.setNocheck(false);
			for(SResource authRes : authResources){
				if(authRes.getId().equals(ztreeForm.getId())){
					ztreeForm.setChecked(true);
				}
			}
			ztreeForm.setOpen(resource.getResCode().length()<=7?true:false);
			
			treeMenus.add(ztreeForm);
		}
		
		return treeMenus;
	}

}