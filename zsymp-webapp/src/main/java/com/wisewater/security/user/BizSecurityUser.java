package com.wisewater.security.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.wisewater.system.controller.SResourceForm;
import com.wisewater.system.controller.SUserForm;

public class BizSecurityUser extends User {
	
	private static final long serialVersionUID = 1L;

	private SUserForm userForm;
	private ArrayList<SResourceForm> resources;
	private String token = "system";
	
	public BizSecurityUser(SUserForm userForm,String token,
			Collection<? extends GrantedAuthority> authorities,ArrayList<SResourceForm> resources) {
		super(userForm.getLoginName(), userForm.getPassword(), authorities);
		
		this.setUserForm(userForm);
		this.setResources(resources);
		this.setToken(token);
	}
	
	
	

	public SUserForm getUserForm() {
		return userForm;
	}


	public void setUserForm(SUserForm userForm) {
		this.userForm = userForm;
	}


	public ArrayList<SResourceForm> getResources() {
		return resources;
	}


	public void setResources(ArrayList<SResourceForm> resources) {
		this.resources = resources;
	}




	public String getToken() {
		return token;
	}




	public void setToken(String token) {
		this.token = token;
	}


}
