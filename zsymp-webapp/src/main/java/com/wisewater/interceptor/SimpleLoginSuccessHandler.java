package com.wisewater.interceptor;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.security.user.BizSecurityUser;
import com.wisewater.security.user.CusSecurityUser;
import com.wisewater.system.controller.SUserForm;
import com.wisewater.system.pojo.SLoginLog;
import com.wisewater.system.repository.SLoginLogRepository;

/**
 * 
 * @author gewen.chen
 * 2015年5月5日下午3:25:30
 * 描述：登录成功后处理器，记录日志，退出登录后处理器，记录日志
 */
public class SimpleLoginSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements AuthenticationSuccessHandler,LogoutSuccessHandler{

	
	@Autowired
	private SLoginLogRepository loginLogRepository;
	

	/**
	 * 登录成功处理事件
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		 this.saveLoginLog(request, authentication);  
		
		 handle(request, response, authentication);
	     clearAuthenticationAttributes(request);
	}

	protected final void clearAuthenticationAttributes(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);

	        if (session == null) {
	            return;
	        }

	        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	/**
	 * 
	 * @param request
	 * @param authentication
	 * gawen.chen
	 * 2015年5月5日下午3:26:24
	 * 描述：记录登录日志，并把以前未正常退出的登录补全
	 */
	public void saveLoginLog(HttpServletRequest request,Authentication authentication){  
		
		
		String url = request.getRequestURI().replaceAll(request.getContextPath(), "");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	       
        BCustomerUserForm userForm = null;
		if(userDetails  instanceof CusSecurityUser){
			CusSecurityUser securityUser = (CusSecurityUser)userDetails;
			if(securityUser!=null){
				userForm = securityUser.getUserForm();
				
				if(url.contains("/cus/j_spring_security_check.do")){
					
					List<SLoginLog> logList = loginLogRepository.findByVisitorAndLogoutTimeIsNull(userForm.getLoginName());
					for(SLoginLog logOutlog : logList){
						logOutlog.setLogoutTime(new Date());
						loginLogRepository.save(logOutlog);
					}
					
					SLoginLog log = new SLoginLog();
			        log.setIpAddress(this.getIpAddress(request));
			        log.setIsLogicDel(0);
			        log.setLoginTime(new Date());
			        log.setSessionID(request.getSession().getId());
			        log.setVisitor(userForm.getLoginName());
			        loginLogRepository.save(log);
			        
					
				}/*else if(url.contains("/cus/j_spring_security_logout.do")){
					SLoginLog log = loginLogRepository.findBySessionIDAndVisitor(request.getSession().getId(),userForm.getLoginName());
					log.setLogoutTime(new Date());
					loginLogRepository.save(log);
				}*/
				
				
				
			}
		}
		
	     SUserForm suserForm = null;
		if(userDetails  instanceof BizSecurityUser){
			BizSecurityUser securityUser = (BizSecurityUser)userDetails;
			if(securityUser!=null){
				suserForm = securityUser.getUserForm();
				
				if(url.contains("/biz/j_spring_security_check.do")){   //登录
					
					List<SLoginLog> logList = loginLogRepository.findByVisitorAndLogoutTimeIsNull(suserForm.getLoginName());
					for(SLoginLog logOutlog : logList){
						logOutlog.setLogoutTime(new Date());
						loginLogRepository.save(logOutlog);
					}
					
					SLoginLog log = new SLoginLog();
			        log.setIpAddress(this.getIpAddress(request));
			        log.setIsLogicDel(0);
			        log.setLoginTime(new Date());
			        log.setSessionID(request.getSession().getId());
			        log.setVisitor(suserForm.getLoginName());
			        loginLogRepository.save(log);
			        
			        
				}/*else if(url.contains("/biz/j_spring_security_logout.do")){  //登出
					SLoginLog log = loginLogRepository.findBySessionIDAndVisitor(request.getSession().getId(), suserForm.getLoginName());
			        log.setLogoutTime(new Date());
			        loginLogRepository.save(log);
				}*/
				
				
				
			}
		}
		
		
    }  
	
	/**
	 * 
	 * @param request
	 * @return
	 * gawen.chen
	 * 2015年5月5日下午3:26:54
	 * 描述：取IP地址
	 */
	public String getIpAddress(HttpServletRequest request){    
        String ip = request.getHeader("x-forwarded-for");    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("Proxy-Client-IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("WL-Proxy-Client-IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("HTTP_CLIENT_IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getRemoteAddr();    
        }    
        return ip;    
    }

	/**
	 * 退出成功处理方法
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		this.saveLogOutLog(request, authentication);
		super.handle(request, response, authentication);
	}
	
	/**
	 * 
	 * @param request
	 * @param authentication
	 * gawen.chen
	 * 2015年5月5日下午3:27:25
	 * 描述：记录退出日志
	 */
	public void saveLogOutLog(HttpServletRequest request,Authentication authentication){  
		String url = request.getRequestURI().replaceAll(request.getContextPath(), "");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        
        BCustomerUserForm userForm = null;
		if(userDetails  instanceof CusSecurityUser){
			CusSecurityUser securityUser = (CusSecurityUser)userDetails;
			if(securityUser!=null){
				userForm = securityUser.getUserForm();
				
				if(url.contains("/cus/j_spring_security_logout.do")){
					List<SLoginLog> logList = loginLogRepository.findByVisitorAndLogoutTimeIsNull(userForm.getLoginName());
					for(SLoginLog log : logList){
						log.setLogoutTime(new Date());
						loginLogRepository.save(log);
					}
				}
				
				
				
			}
		}
		
	     SUserForm suserForm = null;
		if(userDetails  instanceof BizSecurityUser){
			BizSecurityUser securityUser = (BizSecurityUser)userDetails;
			if(securityUser!=null){
				suserForm = securityUser.getUserForm();
				
				if(url.contains("/biz/j_spring_security_logout.do")){  //登出
					List<SLoginLog> logList = loginLogRepository.findByVisitorAndLogoutTimeIsNull(suserForm.getLoginName());
					for(SLoginLog log : logList){
				        log.setLogoutTime(new Date());
				        loginLogRepository.save(log);
					}
				}
				
				
				
			}
		}
	}
}
