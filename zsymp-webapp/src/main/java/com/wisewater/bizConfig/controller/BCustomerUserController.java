package com.wisewater.bizConfig.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.bizConfig.pojo.BCustomerUser;
import com.wisewater.bizConfig.service.BCustomerUserService;
import com.wisewater.fans.controller.CFanForm;
import com.wisewater.fans.service.CFanService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.review.pojo.SReviewPermissions;
import com.wisewater.review.service.SReviewPermissionsService;
import com.wisewater.system.controller.SRoleForm;
import com.wisewater.system.service.SRoleService;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.util.tools.LoadConstant;

@Controller
@RequestMapping("/cus")
public class BCustomerUserController extends BaseController {

	@Autowired
	private BCustomerUserService bcustomeruserService;
	
	@Autowired
	private SReviewPermissionsService reviewService;
	
	@Autowired
	private SRoleService roleService;
   
	@Autowired
	private LoadConstant loadConstant;
	
	@Autowired
	private CFanService cFanService;
	/**
	 * 
	 * @param model
	 * @param fromInvitedCode
	 * @return
	 * gawen.chen
	 * 2015年4月8日下午1:52:00
	 * 描述：从邀请链接跳转至注册页面
	 */
	@RequestMapping("/registerByInvitedCode.do")
	public String registerByInvitedCode(Model model,
			@RequestParam(value="fromInvitedCode",required=false) String fromInvitedCode){
		
	    if(!StringUtils.isEmpty(fromInvitedCode)){
	    	model.addAttribute("fromInvitedCode", fromInvitedCode);
	    }
	 
		return "cus/registerByInvitedCode";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * gawen.chen
	 * 2015年4月8日下午1:57:34
	 * 描述：跳转至查看自己邀请链接
	 */
	@RequestMapping("/customeruser_gx_getInvitedURL.do")
	public String getInvitedURL(Model model){
		model.addAttribute("webSitePath", loadConstant.getWebSitePath());
		return "cus/bizConfig/bcustomeruser_getInvitedURL";
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午5:01:02
	 * 描述：根据ID进行检查
	 */
	@RequestMapping(value="/bcustomeruser_register.do")
	public @ResponseBody ControllerJsonMessage registerCustomerUser(BCustomerUserForm customerUserForm
			,@RequestHeader("Accept-Language") String locales){
		
		ControllerJsonMessage msg = new  ControllerJsonMessage();
    	//form验证
    	if(customerUserForm.getLoginName()==null || customerUserForm.getLoginName().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.null",null,locales));
    		return msg;
    	}
    	if(customerUserForm.getMobile()==null || customerUserForm.getMobile().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.null",null,locales));
    		return msg;
    	}
    	if(customerUserForm.getEmail()==null || customerUserForm.getEmail().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.email.null",null,locales));
    		return msg;
    	}
    	
    	//业务验证
    	BCustomerUserForm checkCusUserForm =  bcustomeruserService.findByLoginName(customerUserForm.getLoginName());
    	if(checkCusUserForm!=null){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.already.exists",null,locales));
    		return msg;
    	}
    	checkCusUserForm =  bcustomeruserService.findByEmail(customerUserForm.getEmail());
    	if(checkCusUserForm!=null){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.email.already.exists",null,locales));
    		return msg;
    	}
    	checkCusUserForm =  bcustomeruserService.findByMobile(customerUserForm.getMobile());
    	if(checkCusUserForm!=null){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.already.exists",null,locales));
    		return msg;
    	}
    	int returnCode = bcustomeruserService.saveRegisterCustomerUser(customerUserForm);
    	//保存操作
    	if(returnCode==0){
    		msg.setResult(true);
    		msg.setWarnMsg(getBundleMessage("register.success",null,locales));
    	}else if(returnCode==1){
    		msg.setWarnMsg(getBundleMessage("register.null.fail",null,locales));
    	}else if(returnCode==3){
    		msg.setWarnMsg(getBundleMessage("register.authCodefail",null,locales));
    	}else{
    		msg.setWarnMsg(getBundleMessage("register.fail",null,locales));
    	}
		return  msg;
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午5:01:02
	 * 描述：根据ID进行检查
	 */
	@RequestMapping(value="/bcustomeruser_checkById.do")
	public @ResponseBody ControllerJsonMessage checkById(@Param("id")String id,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		if(StringUtils.isEmpty(id)){
			msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			return msg;
		}else{
			
			BCustomerUserForm userForm = bcustomeruserService.findById(id);
			if(userForm==null){
				msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			}else{
				msg.setResult(true);
			}
		}
		
		return msg;
	}
	
	
	/**
	 * 
	 * @param email
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午5:01:02
	 * 描述：根据Email进行检查
	 */
	@RequestMapping(value="/bcustomeruser_checkByEmail.do")
	public @ResponseBody ControllerJsonMessage checkByEmail(@Param("email")String email,@Param("id")String id,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		if(StringUtils.isEmpty(email)){
			msg.setWarnMsg(getBundleMessage("b_customer_user.email.null",null,locales));
			return msg;
		}else{
			
			BCustomerUserForm userForm = bcustomeruserService.findByEmail(email);
			if(StringUtils.isEmpty(id)){   //新增操作
				if(userForm!=null){
					msg.setWarnMsg(getBundleMessage("b_customer_user.email.already.exists",null,locales));
				}else{
					msg.setResult(true);
					msg.setWarnMsg(getBundleMessage("b_customer_user.email.canUse",null,locales));
				}
			}else{
				if(userForm==null || userForm.getId().equals(id)){
					msg.setResult(true);
					msg.setWarnMsg(getBundleMessage("b_customer_user.email.canUse",null,locales));
				}else{
					msg.setWarnMsg(getBundleMessage("b_customer_user.email.already.exists",null,locales));
				}
			}
		}
		
		return msg;
	}
	
	/**
	 * 
	 * @param loginName
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午5:01:02
	 * 描述：根据Email进行检查
	 */
	@RequestMapping(value="/bcustomeruser_checkByLoginName.do")
	public @ResponseBody ControllerJsonMessage checkByLoginName(@Param("loginName")String loginName,@Param("id")String id,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		if(StringUtils.isEmpty(loginName)){
			msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.null",null,locales));
			return msg;
		}else{
			
			BCustomerUserForm userForm = bcustomeruserService.findByLoginName(loginName);
			if(StringUtils.isEmpty(id)){   //新增操作
				if(userForm!=null){
					msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.already.exists",null,locales));
				}else{
					msg.setResult(true);
					msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.canUse",null,locales));
				}
			}else{
				if(userForm==null || userForm.getId().equals(id)){
					msg.setResult(true);
					msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.canUse",null,locales));
				}else{
					msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.already.exists",null,locales));
				}
			}
		}
		
		
		return msg;
	}
	
	/**
	 * 
	 * @param mobile
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午5:01:02
	 * 描述：根据Email进行检查
	 */
	@RequestMapping(value="/bcustomeruser_checkByMobile.do")
	public @ResponseBody ControllerJsonMessage checkByMobile(@Param("mobile")String mobile,@Param("id")String id,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		if(StringUtils.isEmpty(mobile)){
			msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.null",null,locales));
			return msg;
		}else{
			
			BCustomerUserForm userForm = bcustomeruserService.findByMobile(mobile);
			if(StringUtils.isEmpty(id)){   //新增操作
				if(userForm!=null){
					msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.already.exists",null,locales));
				}else{
					msg.setResult(true);
					msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.canUse",null,locales));
				}
			}else{
				if(userForm==null || userForm.getId().equals(id)){  //修改操作
					msg.setResult(true);
					msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.canUse",null,locales));
				}else{
					msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.already.exists",null,locales));
				}
			}
		}
		
		
		return msg;
	}
	
	
	
	
	
	
	
	/**
	 * @param model
	 * @param pageNo
	 * @return
	 * gawen.chen
	 * 2015年4月7日下午4:39:59
	 * 描述：跳转至查询列表页面
	 */
	@RequestMapping("/bcustomeruser_list.do")
	public String findAllUserInCustomer(Model model,@RequestParam(value="pageNo",defaultValue="1",required=false) String pageNo){
    	
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
        model.addAttribute("page", pageNo);
    	
    		
		return "cus/bizConfig/bcustomeruser_list";
	}
	
	
	/**
	 * 
	 * @param model
	 * @return
	 * gawen.chen
	 * 2015年4月7日下午4:40:44
	 * 描述：跳转列新增页面
	 */
	@RequestMapping("/bcustomeruser_toAdd.do")
	public String toAddCustomerUser(Model model){
		List<SRoleForm> listForm = roleService.findAll();
		List<SReviewPermissions> permissionForm = reviewService.findAll();
		model.addAttribute("listForm", listForm);
		model.addAttribute("permissionForm", permissionForm);
		return "cus/bizConfig/bcustomeruser_add";
	}
    
   /**
    * 
    * @param model
    * @param userForm
    * @param locales
    * @return
    * gawen.chen
    * 2015年4月7日下午5:40:52
    * 描述：新增操作
    */
    @RequestMapping("/bcustomeruser_add.do")
	public @ResponseBody ControllerJsonMessage saveCustomerUser(Model model,BCustomerUserForm userForm,@RequestHeader("Accept-Language") String locales){
		
    	ControllerJsonMessage msg = new  ControllerJsonMessage();
    	//form验证
    	if(userForm.getLoginName()==null || userForm.getLoginName().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.null",null,locales));
    		return msg;
    	}
    	if(userForm.getMobile()==null || userForm.getMobile().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.null",null,locales));
    		return msg;
    	}
    	if(userForm.getEmail()==null || userForm.getEmail().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.email.null",null,locales));
    		return msg;
    	}
    	
    	if(userForm.getBCusotmer()==null ||userForm.getBCusotmer().getId() ==null|| "".equals(userForm.getBCusotmer().getId())){
    		msg.setWarnMsg(getBundleMessage("arg.not.exists",null,locales));
    		return msg;
    	}
    	
    	if(userForm.getRole()==null || userForm.getRole().getRoleCode()==null ||"".equals(userForm.getRole().getRoleCode())){
    		msg.setWarnMsg(getBundleMessage("arg.not.exists",null,locales));
    		return msg;
    	}
    	
    	//业务验证
    	BCustomerUserForm bUserForm =  bcustomeruserService.findByLoginName(userForm.getLoginName());
    	if(bUserForm!=null){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.already.exists",null,locales));
    		return msg;
    	}
    	bUserForm =  bcustomeruserService.findByEmail(userForm.getEmail());
    	if(bUserForm!=null){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.email.already.exists",null,locales));
    		return msg;
    	}
    	bUserForm =  bcustomeruserService.findByMobile(userForm.getMobile());
    	if(bUserForm!=null){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.already.exists",null,locales));
    		return msg;
    	}
    	
    	//保存操作
    	if(bcustomeruserService.saveCustomerUser(userForm)){
    		msg.setResult(true);
    		msg.setWarnMsg(getBundleMessage("save.success",null,locales));
    	}else{
    		msg.setWarnMsg(getBundleMessage("save.fail",null,locales));
    	}
		return  msg;
	}
    
    
    
    /**
     * 
     * @param model
     * @param id
     * @param pageNo
     * @return
     * gawen.chen
     * 2015年4月7日下午5:40:42
     * 描述：跳转至编辑页面 
     */
    @RequestMapping("/bcustomeruser_toUpdate.do")
	public String toUpdateUser(Model model,@RequestParam("id") String id ,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
		
    	BCustomerUserForm userForm = bcustomeruserService.findById(id);
    	//通过上级 的ID来查找上级的登录名
    	BCustomerUser bCustomerUser = bcustomeruserService.findByHigherCustomerUserId(userForm.getHigherCustomerUserId());
    	//通过自己的openId来查找自己微信的昵称
    	CFanForm cfanform = cFanService.findByOpenID(userForm.getBinOpendId());
    	
    	model.addAttribute("userForm", userForm);
    	model.addAttribute("bCustomerUser", bCustomerUser);
    	model.addAttribute("cfanform", cfanform);
    	
    	//验证数字
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
    	
        //保存页码
        model.addAttribute("page", pageNo);
        List<SRoleForm> listForm = roleService.findAll();
        List<SReviewPermissions> permissionForm = reviewService.findAll();
        model.addAttribute("permissionForm", permissionForm);
		model.addAttribute("listForm", listForm);
		return "cus/bizConfig/bcustomeruser_update";
	}
	
    
    /**
     * 
     * @param model
     * @param id
     * @param pageNo
     * @return
     * gawen.chen
     * 2015年4月1日下午5:40:42
     * 描述：跳转至编辑页面 
     */
    @RequestMapping("/bcustomeruser_toView.do")
	public String toViewUser(Model model,@RequestParam("id") String id ,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
		
    	BCustomerUserForm userForm = bcustomeruserService.findById(id);
    	if(userForm!=null){
    		model.addAttribute("userForm", userForm);
    	}else{
    		return "redirect:/cus/bcustomeruser_list.do?pageNo=1";
    	}
    	
    	//验证数字
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
    	
        //保存页码
        model.addAttribute("page", pageNo);
        
		return "cus/bizConfig/bcustomeruser_view";
	}
    
    /**
     * 
     * @param model
     * @param userForm
     * @param locales
     * @return
     * gawen.chen
     * 2015年4月1日下午5:40:32
     * 描述：处理编辑请求
     */
	@RequestMapping("/bcustomeruser_update.do")
	public  @ResponseBody ControllerJsonMessage updateUser(Model model,BCustomerUserForm userForm,@RequestHeader("Accept-Language") String locales){
		
    	ControllerJsonMessage msg = new  ControllerJsonMessage();
    	
    	//form验证
    	if(userForm.getLoginName()==null || userForm.getLoginName().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.null",null,locales));
    		return msg;
    	}
    	if(userForm.getMobile()==null || userForm.getMobile().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.null",null,locales));
    		return msg;
    	}
    	if(userForm.getEmail()==null || userForm.getEmail().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.email.null",null,locales));
    		return msg;
    	}
    	
    	//业务验证
    	BCustomerUserForm sUserForm =  bcustomeruserService.findByLoginName(userForm.getLoginName());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.already.exists",null,locales));
    		return msg;
    	}
    	sUserForm =  bcustomeruserService.findByEmail(userForm.getEmail());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.email.already.exists",null,locales));
    		return msg;
    	}
    	sUserForm =  bcustomeruserService.findByMobile(userForm.getMobile());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.already.exists",null,locales));
    		return msg;
    	}
		
		//业务验证，修改的记录不存在
    	BCustomerUserForm checkUserForm =  bcustomeruserService.findById(userForm.getId());
    	if(checkUserForm==null){
    		msg.setWarnMsg(getBundleMessage("update.not.exists",null,locales));
    		return msg;
    	}
		
		//非更新字段信息补充
    	userForm.setPassword(checkUserForm.getPassword());
    	userForm.setToInviteCode(checkUserForm.getToInviteCode());
    	userForm.setToInviteCount(checkUserForm.getToInviteCount());
    	userForm.setFromInvitedCode(checkUserForm.getFromInvitedCode());
    	userForm.setIsDisabled(checkUserForm.getIsDisabled());
    	userForm.setRegisterDate(checkUserForm.getRegisterDate());
    	
		//保存操作
    	if(bcustomeruserService.updateCustomerUser(userForm)){
    		msg.setResult(true);
    		msg.setWarnMsg(getBundleMessage("save.success",null,locales));
    	}else{
    		msg.setWarnMsg(getBundleMessage("save.fail",null,locales));
    	}
		return  msg;
		
	}
	
