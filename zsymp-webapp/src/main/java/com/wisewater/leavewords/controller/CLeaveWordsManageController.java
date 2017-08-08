package com.wisewater.leavewords.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

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
import com.wisewater.cusConfig.controller.CMpForm;
import com.wisewater.cusConfig.service.CMpService;
import com.wisewater.fans.controller.CFanForm;
import com.wisewater.fans.controller.CFanUserForm;
import com.wisewater.fans.service.CFanService;
import com.wisewater.fans.service.CFanUserService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.service.CFeedBackTempService;
import com.wisewater.function.service.CLeaveWordsTempService;
import com.wisewater.function.service.CReplyFeedBackTempService;
import com.wisewater.function.service.CReplyLeaveWordsTempService;
import com.wisewater.leavewords.pojo.CLeaveWordsManage;
import com.wisewater.leavewords.service.CLeaveWordsManageService;
import com.wisewater.system.controller.SDictionaryForm;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.service.SDictionaryService;
import com.wisewater.util.service.ImageService;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.wechatpublic.oauth2.Oauth2Util;
import com.wisewater.wechatpublic.util.CommonUtil;
import com.wisewater.weixin.pojo.WxJsBean;
import com.wisewater.weixin.service.WxJsService;

/**
 * 我要留言模块处理
 * @author zhwei
 *
 */
@Controller
public class CLeaveWordsManageController  extends BaseController{
	
	@Autowired
	private SDictionaryService dictionaryService;
	
	@Autowired
	private CLeaveWordsManageService cLeaveWordsManageService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private CFanUserService cFanUserService;
	
	@Autowired
	private CFanService cFanService;
	
	@Autowired
	private SDictionaryService sDictionaryService;
	
	@Autowired
	private WxJsService wxJsService;
	
	@Autowired
	private CFeedBackTempService cFeedBackTempService;
	
	@Autowired
	private CReplyFeedBackTempService cReplyFeedBackTempService;
	
	@Autowired
	private CReplyLeaveWordsTempService cReplyLeaveWordsTempService;
	
	@Autowired
	private CLeaveWordsTempService cLeaveWordsTempService;
	
	@Autowired
	private CMpService cmpService;
	
	
	
	@RequestMapping(value = "/cus/{leavewords}/leavewordsList.do")
	public String showLeaveWordsList(Model model,@RequestParam(value="pageNo",defaultValue="1") String pageNo,@PathVariable String leavewords){
	
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
        
    	SDictionary sDictionary = dictionaryService.findByLogicID(leavewords);
		String dicName = sDictionary.getDicName();
		model.addAttribute("dicName", dicName);
        
        
		String pageSize = String.valueOf(loadConstant.getPageSize());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", pageNo);
		model.addAttribute("leavewords", leavewords);
		return "cus/leavewords/leavewords_list";
		
	
	}
	
	@RequestMapping(value = "/cus/leavewordsGrid.do", method = RequestMethod.GET)
	@ResponseBody
	public JqgridListForm findCmsJson(@RequestParam("page") String page,
			@RequestParam(value = "rows", defaultValue = "10") String rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord, @RequestParam("leavewords") String leavewords,
			@RequestParam(value = "searchField", required = false) String searchField,
			@RequestParam(value = "startDateTime", required = false) String startDateTime,
			@RequestParam(value = "endDateTime", required = false) String endDateTime) {
		int pageNo = 0;
		int row = 0;
		if (page != null) {
			pageNo = Integer.parseInt(page) - 1;
		}
		if (rows != null) {
			row = Integer.parseInt(rows);
		}
		JqgridListForm jqgridListForm = cLeaveWordsManageService.findLeaveWordsByLeavewords(getCusTokenInSession().getToken(), leavewords,
				pageNo + 1, row, sidx, sord);
		
		return jqgridListForm;
	}
	
