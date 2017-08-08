package com.wisewater.cusConfig.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.wisewater.cusConfig.pojo.TimeSet;
import com.wisewater.cusConfig.service.CTextMaterialService;
import com.wisewater.cusConfig.service.SetTimeService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.wechatpublic.util.DateUtils;
import com.wisewater.wechatpublic.util.StringUtil;
import com.wisewater.weixin.service.InterfaceService;

@Controller
@RequestMapping("/cus")
public class CTextMaterialController extends BaseController {

	@Autowired
	private CTextMaterialService ctextmaterialService;

	@Autowired
	private SetTimeService setTimeService;
	
	@Autowired
	protected DozerBeanMapper mapper;
	
	@Autowired
	private InterfaceService interfaceService;
	
	@Autowired
	private LoadConstant loadConstant;

	/**
	 * 
	 * @param model
	 * @param pageNo
	 *            页码，默认查询第1页
	 * @return 描述：
	 */
	@RequestMapping("/cTextMtrl_list.do")
	public String findInPage(Model model, @RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		model.addAttribute("page", pageNo);

		return "cus/cusConfig/cTextMtrl_list";
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
	@RequestMapping("/cTextMtrl_list_json.do")
	public @ResponseBody JqgridListForm findCusTextMtrlsJson(Model model,
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

		JqgridListForm jqgridListForm = ctextmaterialService.findAll(pageNo, sidx, sord, tag, title,
				getCusTokenInSession().getToken());

		return jqgridListForm;
	}

	/**
	 * 
	 * @param model
	 * @return 描述：跳转新增页面
	 */
	@RequestMapping("/cTextMtrl_toAdd.do")
	public String toAddCusTextMtrl(Model model) {

		return "cus/cusConfig/cTextMtrl_add";
	}

	/**
	 * 
	 * @param model
	 * @param roleForm
	 * @param result
	 * @return 描述：新增操作
	 */
	@RequestMapping("/cTextMtrl_add.do")
	public @ResponseBody ControllerJsonMessage saveCusTextMtrl(Model model, CTextMaterialForm textMtrlForm,
			@RequestHeader("Accept-Language") String locales) {

		ControllerJsonMessage msg = new ControllerJsonMessage();

		// form验证
		if (textMtrlForm.getTitle() == null || textMtrlForm.getTitle().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_text_material.title.null", null, locales));
			return msg;
		}
		if (textMtrlForm.getTextContent() == null || textMtrlForm.getTextContent().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_text_material.textContent.null", null, locales));
			return msg;
		}

		textMtrlForm.setCreatedBy(getCusSessionUser().getUserName());
		textMtrlForm.setCreatedDateTime(DateUtils.getDateTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
		textMtrlForm.setToken(getCusTokenInSession().getToken());
		// 保存操作
		if (ctextmaterialService.save(textMtrlForm)) {
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
	@RequestMapping("/cTextMtrl_checkMtrlId.do")
	public @ResponseBody ControllerJsonMessage checkMtrlId(Model model, @RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales) {

		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (id != null && id.trim().length() > 0) {
			// 业务验证
			CTextMaterialForm form = ctextmaterialService.findById(id);
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
	@RequestMapping("/cTextMtrl_toUpdate.do")
	public String toUpdateTextMtrl(Model model, @RequestParam("id") String id,
			@RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {

		CTextMaterialForm mtrlForm = ctextmaterialService.findById(id);
		if (mtrlForm != null) {
			model.addAttribute("mtrlForm", mtrlForm);
		} else {
			return "redirect:/cus/cTextMtrl_list.do?pageNo=1";
		}

		// 验证数字
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}

		// 保存页码
		model.addAttribute("page", pageNo);

		return "cus/cusConfig/cTextMtrl_update";
	}

	/**
	 * 
	 * @param model
	 * @param roleForm
	 * @param result
	 * @param pageNo
	 * @return 描述：处理编辑请求
	 */
	@RequestMapping("/cTextMtrl_update.do")
	public @ResponseBody ControllerJsonMessage updateTextMtrl(Model model, CTextMaterialForm mtrlForm,
			@RequestHeader("Accept-Language") String locales) {

		ControllerJsonMessage msg = new ControllerJsonMessage();

		// 业务验证，修改的记录不存在
		CTextMaterialForm checkedMtrlForm = ctextmaterialService.findById(mtrlForm.getId());
		if (checkedMtrlForm == null) {
			msg.setWarnMsg(getBundleMessage("update.not.exists", null, locales));
			return msg;
		}

		// form验证
		if (mtrlForm.getTitle() == null || mtrlForm.getTitle().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_text_material.title.null", null, locales));
			return msg;
		}
		if (mtrlForm.getTextContent() == null || mtrlForm.getTextContent().trim().equals("")) {
			msg.setWarnMsg(getBundleMessage("c_text_material.textContent.null", null, locales));
			return msg;
		}

		mtrlForm.setCreatedBy(getCusSessionUser().getUserName());
		mtrlForm.setCreatedDateTime(DateUtils.getDateTime(new Date(), "yyyy-MM-dd HH:mm:ss"));

		mtrlForm.setToken(getCusTokenInSession().getToken());

		// 保存操作
		if (ctextmaterialService.update(mtrlForm)) {
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
	@RequestMapping("/cTextMtrl_delete.do")
	public @ResponseBody ControllerJsonMessage deleteTextMtrl(Model model, @RequestParam("ids") String ids,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (ctextmaterialService.deleteByIds(ids)) {
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("delete.success", null, locales));
		} else {
			msg.setWarnMsg(getBundleMessage("delete.fail", null, locales));
		}
		return msg;
	}

	@RequestMapping(value = "/cTextMtrl_getform.do", method = RequestMethod.GET)
	public @ResponseBody CTextMaterialForm inherit(@RequestParam("id") String id) {

		CTextMaterialForm form = ctextmaterialService.findById(id);
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
	@RequestMapping("/cTextMtrl_picklist_json.do")
	public @ResponseBody JqgridListForm findTextMtrlsJson(Model model,
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
		jqgridListForm = ctextmaterialService.findAll(pageNo, sidx, sord, tag, title,
				getCusTokenInSession().getToken());

		return jqgridListForm;
	}
	/**
	 * 
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return 描述：跳转至编辑页面
	 * @throws Exception 
	 */
	@RequestMapping("/setTime.do")
	public String setTime(Model model) throws Exception {
		TimeSet timeSet = new TimeSet();
		String method1 = "selectDay";
		String returnString1 = interfaceService.select_day(loadConstant.getTimeLimitServicePath(), method1);
		String method2 = "selectMonth";
		String returnString2 = interfaceService.select_month(loadConstant.getTimeLimitServicePath(), method2);
		
		JSONObject jsonObject1 = JSONObject.fromObject(returnString1);
		JSONObject jsonObject2 = JSONObject.fromObject(returnString2);
		timeSet.setFirstHour(jsonObject1.getString("StartTime"));
		timeSet.setSecondHour(jsonObject1.getString("EndTime"));
		timeSet.setHourSatus(jsonObject1.getString("Status"));
		JSONArray jsonArray = JSONArray.fromObject(jsonObject2.get("result").toString());
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i);
			if ("1".equals(object.getString("UserType"))) {
				timeSet.setFirstDay1(object.getString("StartTime").replace("/", "-"));
				timeSet.setSecondDay1(object.getString("EndTime").replace("/", "-"));
				timeSet.setDaySatus1(object.getString("Status"));
			}
			if ("2".equals(object.getString("UserType"))) {
				timeSet.setFirstDay2(object.getString("StartTime").replace("/", "-"));
				timeSet.setSecondDay2(object.getString("EndTime").replace("/", "-"));
				timeSet.setDaySatus2(object.getString("Status"));
			}
		}
		model.addAttribute("timeSet",timeSet);

		return "cus/payStatement/setTime";
	}
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return 描述：保存小时的限制
	 */
	@RequestMapping("/saveDay.do")
	public @ResponseBody ControllerJsonMessage setDate(Model model,String time1,String time2,String status)throws Exception {
        
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		String method = "setDay";
		String returnString = interfaceService.set_day(loadConstant.getTimeLimitServicePath(), method,time1,time2,status);
		JSONObject jsonObject = JSONObject.fromObject(returnString);
        String retcode = jsonObject.getString("retcode");
		String retmsg = jsonObject.getString("retmsg");
        msg.setTips(retcode);
        msg.setWarnMsg(retmsg);
		return msg;
	}
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @param pageNo
	 * @return 描述：保存居民缴费时间的限制
	 */
	@RequestMapping("/saveMonth.do")
	public @ResponseBody ControllerJsonMessage setMonth(Model model,String month1,String month2,String months1,String months2,String userType,String status)throws Exception {
        
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		String method = "setMonth";
		String returnString = "";
		if ("1".equals(userType)) {
			returnString = interfaceService.set_month(loadConstant.getTimeLimitServicePath(), method,"1",status,month1,month2,userType);
		}else if("2".equals(userType)) {
			returnString = interfaceService.set_month(loadConstant.getTimeLimitServicePath(), method,"2",status,months1,months2,userType);
		}
		JSONObject jsonObject = JSONObject.fromObject(returnString);
        String retcode = jsonObject.getString("retcode");
		String retmsg = jsonObject.getString("retmsg");
        msg.setTips(retcode);
        msg.setWarnMsg(retmsg);
		return msg;
	}
	
	
	
	
	/**
	 * 校验日期
	 * @param type
	 * @return
	 */
	public String verifyDate(String date,String type){
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		if(date == null || date.equals("")){
			date = sdf.format(new Date());
		}
		return date;
	}

}