	/**
	 * 
	 * @param model
	 * @param page  查询页码
     * @param sidx   排序字段
     * @param sord  排序方式 asc 或desc
	 * @param loginName
	 * @param isDisabled
	 * @param mobile
	 * @param userName
	 * @return
	 * gawen.chen
	 * 2015年4月7日下午3:48:36
	 * 描述：
	 */
	@RequestMapping(value="/bcustomeruser_list_json.do")
	public @ResponseBody JqgridListForm findPageCustomerUserByJson(Model model,
			@RequestParam(value="page",defaultValue="1") String page,
			@RequestParam(value="sidx",required=false) String sidx,
			@RequestParam(value="sord",required=false) String sord,
			@RequestParam(value="loginName",required=false) String loginName,
			@RequestParam(value="isDisabled",required=false,defaultValue="-1") int isDisabled,
			@RequestParam(value="mobile",required=false) String mobile,
			@RequestParam(value="userName",required=false) String userName){
		
		int pageNo = 1;
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(page);
        if(isNum.matches()){
        	pageNo = Integer.parseInt(page);
        }
        
        if(StringUtils.isEmpty(loginName)){loginName="";}
        if(StringUtils.isEmpty(mobile)){mobile="";}
        if(StringUtils.isEmpty(userName)){userName="";}
        
        if(StringUtils.isEmpty(sidx)){
        	sidx="loginName";
        }
        if(StringUtils.isEmpty(sord)){
        	sord="asc";
        }

        BCustomerUserForm userForm  =  getCusSessionUser();
        String customerId = "";
        if(userForm.getBCusotmer()!=null){
        	customerId = userForm.getBCusotmer().getId();
        }
        
        JqgridListForm jqgridListForm = bcustomeruserService.findAll(pageNo, sidx, sord, loginName, isDisabled, mobile, userName,customerId);
		
		return jqgridListForm;
	}
	
	
	
