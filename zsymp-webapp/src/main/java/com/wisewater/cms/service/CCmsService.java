package com.wisewater.cms.service;

import java.util.List;

import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.cms.controller.CCmsForm;
import com.wisewater.cms.pojo.CCms;
import com.wisewater.form.utils.JqgridListForm;

 
public interface CCmsService {

	/**
	 * 根据token查找cms列表
	 * @param token
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @return
	 * AlexFung
	 * 2015年4月1日 上午11:26:42
	 */
	public JqgridListForm findCmsByToken(String token,String cmsType, int pageNo,int rows,String sidx,String sord,BCustomerUserForm user);


	/**
	 * 根据ID查找cms详细信息
	 * @param id
	 * @return
	 * AlexFung
	 * 2015年4月1日 上午11:26:47
	 */
	public CCmsForm findCmsById(String id);

	/**
	 * 更新cms
	 * @param cmsFrom
	 * @return
	 * AlexFung
	 * 2015年4月1日 上午11:26:53
	 */
	public CCms updateCms(CCmsForm cmsFrom);

	/**
	 * 批量删除cms(逻辑删除)
	 * @param idStr
	 * @return
	 * AlexFung
	 * 2015年4月1日 上午11:26:58
	 */
	public String deleteCmsbatch(String idStr);

	/**
	 * 新增cms
	 * @param cmsForm
	 * @return
	 * AlexFung
	 * 2015年4月1日 上午11:27:06
	 */
	public boolean saveCms(CCmsForm cmsForm);
	
	/**
	 * 根据token,cmstype返回总条目数
	 * @author TalonYeung
	 * 2016年8月26日 下午2:54:45
	 * @param token
	 * @param cmsType
	 * @return
	 */
	public List<CCms> findByTokenAndCmsType(String token,String cmsType);
	
	
}