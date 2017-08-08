package com.wisewater.system.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.controller.SRoleForm;
import com.wisewater.system.controller.SUserForm;
import com.wisewater.system.pojo.SRole;
import com.wisewater.system.pojo.SUser;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.system.repository.SRoleRepository;
import com.wisewater.system.repository.SUserRepository;

@Service
public class SUserServiceImpl extends BaseService implements SUserService {

	@Autowired
	private SUserRepository userRepository;
	
	@Autowired
	private SDictionaryRepository  dictionaryRepository;
	
	@Autowired
	private SRoleRepository roleRepository;

	@Override
	public JqgridListForm findAll(int pageNo, String sidx, String sord,
			String loginName, final int isDisabled, String mobile, String userName) {
		
		if(StringUtils.isEmpty(loginName)){loginName="";}
        if(StringUtils.isEmpty(mobile)){mobile="";}
        if(StringUtils.isEmpty(userName)){userName="";}
		
		if(StringUtils.isEmpty(sidx)){
	        	sidx="loginName";
        }
        if(StringUtils.isEmpty(sord)){
        	sord="asc";
        }
        
        final String loginNameFinal = "%"+loginName+"%";
        final String mobileFinal = "%"+mobile+"%";
        final String userNameFinal = "%"+userName+"%";
        
		
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
    	
    	//组合查询条件
    	Specification<SUser> spec = new Specification<SUser>() {
			public Predicate toPredicate(Root<SUser> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Path<String> loginNamePath=  root.get("loginName");
				Path<String> isDisabledPath=  root.get("isDisabled");
				Path<String> mobilePath=  root.get("mobile");
				Path<String> userNamePath=  root.get("userName");
				Path<String> isLogicDelPath=  root.get("isLogicDel");
				
				
				List<Predicate> predic = new ArrayList<Predicate>();
				predic.add(cb.like(loginNamePath, loginNameFinal));
				predic.add(cb.like(userNamePath, userNameFinal));
				predic.add(cb.like(mobilePath, mobileFinal));
				if(isDisabled!=-1){
					predic.add(cb.equal(isDisabledPath, isDisabled));
				}
				predic.add(cb.equal(isLogicDelPath, 0));
				
				
				Predicate[] preArr  = new Predicate[predic.size()]; 
				predic.toArray(preArr);
				
				query.where(preArr);
				
				
				return null;
			}
		};
    	
    	//组合查询方法
    	Page<SUser> userPage = userRepository.findAll(spec,page);
    	
    	
		List<SUserForm> pageList = new ArrayList<SUserForm>();
		if(userPage!=null&&userPage.getContent()!=null){
			for (SUser user : userPage.getContent()) {
				pageList.add(mapper.map(user, SUserForm.class));
			}
		}
		
		JqgridListForm jqgridListForm = new JqgridListForm();
    	jqgridListForm.setPage(pageNo);
    	jqgridListForm.setFormList(pageList);
    	jqgridListForm.setTotal(userPage.getTotalPages());
    	jqgridListForm.setRecords(userPage.getTotalElements());
		
		
		return jqgridListForm;
	}

	@CacheEvict(value="userCache",allEntries=true)
	@Override
	public boolean saveUser(SUserForm userForm) {
		if(userForm==null)return false;
		
		SUser user = mapper.map(userForm, SUser.class);
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		user.setPassword(encoder.encodePassword(loadConstant.getInitPassword(), user.getLoginName()));
		
		user.setGender(dictionaryRepository
				.findByTypeCodeAndDicValue(userForm.getGender().getTypeCode(), 
						userForm.getGender().getDicValue()));
		
		if(userRepository.save(user)!=null){
			return true;
		}
		
		return false;
	}

	@CacheEvict(value="userCache",allEntries=true)
	@Override
	public boolean updateUser(SUserForm userForm) {
		if(userForm==null)return false;
		SUser checkUser = userRepository.findById(userForm.getId());
		if(checkUser==null)return false;
		
		SUser user = mapper.map(userForm, SUser.class);
		
		user.setGender(dictionaryRepository
				.findByTypeCodeAndDicValue(userForm.getGender().getTypeCode(), 
						userForm.getGender().getDicValue()));
		
		user.setRoles(checkUser.getRoles());
		if(userRepository.save(user)!=null){
			return true;
		}
		
		return true;
	}

	@CacheEvict(value="userCache",allEntries=true)
	@Override
	public boolean deleteUsers(String ids) {
		if(StringUtils.isEmpty(ids))return false;
		
		String[] strIds = ids.split(",");
		if(strIds!=null && strIds.length>0){
			List<String> listIds = Arrays.asList(strIds);
			List<SUser> users = userRepository.findByIdsIn(listIds);
			for(SUser user :users){
				user.setIsLogicDel(1);    //逻辑删除
			}
			users = userRepository.save(users);
			if(users!=null&&!users.isEmpty()){
				return true;
			}
		}
		return false;
	}

