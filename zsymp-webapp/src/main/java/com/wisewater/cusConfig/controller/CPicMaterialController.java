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
import com.wisewater.cusConfig.service.CPicMaterialService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.wechatpublic.util.DateUtils;
import com.wisewater.wechatpublic.util.StringUtil;

@Controller
@RequestMapping("/cus")
public class CPicMaterialController extends BaseController {

	@Autowired
	private CPicMaterialService cpicmaterialService;

	@Autowired
	protected DozerBeanMapper mapper;

	/**
	 * 
	 * @param model
	 * @param pageNo
	 *            页码，默认查询第1页
	 * @return 描述：
	 */
	@RequestMapping("/cPicMtrl_list.do")
	public String findInPage(Model model, @RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		model.addAttribute("page", pageNo);

		String picPath = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";
		model.addAttribute("picPath", picPath);

		return "cus/cusConfig/cPicMtrl_list";
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
	@RequestMapping("/cPicMtrl_list_json.do")
	public @ResponseBody JqgridListForm findCusPicMtrlsJson(Model model,
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

		JqgridListForm jqgridListForm = cpicmaterialService.findAll(pageNo, sidx, sord, tag, title,
				getCusTokenInSession().getToken());

		return jqgridListForm;
	}

	/**
	 * 
	 * @param model
	 * @return 描述：跳转新增页面
	 */
	@RequestMapping("/cPicMtrl_toAdd.do")
	public String toAddCusPicMtrl(Model model) {

		model.addAttribute("token", getCusTokenInSession().getToken());

		// for cPicMtrl_picklist.jsp
		String cusPicPathPickList = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";
		model.addAttribute("cusPicPathPickList", cusPicPathPickList);

		return "cus/cusConfig/cPicMtrl_add";

	}

	/**
	 * 
	 * @param model
	 * @param
	 * @param result
	 * @return 描述：新增操作
	 */
	@RequestMapping("/cPicMtrl_add.do")
	public @ResponseBody ControllerJsonMessage saveCusPicMtrl(Model model, CPicMaterialForm picMtrlForm,
			@RequestHeader("Accept-Language") String locales) {

		ControllerJsonMessage msg = new ControllerJsonMessage();

		// form验证
		if (picMtrlForm.getTitle() == null || picMtrlForm.getTitle().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_pic_material.title.null", null, locales));
			return msg;
		}

		picMtrlForm.setCreatedBy(getCusSessionUser().getUserName());
		picMtrlForm.setCreatedDateTime(DateUtils.getDateTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
		picMtrlForm.setToken(getCusTokenInSession().getToken());
		// 保存操作
		if (cpicmaterialService.save(picMtrlForm, getCusTokenInSession().getToken())) {
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
	@RequestMapping("/cPicMtrl_checkMtrlId.do")
	public @ResponseBody ControllerJsonMessage checkMtrlId(Model model, @RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales) {

		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (id != null && id.trim().length() > 0) {
			// 业务验证
			CPicMaterialForm form = cpicmaterialService.findById(id);
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
	@RequestMapping("/cPicMtrl_toUpdate.do")
	public String toUpdatePicMtrl(Model model, @RequestParam("id") String id,
			@RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {

		CPicMaterialForm mtrlForm = cpicmaterialService.findById(id);
		if (mtrlForm != null) {
			model.addAttribute("mtrlForm", mtrlForm);
		} else {
			return "redirect:/biz/cPicMtrl_list.do?pageNo=1";
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

		return "cus/cusConfig/cPicMtrl_update";
	}

	/**
	 * 
	 * @param model
	 * @param roleForm
	 * @param result
	 * @param pageNo
	 * @return 描述：处理编辑请求
	 */
	@RequestMapping("/cPicMtrl_update.do")
	public @ResponseBody ControllerJsonMessage updatePicMtrl(Model model, CPicMaterialForm mtrlForm,
			@RequestHeader("Accept-Language") String locales) {

		ControllerJsonMessage msg = new ControllerJsonMessage();

		// 业务验证，修改的记录不存在
		CPicMaterialForm checkedMtrlForm = cpicmaterialService.findById(mtrlForm.getId());
		if (checkedMtrlForm == null) {
			msg.setWarnMsg(getBundleMessage("update.not.exists", null, locales));
			return msg;
		}

		// form验证
		if (mtrlForm.getTitle() == null || mtrlForm.getTitle().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_pic_material.title.null", null, locales));
			return msg;
		}

		mtrlForm.setCreatedBy(getCusSessionUser().getUserName());
		mtrlForm.setCreatedDateTime(DateUtils.getDateTime(new Date(), "yyyy-MM-dd HH:mm:ss"));

		mtrlForm.setToken(getCusTokenInSession().getToken());
		// 保存操作
		if (cpicmaterialService.update(mtrlForm, getCusTokenInSession().getToken())) {
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
	 * @param ids
	 * @param pageNo
	 * @return 描述：删除操作
	 */
	@RequestMapping("/cPicMtrl_delete.do")
	public @ResponseBody ControllerJsonMessage deletePicMtrl(Model model, @RequestParam("ids") String ids,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (cpicmaterialService.deleteByIds(ids)) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("delete.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("delete.fail", null, locales));
		}
		return msg;
	}

	@RequestMapping(value = "/cPicMtrl_getform.do", method = RequestMethod.GET)
	public @ResponseBody CPicMaterialForm inherit(@RequestParam("id") String id) {

		CPicMaterialForm form = cpicmaterialService.findById(id);

		return form;
	}

	/*
	 * @RequestMapping(value = "/cPicMtrl_getform.do", method =
	 * RequestMethod.GET) public @ResponseBody CPicMaterialForm inherit(
	 * 
	 * @RequestParam("id") String id) {
	 * 
	 * CPicMaterialForm form = cpicmaterialService.findById(id);
	 * 
	 * if(form==null){ BPicMaterialForm bform =
	 * bpicmaterialService.findById(id); form=mapper.map(bform,
	 * CPicMaterialForm.class); form.setToken(loadConstant.getToken()); }
	 * 
	 * String toToken=getCusTokenInSession().getToken(); String fromToken =
	 * form.getToken(); String fromPicName=form.getPicName(); SimpleDateFormat
	 * sdf = new SimpleDateFormat("yyyyMMddHHmmsss"); String
	 * toPicName=sdf.format(new
	 * Date())+fromPicName.substring(fromPicName.lastIndexOf("."),
	 * fromPicName.length());
	 * 
	 * 
	 * HttpServletRequest request = ((ServletRequestAttributes)
	 * RequestContextHolder .getRequestAttributes()).getRequest(); String
	 * fromFile = request.getSession().getServletContext() .getRealPath("\\") +
	 * "resources\\attached\\" + fromToken + "\\"+fromPicName; String toFile =
	 * request.getSession().getServletContext() .getRealPath("\\") +
	 * "resources\\attached\\" + toToken + "\\"+toPicName;
	 * FileCopyUtil.copyFile(fromFile, toFile);
	 * 
	 * form.setPicName(toPicName); return form; }
	 */

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
	@RequestMapping("/cPicMtrl_picklist_json.do")
	public @ResponseBody JqgridListForm findPicMtrlsJson(Model model,
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
		jqgridListForm = cpicmaterialService.findAll(pageNo, sidx, sord, tag, title, getCusTokenInSession().getToken());
		return jqgridListForm;
	}

}