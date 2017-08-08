package com.wisewater.security.voter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.wisewater.bizConfig.pojo.BCustomerUser;
import com.wisewater.bizConfig.repository.BCustomerUserRepository;
import com.wisewater.system.pojo.SResource;
import com.wisewater.system.pojo.SRole;
import com.wisewater.system.pojo.SUser;
import com.wisewater.system.repository.SUserRepository;

public class UserRoleVoter implements AccessDecisionVoter<Object> {

	@Autowired
	private SUserRepository userRepository;
	
	@Autowired
	private BCustomerUserRepository customerUserRepository;
	
	private PathMatcher pathMatcher = new AntPathMatcher();

	final static Pattern mobilePattern = Pattern.compile("^(1[3-9]\\d{9}$)");  //电话号码
	final static Pattern emailPattern = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");  //邮箱
	
	private String excludeUrl;
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return true;
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public int vote(Authentication authentication, Object object,Collection<ConfigAttribute> attributes) {
		int result = ACCESS_DENIED;
		if (!(object instanceof FilterInvocation))
			return result;
		
		FilterInvocation invo = (FilterInvocation) object;
		String requestUrl = invo.getRequestUrl();// 当前请求的URL
		
		if(match(requestUrl)){
			return ACCESS_GRANTED;
		}
		
		String loginName = authentication.getName();
		
		if(requestUrl.contains("/biz")){
			// 获得当前用户的可访问资源，自定义的查询方法，之后和当前请求资源进行匹配，成功则放行，否则拦截
			SUser user =  userRepository.findByLoginName(loginName);
			if(user!=null && user.getLoginName().length()>0){
				Set<String> urlAuthorities = getBizUrlAuthorities(user);  
				if (contain(urlAuthorities,requestUrl)) {
					result = ACCESS_GRANTED;
				}
			}
		}else{
			// 获得当前用户的可访问资源，自定义的查询方法，之后和当前请求资源进行匹配，成功则放行，否则拦截
			BCustomerUser user =  null;
		      Matcher isMobile = mobilePattern.matcher(loginName);
		      Matcher isEmail= emailPattern.matcher(loginName);
		        
		        if(isMobile.matches()){
		        	user = customerUserRepository.findByMobile(loginName);
		        }else if(isEmail.matches()){
		        	user = customerUserRepository.findByEmail(loginName);
		        }else{
		        	user = customerUserRepository.findByLoginName(loginName);
		        }
			
			if(user!=null && user.getLoginName().length()>0){
				Set<String> urlAuthorities = getCusUrlAuthorities(user);  
				if (contain(urlAuthorities,requestUrl)) {
					result = ACCESS_GRANTED;
				}
			}
		}
		
		
		
		return result;
	}

	private boolean contain(Set<String> urlAuthorities,String url){
		
		if(urlAuthorities.contains(url))
		return true;
		
		for(String key: urlAuthorities){
			if(pathMatcher.match(key, url)){
				return true;
			}
		}
		
		return false;
	}
	
	
	private boolean match(String url){
		String[] expUrl = excludeUrl.split(",");
		
		for(String exUrl : expUrl){
			if(exUrl.contains(url)||pathMatcher.match(exUrl, url))return true;
		}
		
		return false;
	}
	
	
	
	private Set<String> getBizUrlAuthorities(SUser user){
		Set<String>  urlAuthorities  =   new  HashSet<String>();
		for  (SRole role : user.getRoles()) {
            for(SResource resource : role.getResources())
            	urlAuthorities.add(resource.getAuthUrl());
		}
		return urlAuthorities;
	}
	
	private Set<String> getCusUrlAuthorities(BCustomerUser user){
		Set<String>  urlAuthorities  =   new  HashSet<String>();
		SRole role = user.getRole();
		if(role!=null){
	        for(SResource resource : role.getResources())
	        	urlAuthorities.add(resource.getAuthUrl());
        }
		return urlAuthorities;
	}

	public String getExcludeUrl() {
		return excludeUrl;
	}

	public void setExcludeUrl(String excludeUrl) {
		this.excludeUrl = excludeUrl;
	}
	
	
	
}
