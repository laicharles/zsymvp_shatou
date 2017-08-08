package com.wisewater.cusConfig.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.cusConfig.pojo.CMenuConfigure;
import com.wisewater.cusConfig.service.CMenuConfigureService;
import com.wisewater.cusConfig.service.CMpService;
import com.wisewater.util.tools.ControllerJsonMessage;

@Controller
public class CMenuConfigureController extends BaseController {

	@Autowired
	private CMenuConfigureService cmenuconfigureService;

	@Autowired
	private CMpService cmpService;

	/**
	 * 获取客户所有菜单
	 * 
	 * @param model
	 * @return XingXingLvCha 2015年4月8日 下午3:48:38
	 */
	@RequestMapping(value = { "/cus/menuConfigure_list.do" }, method = RequestMethod.GET)
	public String menuConfigure_list(Model model) {
		List<CMenuConfigureForm> cMenuConfigureForm = cmenuconfigureService
				.findAllCMenu(getCusTokenInSession().getToken());
		model.addAttribute("cMenuConfigureForm", cMenuConfigureForm);
		return "cus/cusConfig/menuConfigure_list";
	}

	/**
	 * 保存并生成菜单
	 * 
	 * @param model
	 * @param cMenuConfigureFormList
	 * @return XingXingLvCha 2015年4月8日 下午3:48:15
	 */
	@RequestMapping(value = { "/cus/menuConfigure_update.do" }, method = RequestMethod.POST)
	@ResponseBody
	public ControllerJsonMessage menuConfigure_update(Model model, CMenuConfigureFormList cMenuConfigureFormList) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		List<CMenuConfigureForm> cMenuConfigureList = cMenuConfigureFormList.getcMenuConfigureFormList();
		if (cMenuConfigureList == null || cMenuConfigureList.size() <= 0) {
			msg.setResult(false);
			msg.setWarnMsg("菜单保存失败！");
			return msg;
		}
		for (CMenuConfigureForm cMenuConfigureForm : cMenuConfigureList) {
			cmenuconfigureService.updateMenu(cMenuConfigureForm);
		}
		CMpForm cmpForm = cmpService.findByToken(getCusTokenInSession().getToken());
		if (cmpForm.getAppID() == null || "".equals(cmpForm.getAppID()) || cmpForm.getAppSecret() == null
				|| "".equals(cmpForm.getAppSecret())) {
			msg.setResult(false);
			msg.setWarnMsg("菜单保存成功，但生成失败，请配置公众号信息！");
			return msg;
		}

		boolean result = cmenuconfigureService.createWXMenu(getCusTokenInSession().getToken());
		if (result) {
			msg.setResult(true);
			msg.setWarnMsg("菜单保存并生成，24小时内生效！");
		} else {
			msg.setResult(false);
			msg.setWarnMsg("菜单保存成功，但生成失败！");
		}

		return msg;
	}
	
	/********************************************微信端*******************************************************/
	
	/**
	 * 微信端页面  微官网
	 * @param id 编号
	 * @return 
	 */
	@RequestMapping(value="/m/ccms_wgw.do")
	public String findCmsListByMenuLable(@RequestParam("menuLable") String menuLable,@RequestParam("token") String token,Model model){
		
		Map<String, List<CMenuConfigure>> configMap = cmenuconfigureService.findAllChildrenMenu(token,menuLable);
		model.addAttribute("configMap", configMap);
		
		return "m/cms/ccms_wgw";
	}
}