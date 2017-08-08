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
import com.wisewater.function.service.CReminderTempService;
import com.wisewater.review.pojo.SReviewPermissions;
import com.wisewater.review.service.SReviewPermissionsService;
import com.wisewater.util.tools.ControllerJsonMessage;

@Controller
@RequestMapping("/cus")
public class CReminderTempController extends BaseController{
	
	@Autowired
	private CReminderTempService cReminderTempService;
	
	@Autowired
	private SReviewPermissionsService sReviewPermissionsService;
	
	
	@RequestMapping("/reminderTemp_list.do")
	public String findInPage(Model model,@RequestParam(value="pageNo",defaultValue = "1") String pageNo){
		
		Pattern pattern = Pattern.compile("[0-9]*");
	    Matcher isNum = pattern.matcher(pageNo);
	    if (!isNum.matches()) {
	        pageNo = "1";
	    }
	    
	    BCustomerUserForm user = getCusSessionUser();
	    SReviewPermissions reviewPermissions = sReviewPermissionsService.findReviewById(user.getCurrentPermissions());
	    
	    model.addAttribute("page", pageNo);
		model.addAttribute("level", reviewPermissions.getLevel());
	    return "cus/function/reminderTemp_list";
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
	@RequestMapping("/reminderTemp_list_json.do")
	public @ResponseBody JqgridListForm findReminderTempsJson(Model model, @RequestParam("page") String page,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		int pageNo = 1;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(page);
		if (isNum.matches()) {
			pageNo = Integer.parseInt(page);
		}
		JqgridListForm jqgridListForm = cReminderTempService.findAll(pageNo, sidx, sord,
				getCusTokenInSession().getToken());
		return jqgridListForm;
	}
	
	
	
	
	@RequestMapping("/reminderTemp_toAdd.do")
	public String toAddReminderTemp(Model model){
		return "cus/function/reminderTemp_add";
	}
	
	
	/**
	 * 
	 * @param model
	 * @param roleForm
	 * @param result
	 * @return 描述：新增操作
	 */
	@RequestMapping("/reminderTemp_add.do")
	public @ResponseBody ControllerJsonMessage saveReminderTemp(Model model, CReminderTempForm reminderTempForm,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		// form验证
		if (reminderTempForm.getFirst() == null || reminderTempForm.getFirst().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_reminder_temp.first.null", null, locales));
			return msg;
		}
		
	
		if (reminderTempForm.getTempRemark() == null || reminderTempForm.getTempRemark().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_reminder_temp.tempRemark.null", null, locales));
			return msg;
		}
		reminderTempForm.setToken(getCusTokenInSession().getToken());
		// 保存操作
		if (cReminderTempService.save(reminderTempForm) != null) {
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
	@RequestMapping("/reminderTemp_checkReminderTempId.do")
	public @ResponseBody ControllerJsonMessage checkReminderTempId(Model model, @RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (id != null && id.trim().length() > 0) {
			// 业务验证
			CReminderTempForm form = cReminderTempService.findById(id);
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
	@RequestMapping("/reminderTemp_toUpdate.do")
	public String toUpdateTextReminderTemp(Model model, @RequestParam("id") String id,
			@RequestParam(value = "page", defaultValue = "1") String pageNo) {
		CReminderTempForm reminderTempForm = cReminderTempService.findById(id);
		if (reminderTempForm != null) {
			model.addAttribute("reminderTempForm", reminderTempForm);
		} else {
			return "redirect:/biz/reminderTemp_list.do?pageNo=1";
		}
		// 验证数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		// 保存页码
		model.addAttribute("page", pageNo);
		return "cus/function/reminderTemp_update";
	}

	/**
	 * 
	 * @param model
	 * @param roleForm
	 * @param result
	 * @param pageNo
	 * @return 描述：更新
	 */
	@RequestMapping("/reminderTemp_update.do")
	public @ResponseBody ControllerJsonMessage updateReminderTemp(Model model, CReminderTempForm reminderTempForm,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		// 业务验证，修改的记录不存在
		CReminderTempForm checkReminderTempForm = cReminderTempService.findById(reminderTempForm.getId());
		if (checkReminderTempForm == null) {
			msg.setWarnMsg(getBundleMessage("update.not.exists", null, locales));
			return msg;
		}
		// form验证
		if (reminderTempForm.getFirst() == null || reminderTempForm.getFirst().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_reminder_temp.first.null", null, locales));
			return msg;
		}
	
		if (reminderTempForm.getTempRemark() == null || reminderTempForm.getTempRemark().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_reminder_temp.tempRemark.null", null, locales));
			return msg;
		}
		// 保存操作
		if (cReminderTempService.update(reminderTempForm)) {
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
	@RequestMapping("/reminderTemp_delete.do")
	public @ResponseBody ControllerJsonMessage deleteReminderTemp(Model model, @RequestParam("ids") String ids,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (cReminderTempService.deleteByIds(ids)) {
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
	@RequestMapping("/reminderTemp_send.do")
	public @ResponseBody ControllerJsonMessage sendReminderTemp(Model model, @RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (cReminderTempService.sendReminderTempById(id, getCusTokenInSession().getToken())) {
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
	@RequestMapping("/reminderTemp_send_bind.do")
	public @ResponseBody ControllerJsonMessage sendReminderTempForBindUser(Model model, @RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (cReminderTempService.sendReminderTempByIdForBindUser(id, getCusTokenInSession().getToken())) {
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
	 */
	@RequestMapping("/reminderTemp_preview.do")
	public @ResponseBody ControllerJsonMessage preview(Model model,
			@RequestParam(value = "id") String id, @RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		BCustomerUserForm user = getCusSessionUser();
		if (cReminderTempService.previewReminderTempById(id, user,"",user.getBinOpendId(), getCusTokenInSession().getToken())) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("send.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("send.fail", null, locales));
		}
		return msg;
	}
	
}
