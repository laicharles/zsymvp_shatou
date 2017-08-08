package com.wisewater.function.controller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.service.CMassSendService;
import com.wisewater.login.controller.LoginController;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.wechatpublic.util.DateUtils;
import com.wisewater.wechatpublic.util.MassSendUtil;
import com.wisewater.wechatpublic.util.StringUtil;

@Controller
@RequestMapping("/cus")
public class CMassSendController extends BaseController {

	@Autowired
	private CMassSendService cmasssendService;

	Logger logger = Logger.getLogger(LoginController.class);

	/**
	 * 
	 * 描述：群发记录列表
	 * @author AlexFung
	 * 2016年7月26日 下午2:59:20
	 * @param model
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/cMassSend_list.do")
	public String findInPage(Model model, @RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		model.addAttribute("page", pageNo);
		logger.info("用户" + getCusSessionUser().getLoginName() + "点击'群发消息'菜单！");
		return "cus/function/cMassSend_list";
	}

	/**
	 * 
	 * 描述：群发记录列表数据
	 * @author AlexFung
	 * 2016年7月26日 下午2:59:36
	 * @param model
	 * @param title
	 * @param page
	 * @param sidx
	 * @param sord
	 * @return
	 */
	@RequestMapping("/cMassSend_list_json.do")
	public @ResponseBody JqgridListForm findCusMassSendsJson(Model model,
			@RequestParam(value = "title", required = false) String title, @RequestParam("page") String page,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		int pageNo = 1;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(page);
		if (isNum.matches()) {
			pageNo = Integer.parseInt(page);
		}
		if (StringUtils.isEmpty(title)) {
			title = "";
		}
		title = StringUtil.unescape(title);
		JqgridListForm jqgridListForm = cmasssendService.findAll(pageNo, sidx, sord, title,
				getCusTokenInSession().getToken());
		return jqgridListForm;
	}

	/**
	 * 
	 * 描述：跳转新增页面
	 * @author AlexFung
	 * 2016年7月26日 下午2:59:54
	 * @param model
	 * @param materialType
	 * @return
	 */
	@RequestMapping("/cMassSend_toAdd.do")
	public String toAddCusMassSend(Model model, @RequestParam("materialType") String materialType) {
		String url = "cus/function/cMassSend_add";
		if (MassSendUtil.mass_send_type_mpnews.equalsIgnoreCase(materialType)) {
			logger.info("用户" + getCusSessionUser().getLoginName() + "点击'群发消息'菜单-->'新增图文群发'按钮！");
			model.addAttribute("materialType", materialType);
			model.addAttribute("materialTypeName", "图文");
			url += "_" + MassSendUtil.mass_send_type_mpnews;
		} else if (MassSendUtil.mass_send_type_image.equalsIgnoreCase(materialType)) {
			logger.info("用户" + getCusSessionUser().getLoginName() + "点击'群发消息'菜单-->'新增图片群发'按钮！");
			model.addAttribute("materialType", materialType);
			model.addAttribute("materialTypeName", "图片");
			url += "_" + MassSendUtil.mass_send_type_image;
		} else if (MassSendUtil.mass_send_type_text.equalsIgnoreCase(materialType)) {
			logger.info("用户" + getCusSessionUser().getLoginName() + "点击'群发消息'菜单-->'新增文本群发'按钮！");
			model.addAttribute("materialType", materialType);
			model.addAttribute("materialTypeName", "文本");
			url += "_" + MassSendUtil.mass_send_type_text;
		}
		// 素材库
		String cusPicPathPickList = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/";
		model.addAttribute("cusPicPathPickList", cusPicPathPickList);
		return url;
	}

	/**
	 * 
	 * 描述：新增操作
	 * @author AlexFung
	 * 2016年7月26日 下午3:00:38
	 * @param model
	 * @param massSendForm
	 * @param locales
	 * @return
	 */
	@RequestMapping("/cMassSend_add.do")
	public @ResponseBody ControllerJsonMessage saveCusMassSend(Model model, CMassSendForm massSendForm,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		// form验证
		if (massSendForm.getTitle() == null || massSendForm.getTitle().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_mass_send.title.null", null, locales));
			return msg;
		}
		massSendForm.setCreatedBy(getCusSessionUser().getUserName());
		massSendForm.setCreatedDatetime(DateUtils.getDateTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
		massSendForm.setToken(getCusTokenInSession().getToken());
		// 保存操作
		if (cmasssendService.saveSendMass(massSendForm)) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
			logger.info("用户" + getCusSessionUser().getLoginName() + "点击'群发消息'菜单-->保存'新增群发'成功！");
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
			logger.info("用户" + getCusSessionUser().getLoginName() + "点击'群发消息'菜单-->保存'新增群发'失败！");
		}
		return msg;
	}

	/**
	 * 
	 * 描述：群发预览
	 * @author AlexFung
	 * 2016年7月26日 下午3:00:51
	 * @param model
	 * @param OpenID
	 * @param id
	 * @param locales
	 * @return
	 */
	@RequestMapping("/cMassSend_preview.do")
	public @ResponseBody ControllerJsonMessage preview(Model model, @RequestParam(value = "OpenID") String OpenID,
			@RequestParam(value = "id") String id, @RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		String msgId = cmasssendService.previewSendMass(id, getCusTokenInSession().getToken(), OpenID);
		if (msgId != null && !msgId.equalsIgnoreCase("")) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("send.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("send.fail", null, locales));
		}
		return msg;

	}

	/**
	 * 
	 * 描述：删除群发记录
	 * @author AlexFung
	 * 2016年7月26日 下午3:01:03
	 * @param model
	 * @param ids
	 * @param locales
	 * @return
	 */
	@RequestMapping("/cMassSend_delete.do")
	public @ResponseBody ControllerJsonMessage deleteMassSend(Model model, @RequestParam("ids") String ids,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (cmasssendService.deleteByIds(ids)) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("delete.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("delete.fail", null, locales));
		}
		return msg;
	}

	/**
	 * 
	 * 描述：群发
	 * @author AlexFung
	 * 2016年7月26日 下午3:01:16
	 * @param model
	 * @param id
	 * @param locales
	 * @return
	 */
	@RequestMapping("/cMassSend_send.do")
	public @ResponseBody ControllerJsonMessage sendMassSend(Model model, @RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		String msgId = cmasssendService.sendMass(id, getCusTokenInSession().getToken());
		if (msgId != null && !msgId.equalsIgnoreCase("")) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("send.success", null, locales));
			cmasssendService.updateSendMassStatus(id, msgId, "0");// 0-"已发送"
		} else {
			msg.setWarnMsg(getBundleMessage("send.fail", null, locales));
			cmasssendService.updateSendMassStatus(id, "", "2");// 2-"发送失败"
		}
		return msg;
	}

}