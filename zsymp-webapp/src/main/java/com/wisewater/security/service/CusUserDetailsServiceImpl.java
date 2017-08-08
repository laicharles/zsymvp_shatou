package com.wisewater.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.bizConfig.pojo.BCustomer;
import com.wisewater.bizConfig.pojo.BCustomerUser;
import com.wisewater.bizConfig.repository.BCustomerUserRepository;
import com.wisewater.cusConfig.pojo.CMp;
import com.wisewater.security.user.CusSecurityUser;
import com.wisewater.system.controller.SResourceForm;
import com.wisewater.system.pojo.SResource;
import com.wisewater.system.pojo.SRole;

@Service
public class CusUserDetailsServiceImpl extends BaseService implements UserDetailsService {

	@Autowired
	private BCustomerUserRepository customerUserRepository;
	
	
	final static Pattern mobilePattern = Pattern.compile("^(1[3-9]\\d{9}$)");  //电话号码
	final static Pattern emailPattern = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");  //邮箱
	
	@Override
	public UserDetails loadUserByUsername(String loginName)
			throws UsernameNotFoundException {
		
		BCustomerUser customerUser   =  null;
		
        Matcher isMobile = mobilePattern.matcher(loginName);
        Matcher isEmail= emailPattern.matcher(loginName);
        
        if(isMobile.matches()){
        	customerUser = customerUserRepository.findByMobileEnable(loginName);
        	if(customerUser == null){
        		customerUser = customerUserRepository.findByMobile(loginName);
        		if(customerUser ==null){
        			throw   new  UsernameNotFoundException(loginName  +   "  不存在! " );
        		}else{
        			throw   new  UsernameNotFoundException(loginName  +   "  被禁用! " );
        		}
        	}
        	
        }else if(isEmail.matches()){
        	customerUser = customerUserRepository.findByEmailEnable(loginName);
        	if(customerUser == null){
        		customerUser = customerUserRepository.findByEmail(loginName);
        		if(customerUser ==null){
        			throw   new  UsernameNotFoundException(loginName  +   "  不存在! " );
        		}else{
        			throw   new  UsernameNotFoundException(loginName  +   "  被禁用! " );
        		}
        	}
        }else{
        	customerUser = customerUserRepository.findByLoginNameEnable(loginName);
        	if(customerUser == null){
        		customerUser = customerUserRepository.findByLoginName(loginName);
        		if(customerUser ==null){
        			throw   new  UsernameNotFoundException(loginName  +   "  不存在! " );
        		}else{
        			throw   new  UsernameNotFoundException(loginName  +   "  被禁用! " );
        		}
        	}
        }
        

       List<GrantedAuthority>  authsList  =   new  ArrayList <GrantedAuthority> ();
       List<SResource> resourcesPojoList = new ArrayList<SResource>();
       
       if(customerUser.getRole()!=null){
    	   SRole role = customerUser.getRole();
        	authsList.add(new SimpleGrantedAuthority(role.getRoleCode()));
        	resourcesPojoList.addAll(role.getResources());
       }
        
        ArrayList<SResourceForm> resourcesForm = new ArrayList<SResourceForm>();
        for (SResource resource:resourcesPojoList) {
        	resourcesForm.add(mapper.map(resource, SResourceForm.class,"resourcesForLogin"));
		}
        
        BAccessTokenForm token = null;
        BCustomer customer = customerUser.getBCusotmer();
        if(customer!=null){
        	List<CMp> cmps = customer.getCmps();
        	if(cmps!=null&&!cmps.isEmpty()){
        		CMp cmp = cmps.get(0);
        		token = mapper.map(cmp.getAccessToken(),BAccessTokenForm.class);
        		if(StringUtils.isEmpty(token.getToken())){token=null;}   //解除延迟加载
        	}
        	
        }
        
        
        BCustomerUserForm userForm = mapper.map(customerUser, BCustomerUserForm.class);
        CusSecurityUser userdetail  =   new  CusSecurityUser(userForm,token,authsList,resourcesForm);

        return  userdetail;
	}
}
