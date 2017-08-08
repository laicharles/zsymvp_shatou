package com.wisewater.feedback.controller;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wisewater.base.BaseController;
import com.wisewater.bizConfig.pojo.BMenuConfigure;
import com.wisewater.bizConfig.service.BMenuConfigureService;
import com.wisewater.fans.controller.CFanUserForm;
import com.wisewater.fans.pojo.CFanUser;
import com.wisewater.fans.service.CFanUserService;
import com.wisewater.feedback.pojo.CFeedBackManage;
import com.wisewater.feedback.service.CFeedBackManageService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.service.CFeedBackTempService;
import com.wisewater.function.service.CReplyFeedBackTempService;
import com.wisewater.function.service.CReplyLeaveWordsTempService;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.service.SDictionaryService;
import com.wisewater.util.ImageBase64Utils;
import com.wisewater.util.LOCALMAC;
import com.wisewater.util.MyUtilTool.DATE;
import com.wisewater.util.MyUtilTool.DATE_PARAM;
import com.wisewater.util.service.ImageService;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.util.tools.SecurityMD5;
import com.wisewater.util.tools.WxJsUtil;
import com.wisewater.wechatpublic.util.CommonUtil;
import com.wisewater.weixin.pojo.WxJsBean;
import com.wisewater.weixin.service.InterfaceService;
import com.wisewater.weixin.service.WxJsService;

@Controller
public class CFeedBackManageController extends BaseController {
	private static Logger log = LoggerFactory
			.getLogger(CFeedBackManageController.class);
	@Autowired
	private SDictionaryService dictionaryService;

	@Autowired
	private CFeedBackManageService cFeedBackManageService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private CFanUserService cFanUserService;

	@Autowired
	private SDictionaryService sDictionaryService;

	@Autowired
	private WxJsService wxJsService;

	@Autowired
	private CFeedBackTempService cFeedBackTempService;

	@Autowired
	private CReplyFeedBackTempService cReplyFeedBackTempService;

	@Autowired
	private BMenuConfigureService bMenuConfigureService;

	@Autowired
	private CReplyLeaveWordsTempService cReplyLeaveWordsTempService;

	@Autowired
	private InterfaceService interfaceService;

	@Autowired
	private LoadConstant loadConstant;