	@CacheEvict(value="userCache",allEntries=true)
	@Override
	public boolean disableUser(String ids) {
		if(StringUtils.isEmpty(ids))return false;
		
		String[] strIds = ids.split(",");
		if(strIds!=null && strIds.length>0){
			List<String> listIds = Arrays.asList(strIds);
			List<SUser> users = userRepository.findByIdsIn(listIds);
			for(SUser user :users){
				user.setIsDisabled(1);    //禁用
			}
			users = userRepository.save(users);
			if(users!=null&&!users.isEmpty()){
				return true;
			}
		}
		return false;
	}

	@CacheEvict(value="userCache",allEntries=true)
	@Override
	public boolean enableUser(String ids) {
		if(StringUtils.isEmpty(ids))return false;
		
		String[] strIds = ids.split(",");
		if(strIds!=null && strIds.length>0){
			List<String> listIds = Arrays.asList(strIds);
			List<SUser> users = userRepository.findByIdsIn(listIds);
			for(SUser user :users){
				user.setIsDisabled(0);             //启用
			}
			users = userRepository.save(users);
			if(users!=null&&!users.isEmpty()){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean initPassword(String ids) {
		if(StringUtils.isEmpty(ids))return false;
		
		String[] strIds = ids.split(",");
		if(strIds!=null && strIds.length>0){
			List<String> listIds = Arrays.asList(strIds);
			List<SUser> users = userRepository.findByIdsIn(listIds);
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			for(SUser user :users){
				user.setPassword(encoder.encodePassword(loadConstant.getInitPassword(), user.getLoginName()));
			}
			
			users = userRepository.save(users);
			if(users!=null&&!users.isEmpty()){
				return true;
			}
		}
		return false;
	}

	@Override
	public SUserForm findById(String id) {
		if(StringUtils.isEmpty(id))return null;
		SUser user = userRepository.findById(id);
		if(user==null)return null;
		SUserForm userForm = mapper.map(user, SUserForm.class);
		return userForm;
	}

	@Override
	public SUserForm findByEmail(String email) {
		if(StringUtils.isEmpty(email))return null;
		SUser user = userRepository.findByEmail(email);
		if(user==null)return null;
		SUserForm userForm = mapper.map(user, SUserForm.class);
		return userForm;
	}

	@Override
	public SUserForm findByLoginName(String loginName) {
		if(StringUtils.isEmpty(loginName))return null;
		SUser user = userRepository.findByLoginName(loginName);
		if(user==null)return null;
		SUserForm userForm = mapper.map(user, SUserForm.class);
		return userForm;
	}

	@Override
	public SUserForm findByMobile(String mobile) {
		if(StringUtils.isEmpty(mobile))return null;
		SUser user = userRepository.findByMobile(mobile);
		if(user==null)return null;
		SUserForm userForm = mapper.map(user, SUserForm.class);
		return userForm;
	}

	@CacheEvict(value={"userCache","resourceCache"},allEntries=true)
	@Override
	public boolean updateRoleUser(String userId, String roleIds) {
		if(StringUtils.isEmpty(userId))return false;
		SUser user= userRepository.findById(userId);
		if(user==null)return false;
		if(StringUtils.isEmpty(roleIds)){
			user.setRoles(null);
		}else{
			String[] arrayRoleId = roleIds.split(",");
			List<String> listRoleId = Arrays.asList(arrayRoleId);
			user.setRoles(roleRepository.findAll(listRoleId));
		}
		
		if(userRepository.save(user)!=null){
			return true;
		}
		
		return false;
	}
	

	
	@Override
	public JqgridListForm findUserAllRole(String userId) {
		
		
		Specification<SRole> spec = new Specification<SRole>() {
			public Predicate toPredicate(Root<SRole> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Path<String> isLogicDelPath=  root.get("isLogicDel");
				
				
				List<Predicate> predic = new ArrayList<Predicate>();
				predic.add(cb.equal(isLogicDelPath, 0));
				
				Predicate[] preArr  = new Predicate[predic.size()]; 
				predic.toArray(preArr);
				query.where(preArr);
				
				return null;
			}
		};
		
		
		SUser user = userRepository.findById(userId);
		List<SRole> userRoles = user.getRoles();
		
		List<SRole> rolePage = roleRepository.findAll(spec);
		
		List<SRoleForm> pageList = new ArrayList<SRoleForm>();
		if(rolePage!=null&&!rolePage.isEmpty()){
			for (SRole role : rolePage) {
				SRoleForm roleForm = mapper.map(role, SRoleForm.class);
				
				for(SRole userRole : userRoles){
					if(userRole.getId().equals(role.getId())){
						roleForm.setTips("1");
					}
				}
				if(StringUtils.isEmpty(roleForm.getTips())){roleForm.setTips("0");}
				pageList.add(roleForm);
			}
		}
		
		JqgridListForm jqgridListForm = new JqgridListForm();
    	jqgridListForm.setPage(1);
    	jqgridListForm.setFormList(pageList);
    	jqgridListForm.setTotal(1);
    	jqgridListForm.setRecords(pageList.size());
		
		
		return jqgridListForm;
	}

	public static void main(String[] args) {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		System.out.println(encoder.encodePassword("wisewater2012", "ssbtlhgpsgs"));
	}
	
}
