package com.wisewater.system.controller;

import com.wisewater.base.BaseController;
import com.wisewater.form.utils.JqgridListForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import com.wisewater.system.service.SOperateLogService;

@Controller
public class SOperateLogController extends BaseController {

	@Autowired
	private SOperateLogService soperatelogService;
   
	/**
	 * 
	 * 描述：后台日志列表
	 * @author AlexFung
	 * 2016年8月3日 下午1:54:52
	 * @param model
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "/cus/operateLog_list.do")
	public String findOperate(Model model,
			@RequestParam("pageNo") String pageNo) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		String pageSize = String.valueOf(loadConstant.getPageSize());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", pageNo);
		return "cus/log/operateLog_list";
	}

	/**
	 * 
	 * 描述：后台操作日志
	 * @author AlexFung
	 * 2016年8月3日 下午1:55:47
	 * @param page
	 * @param sidx
	 * @param sord
	 * @return
	 */
	@RequestMapping(value = "/cus/operateLogGrid.do", method = RequestMethod.GET)
	public @ResponseBody JqgridListForm findOperate(
			@RequestParam("page") String page,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		int pageNo = 0;
		if (page != null)
			pageNo = Integer.parseInt(page) - 1;
		JqgridListForm jqgridListForm = soperatelogService.findSOperateLog( pageNo + 1, sidx, sord);
		return jqgridListForm;
	}
}