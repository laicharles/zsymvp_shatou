package com.wisewater.review.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.bizConfig.service.BCustomerUserService;
import com.wisewater.function.pojo.CNoticeTemp;
import com.wisewater.function.pojo.CReminderTemp;
import com.wisewater.function.pojo.CStopWtrTemp;
import com.wisewater.function.repository.CNoticeTempRepository;
import com.wisewater.function.repository.CReminderTempRepository;
import com.wisewater.function.repository.CStopWtrTempRepository;
import com.wisewater.function.service.CNoticeTempService;
import com.wisewater.function.service.CReminderTempService;
import com.wisewater.function.service.CStopWtrTempService;
import com.wisewater.function.service.SReviewPermissionsTempService;
import com.wisewater.review.pojo.SReviewPermissions;
import com.wisewater.review.service.SReviewPermissionsService;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;

@Controller
public class SReviewPermissionsController {
	
	@Autowired
	private SReviewPermissionsService reviewService;
	
	@Autowired
	private CStopWtrTempService cStopWtrTempService; 
	
	@Autowired
	private BCustomerUserService bCustomerUserService;
	
	@Autowired
	private SReviewPermissionsTempService sReviewPermissionsTempService; 
	
	@Autowired
	private CNoticeTempService cNoticeTempService;
	
	@Autowired
	private CReminderTempService cReminderTempService;
	
	@Autowired
	private CStopWtrTempRepository cstopwtrtempRepository;
	
	@Autowired
	private SDictionaryRepository sDictionaryRepository;
	
	@Autowired
	private CNoticeTempRepository noticeTempRepository;
	
	@Autowired
	private CReminderTempRepository cReminderTempRepository;
	
	
	/**
	 * 跳转审核页面
	 * @param token  
	 * @param contentId  内容编号
	 * @param templateId  模板编号
	 * @param higherCustomerUserId 上级编号 
	 * @param submitBinOpendId 提交人的OpenId
	 * @param tempGenre 模板类型
	 */
	@RequestMapping("/m/reviewPage.do")
	public String reviewPage(Model model,String token,String contentId,String higherCustomerUserId,String submitBinOpendId,String auditorOpenId,String tempGenre){
		
		model.addAttribute("token", token);
		model.addAttribute("contentId", contentId);
		model.addAttribute("higherCustomerUserId", higherCustomerUserId);
		model.addAttribute("submitBinOpendId",submitBinOpendId);
		model.addAttribute("auditorOpenId",auditorOpenId);
		model.addAttribute("tempGenre",tempGenre);
		
		return "m/review/reviewContent";
	}
	