	@RequestMapping(value = "/cus/{feedBackType}/feedbackList.do")
	public String showFeedBackList(Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") String pageNo,
			@PathVariable String feedBackType) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageNo);
		if (!isNum.matches()) {
			pageNo = "1";
		}

		SDictionary sDictionary = dictionaryService.findByLogicID(feedBackType);
		String dicName = sDictionary.getDicName();
		model.addAttribute("dicName", dicName);

		String pageSize = String.valueOf(loadConstant.getPageSize());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", pageNo);
		model.addAttribute("feedBackType", feedBackType);
		return "cus/feedback/feedback_list";

	}

	@RequestMapping(value = "/cus/feedbackGrid.do", method = RequestMethod.GET)
	@ResponseBody
	public JqgridListForm findCmsJson(
			@RequestParam("page") String page,
			@RequestParam(value = "rows", defaultValue = "10") String rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord,
			@RequestParam("feedBackType") String feedBackType,
			@RequestParam(value = "searchField", required = false) String searchField,
			@RequestParam(value = "startDateTime", required = false) String startDateTime,
			@RequestParam(value = "endDateTime", required = false) String endDateTime,
			Model model) {
		int pageNo = 0;
		int row = 0;
		if (page != null) {
			pageNo = Integer.parseInt(page) - 1;
		}
		if (rows != null) {
			row = Integer.parseInt(rows);
		}
		JqgridListForm jqgridListForm = cFeedBackManageService
				.findFeedBackByFeedbackType(getCusTokenInSession().getToken(),
						feedBackType, pageNo + 1, row, sidx, sord);
		return jqgridListForm;
	}

	@RequestMapping(value = "/cus/toUpdateFeedback.do")
	public String toUpdateFeedBack(Model model, @RequestParam("id") String id,
			String pageNo, String feedBackType) {

		SDictionary sDictionary = dictionaryService.findByLogicID(feedBackType);
		String dicName = sDictionary.getDicName();
		model.addAttribute("dicName", dicName);
		model.addAttribute("token", getCusTokenInSession().getToken());
		CFeedBackManageForm form = cFeedBackManageService.findById(id);

		if (form.getReplyContent() != null) {
			form.setReplyContent(form.getReplyContent()
					.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
					.replaceAll(">", "&gt;"));
		}

		model.addAttribute("feedbackForm", form);
		// 图片展示在电脑端的方法
		List<String> picNameList = cFeedBackManageService.showPicList(id);
		model.addAttribute("picNameList", picNameList);
		String pageSize = String.valueOf(loadConstant.getPageSize());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", pageNo);
		model.addAttribute("feedBackType", feedBackType);
		SDictionary nameDictionary = sDictionaryService
				.findByLogicID(feedBackType);
		model.addAttribute("nameDictionary", nameDictionary);
		model.addAttribute("backendPath", loadConstant.getWebSitePath());
		return "cus/feedback/feedback_update";

	}

	@RequestMapping(value = "/cus/updateFeedback.do")
	@ResponseBody
	public ControllerJsonMessage updateFeedback(Model model, String id,
			String replyBy, @RequestParam(value = "openId") String openId,
			@RequestParam(value = "KindEditor") String replyContent,
			@RequestHeader("Accept-Language") String locales) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		CFeedBackManage feedback = cFeedBackManageService.updateFeedback(id,
				replyBy, replyContent);
		boolean isSuccess = false;
		if (feedback != null) {
			cReplyFeedBackTempService.sendToOne(feedback.getId(),
					feedback.getToken(), openId);
			isSuccess = true;
		}
		if (isSuccess) {
			msg.setWarnMsg(getBundleMessage("save.success", null, locales));

		} else {
			msg.setWarnMsg(getBundleMessage("save.fail", null, locales));

		}
		return msg;
	}

	@RequestMapping(value = "/cus/deleteFeedback.do")
	@ResponseBody
	public List<String> deleteFeedback(@RequestParam("idStr") String idStr,
			@RequestParam("pageNo") String pageNo) {
		String notice = cFeedBackManageService.deleteFeedbackbatch(idStr);
		if ("".equals(notice)) {
			notice = "success";
		}
		List<String> resultList = new ArrayList<String>();
		resultList.add(notice);
		resultList.add(pageNo);
		return resultList;
	}

	/********************************************************
	 * 以下是微信端
	 * 
	 * @throws IOException
	 **********************************************************/
	/*
	 * @RequestMapping(value = "/m/showFeedbackAdd.do", method =
	 * RequestMethod.GET) public String showFeedBackAdd(Model model,
	 * 
	 * @RequestParam(value = "openID") String openID,
	 * 
	 * @RequestParam("token") String token) throws IOException{
	 * 
	 * WxJsBean wxJsBean = wxJsService.findWxJsBean(token,
	 * "/m/showFeedbackAdd.do?openID=" + openID + "&token=" + token);
	 * 
	 * List<CFanUserForm> fanUserFormList =
	 * cFanUserService.findByOpenIDAndToken(openID, token);
	 * List<SDictionaryForm> optionFormList =
	 * sDictionaryService.findByTypeCode("feedbackOption");
	 * 
	 * model.addAttribute("wxJsBean", wxJsBean);
	 * model.addAttribute("fanUserFormList", fanUserFormList);
	 * model.addAttribute("optionFormList", optionFormList);
	 * model.addAttribute("token", token); model.addAttribute("openID", openID);
	 * return "m/feedback/feedbackAdd"; }
	 */

	/*	*//**
	 * 
	 * 描述：Oauth2.0校验
	 * 
	 * @author AlexFung 2016年1月28日 上午11:54:32
	 * @param token
	 * @param menuid
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/m/feedback_oauth.do") public String
	 * feedbackOauth(@RequestParam(value = "token") String token,
	 * 
	 * @RequestParam(value = "menuid") String menuid) { String url = "";
	 * if(!StringUtils.isEmpty(menuid)){ //有menuid，跳转到 对应的反馈功能 url =
	 * Oauth2Util.buildAuthorizationUrl
	 * (mpService.findMpByToken(token).getAppId(),
	 * systemService.findSystem().getMobiePath() + "/m/" + menuid +
	 * "/feedback_oauth2.do", token); } else{ //没有menuid，跳转到 综合反馈功能 url =
	 * Oauth2Util
	 * .buildAuthorizationUrl(mpService.findMpByToken(token).getAppId(),
	 * systemService.findSystem().getMobiePath() +
	 * "/m/feedbackCollection_oauth2.do", token); }
	 * 
	 * return "redirect:" + url; }
	 *//**
	 * 
	 * 描述：中转Oauth2.0（防止刷新报错）
	 * 
	 * @author AlexFung 2016年1月28日 上午11:55:02
	 * @param model
	 * @param menuID
	 * @param code
	 * @param state
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/m/{menuID}/feedback_oauth2.do") public String
	 * feedbackOauth2(Model model, @PathVariable String menuID,
	 * 
	 * @RequestParam(value = "code") String code,
	 * 
	 * @RequestParam(value = "state") String state) { Mp mp =
	 * mpService.findMpByToken(state); String openID =
	 * Oauth2Util.oauth2getOpenID(code, mp.getAppId(), mp.getAppSecret());
	 * model.addAttribute("fToken", state); model.addAttribute("fMenuId",
	 * menuID); model.addAttribute("fOpenId", openID); return
	 * "redirect:/m/toSaveFeedback.do"; }
	 */

	@RequestMapping(value = "/m/feedbackList.do", method = RequestMethod.GET)
	public String showFeedbackList(@RequestParam(value = "token") String token,
			@RequestParam(value = "openId") String openId,
			@RequestParam(value = "feedbackType") String feedbackType,
			Model model) {
		model.addAttribute("token", token);
		model.addAttribute("openID", openId);
		Long count = cFeedBackManageService.findAllCount(openId);
		model.addAttribute("totalSize", count);
		model.addAttribute("feedbackType", feedbackType);
		return "m/feedback/feedbackList";
	}

	@RequestMapping(value = "/m/loadFanUserAddress.do")
	@ResponseBody
	public CFanUserForm loadFanUserAddress(String fanUserId) {

		CFanUserForm form = cFanUserService.findById(fanUserId);
		if (form != null) {

			return form;
		}
		return null;

	}

	/*
	 * @RequestMapping(value = "/m/saveFeedback.do")
	 * 
	 * @ResponseBody public ControllerJsonMessage saveFeedBack(String
	 * feedbackType,String name,String tel,String customerType,String
	 * fanUserId,String feedbackOption, String content,String address,String
	 * openId,String token,String sMediaIdStr,String accesstoken,Model model){
	 * ControllerJsonMessage msg = new ControllerJsonMessage();
	 * 
	 * boolean isSuccess = false;
	 * 
	 * CFeedBackManage feedback =
	 * cFeedBackManageService.saveFeedBack(feedbackType, name, tel,
	 * customerType, fanUserId, feedbackOption, content, address, openId, token,
	 * sMediaIdStr, accesstoken); if(feedback!=null){
	 * cFeedBackTempService.sendToFeedBackPerson(feedback.getId(), token); }
	 * if(feedback!=null){ isSuccess = true; } if (isSuccess) {
	 * msg.setResult(true);
	 * 
	 * } else { msg.setResult(false);
	 * 
	 * } return msg; }
	 */
	@RequestMapping(value = "/m/saveFeedback.do")
	@ResponseBody
	public ControllerJsonMessage saveFeedBacks(String feedbackType,
			String token, String title, String content, String peopleName,
			String peopelAddr, String peopleTel, String openId,
			String sMediaIdStr, String accessToken, Model model) {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		boolean isSuccess = false;
		CFeedBackManage feedback = cFeedBackManageService.saveFeedBacks(
				feedbackType, title, content, peopleName, peopelAddr, token,
				openId, peopleTel, sMediaIdStr, accessToken);
		String result = "";
		String typeCode = "";
		if (feedbackType.equals("feedBackType01")) {
			typeCode = "37";
		} else if (feedbackType.equals("feedBackType02")) {
			typeCode = "25";
		} else if (feedbackType.equals("feedBackType03")) {
			typeCode = "31";
		} else if (feedbackType.equals("feedBackType04")) {
			typeCode = "27";
		} else {
			typeCode = "";
		}
		String method = "PostWorkerOrders";
		if (feedback != null) {
			// cFeedBackTempService.sendToFeedBackPerson(feedback.getId(),
			// token);发送消息给管理的人员
			try {
				result = interfaceService.postWorker(
						loadConstant.getPostWorkerServicePath(), method,
						peopleName, peopelAddr, peopleTel, typeCode, content);
				if (result.equals("1")) {
					System.out.println("成功反馈信息给水司");
				} else {
					System.out.println("给水司的反馈消息失败");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (feedback != null) {
			isSuccess = true;
		}
		if (isSuccess) {
			msg.setResult(true);
		} else {
			msg.setResult(false);
		}
		return msg;
	}

	@RequestMapping(value = "/m/feedbackListJson.do")
	@ResponseBody
	public List<CFeedBackListForm> showFeedbackList(String feedbackType,
			String token, String openId, int page, int rows, Model model) {

		List<CFeedBackListForm> feedbackList = cFeedBackManageService
				.showFeedbackList(feedbackType, token, openId, page, rows);
		return feedbackList;
	}

	@RequestMapping(value = "/m/feedbackView.do")
	public String showFeedBackView(String id, String token, Model model) {

		CFeedBackManageForm form = cFeedBackManageService.findById(id);
		model.addAttribute("feedbackManageForm", form);
		// 图片显示在手机端的方法
		List<String> picNameList = cFeedBackManageService.showPicList(id);
		model.addAttribute("picNameList", picNameList);
		model.addAttribute("token", token);
		model.addAttribute("backendPath", loadConstant.getWebSitePath());
		return "m/feedback/feedbackView";
	}

	@RequestMapping(value = "/m/feedback.do")
	public String showFeedBackAdd(Model model,
			@RequestParam("token") String token,
			@RequestParam("logicID") String logicid,
			@RequestParam("openId") String openId) {

		String menuId = "";

		if (logicid.equals("feedBackType01")) {
			menuId = "5d059ac5-c5c0-11e6-98b8-8c89a5efc215";
		} else if (logicid.equals("feedBackType02")) {
			menuId = "5d059ac5-c5c0-11e6-98b8-8c89a5efa215";
		} else if (logicid.equals("feedBackType03")) {
			menuId = "5d059ac5-c5c0-11e6-98b8-8c89a5efb215";
		} else {
			menuId = "5d059ac5-c5c0-11e6-98b8-8c89a5efd215";
		}

		WxJsBean wxJsBean = wxJsService.findWxJsBean(token,
				"/m/feedback.do?typeCode=feedBackType&logicID=" + logicid
						+ "&openId=" + openId + "&token=" + token);
		model.addAttribute("wxJsBean", wxJsBean);
		BMenuConfigure bMenu = bMenuConfigureService.findMenuById(menuId);
		model.addAttribute("menuName", bMenu);
		model.addAttribute("token", token);
		model.addAttribute("openId", openId);
		model.addAttribute("feedBackType", logicid);
		return "m/feedback/feedbackAdds";
	}

	// 微信抄表

	@RequestMapping(value = "/m/cloudFeedbackList.do")
	public String showCloudFeedbackList(Model model,
			@RequestParam("token") String token,
			@RequestParam("logicID") String logicid,
			@RequestParam("openId") String openId) {

		String menuId = "";

		if (logicid.equals("feedBackType01")) {
			menuId = "5d059ac5-c5c0-11e6-98b8-8c89a5efc215";
		} else if (logicid.equals("feedBackType02")) {
			menuId = "5d059ac5-c5c0-11e6-98b8-8c89a5efa215";
		} else if (logicid.equals("feedBackType03")) {
			menuId = "5d059ac5-c5c0-11e6-98b8-8c89a5efb215";
		} else if (logicid.equals("feedBackType05")) {
			menuId = "a52ba661-167a-4855-b260-3e2112eb558a";

		} else {
			menuId = "a52ba661-167a-4855-b260-3e2112eb558a";
		}

		WxJsBean wxJsBean = wxJsService.findWxJsBean(token,
				"/m/cloudFeedbackList.do?typeCode=feedBackType&logicID="
						+ logicid + "&openId=" + openId + "&token=" + token);
		model.addAttribute("wxJsBean", wxJsBean);
		BMenuConfigure bMenu = bMenuConfigureService.findMenuById(menuId);
		model.addAttribute("menuName", bMenu);
		model.addAttribute("token", token);
		model.addAttribute("openId", openId);
		model.addAttribute("feedBackType", logicid);

		return "cus/cloud/cloud_feedback_list";

	}

	@RequestMapping(value = "/m/saveCloud_feedback.do")
	@ResponseBody
	public ControllerJsonMessage saveCloud_feedback(String feedbackType,
			String token, String title, String content, String peopleName,
			String peopelAddr, String peopleTel, String openId,
			String sMediaIdStr, String accessToken, Model model,String latitude ,String longitude) throws Exception {
		ControllerJsonMessage msg = new ControllerJsonMessage();
		System.out.println("latitude---------->"+latitude);
		System.out.println("longitude--------->"+longitude);
		// 向云识别网络请求解析
		JSONObject jsonObject = callYun(token, accessToken, sMediaIdStr, openId);
		System.out.println("jsonObject----------->" + jsonObject);
		boolean result = false;
		if (null != jsonObject) {
			String resultCode = jsonObject.getString("Code");
			String resultMsg = jsonObject.getString("Message");
			String filename = jsonObject.getString("filename");
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
			String date = formatter.format(new Date());
			List<CFanUser> list = cFanUserService.findByOpenId(openId);
			System.out.println("list--------->"+list);
			if ("0".equals(resultCode)) {
				String Value = jsonObject.getString("Value").split("\\.")[0];
				//向水司反馈读到的水表参数值   如何获取到对应的户号  这里先固定给一个户号
				String results = interfaceService.saveMeter(loadConstant.getTimeLimitServicePath(), "saveMeter", list.size() == 0 ? "390049213":list.get(0).getUserAccount(), openId, Value, date, latitude, longitude, filename);
				log.info("向水司反馈读表的信息的返回结果........"+results);
				msg.setCloudValue(Value);
				result = true;
			} else {
				result = false;
				log.error("解析图片失败 errorCode:{} errmsg:{}", resultCode,
						resultMsg);
			}
		}
		msg.setResult(result);
		return msg;
	}

	public String comParam(String... strings) {
		String result = "";
		String str[] = { "Image", "ClientType", "UserID", "DeviceID",
				"MacAddress", "Datetime", "NumArea", "Province", "City",
				"LastRecord" };
		int strlength = str.length;
		for (int i = 0; i < strlength; i++) {

			if (i != (strlength - 1)) {
				result += str[i] + "=" + strings[i] + "&";
			} else {
				result += str[i] + "=" + strings[i];
			}

		}

		return result;

	}

	public JSONObject callYun(String token, String accessToken,
			String sMediaIdStr, String openId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String ymd = sdf.format(new Date());
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String newUrl = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();

		request.getSession().getServletContext().getRealPath("\\");
		String savePath = newUrl + "resources\\attached\\" + token
				+ "\\feedback\\";
		String picName = "";
		if (sMediaIdStr != null && !"".equals(sMediaIdStr)) {
			for (String str : sMediaIdStr.split(",")) {
				// 时间_openId_6位随机数
				String fileName = ymd + "_" + openId + "_"
						+ SecurityMD5.getUUID(6) + ".jpg";
				try {
					WxJsUtil.saveImageToDisk(str, savePath, fileName,
							accessToken);
				} catch (Exception e) {
					e.printStackTrace();
				}
				picName = picName + fileName + ",";
			}
			picName = picName.substring(0, picName.length() - 1);
		}
		String base64Message = ImageBase64Utils.GetImageStr(savePath + picName);
		String callUrl = "https://cloud-meters.com/water/pc/getDigit";
		String Datetime = DATE.dateToString(new Date(),
				DATE_PARAM.CHN_DATE_SIMPLE_XXX);
		String MacAddress = "";
		try {
			MacAddress = LOCALMAC.getLocalMac();
		} catch (SocketException | UnknownHostException e) {

			e.printStackTrace();
		}
		String Image = base64Message;
		String ClientType = "Web";
		String UserID = "ZHZT2017";
		String DeviceID = "ZHZT";

		// Datetime =Datetime;
		String NumArea = "0,120,950,900";
		String Province = "Guangdong";
		String City = "zhuhai";
		String LastRecord = "0,0";
		String comParamStr = comParam(Image, ClientType, UserID, DeviceID,
				MacAddress, Datetime, NumArea, Province, City, LastRecord);
		// System.out.println("comParamStr----------->" + comParamStr);
		JSONObject jsonObject = CommonUtil.httpsRequest(callUrl, "POST",
				comParamStr);
		return jsonObject;
	}

}
