package com.wisewater.auto.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wisewater.auto.pojo.CAutoAr;
import com.wisewater.auto.pojo.CAutoImgTx;
import com.wisewater.auto.service.CAutoImgTxService;
import com.wisewater.base.BaseController;
import com.wisewater.system.pojo.SFansOperateLog;
import com.wisewater.system.repository.SFansOperateLogRepository;

/**
 * 
 * 描述：自动回复打开图文内容
 * @author AlexFung
 * 2016年8月17日 下午4:29:59
 */
@Controller
public class AnswerController extends BaseController {

	@Autowired
	private CAutoImgTxService cautoImgTxService;
	
	@Autowired
	private SFansOperateLogRepository fansOperateLogRepository;

	@RequestMapping(value = { "/article/{imgTxType}" }, method = RequestMethod.GET)
	public String openArticle(String ID, Model model, @PathVariable String imgTxType) {
		if ("auto".equals(imgTxType)) {
			CAutoImgTx cautoImgTx = cautoImgTxService.findCAutoImgTxById(ID);
			CAutoAr cautoAr = cautoImgTx.getCAutoAr();
			if (cautoAr != null) {
				model.addAttribute("title", cautoImgTx.getTitle());
				model.addAttribute("author", cautoAr.getAuthor());
				model.addAttribute("content", cautoAr.getPageContent());
				model.addAttribute("origUrl", cautoAr.getOrigUrl());
			}
		}
		
		fansOperateLogRepository.save(new SFansOperateLog("用户访问自动回复图文页面",  "点击图文", "", new Date()));
		return "/m/article";
	}
}
