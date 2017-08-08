package com.wisewater.cms.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.cms.controller.CCmsForm;
import com.wisewater.cms.pojo.CCms;
import com.wisewater.cms.repository.CCmsRepository;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.review.pojo.SReviewPermissions;
import com.wisewater.review.repository.SReviewPermissionsRepository;
import com.wisewater.util.service.ImageService;
import com.wisewater.wechatpublic.util.StringUtil;


@Service
public class CCmsServiceImpl extends BaseService  
    implements CCmsService{

	@Autowired
	private CCmsRepository ccmscmsRepository;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private SReviewPermissionsRepository sReviewPermissionsRepository;

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
	@Override
	public JqgridListForm findCmsByToken(String token,String cmsType, int pageNo,int rows,String sidx,String sord,BCustomerUserForm user) {
		int pageSize = loadConstant.getPageSize();
		
		Pageable pager = new PageRequest(pageNo - 1, pageSize);
		
		//设置排序方式
    	if(sidx!=null&&sidx.length()>0){
    		if(sord!=null&&sord.equalsIgnoreCase("desc")){
    			pager = new PageRequest(pageNo-1, rows,Direction.DESC,sidx);
    		}else{
    			pager = new PageRequest(pageNo-1, rows,Direction.ASC,sidx);
    		}
    	}
    	
    	Page<CCms> cmsPage = null;
    	if(user != null && !user.equals("")){
    		SReviewPermissions sReviewPermissions = sReviewPermissionsRepository.findById(user.getCurrentPermissions());
    		if(sReviewPermissions.getLevel()<2){
    			cmsPage = ccmscmsRepository.findCmsByTokenSubmit(token, cmsType, user.getBinOpendId(), pager);
    		}else{
    			String level = "1";
    			if(sReviewPermissions.getLevel()==2){
    				level= "3";
    			}
    			cmsPage = ccmscmsRepository.findCmsByTokenReviewONE(token, cmsType, user.getBinOpendId(), level, pager);
    		}
    	}else{
    		cmsPage = ccmscmsRepository.findCmsByToken(token,cmsType,pager);
    	}
		
		List<CCmsForm> cmsFormList = new ArrayList<CCmsForm>();
		if (cmsPage != null && cmsPage.getContent() != null) {
			for (CCms cms : cmsPage.getContent()) {
				cmsFormList.add(mapper.map(cms, CCmsForm.class));
			}
		}
		PageImpl<CCmsForm> pageList = new PageImpl<CCmsForm>(
				cmsFormList, pager, cmsPage.getTotalElements());
		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList.getContent());
		jqgridListForm.setTotal(pageList.getTotalPages());
		jqgridListForm.setRecords(pageList.getTotalElements());
		return jqgridListForm;
	}

	/**
	 * 根据ID查找cms详细信息
	 * @param id
	 * @return
	 * AlexFung
	 * 2015年4月1日 上午11:26:47
	 */
	@Override
	public CCmsForm findCmsById(String id) {
		CCms cms = ccmscmsRepository.getOne(id);
		CCmsForm cmsForm = new CCmsForm();
		cmsForm = mapper.map(cms, CCmsForm.class);
		return cmsForm;
	}

	/**
	 * 更新cms
	 * @param cmsFrom
	 * @return
	 * AlexFung
	 * 2015年4月1日 上午11:26:53
	 */
	@Override
	public CCms updateCms(CCmsForm cmsFrom) {
		if(cmsFrom==null){
			return null;
		}
		CCms cms = ccmscmsRepository.getOne(cmsFrom.getId());
		if(cms==null){
			return null;
		}
		
		if(cmsFrom.getPicNameFile()!=null&&cmsFrom.getPicNameFile().getSize()>0){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			String picName = imageService.uploadImg(request, cmsFrom.getPicNameFile(), cmsFrom.getToken());
			cmsFrom.setImage(picName);
		}else{
			if(cmsFrom.getPicName()!=null&&!"".equals(cmsFrom.getPicName())){
				cmsFrom.setImage(cmsFrom.getPicName());
			}else{
				cmsFrom.setImage(cms.getImage());
			}
			
		}
		
		
		cms = mapper.map(cmsFrom, CCms.class);
		return ccmscmsRepository.saveAndFlush(cms);
	}

	/**
	 * 批量删除cms(逻辑删除)
	 * @param idStr
	 * @return
	 * AlexFung
	 * 2015年4月1日 上午11:26:58
	 */
	@Override
	public String deleteCmsbatch(String idStr) {
		String notice = "";
		for (String id : StringUtil.strToList(idStr, ";")) {
			CCms cms = new CCms();
			cms = mapper.map(findCmsById(id), CCms.class);
			cms.setIsLogicDel(1);
			CCms cmsDel = ccmscmsRepository.save(cms);
			if (cmsDel == null) {
				notice = notice + "," + cms.getTitle();
			}
		}
		return notice;
	}

	/**
	 * 新增cms
	 * @param cmsForm
	 * @return
	 * AlexFung
	 * 2015年4月1日 上午11:27:06
	 */
	@Override
	public boolean saveCms(CCmsForm cmsForm) {
		if(cmsForm==null){
			return false;
		}
		if(cmsForm.getPicNameFile()!=null&&cmsForm.getPicNameFile().getSize()>0){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			String picName = imageService.uploadImg(request, cmsForm.getPicNameFile(), cmsForm.getToken());
			cmsForm.setImage(picName);
		}else{
			if(cmsForm.getPicName()!=null&&!"".equals(cmsForm.getPicName())){
				cmsForm.setImage(cmsForm.getPicName());
			}
			
		}
		
		CCms cms = new CCms();
		cms = mapper.map(cmsForm, CCms.class);
		
		if (ccmscmsRepository.saveAndFlush(cms) != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<CCms> findByTokenAndCmsType(String token, String cmsType) {
		// TODO Auto-generated method stub
		return ccmscmsRepository.findByTokenAndCmsType(token, cmsType);
	}


	
	
	
	
}