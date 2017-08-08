package com.wisewater.fans.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.fans.service.CFanUserService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.pojo.SFansOperateLog;
import com.wisewater.system.repository.SFansOperateLogRepository;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.wechatpublic.util.StringUtil;
import com.wisewater.weixin.pojo.DetailedBindUserForm;
import com.wisewater.weixin.service.InterfaceService;

@Controller
public class CFanUserController extends BaseController {

	@Autowired
	private CFanUserService cfanuserService;
	
	@Autowired
	private SFansOperateLogRepository fansOperateLogRepository;
	
	@Autowired
	private InterfaceService InterfaceService;
	
	@Autowired
	private LoadConstant loadConstant;
	
	/**
	 * 
	 * @param model
	 * @param pageNo
	 *            页码，默认查询第1页
	 * @return 描述：
	 */
	@RequestMapping("/cus/cFanUser_list.do")
	public String findInPage(Model model, @RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}
		model.addAttribute("page", pageNo);

		return "cus/fans/cFanUser_list";
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
	@RequestMapping("/cus/cFanUser_list_json.do")
	public @ResponseBody JqgridListForm findFanUsersJson(Model model,
			@RequestParam(value = "userAccount", required = false) String userAccount,
			@RequestParam("page") String page, @RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		int pageNo = 1;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(page);
		if (isNum.matches()) {
			pageNo = Integer.parseInt(page);
		}
		if (StringUtils.isEmpty(userAccount)) {
			userAccount = "";
		}
		userAccount = StringUtil.unescape(userAccount);
		JqgridListForm jqgridListForm = cfanuserService.findAll(pageNo, sidx, sord, userAccount,
				getCusTokenInSession().getToken());
		return jqgridListForm;
	}
	
	
	/**
	 * 批量删除绑定用户(逻辑删除)
	 * @param idStr
	 * @param pageNo
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:50:31
	 */
	@RequestMapping(value = "/cus/fanUser_delBatch.do")
	@ResponseBody
	public ControllerJsonMessage deleteFanUserBatch(Model model, String idStr, String pageNo) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		boolean result = cfanuserService.deleteFanUserbatch(idStr);
		if (result) {
			msg.setResult(true);
		}
		return msg;
	}
	

	/*************************************************** 以下为手机端 ***************************************************************/

	/**
	 * 
	 * 描述：绑定列表
	 * 
	 * @author AlexFung 2016年8月18日 上午11:46:40
	 * @param model
	 * @param openID
	 * @param token
	 * @return
	 */
	@RequestMapping("/m/cfanuser_bindList.do")
	public String getBindList(Model model, @RequestParam(value = "openID") String openID,
			@RequestParam("token") String token) {
		List<CFanUserForm> fanUserFormList = cfanuserService.findByOpenIDAndToken(openID, token);
		// add by alex at 20151130 for hide some message
		List<CFanUserForm> fanUserFormList1 = new ArrayList<CFanUserForm>();
		if (fanUserFormList != null && fanUserFormList.size() > 0) {
			for (CFanUserForm f : fanUserFormList) {
				f.setUserName(StringUtil.hideUserName(f.getUserName()));
				f.setMobile(StringUtil.hideMobile(f.getMobile()));
				fanUserFormList1.add(f);
			}
		}
		model.addAttribute("openID", openID);
		model.addAttribute("token", token);
		model.addAttribute("fanUserFormList", fanUserFormList1);
		fansOperateLogRepository.save(new SFansOperateLog("用户访问访问绑定户号列表",  "点击图文", "", new Date()));
		return "m/fans/cfanuser_bindList";
	}

	/**
	 * 
	 * 描述：打开绑定页面
	 * 
	 * @author AlexFung 2016年8月18日 上午11:47:21
	 * @param model
	 * @param openID
	 * @param token
	 * @return
	 */
	@RequestMapping("/m/cfanuser_toAdd.do")
	public String toAdd(Model model, @RequestParam(value = "openID") String openID,
			@RequestParam("token") String token) {
		model.addAttribute("openID", openID);
		model.addAttribute("token", token);
		fansOperateLogRepository.save(new SFansOperateLog("用户访问绑定户号页面",  "绑定户号", "", new Date()));
		return "m/fans/cfanuser_bind";
	}

	/**
	 * 
	 * 描述：校验绑定信息
	 * 
	 * @author AlexFung 2016年8月18日 上午11:47:39
	 * @param fanUserForm
	 * @param locales
	 * @return
	 */
	@RequestMapping("/m/cfanuser_checkBindUser.do")
	public @ResponseBody CFanUserForm checkBindUser(CFanUserForm fanUserForm,
			@RequestHeader("Accept-Language") String locales) {
		// 限制绑定户数
		if (cfanuserService.countBindUserByOpenID(fanUserForm.getFan().getOpenID()) >= loadConstant.getBindCount()) {
			fanUserForm.setStatus("OVER");
			return fanUserForm;
		}
		// 处理重复绑定
		if (cfanuserService.countBindUserByOpenIDNUserAccount(fanUserForm.getFan().getOpenID(),
				fanUserForm.getUserAccount()) > 0) {
			fanUserForm.setStatus("REPEAT");
			return fanUserForm;
		}
		
		//TODO 校验用户
		try {
			
			String method = "findData";
			
			int bindCode = InterfaceService.bindUser(loadConstant.getBindUserServicePath(), method, fanUserForm);
			if(bindCode == 0 ){
				fanUserForm.setStatus("FAIL");
				return fanUserForm;
			}else if(bindCode == 1){
				fanUserForm.setStatus("SUCCESS");
				return fanUserForm;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		fanUserForm.setStatus("FAIL");
		return fanUserForm;
	}

	/**
	 * 
	 * 描述：执行绑定
	 * 
	 * @author AlexFung 2016年8月18日 上午11:48:07
	 * @param model
	 * @param fanUserForm
	 * @param locales
	 * @return
	 */
	@RequestMapping(value = "/m/cfanuser_saveBindUser.do", method = RequestMethod.POST)
	public @ResponseBody ControllerJsonMessage saveBindUser(Model model, String userAccount, String userName,
			String contactAddr, String mobile, String token, String openID, String remarks,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (cfanuserService.saveBindCfanUser(userAccount, userName, contactAddr, mobile, token, openID, remarks)) {
			msg.setResult(true);
		}
		fansOperateLogRepository.save(new SFansOperateLog("用户进行绑定户号",  "绑定户号", "", new Date()));
		return msg;
	}

	/**
	 * 
	 * 描述：查看绑定信息
	 * 
	 * @author AlexFung 2016年8月18日 上午11:49:50
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/m/cfanuser_viewBindUser.do")
	public String viewBindUser(Model model, String id) {
		CFanUserForm fanUserForm = cfanuserService.findById(id);
		//fanUserForm.setUserName(StringUtil.hideUserName(fanUserForm.getUserName()));
		//fanUserForm.setContactAddr(StringUtil.hideString(fanUserForm.getContactAddr()));
		fanUserForm.setMobile(StringUtil.hideMobile(fanUserForm.getMobile()));
		model.addAttribute("fanUserForm", fanUserForm);
		fansOperateLogRepository.save(new SFansOperateLog("用户查看绑定信息",  "绑定户号", "", new Date()));
		return "m/fans/cfanuser_view";
	}

	/**
	 * 
	 * 描述：删除绑定
	 * 
	 * @author AlexFung 2016年8月18日 下午1:54:07
	 * @param model
	 * @param id
	 * @param locales
	 * @return
	 */
	@RequestMapping("/m/cfanuser_del.do")
	public @ResponseBody ControllerJsonMessage deleteBindUser(Model model, @RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if (cfanuserService.deleteById(id)) {
			msg.setResult(true);
		}
		
		fansOperateLogRepository.save(new SFansOperateLog("用户取消绑定户号",  "绑定户号", "", new Date()));
		
		return msg;
	}
	
	/**
	 * 用户修改备注，重新绑定
	 * @param model
	 * @param fanUserForm
	 * @param locales
	 * @return
	 */
	@RequestMapping("/m/cfanuser_save.do")
	public @ResponseBody ControllerJsonMessage saveBindUser(Model model,CFanUserForm fanUserForm,
			@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		if(cfanuserService.reSaveBindCfanUser(fanUserForm)){
			msg.setResult(true);
		}
		fansOperateLogRepository.save(new SFansOperateLog("用户保存绑定信息",  "保存信息", "", new Date()));
		
		return msg;
	}
	
	/**
	 * 查询户号的详细信息
	 * @param model
	 * @param openId  微信编号
	 * @param userAccount  户号
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/m/cfanuser_detailed.do")
	public String detailed(Model model,String userAccount){
		//ControllerJsonMessage msg = new ControllerJsonMessage();
		
		String method = "detailedDate";
		String detailed = InterfaceService.dateiled(loadConstant.getBindUserServicePath(), method, userAccount);
		
		DetailedBindUserForm dateiledBindUser = new DetailedBindUserForm();
		if(!detailed.equals("") && detailed != null){
			List<DetailedBindUserForm> list = 
					(List) JSONArray.toCollection(JSONArray.fromObject(detailed), DetailedBindUserForm.class);
			dateiledBindUser = list.get(0);
		}
		
		model.addAttribute("dateiledBindUser", dateiledBindUser);
		fansOperateLogRepository.save(new SFansOperateLog("用户查看详细信息",  "详细信息", "", new Date()));
		return "m/fans/cfanuser_detailed";
	}
}