	/**
	 * 审核
	 * @param token  
	 * @param contentId  内容编号
	 * @param submitBinOpendId 提交人的OpendId
	 * @param reviewCustomerUserId   审核人编号
	 * @param isThrough  是否同意
	 * @param remark  不同意事的备注
	 */
	@RequestMapping("/m/audit.do")
	@ResponseBody
	public Map<String,String> audit(String token,String contentId,String submitBinOpendId,String reviewCustomerUserId,String auditorOpenId,
			String isThrough,String remark,String tempGenre){
		
		Map<String, String> strMap = new HashMap<String, String>();
		
		//拿出审核人
		BCustomerUserForm bCustomerUserForm = bCustomerUserService.findById(reviewCustomerUserId);
		if(bCustomerUserForm == null){
			//返回没有审核人界面
			strMap.put("msg", "该审核人不存在");
			strMap.put("status","danger");
		}
		
		SReviewPermissions reviewPermissions = null;
		//获取用户权限id
		String permissionsId = bCustomerUserForm.getCurrentPermissions();
		//判断发送人的上级
		if(permissionsId != null && !permissionsId.equals("")){
			reviewPermissions = reviewService.findReviewById(permissionsId);
		}
		

		List<String> openIdList = new ArrayList<String>();
		if(submitBinOpendId != null && !submitBinOpendId.equals("")){
			openIdList.add(submitBinOpendId);
		}
		if(auditorOpenId != null && !auditorOpenId.equals("")){
			openIdList.add(auditorOpenId);
		}
		
		if(reviewPermissions != null){
			
			//继续往下面审核
			if(isThrough.equals("SUCCESS")){ //通过
				
				if(reviewPermissions.getLevel() < 3){
					//调用发布程序在发布一次
					auditorOpenId = bCustomerUserForm.getBinOpendId();
					boolean flag = false;
					if(tempGenre.equals("stopWtr")){  
						flag = cStopWtrTempService.previewStopWtrTempById(contentId, bCustomerUserForm,auditorOpenId,submitBinOpendId, token);
					}else if(tempGenre.equals("notice")){ //恢水
						flag = cNoticeTempService.previewNoticeTempById(contentId, bCustomerUserForm,auditorOpenId,submitBinOpendId, token);
					}else if(tempGenre.equals("reminder")){
						flag = cReminderTempService.previewReminderTempById(contentId, bCustomerUserForm,auditorOpenId,submitBinOpendId, token);
					}
					
					if(!flag){
						strMap.put("msg", "审核失败！");
						strMap.put("status","danger");
					}else{
						strMap.put("msg", "审核成功！");
						strMap.put("status","success");
						sReviewPermissionsTempService.sendReviewConsentTemp(token, remark, openIdList, String.valueOf(reviewPermissions.getLevel()));
					}
					
				}else{
					//发布出来
					if(tempGenre.equals("stopWtr")){  //停水通知
						cStopWtrTempService.sendStopWtrTempById(contentId, token);
						//只要同意并且审核权限是最高权限，就审核成功
						updateSendDateTimeAndStatusStopWtr(contentId,"2");
						
					}else if(tempGenre.equals("notice")){ //恢水
						cNoticeTempService.sendNoticeTempById(contentId, token);
						updateSendDateTimeAndStatusNotice(contentId,"2");
						
					}else if(tempGenre.equals("reminder")){  //温馨提示
						cReminderTempService.sendReminderTempById(contentId, token);
						updateSendDateTimeAndStatusReminder(contentId,"2");
						
					}
					strMap.put("msg", "审核成功！");
					strMap.put("status","success");
					sReviewPermissionsTempService.sendReviewConsentTemp(token, remark, openIdList, String.valueOf(reviewPermissions.getLevel()));
				}
			}else if(isThrough.equals("FAIL")){
				
				//发布出来
				if(tempGenre.equals("stopWtr")){  
					updateSendDateTimeAndStatusStopWtr(contentId,"3");
					
				}else if(tempGenre.equals("notice")){ //恢水
					updateSendDateTimeAndStatusNotice(contentId,"3");
					
				}else if(tempGenre.equals("reminder")){
					updateSendDateTimeAndStatusReminder(contentId,"3");
				}
				
				//将不符合内容备注发送给上一个人
				boolean flag = sReviewPermissionsTempService.sendReviewDisagreeTemp(token, remark, openIdList, String.valueOf(reviewPermissions.getLevel()));
				if(!flag){
					strMap.put("msg", "审核失败！");
					strMap.put("status","danger");
				}else{
					strMap.put("msg", "审核成功！");
					strMap.put("status","success");
				}
			}
			
		}else{
			//返回没有权限页面
			strMap.put("msg", "该权限不存在");
			strMap.put("status","danger");
		}
		
		
		return strMap;
	}
	
	
	/**
	 * 修改发送时间和状态(停水)
	 * 
	 * @param temp
	 * @param flag
	 *            XingXingLvCha 2015年9月11日 下午1:21:03
	 */
	private void updateSendDateTimeAndStatusStopWtr(String cStopWtrTempId, String flag) {
		CStopWtrTemp cStopWtrTemp = cstopwtrtempRepository.findOne(cStopWtrTempId);
		cStopWtrTemp.setSendDateTime(new Date());
		SDictionary sendStusEnt = sDictionaryRepository.findByTypeCodeAndDicValue("reviewStatus", flag);
		cStopWtrTemp.setSendStatus(sendStusEnt);
		cstopwtrtempRepository.save(cStopWtrTemp);
	}
	
	
	/**
	 * 修改发送时间和状态(复水通知)
	 * 
	 * @param temp
	 * @param flag
	 *            XingXingLvCha 2015年9月11日 下午1:21:03
	 */
	private void updateSendDateTimeAndStatusNotice(String noticeTempId, String flag) {
		CNoticeTemp noticeTemp = noticeTempRepository.findOne(noticeTempId);
		SDictionary sendStusEnt = sDictionaryRepository.findByTypeCodeAndDicValue("reviewStatus", flag);
		noticeTemp.setSendStatus(sendStusEnt);
		noticeTemp.setSendDateTime(new Date());
		noticeTempRepository.save(noticeTemp);
	}
	
	/**
	 * 修改发送时间和状态(温馨提示)
	 * 
	 * @param temp
	 * @param flag
	 *            XingXingLvCha 2015年9月11日 下午1:21:03
	 */
	private void updateSendDateTimeAndStatusReminder(String cStopWtrTempId, String flag) {
		CReminderTemp cReminderTemp = cReminderTempRepository.findOne(cStopWtrTempId);
		cReminderTemp.setSendDateTime(new Date());
		SDictionary sendStusEnt = sDictionaryRepository.findByTypeCodeAndDicValue("reviewStatus", flag);
		cReminderTemp.setSendStatus(sendStusEnt);
		
		cReminderTempRepository.save(cReminderTemp);
	}
}
