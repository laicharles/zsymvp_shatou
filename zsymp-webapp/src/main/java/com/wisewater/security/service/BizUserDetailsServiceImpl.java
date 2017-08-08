package com.wisewater.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.security.user.BizSecurityUser;
import com.wisewater.system.controller.SResourceForm;
import com.wisewater.system.controller.SUserForm;
import com.wisewater.system.pojo.SResource;
import com.wisewater.system.pojo.SRole;
import com.wisewater.system.pojo.SUser;
import com.wisewater.system.repository.SUserRepository;

@Service
public class BizUserDetailsServiceImpl extends BaseService implements UserDetailsService {

	@Autowired
	private SUserRepository sUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String loginName)
			throws UsernameNotFoundException {
		
		SUser user   =  sUserRepository.findByLoginNameEnable(loginName);
		
        if  (user  ==   null ){
        	user   =  sUserRepository.findByLoginName(loginName);
        	if(user == null){
        		throw   new  UsernameNotFoundException(loginName  +   "  不存在 ！" );
        	}else{
        		throw   new  UsernameNotFoundException(loginName  +   "  已被禁用 ！" );
        	}
        }
       List<GrantedAuthority>  authsList  =   new  ArrayList <GrantedAuthority> ();
       List<SResource> resourcesPojoList = new ArrayList<SResource>();
        for  (SRole role : user.getRoles()) {
        	authsList.add(new SimpleGrantedAuthority(role.getRoleCode()));
        	resourcesPojoList.addAll(role.getResources());
       }

        ArrayList<SResourceForm> resourcesForm = new ArrayList<SResourceForm>();
        for (SResource resource:resourcesPojoList) {
        	resourcesForm.add(mapper.map(resource, SResourceForm.class,"resourcesForLogin"));
		}
        
        
        SUserForm userForm = mapper.map(user, SUserForm.class);
        BizSecurityUser userdetail  =   new  BizSecurityUser(userForm,"",authsList,resourcesForm);

        return  userdetail;
	}
}
