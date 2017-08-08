package com.wisewater.base;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.security.user.BizSecurityUser;
import com.wisewater.security.user.CusSecurityUser;
import com.wisewater.system.controller.SResourceForm;
import com.wisewater.system.controller.SUserForm;
import com.wisewater.util.tools.LoadConstant;


public abstract class BaseController {
	
	@Autowired
	@Qualifier(value="i18nMessageSource")
	private ReloadableResourceBundleMessageSource i18nMessageSource;
	
	@Autowired
	protected LoadConstant loadConstant;
	
	
	/**
	 * 
	 * @param msgKey
	 * @param args
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月2日下午5:20:58
	 * 描述：提示消息国际化解析方法
	 */
	protected String getBundleMessage(String msgKey,Object[] args,String locales) {
		
		Locale locale = Locale.CHINESE;
		/*if(locales!=null&&locales.length()>0){
			String[] arraylocal = locales.split(";");
			if(arraylocal.length>0){
				String[] arylocales = arraylocal[0].split(",");
				if(arylocales.length>0){
					String language = arylocales[0];
					locale = new Locale(language);
				}
			}
		}*/
		
		
		return i18nMessageSource.getMessage(msgKey, args, locale);
	}
	
	
	/**
	 * 
	 * @return
	 * gawen.chen
	 * 2015年4月2日下午5:21:22
	 * 描述：运营后台session取当前登录用户
	 */
	protected SUserForm getBizSessionUser() {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		
		
		SUserForm userForm = null;
		if(userDetails  instanceof BizSecurityUser){
			BizSecurityUser securityUser = (BizSecurityUser)userDetails;
				if(securityUser!=null){
					userForm = securityUser.getUserForm();
				}
		}
		
		return userForm;
	}
	
	/**
	 * 
	 * @return
	 * gawen.chen
	 * 2015年4月2日下午5:21:55
	 * 描述：运营后台取资源
	 */
	protected List<SResourceForm> getBizSessionResource() {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		
		List<SResourceForm> resourceList = null;
		if(userDetails  instanceof BizSecurityUser){
			BizSecurityUser securityUser = (BizSecurityUser)userDetails;
			if(securityUser!=null){
				resourceList = securityUser.getResources();
			}
		}
		
		return resourceList;
	}
	
	
	/**
	 * 	
	 * @return
	 * gawen.chen
	 * 2015年4月2日下午5:22:25
	 * 描述：客户管理后台登录后在session取当前用户
	 */
	protected BCustomerUserForm getCusSessionUser() {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		
		
		BCustomerUserForm userForm = null;
		if(userDetails  instanceof CusSecurityUser){
			CusSecurityUser securityUser = (CusSecurityUser)userDetails;
			if(securityUser!=null){
				userForm = securityUser.getUserForm();
			}
		}
		
		return userForm;
	}
	

	/**
	 * 
	 * @return
	 * gawen.chen
	 * 2015年4月2日下午5:22:42
	 * 描述：客户管理后台session里取资源列表
	 */
	protected List<SResourceForm> getCusSessionResource() {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		
		List<SResourceForm> resourceList = null;
		if(userDetails  instanceof CusSecurityUser){
			CusSecurityUser securityUser = (CusSecurityUser)userDetails;
			if(securityUser!=null){
				resourceList = securityUser.getResources();
			}
		}
		
		return resourceList;
	}
	
	/**
	 * 
	 * @return
	 * gawen.chen
	 * 2015年4月2日下午5:23:06
	 * 描述：运营后台取token，主要用于图片上传等分类
	 */
	protected String getBizTokenInSession(){
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		
		String token = "";
		
		if(userDetails  instanceof BizSecurityUser){
			BizSecurityUser securityUser = (BizSecurityUser)userDetails;
			if(securityUser!=null){
				token = securityUser.getToken();
			}
		}
		return token;
	}
	
	/**
	 * 
	 * @return
	 * gawen.chen
	 * 2015年4月2日下午5:23:34
	 * 描述：session取客户管理后台的token
	 */
	protected BAccessTokenForm getCusTokenInSession(){
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		
		BAccessTokenForm token = null;
		
		if(userDetails  instanceof CusSecurityUser){
			CusSecurityUser securityUser = (CusSecurityUser)userDetails;
			if(securityUser!=null){
				token = securityUser.getToken();
			}
		}
		return token;
	}
	
	/**
	 * 
	 * @param token
	 * gawen.chen
	 * 2015年4月2日下午5:23:57
	 * 描述：更新session里的token，只新session里的 token
	 */
	protected void updateCusTokenInSession(BAccessTokenForm token){
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		
		if(userDetails  instanceof CusSecurityUser){
			CusSecurityUser securityUser = (CusSecurityUser)userDetails;
			if(securityUser!=null){
				 securityUser.setToken(token);;
			}
		}
	}
	
}
