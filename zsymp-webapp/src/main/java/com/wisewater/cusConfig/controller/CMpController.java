package com.wisewater.cusConfig.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jaxen.function.SubstringAfterFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.bizConfig.pojo.BCustomerUser;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.bizConfig.service.BCustomerUserService;
import com.wisewater.cusConfig.service.CMpService;
import com.wisewater.fans.controller.CFanForm;
import com.wisewater.fans.controller.CFanUserForm;
import com.wisewater.fans.pojo.CFan;
import com.wisewater.fans.pojo.CFanUser;
import com.wisewater.fans.service.CFanService;
import com.wisewater.fans.service.CFanUserService;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.wechatpublic.model.WxUser;
import com.wisewater.wechatpublic.util.CommonUtil;
import com.wisewater.wechatpublic.util.WxUserUtil;

@Controller
public class CMpController extends BaseController {

	@Autowired
	private CMpService cmpService;
	
	@Autowired
	private BCustomerUserService bcustomeruserService;
	
	@Autowired
	private CFanService cFanService;
	
	@Autowired
	private BAccessTokenService accessTokenService;
		
	@RequestMapping(value="/cus/cmp_toAdd.do")
	public String toAddMp(Model model){
		
	    BAccessTokenForm token = getCusTokenInSession();
	    
		CMpForm cmpForm =  cmpService.findByToken(token.getToken());
    	if(cmpForm!=null){
    		model.addAttribute("cmpForm", cmpForm);
    	}
    	
    	model.addAttribute("webSitePath", loadConstant.getWebSitePath());
		
		return "cus/cusConfig/cmp_add";
	}
	
	@RequestMapping("/cus/cmp_add.do")
	public @ResponseBody ControllerJsonMessage addMp(Model model, CMpForm cmpForm,@RequestHeader("Accept-Language") String locales){
		
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		String cusId=getCusSessionUser().getBCusotmer().getId();
		
		CMpForm rtnCmpForm = cmpService.saveAndGetCMpForm(cmpForm,getCusTokenInSession(), cusId);
		if(rtnCmpForm!=null){
			msg.setResult(true);
			if(rtnCmpForm.isTempInitFlag()){
			    msg.setWarnMsg(getBundleMessage("save.success",null,locales));
			}else{
			    msg.setWarnMsg(getBundleMessage("save.success",null,locales));
			}
		}else{
		    msg.setWarnMsg(getBundleMessage("save.fail",null,locales));
	        
		}
		return msg;
	}
	
    @RequestMapping("/cus/cmp_chkApp.do")
    public @ResponseBody ControllerJsonMessage checkApp(Model model,@RequestParam("appID") String appID, @Param("appSecret")String appSecret,
            @RequestHeader("Accept-Language") String locales){
        
        ControllerJsonMessage msg = new  ControllerJsonMessage();
        
        if(CommonUtil.getToken(appID, appSecret)!=null){
            msg.setResult(true);
        }else{
            msg.setWarnMsg("appID 或 appSecret 可能有误，请查证后再试");
        }
        
        return  msg;
        
    }
    /**
     * 
     * @param model
     * @param loginName
     * @return
     * 2015年4月17日下午6:09:14
     * 描述：昵称的匹配
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/cus/findNickname.do")
	public @ResponseBody List<CFanForm> findByNickName(Model model,String nickname) throws UnsupportedEncodingException {
    	nickname = new String(nickname.getBytes("ISO-8859-1"),"utf-8");
    	
    	List<CFan> fanList = cFanService.findByNickName(nickname);
    	List<CFanForm> list = new ArrayList<CFanForm>();
    	
    	BAccessTokenForm accessTokenForm = null;
    	if(fanList.size() > 0){
    		accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(fanList.get(0).getToken());
    	}else{
    		return list;
    	}
    	
    	
    	for (CFan cFan : fanList) {
    		WxUser wxUser = WxUserUtil.getUserInfo(accessTokenForm.getAccessToken(), cFan.getOpenID());

    		CFanForm cFanForm = new CFanForm();
			cFanForm.setNickName(cFan.getNickName());
			cFanForm.setOpenID(cFan.getOpenID());
			cFanForm.setToken(cFan.getToken());
			if(wxUser != null && wxUser.getHeadimgurl() != null){
				String url = wxUser.getHeadimgurl();
				String pathUrlString = url.substring(0,url.lastIndexOf("/")) +"/46";
				cFanForm.setHeadimgurl(pathUrlString);
			}
			list.add(cFanForm);
		}
    	
    	System.out.println(list.size());
    	return list;
    }
    /**
     * 
     * @param model
     * @param loginName
     * @return
     * 2015年4月17日下午6:09:14
     * 描述：昵称的匹配
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/cus/findBossLoginName.do")
	public @ResponseBody BCustomerUserForm findByBossLogin(Model model,String bossName) throws UnsupportedEncodingException {
    	bossName = new String(bossName.getBytes("ISO-8859-1"),"utf-8");
    	BCustomerUser bUser = new BCustomerUser();
    	BCustomerUserForm bCustomerUserForm = new BCustomerUserForm();
    	if(!StringUtils.isEmpty(bossName)){
    		bUser = bcustomeruserService.findByBossLoginName(bossName);
    		if (bUser != null) {
                bCustomerUserForm.setId(bUser.getId());
    			bCustomerUserForm.setLoginName(bUser.getLoginName());
			}else {
                bCustomerUserForm.setId("");
                bCustomerUserForm.setLoginName("");
			}
    	}
    	return bCustomerUserForm;
    }
	
}