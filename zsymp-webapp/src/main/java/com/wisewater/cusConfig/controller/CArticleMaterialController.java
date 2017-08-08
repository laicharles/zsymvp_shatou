package com.wisewater.cusConfig.controller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.cusConfig.service.CArticleMaterialService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.wechatpublic.util.DateUtils;
import com.wisewater.wechatpublic.util.StringUtil;

@Controller
@RequestMapping("/cus")
public class CArticleMaterialController extends BaseController {

	@Autowired
	private CArticleMaterialService carticlematerialService;

	@Autowired
	protected DozerBeanMapper mapper;

	/**
	 * 
	 * @param model
	 * @param pageNo
	 *            页码，默认查询第1页
	 * @return 描述：
	 */
	@RequestMapping("/cArticleMtrl_list.do")
	public String findInPage(Model model, @RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		model.addAttribute("page", pageNo);

		String picPath = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";
		model.addAttribute("picPath", picPath);

		return "cus/cusConfig/cArticleMtrl_list";
	}

	/**
	 * 
	 * @param page
	 *            查询页码
	 * @param sidx
	 *            排序字段
	 * @param sord
	 *            排序方式 asc 或desc
	 * @return 描述：分页查询角色列表
	 */
	@RequestMapping("/cArticleMtrl_list_json.do")
	public @ResponseBody JqgridListForm findCusArticleMtrlsJson(Model model,
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "title", required = false) String title, @RequestParam("page") String page,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {

		int pageNo = 1;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(page);
		if (isNum.matches()) {
			pageNo = Integer.parseInt(page);
		}

		if (StringUtils.isEmpty(tag)) {
			tag = "";
		}
		if (StringUtils.isEmpty(title)) {
			title = "";
		}
		tag = StringUtil.unescape(tag);
		title = StringUtil.unescape(title);

		JqgridListForm jqgridListForm = carticlematerialService.findAll(pageNo, sidx, sord, tag, title,
				getCusTokenInSession().getToken());

		return jqgridListForm;
	}

	/**
	 * 
	 * @param model
	 * @return 描述：跳转新增页面
	 */
	@RequestMapping("/cArticleMtrl_toAdd.do")
	public String toAddCusArticleMtrl(Model model) {

		model.addAttribute("token", getCusTokenInSession().getToken());

		// for cPicMtrl_picklist.jsp
		String cusPicPathPickList = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";
		model.addAttribute("cusPicPathPickList", cusPicPathPickList);

		return "cus/cusConfig/cArticleMtrl_add";
	}

	/**
	 * 
	 * @param model
	 * @param roleForm
	 * @param result
	 * @return 描述：新增操作
	 */
	@RequestMapping("/cArticleMtrl_add.do")
	public @ResponseBody ControllerJsonMessage saveCusArticleMtrl(Model model, CArticleMaterialForm articleMtrlForm,
			@RequestHeader("Accept-Language") String locales) {

		ControllerJsonMessage msg = new ControllerJsonMessage();

		// form验证
		if (articleMtrlForm.getTitle() == null || articleMtrlForm.getTitle().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_article_material.title.null", null, locales));
			return msg;
		}

		articleMtrlForm.setCreatedBy(getCusSessionUser().getUserName());
		articleMtrlForm.setCreatedDateTime(DateUtils.getDateTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
		articleMtrlForm.setToken(getCusTokenInSession().getToken());
		// 保存操作
		if (carticlematerialService.save(articleMtrlForm, getCusTokenInSession().getToken())) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
		}
		return msg;
	}

