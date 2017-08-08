package com.wisewater.fans.controller;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.cusConfig.controller.CMpForm;
import com.wisewater.cusConfig.service.CMpService;
import com.wisewater.fans.service.CFanService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.controller.CCommonPersonForm;
import com.wisewater.function.controller.CFeedBackPersonForm;
import com.wisewater.function.service.CCommonPersonService;
import com.wisewater.system.controller.SDictionaryForm;
import com.wisewater.system.service.SDictionaryService;
import com.wisewater.util.tools.ControllerJsonMessage;

@Controller
@RequestMapping("/cus")
public class CFanController extends BaseController {

	@Autowired
	private CFanService cfanService;
	
	@Autowired
	private SDictionaryService dictionaryService;
   
	@Autowired
	private CMpService cmpService;
	
	@Autowired
	private CCommonPersonService cCommonPersonService;
	
	/**
	 * 
	 * @param model
	 * @param pageNo
	 * @return
	 * gawen.chen
	 * 2015年4月10日上午10:35:10
	 * 描述：跳转查询页面
	 */
	@RequestMapping("/cfan_list.do")
	public String findAllInPage(Model model,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
    	
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
        model.addAttribute("page", pageNo);
    	
        List<SDictionaryForm> bindStatus = dictionaryService.findByTypeCode("bindStatus");
        model.addAttribute("bindStatus", bindStatus);
    		
		return "cus/fans/cfan_list";
	}
	
	
	/**
	 * 
	 * @param model
	 * @param page  查询页码
     * @param sidx   排序字段
     * @param sord  排序方式 asc 或desc
	 * @param nickName
	 * @param isDisabled
	 * @param bindStatus
	 * @param userName
	 * @return
	 * gawen.chen
	 * 2015年4月10日上午10:54:26
	 * 描述：分页查询
	 */
	@RequestMapping(value = { "/cfan_list_json.do" },method = RequestMethod.POST)
	public @ResponseBody JqgridListForm findPageByJson(Model model,
			@RequestParam(value="page",defaultValue="1") String page,
			@RequestParam(value="sidx",required=false) String sidx,
			@RequestParam(value="sord",required=false) String sord,
			@RequestParam(value="nickName",required=false) String nickName,
			@RequestParam(value="hasBind",required=false) String hasBind){
		
		int pageNo = 1;
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(page);
        if(isNum.matches()){
        	pageNo = Integer.parseInt(page);
        }
        
        if(StringUtils.isEmpty(nickName)){nickName="";}
        if(StringUtils.isEmpty(hasBind)){hasBind="";}
        
        if(StringUtils.isEmpty(sidx)){
        	sidx="nickName";
        }
        if(StringUtils.isEmpty(sord)){
        	sord="asc";
        }

        JqgridListForm jqgridListForm = cfanService.findAll(pageNo, sidx, sord, nickName,  hasBind,getCusTokenInSession().getToken());
		
		return jqgridListForm;
	}
	
	/**
	 * 
	 * @param id
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月10日下午5:15:51
	 * 描述：根据ID检查
	 */
	@RequestMapping(value="/cfan_checkById.do")
	public @ResponseBody ControllerJsonMessage checkById(@Param("id")String id,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		if(StringUtils.isEmpty(id)){
			msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			return msg;
		}else{
			
			CFanForm fanForm = cfanService.findById(id);
			if(fanForm==null){
				msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			}else{
				msg.setResult(true);
			}
		}
		
		return msg;
	}
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return
	 * gawen.chen
	 * 2015年4月10日下午5:15:55
	 * 描述：查看粉丝
	 */
	@RequestMapping(value="/cfan_toView.do")
	public String toViewFan(Model model,@Param("id")String id,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
		
		CFanForm fanForm = cfanService.findById(id);
    	if(fanForm!=null){
    		model.addAttribute("fanForm", fanForm);
    	}else{
    		return "redirect:/cus/cfan_list.do?pageNo=1";
    	}
    	
    	List<SDictionaryForm> bindStatus = dictionaryService.findByTypeCode("bindStatus");
        model.addAttribute("bindStatus", bindStatus);
    	
    	//验证数字
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
    	
        //保存页码
        model.addAttribute("pageNo", pageNo);
		
		return "/cus/fans/cfan_view";
	}
	
	
	/**
	 * 
	 * @param id
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月10日下午5:16:58
	 * 描述：同步粉丝列表
	 */
	@RequestMapping(value="/cfan_synFans.do")
	public @ResponseBody ControllerJsonMessage synFans(@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		CMpForm cmp = cmpService.findByToken(getCusTokenInSession().getToken());
		if(cmp==null || cmp.getAppID()==null || cmp.getAppID().equalsIgnoreCase("")){
			msg.setWarnMsg(getBundleMessage("c_fan.syn.mp.null",null,locales));
			return msg;
		} 
		if(cfanService.saveSynAllFans(getCusTokenInSession().getToken())){
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("c_fan.syn.success",null,locales));
		}else{
			msg.setWarnMsg(getBundleMessage("c_fan.syn.fail",null,locales));
		}
		
		return msg;
	}
	
	
	/**
	 * 把粉丝添加到常用人员
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return
	 * Jiahui 2016年8月19日上午10:10:10
	 */
	@RequestMapping(value = "/toAddCommonPerson.do")
	public String fanToaddCCommonPerson(Model model, @RequestParam("id") String id,
			@RequestParam("pageNo") String pageNo) {
		//根据id查粉丝详情
		CFanForm fanForm = cfanService.findById(id);
		CCommonPersonForm cCommonPersonForm = new CCommonPersonForm();
		cCommonPersonForm.setId(UUID.randomUUID().toString());
		cCommonPersonForm.setToken(fanForm.getToken());
		cCommonPersonForm.setOpenID(fanForm.getOpenID());
		cCommonPersonForm.setName(fanForm.getNickName());
		
		model.addAttribute("cCommonPersonForm", cCommonPersonForm);
		// 验证数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		// 保存页码
		model.addAttribute("page", pageNo);
		return "cus/fans/cfan_toAddCommonPerson";
	}
	
	@RequestMapping(value = "/toAddCFeedBackPerson.do")
	public String fanToaddCFeedBackPerson(Model model, @RequestParam("id") String id,
			@RequestParam("pageNo") String pageNo) {
		//根据id查粉丝详情
		CFanForm fanForm = cfanService.findById(id);
		CFeedBackPersonForm cFeedBackPersonForm = new CFeedBackPersonForm();
		cFeedBackPersonForm.setId(UUID.randomUUID().toString());
		cFeedBackPersonForm.setToken(fanForm.getToken());
		cFeedBackPersonForm.setOpenID(fanForm.getOpenID());
		cFeedBackPersonForm.setName(fanForm.getNickName());
		
		model.addAttribute("cFeedBackPersonForm", cFeedBackPersonForm);
		// 验证数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		// 保存页码
		model.addAttribute("page", pageNo);
		return "cus/fans/cfan_toAddFeedBackPerson";
	}
   
}