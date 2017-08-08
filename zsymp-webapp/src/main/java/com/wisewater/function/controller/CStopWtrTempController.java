package com.wisewater.function.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.service.CStopWtrTempService;
import com.wisewater.review.pojo.SReviewPermissions;
import com.wisewater.review.service.SReviewPermissionsService;
import com.wisewater.util.tools.ControllerJsonMessage;

/**
 * 
 * 描述：停水通知模板
 * 
 * @author AlexFung 2016年7月26日 下午3:08:33
 */
@Controller
@RequestMapping("/cus")
public class CStopWtrTempController extends BaseController {

	@Autowired
	private CStopWtrTempService cStopWtrTempService;
	
	@Autowired
	private SReviewPermissionsService sReviewPermissionsService;

	/**
	 * 
	 * @param model
	 * @param pageNo
	 *            页码，默认查询第1页
	 * @return 描述：
	 */
	@RequestMapping("/cStopWtrTemp_list.do")
	public String findInPage(Model model, @RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		BCustomerUserForm user = getCusSessionUser();
		SReviewPermissions reviewPermissions = sReviewPermissionsService.findReviewById(user.getCurrentPermissions());
		
		model.addAttribute("page", pageNo);
		model.addAttribute("level", reviewPermissions.getLevel());
		
		return "cus/function/cStopWtrTemp_list";
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
	@RequestMapping("/cStopWtrTemp_list_json.do")
	public @ResponseBody JqgridListForm findStopWtrTempsJson(Model model, @RequestParam("page") String page,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		int pageNo = 1;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(page);
		if (isNum.matches()) {
			pageNo = Integer.parseInt(page);
		}
		JqgridListForm jqgridListForm = cStopWtrTempService.findAll(pageNo, sidx, sord,
				getCusTokenInSession().getToken());
		return jqgridListForm;
	}

	/**
	 * 
	 * @param model
	 * @return 描述：跳转新增页面
	 */
	@RequestMapping("/cStopWtrTemp_toAdd.do")
	public String toAddStopWtrTemp(Model model) {
		return "cus/function/cStopWtrTemp_add";
	}

	/**
	 * 
	 * @param model
	 * @param roleForm
	 * @param result
	 * @return 描述：新增操作
	 */
	@RequestMapping("/cStopWtrTemp_add.do")
	public @ResponseBody ControllerJsonMessage saveStopWtrTemp(Model model, CStopWtrTempForm stopWtrTempForm,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		// form验证
		if (stopWtrTempForm.getFirst() == null || stopWtrTempForm.getFirst().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_stop_wtr_temp.first.null", null, locales));
			return msg;
		}
		if (stopWtrTempForm.getWhy() == null || stopWtrTempForm.getWhy().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_stop_wtr_temp.why.null", null, locales));
			return msg;
		}
		if (stopWtrTempForm.getStopDuration() == null || stopWtrTempForm.getStopDuration().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_stop_wtr_temp.stopDuration.null", null, locales));
			return msg;
		}
		if (stopWtrTempForm.getArea() == null || stopWtrTempForm.getArea().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_stop_wtr_temp.area.null", null, locales));
			return msg;
		}
		if (stopWtrTempForm.getTempRemark() == null || stopWtrTempForm.getTempRemark().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_stop_wtr_temp.tempRemark.null", null, locales));
			return msg;
		}
		stopWtrTempForm.setToken(getCusTokenInSession().getToken());
		// 保存操作
		if (cStopWtrTempService.save(stopWtrTempForm) != null) {
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
	@RequestMapping("/cStopWtrTemp_checkStopWtrTempId.do")
	public @ResponseBody ControllerJsonMessage checkStopWtrTempId(Model model, @RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (id != null && id.trim().length() > 0) {
			// 业务验证
			CStopWtrTempForm form = cStopWtrTempService.findById(id);
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
	@RequestMapping("/cStopWtrTemp_toUpdate.do")
	public String toUpdateTextStopWtrTemp(Model model, @RequestParam("id") String id,
			@RequestParam(value = "page", defaultValue = "1") String pageNo) {
		CStopWtrTempForm stopWtrTempForm = cStopWtrTempService.findById(id);
		if (stopWtrTempForm != null) {
			model.addAttribute("stopWtrTempForm", stopWtrTempForm);
		} else {
			return "redirect:/biz/cStopWtrTemp_list.do?pageNo=1";
		}
		// 验证数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		// 保存页码
		model.addAttribute("page", pageNo);
		return "cus/function/cStopWtrTemp_update";
	}

	/**
	 * 
	 * @param model
	 * @param roleForm
	 * @param result
	 * @param pageNo
	 * @return 描述：更新
	 */
	@RequestMapping("/cStopWtrTemp_update.do")
	public @ResponseBody ControllerJsonMessage updateStopWtrTemp(Model model, CStopWtrTempForm stopWtrTempForm,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		// 业务验证，修改的记录不存在
		CStopWtrTempForm checkedStopWtrTempForm = cStopWtrTempService.findById(stopWtrTempForm.getId());
		if (checkedStopWtrTempForm == null) {
			msg.setWarnMsg(getBundleMessage("update.not.exists", null, locales));
			return msg;
		}
		// form验证
		if (stopWtrTempForm.getFirst() == null || stopWtrTempForm.getFirst().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_stop_wtr_temp.first.null", null, locales));
			return msg;
		}
		if (stopWtrTempForm.getWhy() == null || stopWtrTempForm.getWhy().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_stop_wtr_temp.why.null", null, locales));
			return msg;
		}
		if (stopWtrTempForm.getStopDuration() == null || stopWtrTempForm.getStopDuration().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_stop_wtr_temp.stopDuration.null", null, locales));
			return msg;
		}
		if (stopWtrTempForm.getArea() == null || stopWtrTempForm.getArea().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_stop_wtr_temp.area.null", null, locales));
			return msg;
		}
		if (stopWtrTempForm.getTempRemark() == null || stopWtrTempForm.getTempRemark().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_stop_wtr_temp.tempRemark.null", null, locales));
			return msg;
		}
		// 保存操作
		if (cStopWtrTempService.update(stopWtrTempForm)) {
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
	@RequestMapping("/cStopWtrTemp_delete.do")
	public @ResponseBody ControllerJsonMessage deleteStopWtrTemp(Model model, @RequestParam("ids") String ids,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (cStopWtrTempService.deleteByIds(ids)) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("delete.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("delete.fail", null, locales));
		}
		return msg;
	}

	/**
	 * 
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return 描述：发送
	 */
	@RequestMapping("/cStopWtrTemp_send.do")
	public @ResponseBody ControllerJsonMessage sendStopWtrTemp(Model model, @RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (cStopWtrTempService.sendStopWtrTempById(id, getCusTokenInSession().getToken())) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("send.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("send.fail", null, locales));
		}
		return msg;
	}
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return 描述：发送给所有绑定用户
	 */
	@RequestMapping("/cStopWtrTemp_send_bind.do")
	public @ResponseBody ControllerJsonMessage sendStopWtrTempForBindUser(Model model, @RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (cStopWtrTempService.sendStopWtrTempByIdForBindUser(id, getCusTokenInSession().getToken())) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("send.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("send.fail", null, locales));
		}
		return msg;
	}

	/**
	 * 发送预览
	 * 
	 * @param model
	 * @param OpenID
	 * @param id
	 * @param locales
	 * @return XingXingLvCha 2015年9月6日 上午11:26:11
	 * @RequestParam(value = "OpenID") String OpenID,
	 */
	@RequestMapping("/cStopWtrTemp_preview.do")
	public @ResponseBody ControllerJsonMessage preview(Model model, 
			@RequestParam(value = "id") String id, @RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		//TODO 获取当前登录的用户
		BCustomerUserForm user = getCusSessionUser();
		if (cStopWtrTempService.previewStopWtrTempById(id, user,"",user.getBinOpendId(), getCusTokenInSession().getToken())) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("send.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("send.fail", null, locales));
		}
		return msg;
	}
}