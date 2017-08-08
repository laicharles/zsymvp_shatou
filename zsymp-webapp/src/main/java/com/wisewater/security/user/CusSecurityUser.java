package com.wisewater.security.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.system.controller.SResourceForm;

public class CusSecurityUser extends User {
	
	private static final long serialVersionUID = 1L;

	private BCustomerUserForm userForm;
	private ArrayList<SResourceForm> resources;
	private BAccessTokenForm token;
	
	public CusSecurityUser(BCustomerUserForm userForm,BAccessTokenForm token,
			Collection<? extends GrantedAuthority> authorities,ArrayList<SResourceForm> resources) {
		super(userForm.getLoginName(), userForm.getPassword(), authorities);
		
		this.setUserForm(userForm);
		this.setResources(resources);
		this.setToken(token);
	}
	
	

	public ArrayList<SResourceForm> getResources() {
		return resources;
	}


	public void setResources(ArrayList<SResourceForm> resources) {
		this.resources = resources;
	}


	public BCustomerUserForm getUserForm() {
		return userForm;
	}

	public void setUserForm(BCustomerUserForm userForm) {
		this.userForm = userForm;
	}



	public BAccessTokenForm getToken() {
		return token;
	}



	public void setToken(BAccessTokenForm token) {
		this.token = token;
	}



}