	/**
	 * 
	 * @param model
	 * @param id
	 * @param locales
	 * @return 描述：ID检查记录
	 */
	@RequestMapping("/cArticleMtrl_checkMtrlId.do")
	public @ResponseBody ControllerJsonMessage checkMtrlId(Model model, @RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales) {

		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (id != null && id.trim().length() > 0) {
			// 业务验证
			CArticleMaterialForm form = carticlematerialService.findById(id);
			if (form == null) {
				msg.setWarnMsg(getBundleMessage("record.not.exists", null, locales));
				return msg;
			} else {
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
	 * @return 描述：跳转至编辑页面
	 */
	@RequestMapping("/cArticleMtrl_toUpdate.do")
	public String toUpdateArticleMtrl(Model model, @RequestParam("id") String id,
			@RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {

		CArticleMaterialForm mtrlForm = carticlematerialService.findById(id);
		if (mtrlForm != null) {
			model.addAttribute("mtrlForm", mtrlForm);
		} else {
			return "redirect:/cus/cArticleMtrl_list.do?pageNo=1";
		}

		// 验证数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}

		// 保存页码
		model.addAttribute("page", pageNo);

		model.addAttribute("token", getCusTokenInSession().getToken());

		String picPath = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";
		model.addAttribute("picPath", picPath);

		// for cPicMtrl_picklist.jsp
		String cusPicPathPickList = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";
		model.addAttribute("cusPicPathPickList", cusPicPathPickList);

		return "cus/cusConfig/cArticleMtrl_update";
	}

	/**
	 * 
	 * @param model
	 * @param roleForm
	 * @param result
	 * @param pageNo
	 * @return 描述：处理编辑请求
	 */
	@RequestMapping(value = { "/cArticleMtrl_update.do" })
	public @ResponseBody ControllerJsonMessage updateArticleMtrl(Model model, CArticleMaterialForm mtrlForm,
			@RequestHeader("Accept-Language") String locales) {

		ControllerJsonMessage msg = new ControllerJsonMessage();

		// 业务验证，修改的记录不存在
		CArticleMaterialForm checkedMtrlForm = carticlematerialService.findById(mtrlForm.getId());
		if (checkedMtrlForm == null) {
			msg.setWarnMsg(getBundleMessage("update.not.exists", null, locales));
			return msg;
		}

		// form验证
		if (mtrlForm.getTitle() == null || mtrlForm.getTitle().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_article_material.title.null", null, locales));
			return msg;
		}

		mtrlForm.setCreatedBy(getCusSessionUser().getUserName());
		mtrlForm.setCreatedDateTime(DateUtils.getDateTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
		mtrlForm.setToken(getCusTokenInSession().getToken());
		// 保存操作
		if (carticlematerialService.update(mtrlForm, getCusTokenInSession().getToken())) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
		}
		return msg;

	}

	/**
	 * 
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return 描述：删除操作
	 */
	@RequestMapping("/cArticleMtrl_delete.do")
	public @ResponseBody ControllerJsonMessage deleteArticleMtrl(Model model, @RequestParam("ids") String ids,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (carticlematerialService.deleteByIds(ids)) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("delete.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("delete.fail", null, locales));
		}
		return msg;
	}

	@RequestMapping(value = "/cArticleMtrl_getform.do", method = RequestMethod.GET)
	public @ResponseBody CArticleMaterialForm inherit(@RequestParam("id") String id) {

		CArticleMaterialForm form = carticlematerialService.findById(id);

		return form;
	}

	/**
	 * 
	 * @param page
	 *            查询页码
	 * @param sidx
	 *            排序字段
	 * @param sord
	 *            排序方式 asc 或desc
	 * @return 描述：分页查询角色列表
	 */
	@RequestMapping("/cArticleMtrl_picklist_json.do")
	public @ResponseBody JqgridListForm findArticleMtrlsJson(Model model,
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "mtrlFrom", required = false) String mtrlFrom, @RequestParam("page") String page,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {

		int pageNo = 1;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(page);
		if (isNum.matches()) {
			pageNo = Integer.parseInt(page);
		}
		if (StringUtils.isEmpty(tag)) {
			tag = "";
		}
		if (StringUtils.isEmpty(title)) {
			title = "";
		}
		if (StringUtils.isEmpty(mtrlFrom)) {
			mtrlFrom = "";
		}
		tag = StringUtil.unescape(tag);
		title = StringUtil.unescape(title);
		mtrlFrom = StringUtil.unescape(mtrlFrom);
		JqgridListForm jqgridListForm;
		jqgridListForm = carticlematerialService.findAll(pageNo, sidx, sord, tag, title,
				getCusTokenInSession().getToken());
		return jqgridListForm;
	}

}