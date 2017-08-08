package com.wisewater.auto.controller;

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

import com.wisewater.auto.service.CAutoService;
import com.wisewater.base.BaseController;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.util.tools.LoadConstant;

@Controller
public class CAutoController extends BaseController {

	@Autowired
	LoadConstant loadConstant;

	@Autowired
	private CAutoService cautoService;

	/**
	 * 打开自动列表
	 * 
	 * @param model
	 * @param pageNo
	 * @return XingXingLvCha 2015年4月7日 上午11:25:54
	 */
	@RequestMapping(value = { "/cus/cauto_list.do" }, method = RequestMethod.GET)
	public String cauto_list(Model model, String pageNo) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		String pageSize = String.valueOf(loadConstant.getPageSize());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", pageNo);
		return "/cus/auto/cauto_list";
	}

	/**
	 * grid数据
	 * 
	 * @param page
	 * @param sidx
	 * @param sord
	 * @return XingXingLvCha 2015年4月7日 下午8:16:21
	 */
	@RequestMapping(value = { "/cus/cautoGrid.do" }, method = RequestMethod.GET)
	@ResponseBody
	public JqgridListForm findCAutoList(String page, @RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord,
			@RequestParam(value = "question", required = false) String question,
			@RequestParam(value = "keywordStr", required = false) String keywordStr,
			@RequestParam(value = "tagStr", required = false) String tagStr) {
		int pageNo = 0;
		if (page != null) {
			pageNo = Integer.parseInt(page) - 1;
		}

		JqgridListForm jqgridListForm = cautoService.findAllCAuto(pageNo + 1, sidx, sord, question, keywordStr, tagStr,
				getCusTokenInSession().getToken());
		return jqgridListForm;
	}

	/**
	 * 打开新增页面
	 * 
	 * @return XingXingLvCha 2015年4月7日 下午3:13:38
	 */
	@RequestMapping(value = { "/cus/cauto_toAdd.do" }, method = RequestMethod.GET)
	public String cauto_toAdd(Model model) {

		return "/cus/auto/cauto_add";
	}

	/**
	 * 新增自动回复
	 * 
	 * @param model
	 * @param cAutoForm
	 * @return XingXingLvCha 2015年4月7日 上午10:29:54
	 */
	@RequestMapping(value = { "/cus/cauto_add.do" }, method = RequestMethod.POST)
	@ResponseBody
	public String cauto_add(Model model, CAutoForm cAutoForm) {

		return cautoService.saveCAuto(cAutoForm, getCusTokenInSession().getToken());
	}

	/**
	 * 编辑自动
	 * 
	 * @param id
	 * @param model
	 * @return XingXingLvCha 2015年4月7日 上午10:30:14
	 */
	@RequestMapping(value = { "/cus/cauto_toUpdate.do" }, method = RequestMethod.GET)
	public String cauto_toUpdate(String id, Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {
		CAutoForm cAutoForm = cautoService.findCAutoByID(id);
		model.addAttribute("cAutoForm", cAutoForm);
		model.addAttribute("page", pageNo);

		model.addAttribute("token", getCusTokenInSession().getToken());
		return "/cus/auto/cauto_update";
	}

	/**
	 * 修改自动
	 * 
	 * @param model
	 * @param cAutoForm
	 * @return XingXingLvCha 2015年4月7日 上午10:30:37
	 */
	@RequestMapping(value = { "/cus/cauto_update.do" }, method = RequestMethod.POST)
	@ResponseBody
	public String cauto_update(Model model, CAutoForm cAutoForm) {

		return cautoService.updateCAuto(cAutoForm, getCusTokenInSession().getToken());
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @param IDStr
	 * @param locales
	 * @return XingXingLvCha 2015年4月9日 下午1:31:41
	 */
	@RequestMapping(value = { "/cus/cauto_delete.do" }, method = RequestMethod.POST)
	@ResponseBody
	public ControllerJsonMessage cauto_delete(Model model, String IDStr,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (IDStr == null || "".equals(IDStr)) {
			msg.setResult(false);
			msg.setWarnMsg(getBundleMessage("delete.not.exists", null, locales));
			return msg;
		}
		cautoService.deleteCAuto(IDStr);
		msg.setResult(true);
		msg.setWarnMsg(getBundleMessage("delete.success", null, locales));
		return msg;
	}

}