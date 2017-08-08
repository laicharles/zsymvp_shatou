package com.wisewater.auto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.auto.pojo.CAutoDefault;
import com.wisewater.auto.service.CAutoDefaultService;
import com.wisewater.base.BaseController;
import com.wisewater.system.controller.SDictionaryForm;
import com.wisewater.system.service.SDictionaryService;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.util.tools.LoadConstant;

@Controller
public class CAutoDefaultController extends BaseController {

	@Autowired
	LoadConstant loadConstant;

	@Autowired
	private CAutoDefaultService cautoDefaultService;

	@Autowired
	private SDictionaryService sdictionaryService;

	/**
	 * 
	 * @param model
	 * @return AlexFung 2015年6月11日 下午2:47:49
	 */
	@RequestMapping(value = "/cus/cautoDefault_toUpdate.do")
	public String toUpdateAutoDefault(Model model) {
		CAutoDefaultForm cautoDefaultForm = cautoDefaultService
				.findCAutoDefaulByToken(getCusTokenInSession().getToken());
		String picName = "";
		if (cautoDefaultForm != null && !"".equals(cautoDefaultForm.getPageContent())) {
			cautoDefaultForm.setPageContent(cautoDefaultForm.getPageContent().replaceAll("&", "&amp;")
					.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
		}
		if (cautoDefaultForm != null) {
			picName = cautoDefaultForm.getPicName();
		}
		String picPath = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/" + picName;
		model.addAttribute("picPath", picPath);
		model.addAttribute("cautoDefault", cautoDefaultForm);
		List<SDictionaryForm> sDictionaryFormList = sdictionaryService.findByTypeCode("answerType");
		model.addAttribute("sDictionaryFormList", sDictionaryFormList);
		return "cus/auto/cautoDefault_update";
	}

	/**
	 * 
	 * @param model
	 * @param cautoDefaultForm
	 * @param locales
	 * @return AlexFung 2015年6月11日 下午2:45:02
	 */
	@RequestMapping(value = "/cus/cautoDefault_update.do")
	public @ResponseBody ControllerJsonMessage updateCAutoDefault(Model model, CAutoDefaultForm cautoDefaultForm,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		cautoDefaultForm.setToken(getCusTokenInSession().getToken());
		CAutoDefault cautoDefault = cautoDefaultService.updateCAutoDefaul(cautoDefaultForm,
				getCusTokenInSession().getToken());
		boolean isSuccess = false;
		if (cautoDefault != null) {
			isSuccess = true;
		}
		msg.setResult(isSuccess);
		if (isSuccess) {
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
		}
		model.addAttribute("cautoDefault", cautoDefault);
		String picPath = loadConstant.getPicPath() + getCusTokenInSession().getToken() + "/"
				+ cautoDefaultForm.getPicName();
		model.addAttribute("picPath", picPath);
		return msg;
	}

	/**
	 * 打开关注回复的图文文章
	 * 
	 * @param model
	 * @param token
	 * @return XingXingLvCha 2015年6月19日 上午10:20:41
	 */
	@RequestMapping(value = "/cautoDefault_article.do")
	public String openArticle(Model model, String token) {
		CAutoDefaultForm cautoDefaultForm = cautoDefaultService.findCAutoDefaulByToken(token);
		if (cautoDefaultForm != null && "answerType2".equals(cautoDefaultForm.getSdictionary().getLogicID())) {
			model.addAttribute("title", cautoDefaultForm.getTitle());
			model.addAttribute("author", cautoDefaultForm.getAuthor());
			model.addAttribute("content", cautoDefaultForm.getPageContent());
			model.addAttribute("origUrl", cautoDefaultForm.getOrigUrl());
			return "/m/article";
		} else {
			return "";
		}
	}

}