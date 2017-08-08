package com.wisewater.interceptor;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.system.controller.SUserForm;
import com.wisewater.system.pojo.SOperateLog;
import com.wisewater.system.pojo.SResource;
import com.wisewater.system.repository.SLoginLogRepository;
import com.wisewater.system.repository.SOperateLogRepository;
import com.wisewater.system.repository.SResourceRepository;

/**
 * 
 * @author gewen.chen
 * 2015年5月5日下午3:27:46
 * 描述：全局拦截器
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

	private SResourceRepository resourceRepository;
	
	private SLoginLogRepository loginLogRepository;
	
	private SOperateLogRepository operateLogRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		
		String url = request.getRequestURI().replaceAll(request.getContextPath(), "");
		
		if(!StringUtils.isEmpty(request.getQueryString())){
			url = url+"?"+request.getQueryString();
		}
		
		Object obj = request.getSession().getAttribute("user");
		String visitor = "";
		if(obj instanceof SUserForm){
			SUserForm user = (SUserForm)obj;
			visitor = user.getLoginName();
		}else if(obj instanceof BCustomerUserForm){
			BCustomerUserForm user = (BCustomerUserForm)obj;
			visitor = user.getLoginName();
		}
		
		//日志处理
		if(url.contains(".do")){ //过滤请求
			url = url.substring(1);   //请除科杠
			saveLogs(url,request.getRemoteAddr(),request.getSession().getId(),visitor);
		}
		return super.preHandle(request, response, handler);
	}

	/**
	 * 
	 * @param url
	 * @param ipAddr
	 * @param sessionId
	 * @param visitor
	 * gawen.chen
	 * 2015年5月5日下午3:28:02
	 * 描述：操作记录
	 */
	private void saveLogs(String url,String ipAddr,String sessionId,String visitor){
		List<SResource> resourceList =  resourceRepository.findAll();
		for(SResource resource : resourceList){
			if(url.startsWith(resource.getReqUrl()) && !StringUtils.isEmpty(resource.getReqUrl())){
				SOperateLog log = new SOperateLog();
				log.setDescript(visitor+"在"+resource.getParentResource().getResName()+"执行"+resource.getResName()+"操作");
				log.setIpAddress(ipAddr);
				log.setIsLogicDel(0);
				if(resource.getResType().getDicValue().equals("2")){  //操作
					log.setModuleName(resource.getParentResource().getResName());
				}else{
					log.setModuleName(resource.getResName());
				}
				log.setSessionID(sessionId);
				log.setVisitor(visitor);
				log.setVisitTime(new Date());
				operateLogRepository.save(log);
			}
		}
		
		//pathMatcher.match(exUrl, url);
	}
	
	
	public SResourceRepository getResourceRepository() {
		return resourceRepository;
	}

	public void setResourceRepository(SResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}


	public SLoginLogRepository getLoginLogRepository() {
		return loginLogRepository;
	}


	public void setLoginLogRepository(SLoginLogRepository loginLogRepository) {
		this.loginLogRepository = loginLogRepository;
	}


	public SOperateLogRepository getOperateLogRepository() {
		return operateLogRepository;
	}


	public void setOperateLogRepository(SOperateLogRepository operateLogRepository) {
		this.operateLogRepository = operateLogRepository;
	}

	
}
