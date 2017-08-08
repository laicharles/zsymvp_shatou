package com.wisewater.function.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
import com.wisewater.function.service.CCommonPersonService;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.util.tools.LoadConstant;

@Controller
public class CCommonPersonController extends BaseController {

	@Autowired
	private CCommonPersonService cCommonPersonService;
	
	@Autowired
	LoadConstant lc;
	
	
	/**
	 * 列表
	 * @param model
	 * @param pageNo
	 * @return
	 * AlexFung
	 * 2015年8月28日 下午1:52:22
	 */
	@RequestMapping(value = "/cus/cCommonPerson_list.do")
	public String findCCommonPersonByToken(Model model,
			@RequestParam("pageNo") String pageNo) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		String pageSize = String.valueOf(lc.getPageSize());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", pageNo);
		return "cus/function/cCommonPerson_list";
	}

	/**
	 * 列表json
	 * 
	 * @param page
	 * @param sidx
	 * @param sord
	 * @return AlexFung 2015年4月1日 上午11:01:54
	 */
	@RequestMapping(value = "/cus/cCommonPersonGrid.do", method = RequestMethod.GET)
	public @ResponseBody JqgridListForm findCCommonPersonJson(
			@RequestParam("page") String page,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		int pageNo = 0;
		if (page != null)
			pageNo = Integer.parseInt(page) - 1;
		JqgridListForm jqgridListForm = cCommonPersonService.findCCommonPersonByToken(
				getCusTokenInSession().getToken(), pageNo + 1, sidx, sord);
		return jqgridListForm;
	}

	/**
	 * 新增
	 * @param model
	 * @param wxPayErrorForm
	 * @return
	 * AlexFung
	 * 2015年8月28日 下午1:57:12
	 */
	@RequestMapping(value = "/cus/cCommonPerson_toAdd.do")
	public String toSaveCCommonPerson(Model model, CCommonPersonForm cCommonPersonForm) {
		return "cus/function/cCommonPerson_add";
	}

	@RequestMapping(value = "/cus/findcCommonPersonList.do")
	@ResponseBody
	public List<CCommonPersonForm> findAllCCommonPersonByToken(){
		
		return cCommonPersonService.findCCommonPersonByToken(getCusTokenInSession().getToken());
		
	}
	
	/**
	 * 新增
	 * @param model
	 * @param wxPayErrorForm
	 * @param locales
	 * @return
	 * AlexFung
	 * 2015年8月28日 下午1:54:32
	 */
	@RequestMapping(value = "/cus/cCommonPerson_add.do")
	public @ResponseBody ControllerJsonMessage saveCCommonPerson(Model model,
			CCommonPersonForm cCommonPersonForm,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		cCommonPersonForm.setToken(getCusTokenInSession().getToken());
		boolean isSuccess = cCommonPersonService.saveCCommonPerson(cCommonPersonForm);
		msg.setResult(isSuccess);
		if (isSuccess) {
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
		}
		return msg;
	}
	

	/**
	 * 根据ID查找新闻动态详细信息
	 * 
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return AlexFung 2015年4月1日 上午11:02:14
	 */
	@RequestMapping(value = "/cus/cCommonPerson_toUpdate.do")
	public String toUpdateCCommonPerson(Model model, @RequestParam("id") String id,
			@RequestParam("pageNo") String pageNo) {
		CCommonPersonForm cCommonPersonForm = cCommonPersonService.findCCommonPersonById(id);
		model.addAttribute("cCommonPersonForm", cCommonPersonForm);
		// 验证数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		// 保存页码
		model.addAttribute("page", pageNo);
		return "cus/function/cCommonPerson_update";
	}

	/**
	 * 更新新闻动态
	 * 
	 * @param model
	 * @param wxPayErrorForm
	 * @param locales
	 * @return AlexFung 2015年4月1日 上午11:02:27
	 */
	@RequestMapping(value = "/cus/cCommonPerson_update.do")
	public @ResponseBody ControllerJsonMessage updateCCommonPerson(Model model,
			CCommonPersonForm cCommonPersonForm,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		CCommonPerson cCommonPerson = cCommonPersonService.updateCCommonPerson(cCommonPersonForm);
		boolean isSuccess = false;
		if (cCommonPerson != null) {
			isSuccess = true;
		}
		msg.setResult(isSuccess);
		if (isSuccess) {
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
		}
		model.addAttribute("cCommonPersonForm", cCommonPerson);
		return msg;
	}

	/**
	 * 批量删除新闻动态(逻辑删除)
	 * 
	 * @param idStr
	 * @param pageNo
	 * @return AlexFung 2015年4月1日 上午11:02:33
	 */
	@RequestMapping(value = "/cus/cCommonPerson_delete.do")
	public @ResponseBody List<String> deleteCCommonPersonBatch(
			@RequestParam("idStr") String idStr,
			@RequestParam("pageNo") String pageNo) {
		String notice = cCommonPersonService.deleteCCommonPersonbatch(idStr);
		if ("".equals(notice)) {
			notice = "success";
		}
		List<String> resultList = new ArrayList<String>();
		resultList.add(notice);
		resultList.add(pageNo);
		return resultList;
	}
}
