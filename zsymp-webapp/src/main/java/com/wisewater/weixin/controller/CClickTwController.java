package com.wisewater.weixin.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.service.SDictionaryService;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.weixin.service.CClickTwService;

@Controller

public class CClickTwController extends BaseController {

	@Autowired
	private CClickTwService clickTwService;
	
	@Autowired
	private SDictionaryService dictionaryService;

	/**
	 * 
	 * @param model
	 * @return gawen.chen 2015年4月20日下午7:23:09 描述：
	 */
	@RequestMapping("/cus/{twType}/clickTw_toUpdate.do")
	public String findByToken(Model model, @PathVariable String twType) {
		CClickTwForm clickTwForm = clickTwService.findByToken(getCusTokenInSession().getToken(), twType);
		if (clickTwForm != null) {
			String picPath = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/"
								+ clickTwForm.getPicName();
			if("banding.jpg".equals(clickTwForm.getPicName()) || "waterbill.jpg".equals(clickTwForm.getPicName()) || 
			   "watercost.jpg".equals(clickTwForm.getPicName())){
				picPath = loadConstant.getPicPath()+"management/"+ clickTwForm.getPicName();
			}
			model.addAttribute("picPath", picPath);
		} else {
			clickTwForm = new CClickTwForm();
			clickTwForm.setToken(getCusTokenInSession().getToken());
		}
		SDictionary sDictionary = dictionaryService.findByLogicID(twType);
		String dicName = sDictionary.getDicName();
		model.addAttribute("dicName", dicName);
		model.addAttribute("clickTwForm", clickTwForm);
		model.addAttribute("twType", twType);
		return "cus/clickTw/cclicktw_update";
	}

	/**
	 * 
	 * @param model
	 * @param clickTwForm
	 * @param locales
	 * @return gawen.chen 2015年4月20日下午7:23:17 描述：
	 */
	@RequestMapping("/cus/clickTw_update.do")
	public @ResponseBody ControllerJsonMessage saveCmsBill(Model model, CClickTwForm clickTwForm,
			@RequestHeader("Accept-Language") String locales) {

		ControllerJsonMessage msg = new ControllerJsonMessage();

		// 非空验证
		if (StringUtils.isEmpty(clickTwForm.getTitle())) {
			msg.setWarnMsg(getBundleMessage("c_user_article.title.null", null, locales));
			return msg;
		}
		if (StringUtils.isEmpty(clickTwForm.getDescription())) {
			msg.setWarnMsg(getBundleMessage("c_user_article.description.null", null, locales));
			return msg;
		}
		if (StringUtils.isEmpty(clickTwForm.getPicName())) {
			msg.setWarnMsg(getBundleMessage("c_user_article.picName.null", null, locales));
			return msg;
		}
		clickTwForm.setTwType(dictionaryService.findByLogicID(clickTwForm.getTwType().getLogicID()));
		if (clickTwService.saveClickTw(clickTwForm, clickTwForm.getImgFile(), getCusTokenInSession().getToken())) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
			clickTwForm = clickTwService.findByToken(getCusTokenInSession().getToken(),
					clickTwForm.getTwType().getLogicID());
			msg.setTips(clickTwForm.getId());
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
		}

		return msg;
	}

}