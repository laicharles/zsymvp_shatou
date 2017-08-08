package com.wisewater.bill.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wisewater.base.BaseController;
import com.wisewater.fans.controller.CFanUserForm;
import com.wisewater.fans.service.CFanUserService;
import com.wisewater.system.pojo.SFansOperateLog;
import com.wisewater.system.repository.SFansOperateLogRepository;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.wechatpublic.util.StringUtil;
import com.wisewater.weixin.service.InterfaceService;

/**
 * 水费账单Controller
 * 
 * @author jiahui
 *
 */
@Controller
public class BillController extends BaseController {

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
	 * 描述：绑定列表
	 * 
	 * @author Jiahui 2016年8月23日 上午11:46:40
	 * @param model
	 * @param openID
	 * @param token
	 * @return
	 */
	@RequestMapping("/m/waterBill_bindList.do")
	public String getfanuserBindList(Model model, @RequestParam(value = "openID") String openID,
			@RequestParam("token") String token) {
		List<CFanUserForm> fanUserFormList = cfanuserService.findByOpenIDAndToken(openID, token);
		if (fanUserFormList != null && fanUserFormList.size() == 1) {
			//如果只有一条绑定，则跳到该条绑定的水费账单页面
			return "redirect:/m/showBill.do?account=" + fanUserFormList.get(0).getUserAccount() + "&token=" + token
					+ "&openID=" + openID;
		}
		else if(fanUserFormList == null || fanUserFormList.size() <= 0){
			//如果没有绑定，则跳到绑定页面
			return "redirect:/m/cfanuser_toAdd.do?openID=" + openID + "&token=" + token;
		}
		
		
		// add by alex at 20151130 for hide some message
		List<CFanUserForm> fanUserFormList1 = new ArrayList<CFanUserForm>();
		if (fanUserFormList != null && fanUserFormList.size() > 0) {
			for (CFanUserForm f : fanUserFormList) {
				f.setUserName(StringUtil.hideUserName(f.getUserName()));
				f.setContactAddr(StringUtil.hideString(f.getContactAddr()));
				fanUserFormList1.add(f);
			}
		}
		model.addAttribute("openID", openID);
		model.addAttribute("token", token);
		model.addAttribute("fanUserFormList", fanUserFormList1);

		fansOperateLogRepository.save(new SFansOperateLog("用户访问水费绑定列表页面", "点击菜单", "", new Date()));
		return "m/bill/waterBill_bindList";
	}

	/**
	 * 描述：显示水费账单
	 * 
	 * @author Jiahui 2016年8月24日 上午09:56:13
	 * @param model
	 * @param account
	 * @param openID
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/m/showBill.do")
	public String showBill(Model model, @RequestParam("account") String account,
			@RequestParam(value = "token") String token, @RequestParam(value = "openID") String openID) {
		
		String method = "findWaterBill";
		try {
			String results = InterfaceService.findNewWaterBill(loadConstant.getBillServicePath(), method, account);
			
			//拿出第一个，即最新一个
			List<BillForm> billList = (List) JSONArray.toCollection(JSONArray.fromObject(results), BillForm.class);
			
			
			if(billList.size() <= 0){
				model.addAttribute("error", "查不到您的水费账单");
				model.addAttribute("billFormStatus", "FAIL");
				return "m/error/error";
			}
			if(billList.size() > 1){
				BillForm bill = billList.get(0);
				bill.setId("0");
				bill.setCdate(bill.getCdate().split(" ")[0]);
				bill.setLcdate(bill.getLcdate().split(" ")[0]);
				model.addAttribute("billFormStatus", "SUCCESS");
				model.addAttribute("billForm", bill);
			}
			
			model.addAttribute("bill", "newBill");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fansOperateLogRepository.save(new SFansOperateLog("用户访问水费账单页面", "点击菜单", "", new Date()));
		return "m/bill/bill";
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/m/openOldBillDateList.do")
	public String openOldBillDateList(Model model,String accountNo){
		Map<String,String> map = new LinkedHashMap<String, String>();
		
		String method = "findWaterBill";
		try {
			String results = InterfaceService.findNewWaterBill(loadConstant.getBillServicePath(), method, accountNo);
			
			//拿出第一个，即最新一个
			List<BillForm> list = (List) JSONArray.toCollection(JSONArray.fromObject(results), BillForm.class); 
			
			for (int i = 0; i < list.size(); i++) {
				BillForm bill = list.get(i);
				map.put(i+"", bill.getLcdate().split(" ")[0]+" - "+bill.getCdate().split(" ")[0]);
			}
			
			if(list.size() <= 0){
				model.addAttribute("error", "查不到您的历史记录");
				return "m/error/error";
			}
			
			model.addAttribute("billFormMap", map);
			model.addAttribute("accountNo", list.get(0).getHno());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fansOperateLogRepository.save(new SFansOperateLog("用户访问水费账单历史界面", "点击菜单", "", new Date()));
		return "m/bill/oldBillDataList";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/m/oldWaterBill.do")
	public String oldWaterBill(Model model,String id,String accountNo){
		
		String method = "findWaterBill";
		try {
			String results = InterfaceService.findNewWaterBill(loadConstant.getBillServicePath(), method, accountNo);
			//拿出第一个，即最新一个
			List<BillForm> billList = (List) JSONArray.toCollection(JSONArray.fromObject(results), BillForm.class);
			
			BillForm bill = billList.get(Integer.parseInt(id));
			bill.setId(id);
			bill.setCdate(bill.getCdate().split(" ")[0]);
			bill.setLcdate(bill.getLcdate().split(" ")[0]);
			
			if(billList.size() <= 0){
				model.addAttribute("error", "查不到您的历史水费账单");
				return "m/error/error";
			}
			
			if(billList.size() > 1){
				model.addAttribute("billFormStatus", "SUCCESS");
			}else{
				model.addAttribute("billFormStatus", "FAIL");
			}
			
			model.addAttribute("bill", "oldBill");
			model.addAttribute("billForm", bill);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fansOperateLogRepository.save(new SFansOperateLog("用户访问水费历史账单", "点击菜单", "", new Date()));
		return "m/bill/bill";
	}
	
}