	/**
	 * 
	 * @param model
	 * @param ids
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午5:37:24
	 * 描述：删除操作
	 */
	@RequestMapping("/bcustomeruser_delete.do")
	public @ResponseBody ControllerJsonMessage deleteUsers(Model model,@RequestParam("ids") String ids
			,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		if(StringUtils.isEmpty(ids)){
			msg.setWarnMsg(getBundleMessage("delete.not.exists",null,locales));
			return msg;
		}
		
		if(bcustomeruserService.checkUsersInAdmin(ids)){
			msg.setWarnMsg(getBundleMessage("b_customer_user.operate.fail",null,locales));
			return msg;
		}
		
		if(bcustomeruserService.deleteUsers(ids)){
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("delete.success",null,locales));
		}else{
			msg.setWarnMsg(getBundleMessage("delete.fail",null,locales));
		}
		return msg;
	}
	
	
	/**
	 * 
	 * @param model
	 * @param ids
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午5:37:24
	 * 描述：删除操作
	 */
	@RequestMapping("/bcustomeruser_disable.do")
	public @ResponseBody ControllerJsonMessage disableUser(Model model,@RequestParam("ids") String ids
			,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		if(StringUtils.isEmpty(ids)){
			msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			return msg;
		}
		
		if(bcustomeruserService.disableUser(ids)){
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("b_customer_user.disable.success",null,locales));
		}else{
			msg.setWarnMsg(getBundleMessage("b_customer_user.disable.fail",null,locales));
		}
		return msg;
	}
	
	/**
	 * 
	 * @param model
	 * @param ids
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午5:47:10
	 * 描述：启用
	 */
	@RequestMapping("/bcustomeruser_enable.do")
	public @ResponseBody ControllerJsonMessage enableUser(Model model,@RequestParam("ids") String ids
			,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		if(StringUtils.isEmpty(ids)){
			msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			return msg;
		}
		
		if(bcustomeruserService.enableUser(ids)){
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("b_customer_user.enable.success",null,locales));
		}else{
			msg.setWarnMsg(getBundleMessage("b_customer_user.enable.fail",null,locales));
		}
		return msg;
	}
	
	
	/**
	 * 
	 * @param model
	 * @param ids
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午5:49:57
	 * 描述：初始化密码
	 */
	@RequestMapping("/bcustomeruser_initPassword.do")
	public @ResponseBody ControllerJsonMessage initPassword(Model model,@RequestParam("ids") String ids
			,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		if(StringUtils.isEmpty(ids)){
			msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			return msg;
		}
		
		if(bcustomeruserService.initPassword(ids)){
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("b_customer_user.original.success",null,locales));
		}else{
			msg.setWarnMsg(getBundleMessage("b_customer_user.original.fail",null,locales));
		}
		return msg;
	}
	
	
	/**
     * 
     * @param model
     * @param id
     * @param pageNo
     * @return
     * gawen.chen
     * 2015年4月8日下午9:27:42
     * 描述：跳转至编辑页面 
     */
    @RequestMapping("/customeruser_x_toPersonalInfo.do")
	public String toUpdatePersonalInfo(Model model){
		
    	BCustomerUserForm userForm = getCusSessionUser();
    	if(userForm!=null){
    		userForm = bcustomeruserService.findById(userForm.getId());
    		model.addAttribute("userForm", userForm);
    	}
        
		return "cus/bizConfig/bcustomeruser_updatePersonalInfo";
	}
	
   
    /**
     * 
     * @param model
     * @param userForm
     * @param locales
     * @return
     * gawen.chen
     * 2015年4月17日下午1:30:00
     * 描述：更新人人信息
     */
    @RequestMapping("/customeruser_x_updateInfo.do")
	public  @ResponseBody ControllerJsonMessage updatePersonalInfo(Model model,BCustomerUserForm userForm,@RequestHeader("Accept-Language") String locales){
		
    	ControllerJsonMessage msg = new  ControllerJsonMessage();
    	
    	//form验证
    	if(userForm.getLoginName()==null || userForm.getLoginName().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.null",null,locales));
    		return msg;
    	}
    	if(userForm.getMobile()==null || userForm.getMobile().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.null",null,locales));
    		return msg;
    	}
    	if(userForm.getEmail()==null || userForm.getEmail().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.email.null",null,locales));
    		return msg;
    	}
    	
    	if(
    			(!StringUtils.isEmpty(userForm.getOldPassword()) && StringUtils.isEmpty(userForm.getPassword()))
    			||(StringUtils.isEmpty(userForm.getOldPassword()) && !StringUtils.isEmpty(userForm.getPassword()))
    			){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.password.null",null,locales));
    		return msg;
    	}
    	
    	if(!StringUtils.isEmpty(userForm.getOldPassword()) ){
    		if(!bcustomeruserService.checkOldPassword(userForm.getId(), userForm.getOldPassword())){
	    		msg.setWarnMsg(getBundleMessage("b_customer_user.password.invalid",null,locales));
	    		return msg;
    		}
    	}
    	
    	//业务验证
    	BCustomerUserForm sUserForm =  bcustomeruserService.findByLoginName(userForm.getLoginName());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.loginName.already.exists",null,locales));
    		return msg;
    	}
    	sUserForm =  bcustomeruserService.findByEmail(userForm.getEmail());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.email.already.exists",null,locales));
    		return msg;
    	}
    	sUserForm =  bcustomeruserService.findByMobile(userForm.getMobile());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.mobile.already.exists",null,locales));
    		return msg;
    	}
		
		//业务验证，修改的记录不存在
    	BCustomerUserForm checkUserForm =  bcustomeruserService.findById(userForm.getId());
    	if(checkUserForm==null){
    		msg.setWarnMsg(getBundleMessage("update.not.exists",null,locales));
    		return msg;
    	}
		
    	//非更新字段信息补充
    	if(StringUtils.isEmpty(userForm.getPassword())){
    		userForm.setPassword(checkUserForm.getPassword());
    	}else{
    		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    		userForm.setPassword(encoder.encodePassword(userForm.getPassword(), userForm.getLoginName()));
    	}
    	userForm.setToInviteCode(checkUserForm.getToInviteCode());
    	userForm.setToInviteCount(checkUserForm.getToInviteCount());
    	userForm.setFromInvitedCode(checkUserForm.getFromInvitedCode());
    	userForm.setIsDisabled(checkUserForm.getIsDisabled());
    	userForm.setRegisterDate(checkUserForm.getRegisterDate());
    	
		
		//保存操作
    	if(bcustomeruserService.updateCustomerUser(userForm)){
    		msg.setResult(true);
    		msg.setWarnMsg(getBundleMessage("save.success",null,locales));
    	}else{
    		msg.setWarnMsg(getBundleMessage("save.fail",null,locales));
    	}
		return  msg;
		
	}
    
    
    
    /**
     * 
     * @param model
     * @param loginName
     * @param locales
     * @return
     * gawen.chen
     * 2015年4月17日下午6:09:14
     * 描述：
     */
    @RequestMapping("/bcustomeruser_toresetPassword.do")
	public String toResetPassword(Model model,
			@RequestParam("loginName")String loginName,
			@RequestHeader("Accept-Language") String locales){
    	
    	BCustomerUserForm customerUserForm = new BCustomerUserForm();
    	if(!StringUtils.isEmpty(loginName)){
    		customerUserForm = bcustomeruserService.findByLoginName(loginName);
    	}
    	model.addAttribute("userForm", customerUserForm);
    	return "/cus/bizConfig/bcustomeruser_resetPassword";
    }
    
      
    /**
     * 
     * @param model
     * @return
     * gawen.chen
     * 2015年4月17日下午17:33:13
     * 描述：忘记密码，重置申请
     */
    @RequestMapping("/bcustomeruser_resetPassword.do")
	public @ResponseBody ControllerJsonMessage resetPassword(Model model,
			BCustomerUserForm customerUserForm,
			@RequestHeader("Accept-Language") String locales){
		
    	ControllerJsonMessage msg = new  ControllerJsonMessage();
    	if(customerUserForm== null 
    			|| StringUtils.isEmpty(customerUserForm.getLoginName())
    			||StringUtils.isEmpty(customerUserForm.getEmail())){
    		msg.setWarnMsg(getBundleMessage("user.email.null",null,locales));
    		return msg;
    	}
    	
    	BCustomerUserForm userForm = bcustomeruserService.findByLoginName(customerUserForm.getLoginName());
    	if(userForm==null){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.user.not.exists",null,locales));
    		return msg;
    	}
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date resetInDate = null;
    	try {
			resetInDate = dateFormat.parse(userForm.getResetTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	if(resetInDate==null || resetInDate.getTime()<Calendar.getInstance().getTimeInMillis()){
    		msg.setWarnMsg(getBundleMessage("b_customer_user.password.no.time",null,locales));
    		return msg;
    	}
    	
    	
    	userForm.setPassword(customerUserForm.getPassword());
    	if(bcustomeruserService.saveResetPassowrd(userForm)){
    		msg.setResult(true);
    		msg.setWarnMsg(getBundleMessage("b_customer_user.password.reset.success",null,locales));
    	}else{
    		msg.setWarnMsg(getBundleMessage("b_customer_user.password.reset.fail",null,locales));
    	}
    	
        
		return msg;
	}
    
    public static void main(String[] args) {
    	Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    	System.out.println(encoder.encodePassword("123456", "admin"));
	}
    
}