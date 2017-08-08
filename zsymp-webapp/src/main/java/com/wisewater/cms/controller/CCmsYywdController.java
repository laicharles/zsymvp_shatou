package com.wisewater.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.wisewater.base.BaseController;
import com.wisewater.cms.pojo.CCmsYywd;
import com.wisewater.cms.service.CCmsYywdService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.pojo.SFansOperateLog;
import com.wisewater.system.repository.SFansOperateLogRepository;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.util.tools.LoadConstant;

@Controller
public class CCmsYywdController extends BaseController {

	@Autowired
	private CCmsYywdService ccmsyywdService;

	@Autowired
	LoadConstant lc;
	
	@Autowired
	private SFansOperateLogRepository fansOperateLogRepository;
	
	
	/**
	 * 营业网点列表
	 * @param model
	 * @param pageNo
	 * @param searchField
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:49:51
	 */
	@RequestMapping(value = "/cus/ccmsYywd_list.do")
	public String findYywdByToken(Model model,
			@RequestParam("pageNo") String pageNo,String searchField) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		String pageSize = String.valueOf(lc.getPageSize());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", pageNo);
		model.addAttribute("searchField", searchField);
		return "cus/cms/ccmsYywd_list";
	}

	/**
	 * 营业网点列表
	 * @param page
	 * @param sidx
	 * @param sord
	 * @param searchField
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:49:57
	 */
	@RequestMapping(value = "/cus/yywdGrid.do", method = RequestMethod.GET)
	public @ResponseBody JqgridListForm findYywdJson(
			@RequestParam("page") String page,
			@RequestParam(value="sidx",required=false) String sidx,
			@RequestParam(value="sord",required=false)String sord,
			@RequestParam("searchField") String searchField) {
		int pageNo = 0;
		if (page != null)
			pageNo = Integer.parseInt(page) - 1;
		JqgridListForm jqgridListForm = ccmsyywdService.findYywdByToken(getCusTokenInSession().getToken(),
				pageNo + 1,sidx,sord,searchField);
		return jqgridListForm;
	}

	/**
	 * 新增营业网点
	 * @param model
	 * @param yywdForm
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:50:03
	 */
	@RequestMapping(value = "/cus/ccmsYywd_toAdd.do")
	public String toSaveYywd(Model model, CCmsYywdForm yywdForm) {

		return "cus/cms/ccmsYywd_add";
	}

	/**
	 * 新增营业网点
	 * @param model
	 * @param yywdForm
	 * @param locales
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:50:10
	 */
	@RequestMapping(value = "/cus/ccmsYywd_add.do")
	public @ResponseBody ControllerJsonMessage saveYywd(Model model,
			CCmsYywdForm yywdForm,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		yywdForm.setToken(getCusTokenInSession().getToken());
		boolean isSuccess = ccmsyywdService.saveYywd(yywdForm);
		msg.setResult(isSuccess);
		if(isSuccess){
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
		}else{
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
		}
		return msg;
	}

	/**
	 * 根据ID查找营业网点详细信息
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:50:17
	 */
	@RequestMapping(value = "/cus/ccmsYywd_toUpdate.do")
	public String toUpdateYywd(Model model, @RequestParam("id") String id,
			@RequestParam("pageNo") String pageNo) {
		CCmsYywdForm yywdForm = ccmsyywdService.findYywdById(id);
		model.addAttribute("yywdForm", yywdForm);
		// 验证数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		// 保存页码
		model.addAttribute("page", pageNo);
		return "cus/cms/ccmsYywd_update";
	}

	/**
	 * 更新营业网点
	 * @param model
	 * @param yywdForm
	 * @param locales
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:50:24
	 */
	@RequestMapping(value = "/cus/ccmsYywd_update.do")
	public @ResponseBody ControllerJsonMessage updateYywd(Model model,
			CCmsYywdForm yywdForm,@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		CCmsYywd yywd = ccmsyywdService.updateYywd(yywdForm);
		boolean isSuccess = false;
		if (yywd != null) {
			isSuccess = true;
		}
		msg.setResult(isSuccess);
		if(isSuccess){
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));
		}else{
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
		}
		model.addAttribute("yywdForm", yywd);
		return msg;
	}

	/**
	 * 批量删除营业网点(逻辑删除)
	 * @param idStr
	 * @param pageNo
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:50:31
	 */
	@RequestMapping(value = "/cus/ccmsYywd_delete.do")
	public @ResponseBody List<String> deleteYywdBatch(
			@RequestParam("idStr") String idStr,
			@RequestParam("pageNo") String pageNo) {
		String notice = ccmsyywdService.deleteYywdbatch(idStr);
		if ("".equals(notice)) {
			notice = "success";
		}
		List<String> resultList = new ArrayList<String>();
		resultList.add(notice);
		resultList.add(pageNo);
		return resultList;
	}
	/**
	 * 微信端页面-营业网点列表
	 * 
	 * @param model
	 * @param pageNo
	 * @return AlexFung 2015年4月8日 上午9:55:35
	 */
	@RequestMapping(value = "/m/ccmsYywd_wx_list.do")
	public String findYywdByTokenWx(Model model,
			@RequestParam("token") String token) {
		List<CCmsYywd> list = ccmsyywdService.findYywdByToken(token);
		if(list!=null&&!list.isEmpty()&&list.size()==1){
			return "redirect:/m/ccmsYywd_wx_view.do?id="+list.get(0).getId();
		}
		model.addAttribute("pageNo", 0);
		model.addAttribute("token", token);
		fansOperateLogRepository.save(new SFansOperateLog("用户访问营业网点列表",  "点击菜单", "", new Date()));
		return "m/cms/ccmsYywd_wx_list";
	}

	/**
	 * 微信端页面-营业网点列表
	 * 
	 * @param page
	 * @param sidx
	 * @param sord
	 * @return AlexFung 2015年3月31日 下午7:56:48
	 */
	@RequestMapping(value = "/m/yywdGrid_wx.do", method = RequestMethod.GET)
	public @ResponseBody JqgridListForm findYywdJsonWx(
			@RequestParam("page") String page,
			@RequestParam("token") String token) {
		int pageNo = 0;
		if (page != null)
			pageNo = Integer.parseInt(page) - 1;
		JqgridListForm jqgridListForm = ccmsyywdService.findYywdByToken(token,
				pageNo + 1, null, null,"");
		return jqgridListForm;
	}

	/**
	 * 微信端页面-根据ID查找营业网点详细信息
	 * 
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return AlexFung 2015年4月8日 上午11:53:08
	 */
	@RequestMapping(value = "/m/ccmsYywd_wx_view.do")
	public String toUpdateYywdWx(Model model, @RequestParam("id") String id) {
		CCmsYywdForm yywdForm = ccmsyywdService.findYywdById(id);
		model.addAttribute("yywdForm", yywdForm);
		fansOperateLogRepository.save(new SFansOperateLog("用户访问营业网点页面",  "点击菜单", "", new Date()));
		return "m/cms/ccmsYywd_wx_view";
	}
	
}