package com.wisewater.function.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.pojo.CCommonPerson;
import com.wisewater.function.pojo.CFeedBackPerson;
import com.wisewater.function.service.CFeedBackPersonService;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.util.tools.LoadConstant;

@Controller
public class CFeedBackPersonController extends BaseController{
	
	@Autowired
	private CFeedBackPersonService cFeedBackPersonService;
	
	@Autowired
	LoadConstant lc;
	
	@RequestMapping(value = "/cus/cFeedBackPerson_list.do")
	public String findCFeedBackPerson(Model model,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		String pageSize = String.valueOf(lc.getPageSize());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", pageNo);
		return "cus/function/cFeedBackPerson_list";
	}
	
	@RequestMapping(value = "/cus/cFeedBackPersonGrid.do", method = RequestMethod.GET)
	@ResponseBody
	public JqgridListForm findCFeedBackPersonJson(
			@RequestParam("page") String page,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord){
		int pageNo = 0;
		if (page != null){
			pageNo = Integer.parseInt(page) - 1;
		}
		System.out.println(getCusTokenInSession().getToken());
		JqgridListForm jqgridListForm = cFeedBackPersonService.showAllPerson(pageNo, sidx, sord, getCusTokenInSession().getToken());
		return jqgridListForm;
	}
	
	@RequestMapping(value = "/cus/cFeedBackPerson_toAdd.do")
	public String toSaveCFeedBackPerson(Model model) {
		return "cus/function/cFeedBackPerson_add";
	}
	
	@RequestMapping(value = "/cus/cFeedBackPerson_add.do")
	@ResponseBody
	public ControllerJsonMessage saveCFeedBackPerson(Model model,CFeedBackPersonForm cFeedBackPersonForm,@RequestHeader("Accept-Language") String locales){
		boolean isSuccess = false;
		ControllerJsonMessage msg = new ControllerJsonMessage();
		cFeedBackPersonForm.setToken(getCusTokenInSession().getToken());
		if(cFeedBackPersonService.saveCFeedBackPerson(cFeedBackPersonForm)){
			isSuccess = true;
		}
		msg.setResult(isSuccess);
		if (isSuccess) {
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/cus/cFeedBackPerson_toUpdate.do")
	public String toUpdateCFeedBackPerson(Model model, @RequestParam("id") String id,
			@RequestParam("pageNo") String pageNo) {
		
		CFeedBackPersonForm form = cFeedBackPersonService.findById(id);
		model.addAttribute("cFeedBackPersonForm", form);
		// 验证数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		// 保存页码
		model.addAttribute("page", pageNo);
		return "cus/function/cFeedBackPerson_update";
	}
	
	@RequestMapping(value = "/cus/cFeedBackPerson_update.do")
	public @ResponseBody ControllerJsonMessage updateCCommonPerson(Model model,
			CFeedBackPersonForm cFeedBackPersonForm,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		cFeedBackPersonForm.setToken(getCusTokenInSession().getToken());
		CFeedBackPerson person = cFeedBackPersonService.updateCFeedBackPerson(cFeedBackPersonForm);
		boolean isSuccess = false;
		if (person != null) {
			isSuccess = true;
		}
		msg.setResult(isSuccess);
		if (isSuccess) {
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
		}
		model.addAttribute("cFeedBackPersonForm", person);
		return msg;
	}
	
	
	@RequestMapping(value = "/cus/cFeedBackPerson_delete.do")
	public @ResponseBody List<String> deleteCCommonPersonBatch(
			@RequestParam("idStr") String idStr,
			@RequestParam("pageNo") String pageNo) {
		String notice = cFeedBackPersonService.deleteCFeedBackPersonBatch(idStr);
		if ("".equals(notice)) {
			notice = "success";
		}
		List<String> resultList = new ArrayList<String>();
		resultList.add(notice);
		resultList.add(pageNo);
		return resultList;
	}
}
