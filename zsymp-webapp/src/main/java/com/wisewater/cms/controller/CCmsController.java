package com.wisewater.cms.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wisewater.base.BaseController;
import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.cms.pojo.CCms;
import com.wisewater.cms.service.CCmsService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.service.SReviewPermissionsTempService;
import com.wisewater.review.pojo.SReviewPermissions;
import com.wisewater.review.repository.SReviewPermissionsRepository;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.pojo.SFansOperateLog;
import com.wisewater.system.repository.SFansOperateLogRepository;
import com.wisewater.system.service.SDictionaryService;
import com.wisewater.util.service.ImageService;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.wechatpublic.util.DateUtils;

@Controller
public class CCmsController extends BaseController {
	
	private Logger logger = Logger.getLogger(CCmsController.class);
	
	@Autowired
	private CCmsService ccmscmsService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private SDictionaryService dictionaryService;

	@Autowired
	private SFansOperateLogRepository fansOperateLogRepository;
	
	@Autowired
	private SReviewPermissionsRepository sReviewPermissionsRepository;
	
	@Autowired
	private SReviewPermissionsTempService sReviewPermissionsTempService;

	/**
	 * cms列表
	 * 
	 * @param model
	 * @param pageNo
	 * @return AlexFung 2015年4月1日 上午11:22:28
	 */
	@RequestMapping(value = "/cus/{cmsType}/ccms_list.do")
	public String findCmsByToken(Model model, @RequestParam("pageNo") String pageNo, @PathVariable String cmsType) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}

		SDictionary sDictionary = dictionaryService.findByLogicID(cmsType);
		String dicName = sDictionary.getDicName();
		model.addAttribute("dicName", dicName);
		
		//获取当前用的权限
		BCustomerUserForm user = getCusSessionUser();
		SReviewPermissions reviewPermissions = sReviewPermissionsRepository.findById(user.getCurrentPermissions());
		
		String pageSize = String.valueOf(loadConstant.getPageSize());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", pageNo);
		model.addAttribute("cmsType", cmsType);
		model.addAttribute("level",reviewPermissions.getLevel());
		model.addAttribute("reviewId",user.getCurrentPermissions());
		return "cus/cms/ccms_list";
	}

	/**
	 * cms列表
	 * 
	 * @param page
	 * @param sidx
	 * @param sord
	 * @return AlexFung 2015年4月1日 上午11:22:34
	 */
	@RequestMapping(value = "/cus/ccmsGrid.do", method = RequestMethod.GET)
	public @ResponseBody JqgridListForm findCmsJson(@RequestParam("page") String page,
			@RequestParam(value = "rows", defaultValue = "10") String rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord, @RequestParam("cmsType") String cmsType) {
		int pageNo = 0;
		int row = 0;
		if (page != null) {
			pageNo = Integer.parseInt(page) - 1;
		}
		if (rows != null) {
			row = Integer.parseInt(rows);
		}
		BCustomerUserForm user = getCusSessionUser();
		JqgridListForm jqgridListForm = ccmscmsService.findCmsByToken(getCusTokenInSession().getToken(), cmsType,
				pageNo + 1, row, sidx, sord,user);
		return jqgridListForm;
	}

	/**
	 * 新增cms
	 * 
	 * @param model
	 * @param cmsForm
	 * @return AlexFung 2015年4月1日 上午11:22:41
	 */
	@RequestMapping(value = "/cus/ccms_toAdd.do")
	public String toSaveCms(Model model, String cmsType) {
		model.addAttribute("cmsType", cmsType);
		model.addAttribute("token", getCusTokenInSession().getToken());

		SDictionary sDictionary = dictionaryService.findByLogicID(cmsType);
		String dicName = sDictionary.getDicName();
		model.addAttribute("dicName", dicName);

		String defaultPicName = sDictionary.getRemarks();
		model.addAttribute("defaultPicName", defaultPicName);

		String defaultPicPath = "/resources/images/m/";
		model.addAttribute("defaultPicPath", defaultPicPath);

		// for cPicMtrl_picklist.jsp
		String cusPicPathPickList = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";
		model.addAttribute("cusPicPathPickList", cusPicPathPickList);

		return "cus/cms/ccms_add";
	}

	/**
	 * 新增cms
	 * 
	 * @param model
	 * @param cmsForm
	 * @param locales
	 * @return AlexFung 2015年4月1日 上午11:22:51
	 */
	@RequestMapping(value = "/cus/ccms_add.do")
	public @ResponseBody ControllerJsonMessage saveCms(Model model, CCmsForm cmsForm,
			@RequestHeader("Accept-Language") String locales) {

		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		BCustomerUserForm user = getCusSessionUser();
		SReviewPermissions reviewPermissions = sReviewPermissionsRepository.findById(user.getCurrentPermissions());
		cmsForm.setSubmitOpenId(user.getBinOpendId());
		cmsForm.setAuditorOpenId("");
		cmsForm.setAuditorLevel(String.valueOf(reviewPermissions.getLevel()));
		if(reviewPermissions.getLevel()==3){  //如果是办公室发布可直接显示
			cmsForm.setAuditorStatus(dictionaryService.findByLogicID("reviewStatus2"));
		}else{
			cmsForm.setAuditorStatus(dictionaryService.findByLogicID("reviewStatus0"));
		}
		
		cmsForm.setCreatedDateTime(DateUtils.getDateTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
		cmsForm.setToken(getCusTokenInSession().getToken());
		cmsForm.setPageContent(
				imageService.handleContentImg(cmsForm.getPageContent(), getCusTokenInSession().getToken()));
		cmsForm.setCmsType(dictionaryService.findByLogicID(cmsForm.getCmsType().getLogicID()));
		boolean isSuccess = ccmscmsService.saveCms(cmsForm);
		msg.setResult(isSuccess);
		if (isSuccess) {
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
			msg.setTips(cmsForm.getCmsType().getLogicID());
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
			msg.setTips(cmsForm.getCmsType().getLogicID());
		}
		return msg;
	}
	
	@RequestMapping("/cus/cms_toExmine.do")
	public String toExamineCms(Model model, @RequestParam("id") String id, @RequestParam("pageNo") String pageNo,
			String cmsType){
		CCmsForm cmsForm = ccmscmsService.findCmsById(id);
		cmsForm.setPageContent(
				cmsForm.getPageContent().replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
		model.addAttribute("cmsForm", cmsForm);
		// 验证数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}

		model.addAttribute("token", getCusTokenInSession().getToken());

		String picPath = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";

		model.addAttribute("picPath", picPath);

		SDictionary sDictionary = dictionaryService.findByLogicID(cmsType);
		String dicName = sDictionary.getDicName();
		model.addAttribute("dicName", dicName);

		String defaultPicName = sDictionary.getRemarks();
		model.addAttribute("defaultPicName", defaultPicName);

		String defaultPicPath = "/resources/images/m/";
		model.addAttribute("defaultPicPath", defaultPicPath);

		// for cPicMtrl_picklist.jsp
		String cusPicPathPickList = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";

		model.addAttribute("cusPicPathPickList", cusPicPathPickList);

		// 微信端链接
		String weixinPath = loadConstant.getWebSitePath() + "/m/ccms_wx_view.do?id=" + id;
		model.addAttribute("weixinPath", weixinPath);

		// 保存页码
		model.addAttribute("page", pageNo);
		model.addAttribute("cmsType", cmsType);
		
		return "cus/cms/ccms_exmine";
	}
	
	/**
	 * 根据ID查找cms详细信息
	 * 
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return AlexFung 2015年4月1日 上午11:22:57
	 */
	@RequestMapping(value = "/cus/ccms_toUpdate.do")
	public String toUpdateCms(Model model, @RequestParam("id") String id, @RequestParam("pageNo") String pageNo,
			String cmsType) {
		CCmsForm cmsForm = ccmscmsService.findCmsById(id);
		cmsForm.setPageContent(
				cmsForm.getPageContent().replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
		model.addAttribute("cmsForm", cmsForm);
		// 验证数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}

		model.addAttribute("token", getCusTokenInSession().getToken());

		String picPath = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";

		model.addAttribute("picPath", picPath);

		SDictionary sDictionary = dictionaryService.findByLogicID(cmsType);
		String dicName = sDictionary.getDicName();
		model.addAttribute("dicName", dicName);

		String defaultPicName = sDictionary.getRemarks();
		model.addAttribute("defaultPicName", defaultPicName);

		String defaultPicPath = "/resources/images/m/";
		model.addAttribute("defaultPicPath", defaultPicPath);

		// for cPicMtrl_picklist.jsp
		String cusPicPathPickList = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";

		model.addAttribute("cusPicPathPickList", cusPicPathPickList);

		// 微信端链接
		String weixinPath = loadConstant.getWebSitePath() + "/m/ccms_wx_view.do?id=" + id;
		model.addAttribute("weixinPath", weixinPath);

		// 保存页码
		model.addAttribute("page", pageNo);
		model.addAttribute("cmsType", cmsType);
		return "cus/cms/ccms_update";
	}

	/**
	 * 更新cms
	 * 
	 * @param model
	 * @param cmsForm
	 * @param locales
	 * @return AlexFung 2015年4月1日 上午11:23:04
	 */
	@RequestMapping(value = "/cus/ccms_update.do")
	public @ResponseBody ControllerJsonMessage updateCms(Model model, CCmsForm cmsForm,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();

		BCustomerUserForm user = getCusSessionUser();
		SReviewPermissions reviewPermissions = sReviewPermissionsRepository.findById(user.getCurrentPermissions());
		
		cmsForm.setSubmitOpenId(user.getBinOpendId());
		cmsForm.setAuditorOpenId("");
		cmsForm.setAuditorLevel(String.valueOf(reviewPermissions.getLevel()));
		if(reviewPermissions.getLevel()==3){  //如果是办公室发布可直接显示
			cmsForm.setAuditorStatus(dictionaryService.findByLogicID("reviewStatus2"));
		}else{
			cmsForm.setAuditorStatus(dictionaryService.findByLogicID("reviewStatus0"));
		}
		
		cmsForm.setPageContent(
				imageService.handleContentImg(cmsForm.getPageContent(), getCusTokenInSession().getToken()));
		cmsForm.setCmsType(dictionaryService.findByLogicID(cmsForm.getCmsType().getLogicID()));
		CCms cms = ccmscmsService.updateCms(cmsForm);
		boolean isSuccess = false;
		if (cms != null) {
			isSuccess = true;
		}
		msg.setResult(isSuccess);
		if (isSuccess) {
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
			msg.setTips(cmsForm.getCmsType().getLogicID());
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
			msg.setTips(cmsForm.getCmsType().getLogicID());
		}
		model.addAttribute("cmsForm", cmsForm);
		return msg;
	}

	/**
	 * 批量删除cms(逻辑删除)
	 * 
	 * @param idStr
	 * @param pageNo
	 * @return AlexFung 2015年4月1日 上午11:23:10
	 */
	@RequestMapping(value = "/cus/ccms_delete.do")
	public @ResponseBody List<String> deleteCmsBatch(@RequestParam("idStr") String idStr,
			@RequestParam("pageNo") String pageNo) {
		String notice = ccmscmsService.deleteCmsbatch(idStr);
		if ("".equals(notice)) {
			notice = "success";
		}
		List<String> resultList = new ArrayList<String>();
		resultList.add(notice);
		resultList.add(pageNo);
		return resultList;
	}
	
	//审核
	@RequestMapping(value = "/cus/ccms_toReviewCmss.do")
	public String toReviewCmss(Model model,@RequestParam("contentId") String contentId,@RequestParam("pageNo") String pageNo,
				@RequestParam("cmsType") String cmsType){
		
		model.addAttribute("cmsType", cmsType);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("contentId",contentId);
		
		logger.info("cmsType:"+cmsType);
		logger.info("pageNo:"+pageNo);
		logger.info("contentId:"+contentId);
		
		return "cus/cms/ccms_reviewCmss";
	}
	
	//审核结束
	@RequestMapping(value = "/cus/ccms_reviewCmss.do")
	@ResponseBody
	public String reviewCmss(String contentId,String isThrough,String remark){
		
		try {
			remark = new String(remark.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//获取当前内容
		String token = getCusTokenInSession().getToken();
		CCmsForm cmsForm = ccmscmsService.findCmsById(contentId);
		BCustomerUserForm userForm = getCusSessionUser();
		SReviewPermissions reviewPermissions = sReviewPermissionsRepository.findById(userForm.getCurrentPermissions());
		
		List<String> openIdList = new ArrayList<String>();
		if(cmsForm.getSubmitOpenId() != null && !cmsForm.getSubmitOpenId().equals("")){
			openIdList.add(cmsForm.getSubmitOpenId());
		}
		if(cmsForm.getAuditorOpenId() != null && !cmsForm.getAuditorOpenId().equals("")){
			openIdList.add(cmsForm.getAuditorOpenId());
		}
		
		//判断用户是同意还是不同意
		if(reviewPermissions.getLevel() > 1){
			if(isThrough.equals("SUCCESS")){
				//设置当前权限
				cmsForm.setAuditorLevel(String.valueOf(reviewPermissions.getLevel()));
				if(reviewPermissions.getLevel()==2){  //如果是一级审核就正在审核中
					cmsForm.setAuditorStatus(dictionaryService.findByLogicID("reviewStatus1"));
					cmsForm.setAuditorOpenId(userForm.getBinOpendId());
				}else if(reviewPermissions.getLevel()==3){ //如果是二级的话，就直接审核成功
					cmsForm.setAuditorStatus(dictionaryService.findByLogicID("reviewStatus2"));
				}
				
				boolean flag = ccmscmsService.saveCms(cmsForm);
				
				if(flag){
					sReviewPermissionsTempService.sendReviewConsentTemp(token, remark, openIdList, String.valueOf(reviewPermissions.getLevel()));
				}else{
					return "FAIL";
				}
			}else if(isThrough.equals("FAIL")){
				//cmsForm.setAuditorLevel(String.valueOf(reviewPermissions.getLevel()));
				cmsForm.setAuditorOpenId(userForm.getBinOpendId());
				cmsForm.setAuditorStatus(dictionaryService.findByLogicID("reviewStatus3"));
				boolean flag = ccmscmsService.saveCms(cmsForm);
				
				if(flag){
					sReviewPermissionsTempService.sendReviewDisagreeTemp(token, remark, openIdList, String.valueOf(reviewPermissions.getLevel()));
				}else{
					return "FAIL";
				}
			}
		}
		
		return "SUCCESS";
	}
	/************************************************************ 以下是微信端 ********************************************************************/

	/**
	 * 微信端页面-cms列表
	 * 
	 * @param model
	 * @param pageNo
	 * @return AlexFung 2015年4月8日 上午9:55:35
	 */
	@RequestMapping(value = "/m/ccms_wx_list.do")
	public String findCmsByTokenWx(Model model, @RequestParam("token") String token,
			@RequestParam("cmsType") String cmsType) {
		model.addAttribute("pageNo", 0);
		model.addAttribute("token", token);
		model.addAttribute("cmsType", cmsType);
		SDictionary sDictionary = dictionaryService.findByLogicID(cmsType);
		String dicName = sDictionary.getDicName();
		model.addAttribute("dicName", dicName);

		String defaultPicName = sDictionary.getRemarks();
		model.addAttribute("defaultPicName", defaultPicName);

		String defaultPicPath = "/resources/images/m/";
		model.addAttribute("defaultPicPath", defaultPicPath);
		List<CCms> list = ccmscmsService.findByTokenAndCmsType(token, cmsType);
		if (list != null && !list.isEmpty()) {
			if (list.size() == 1) {
				CCms cms = list.get(0);
				model.addAttribute("id", cms.getId());
				fansOperateLogRepository.save(new SFansOperateLog("用户访问" + dicName + "页面", "点击菜单", "", new Date()));
				return "redirect:/m/ccms_wx_view.do";
			}
		}
		fansOperateLogRepository.save(new SFansOperateLog("用户访问" + dicName + "列表", "点击菜单", "", new Date()));
		return "m/cms/ccms_wx_list";
	}

	/**
	 * 微信端页面-cms列表
	 * 
	 * @param page
	 * @param sidx
	 * @param sord
	 * @return AlexFung 2015年3月31日 下午7:56:48
	 */
	@RequestMapping(value = "/m/ccmsGrid_wx.do", method = RequestMethod.POST)
	public @ResponseBody JqgridListForm findCmsJsonWx(@RequestParam("page") String page,
			@RequestParam("rows") String rows, @RequestParam("token") String token,
			@RequestParam("cmsType") String cmsType) {
		int pageNo = 0;
		int row = 0;
		if (page != null)
			pageNo = Integer.parseInt(page) - 1;
		if (rows != null) {
			row = Integer.parseInt(rows);
		}
		JqgridListForm jqgridListForm = ccmscmsService.findCmsByToken(token, cmsType, pageNo + 1, row,
				"sortNumber", "asc",null);
		
		return jqgridListForm;
	}

	/**
	 * 微信端页面-根据ID查找cms详细信息
	 * 
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return AlexFung 2015年4月8日 上午11:53:08
	 */
	@RequestMapping(value = "/m/ccms_wx_view.do")
	public String toUpdateCmsWx(Model model, @RequestParam("id") String id) {
		CCmsForm cmsForm = ccmscmsService.findCmsById(id);
		if (!"".equals(cmsForm.getPageUrl())) {
			return "redirect:" + cmsForm.getPageUrl();
		}

		SDictionary sDictionary = dictionaryService.findByLogicID(cmsForm.getCmsType().getLogicID());
		String dicName = sDictionary.getDicName();
		model.addAttribute("dicName", dicName);

		model.addAttribute("cmsForm", cmsForm);
		fansOperateLogRepository.save(new SFansOperateLog("用户点击" + dicName + "菜单", "点击菜单", "", new Date()));

		return "m/cms/ccms_wx_view";
	}

}