	@RequestMapping(value = "/cus/toUpdateLeavewords.do")
	public String toUpdateLeaveWords(Model model,@RequestParam("id") String id,String pageNo,String leavewords){
		
        
    	SDictionary sDictionary = dictionaryService.findByLogicID(leavewords);
		String dicName = sDictionary.getDicName();
		model.addAttribute("dicName", dicName);
		model.addAttribute("token", getCusTokenInSession().getToken());
		CLeaveWordsManageForm form = cLeaveWordsManageService.findById(id);
		
		if(form.getReplyContent()!=null){
			form.setReplyContent(form.getReplyContent().replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
		}
		
		model.addAttribute("leavewordsForm", form);
//		List<String> picNameList = cLeaveWordsManageService.showPicList(id);
//		model.addAttribute("picNameList",picNameList);
		
		
		String pageSize = String.valueOf(loadConstant.getPageSize());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", pageNo);
		model.addAttribute("leavewords", leavewords);
		return "cus/leavewords/leavewords_update";
		
	
	}
	
	@RequestMapping(value = "/cus/updateLeavewords.do")
	@ResponseBody
	public ControllerJsonMessage updateLeavewords(Model model,String id,String replyBy,@RequestParam(value="KindEditor")String replyContent,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		CLeaveWordsManage leaveword = cLeaveWordsManageService.updateLeavewords(id, replyBy, replyContent);
		
		boolean isSuccess = false;
		if(leaveword!=null){
			//发送模板
			cReplyLeaveWordsTempService.sendToOne(leaveword.getId(), leaveword.getToken(), leaveword.getOpenId());
			isSuccess = true;
		}
		if (isSuccess) {
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
			
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
			
		}
		System.out.println("msg的信息为："+msg);
		return msg;
	}
	
	
	@RequestMapping(value = "/cus/deleteLeavewords.do")
	@ResponseBody
	public List<String> deleteLeavewords(@RequestParam("idStr") String idStr,
			@RequestParam("pageNo") String pageNo){
		String notice = cLeaveWordsManageService.deleteLeavewordsbatch(idStr);
		if ("".equals(notice)) {
			notice = "success";
		}
		List<String> resultList = new ArrayList<String>();
		resultList.add(notice);
		resultList.add(pageNo);
		return resultList;
	}
	
	
	
	
	
	
	/********************************************************以下是微信端
	 * @throws IOException **********************************************************/
	/*
	@RequestMapping("/m/leavewordsGetOpenId.do")
	public String getOpenId(RedirectAttributes attr,@RequestParam(value="openId") String openId,@RequestParam(value="typeCode") String typeCode,
			@RequestParam("logicID") String logicID,@RequestParam("token") String token){
		if(!typeCode.equals("typeCode") && !logicID.equals("logicID")){
			attr.addAttribute(typeCode, logicID);
		}
		attr.addAttribute("openId", openId);
		attr.addAttribute("token", token)
		return "redirect:/m/getOauthCode.do";
	}*/
	
	/**
	 * 获取授权code
	 * @param token
	 * @param leavewords
	 * @return
	 */
	/*@RequestMapping(value = "/m/getOauthCode.do", method = RequestMethod.GET)
	public String getOauthCode(@RequestParam("token") String token,@RequestParam(value = "leavewords") String leavewords,@RequestParam(value="openId") String openId){
		String appid = "wxb8e15ab662e90e56";
//		String appsecret = "06b90af35a7d18ef8df511ed09fd20fe";
//		String state = "225fb8d5394a44e983e7f6354954a21eYleavewords01";
		String state = token + "Y" + leavewords;
		String redirectUri = "http://zhwei.tunnel.qydev.com/zsymp-webapp/m/showLeavewordsAdd.do";
		String url = Oauth2Util.buildAuthorizationUrl(appid, redirectUri, state);
		
		return "redirect:" + url;
		
	}*/
	
	
	@RequestMapping(value = "/m/showLeavewordsAdd.do", method = RequestMethod.GET)
	public String showLeaveWordAdd(Model model,
			@RequestParam(value="openId") String openId,@RequestParam(value="typeCode") String typeCode,
			@RequestParam("logicID") String logicID,@RequestParam("token") String token) throws IOException{
		
		String leavewords = logicID;
		
		WxJsBean wxJsBean = wxJsService.findWxJsBean(token,
				"/m/showLeavewordsAdd.do?openID=" + openId + "&token=" + token);
		
		CFanForm cFanForm = cFanService.findByOpenID(openId);
		System.out.println("粉丝详情为---------："+cFanForm);
//		List<CFanUserForm> fanUserFormList = cFanUserService.findByOpenIDAndToken(openID, token);
//		List<SDictionaryForm> leavewordsFormList = sDictionaryService.findByTypeCode("leavewords");
//		List<SDictionaryForm> optionFormList = sDictionaryService.findByTypeCode("feedbackOption");
		
		model.addAttribute("wxJsBean", wxJsBean);
		model.addAttribute("cFanForm", cFanForm);
//		model.addAttribute("fanUserFormList", fanUserFormList);
//		model.addAttribute("leavewordsFormList", leavewordsFormList);
//		model.addAttribute("optionFormList", optionFormList);
		model.addAttribute("leavewords", leavewords);
		model.addAttribute("token", token);
		model.addAttribute("openId", openId);
		return "m/leavewords/leavewordsAdd";
	}
	
	
	
//	@RequestMapping(value = "/m/loadLWFanUserAddress.do")
//	@ResponseBody
//	public CFanUserForm loadFanUserAddress(String fanUserId){
//	
//		CFanUserForm form = cFanUserService.findById(fanUserId);
//		if(form!=null){
//			
//			return form;
//		}
//		return null;
//		
//	}
	
	@RequestMapping(value = "/m/saveLeavewords.do")
	@ResponseBody
	public ControllerJsonMessage saveLeaveWords(String leavewords,String nickName,String cityName,
			String content,String openId,String token,String accesstoken,Model model){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		boolean isSuccess = false;
		
		CLeaveWordsManage leaveword = cLeaveWordsManageService.saveLeaveWords(leavewords, nickName, cityName, content, openId, token, accesstoken);
		String id = leaveword.getId();
		if(leaveword!=null){
			//发送模板
		boolean flage=	cLeaveWordsTempService.sendToLeaveWordsPerson(token, openId, nickName, content);
		}
		if(leaveword!=null){
			isSuccess = true;
		}
		if (isSuccess) {
			msg.setResult(true);
			
		} else {
			msg.setResult(false);
			
		}
		return msg;
	}
	
//	public ControllerJsonMessage saveLeaveWords(String leavewords,String name,String tel,String customerType,String fanUserId,
//			String content,String address,String openId,String token,String sMediaIdStr,String accesstoken,Model model){
//		ControllerJsonMessage msg = new ControllerJsonMessage();
//		
//		boolean isSuccess = false;
//		
//		CLeaveWordsManage leaveword = cLeaveWordsManageService.saveLeaveWords(leavewords, name, tel, customerType, fanUserId, content, address, openId, token, sMediaIdStr, accesstoken);
//		if(leaveword!=null){
//			//发送模板
////			cFeedBackTempService.sendToFeedBackPerson(leaveword.getId(), token);
//		}
//		if(leaveword!=null){
//			isSuccess = true;
//		}
//		if (isSuccess) {
//			msg.setResult(true);
//			
//		} else {
//			msg.setResult(false);
//			
//		}
//		return msg;
//	}
	
}
