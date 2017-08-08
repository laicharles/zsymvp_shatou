package com.wisewater.system.controller;

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
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.service.SUserService;
import com.wisewater.util.tools.ControllerJsonMessage;

@Controller
@RequestMapping("/biz")
public class SUserController extends BaseController {

	@Autowired
	private SUserService userService;
	
	
	/**
	 * 
	 * @param model
	 * @param pageNo  页码，默认查询第1页
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午5:52:34
	 * 描述：
	 */
    @RequestMapping("/suser_list.do")
	public String findAllInPage(Model model,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
    	
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
        model.addAttribute("page", pageNo);
    	
    		
		return "biz/system/suser_list";
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
	 * 2015年4月1日下午4:54:26
	 * 描述：分页查询
	 */
	@RequestMapping(value="/suser_list_json.do")
	public @ResponseBody JqgridListForm findPageByJson(Model model,
			@RequestParam(value="page",defaultValue="1") String page,
			@RequestParam(value="sidx",required=false) String sidx,
			@RequestParam(value="sord",required=false) String sord,
			@RequestParam(value="loginName",required=false) String loginName,
			@RequestParam(value="isDisabled",required=false,defaultValue="0") int isDisabled,
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

        JqgridListForm jqgridListForm = userService.findAll(pageNo, sidx, sord, loginName, isDisabled, mobile, userName);
		
		return jqgridListForm;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午5:01:02
	 * 描述：根据ID进行检查
	 */
	@RequestMapping(value="/suser_checkById.do")
	public @ResponseBody ControllerJsonMessage checkById(@Param("id")String id,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		if(StringUtils.isEmpty(id)){
			msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			return msg;
		}else{
			
			SUserForm userForm = userService.findById(id);
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
	 * 2015年4月1日下午5:01:02
	 * 描述：根据Email进行检查
	 */
	@RequestMapping(value="/suser_checkByEmail.do")
	public @ResponseBody ControllerJsonMessage checkByEmail(@Param("email")String email,@Param("id")String id,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		if(StringUtils.isEmpty(email)){
			msg.setWarnMsg(getBundleMessage("s_user.email.null",null,locales));
			return msg;
		}else{
			
			SUserForm userForm = userService.findByLoginName(email);
			if(userForm==null || userForm.getId().equals(id)){
				msg.setResult(true);
				msg.setWarnMsg(getBundleMessage("s_user.email.canUse",null,locales));
			}else{
				msg.setWarnMsg(getBundleMessage("s_user.email.already.exists",null,locales));
			}
		}
		
		return msg;
	}
	
	/**
	 * 
	 * @param loginName
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午5:01:02
	 * 描述：根据Email进行检查
	 */
	@RequestMapping(value="/suser_checkByLoginName.do")
	public @ResponseBody ControllerJsonMessage checkByLoginName(@Param("loginName")String loginName,@Param("id")String id,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		if(StringUtils.isEmpty(loginName)){
			msg.setWarnMsg(getBundleMessage("s_user.loginName.null",null,locales));
			return msg;
		}else{
			
			SUserForm userForm = userService.findByLoginName(loginName);
			if(userForm==null || userForm.getId().equals(id)){
				msg.setResult(true);
				msg.setWarnMsg(getBundleMessage("s_user.loginName.canUse",null,locales));
			}else{
				msg.setWarnMsg(getBundleMessage("s_user.loginName.already.exists",null,locales));
			}
		}
		
		
		return msg;
	}
	
	/**
	 * 
	 * @param mobile
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午5:01:02
	 * 描述：根据Email进行检查
	 */
	@RequestMapping(value="/suser_checkByMobile.do")
	public @ResponseBody ControllerJsonMessage checkByMobile(@Param("mobile")String mobile,@Param("id")String id,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new ControllerJsonMessage();
		
		if(StringUtils.isEmpty(mobile)){
			msg.setWarnMsg(getBundleMessage("s_user.mobile.null",null,locales));
			return msg;
		}else{
			
			SUserForm userForm = userService.findByMobile(mobile);
			if(userForm==null || userForm.getId().equals(id)){
				msg.setResult(true);
				msg.setWarnMsg(getBundleMessage("s_user.mobile.canUse",null,locales));
			}else{
				msg.setWarnMsg(getBundleMessage("s_user.mobile.already.exists",null,locales));
			}
		}
		
		
		return msg;
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午5:27:18
	 * 描述：
	 */
    @RequestMapping("/suser_toAdd.do")
	public String toAddUser(Model model){
    	
		return "biz/system/suser_add";
	}
    
   /**
    * 
    * @param model
    * @param userForm
    * @param locales
    * @return
    * gawen.chen
    * 2015年4月1日下午5:40:52
    * 描述：新增操作
    */
    @RequestMapping("/suser_add.do")
	public @ResponseBody ControllerJsonMessage saveUser(Model model,SUserForm userForm,@RequestHeader("Accept-Language") String locales){
		
    	ControllerJsonMessage msg = new  ControllerJsonMessage();
    	//form验证
    	if(userForm.getLoginName()==null || userForm.getLoginName().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("s_user.loginName.null",null,locales));
    		return msg;
    	}
    	if(userForm.getMobile()==null || userForm.getMobile().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("s_user.mobile.null",null,locales));
    		return msg;
    	}
    	if(userForm.getEmail()==null || userForm.getEmail().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("s_user.eamil.null",null,locales));
    		return msg;
    	}
    	
    	//业务验证
    	SUserForm sUserForm =  userService.findByLoginName(userForm.getLoginName());
    	if(sUserForm!=null){
    		msg.setWarnMsg(getBundleMessage("s_user.loginName.already.exists",null,locales));
    		return msg;
    	}
    	sUserForm =  userService.findByEmail(userForm.getEmail());
    	if(sUserForm!=null){
    		msg.setWarnMsg(getBundleMessage("s_user.eamil.already.exists",null,locales));
    		return msg;
    	}
    	sUserForm =  userService.findByMobile(userForm.getMobile());
    	if(sUserForm!=null){
    		msg.setWarnMsg(getBundleMessage("s_user.mobile.already.exists",null,locales));
    		return msg;
    	}
    	
    	//保存操作
    	if(userService.saveUser(userForm)){
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
     * 2015年4月1日下午5:40:42
     * 描述：跳转至编辑页面 
     */
    @RequestMapping("/suser_toUpdate.do")
	public String toUpdateUser(Model model,@RequestParam("id") String id ,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
		
    	SUserForm userForm = userService.findById(id);
    	if(userForm!=null){
    		model.addAttribute("userForm", userForm);
    	}else{
    		return "redirect:/biz/suser_list.do?pageNo=1";
    	}
    	
    	//验证数字
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
    	
        //保存页码
        model.addAttribute("page", pageNo);
        
		return "biz/system/suser_update";
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
    @RequestMapping("/suser_toView.do")
	public String toViewUser(Model model,@RequestParam("id") String id ,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
		
    	SUserForm userForm = userService.findById(id);
    	if(userForm!=null){
    		model.addAttribute("userForm", userForm);
    	}else{
    		return "redirect:/biz/suser_list.do?pageNo=1";
    	}
    	
    	//验证数字
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
    	
        //保存页码
        model.addAttribute("page", pageNo);
        
		return "biz/system/suser_view";
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
	@RequestMapping("/suser_update.do")
	public  @ResponseBody ControllerJsonMessage updateUser(Model model,SUserForm userForm,@RequestHeader("Accept-Language") String locales){
		
    	ControllerJsonMessage msg = new  ControllerJsonMessage();
    	
    	//form验证
    	if(userForm.getLoginName()==null || userForm.getLoginName().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("s_user.loginName.null",null,locales));
    		return msg;
    	}
    	if(userForm.getMobile()==null || userForm.getMobile().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("s_user.mobile.null",null,locales));
    		return msg;
    	}
    	if(userForm.getEmail()==null || userForm.getEmail().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("s_user.eamil.null",null,locales));
    		return msg;
    	}
    	
    	//业务验证
    	SUserForm sUserForm =  userService.findByLoginName(userForm.getLoginName());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("s_user.loginName.already.exists",null,locales));
    		return msg;
    	}
    	sUserForm =  userService.findByEmail(userForm.getEmail());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("s_user.eamil.already.exists",null,locales));
    		return msg;
    	}
    	sUserForm =  userService.findByMobile(userForm.getMobile());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("s_user.mobile.already.exists",null,locales));
    		return msg;
    	}
		
		//业务验证，修改的记录不存在
    	SUserForm checkUserForm =  userService.findById(userForm.getId());
    	if(checkUserForm==null){
    		msg.setWarnMsg(getBundleMessage("update.not.exists",null,locales));
    		return msg;
    	}
		
		//补充非修改字段
    	userForm.setPassword(checkUserForm.getPassword());
    	userForm.setIsDisabled(checkUserForm.getIsDisabled());
    	
		//保存操作
    	if(userService.updateUser(userForm)){
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
	 * @param ids
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午5:37:24
	 * 描述：删除操作
	 */
	@RequestMapping("/suser_delete.do")
	public @ResponseBody ControllerJsonMessage deleteUsers(Model model,@RequestParam("ids") String ids
			,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		if(StringUtils.isEmpty(ids)){
			msg.setWarnMsg(getBundleMessage("delete.not.exists",null,locales));
			return msg;
		}
		
		if(userService.deleteUsers(ids)){
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
	@RequestMapping("/suser_disable.do")
	public @ResponseBody ControllerJsonMessage disableUser(Model model,@RequestParam("ids") String ids
			,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		if(StringUtils.isEmpty(ids)){
			msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			return msg;
		}
		
		if(userService.disableUser(ids)){
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("s_user.disable.success",null,locales));
		}else{
			msg.setWarnMsg(getBundleMessage("s_user.disable.fail",null,locales));
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
	@RequestMapping("/suser_enable.do")
	public @ResponseBody ControllerJsonMessage enableUser(Model model,@RequestParam("ids") String ids
			,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		if(StringUtils.isEmpty(ids)){
			msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			return msg;
		}
		
		if(userService.enableUser(ids)){
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("s_user.enable.success",null,locales));
		}else{
			msg.setWarnMsg(getBundleMessage("s_user.enable.fail",null,locales));
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
	@RequestMapping("/suser_initPassword.do")
	public @ResponseBody ControllerJsonMessage initPassword(Model model,@RequestParam("ids") String ids
			,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		if(StringUtils.isEmpty(ids)){
			msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			return msg;
		}
		
		if(userService.initPassword(ids)){
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("s_user.original.success",null,locales));
		}else{
			msg.setWarnMsg(getBundleMessage("s_user.original.fail",null,locales));
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
	 * 2015年4月3日上午11:28:18
	 * 描述：跳转至角色配置页面
	 */
    @RequestMapping("/suser_toUpdateUserRole.do")
	public String toUpdateUserRole(Model model,@RequestParam("id") String id ,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
		
    	SUserForm userForm = userService.findById(id);
    	if(userForm!=null){
    		model.addAttribute("userForm", userForm);
    	}else{
    		return "redirect:/biz/suser_list.do?pageNo=1";
    	}
    	
    	//验证数字
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
    	
        //保存页码
        model.addAttribute("page", pageNo);
        
		return "biz/system/suser_updateUserRole";
	}
	
	/**
	 * 
	 * @param model
	 * @param userId
	 * @param roleIds
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月3日上午11:40:53
	 * 描述：
	 */
    @RequestMapping("/suser_updateUserRole.do")
	public @ResponseBody ControllerJsonMessage updateUserRole(Model model,@RequestParam("userId") String userId,
			@RequestParam("roleIds") String roleIds,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		if(StringUtils.isEmpty(userId)){
			msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
			return msg;
		}
		
		
		if(userService.updateRoleUser(userId, roleIds)){
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("s_user.role.config.success",null,locales));
		}else{
			msg.setWarnMsg(getBundleMessage("s_user.role.config.fail",null,locales));
		}
		return msg;
	}
	
	
    /**
     * 
     * @return
     * gawen.chen
     * 2015年4月3日上午11:54:27
     * 描述：不分页，查询所有角色记录
     */
    @RequestMapping("/suser_checkRole_json.do")
	public @ResponseBody JqgridListForm findAllRolesJson(@RequestParam("userId") String userId){
    	
    	JqgridListForm jqgridListForm = userService.findUserAllRole(userId);
    	
		return jqgridListForm;
	}
    
    
    /**
     * 
     * @param model
     * @return
     * gawen.chen
     * 2015年4月8日上午10:27:21
     * 描述：个人信息修改
     */
    @RequestMapping("/user_x_toUpdatePersonalInfo.do")
	public String toUpdatePersonalInfo(Model model){
		
    	SUserForm userForm = getBizSessionUser();
    	if(userForm!=null){
    		userForm = userService.findById(userForm.getId());
    		model.addAttribute("userForm", userForm);
    	}
    	
		return "biz/system/suser_updatePersonalInfo";
	}
    
    /**
     * 
     * @param model
     * @param userForm
     * @param locales
     * @return
     * gawen.chen
     * 2015年4月22日下午12:00:17
     * 描述：修改个人信息,requestMapping里的x是为了在菜单里的选中进行区别
     */
    @RequestMapping("/user_x_updatePersonalInfo.do")
	public  @ResponseBody ControllerJsonMessage updatePersonalInfo(Model model,SUserForm userForm,@RequestHeader("Accept-Language") String locales){
		
    	ControllerJsonMessage msg = new  ControllerJsonMessage();
    	
    	//form验证
    	if(userForm.getLoginName()==null || userForm.getLoginName().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("s_user.loginName.null",null,locales));
    		return msg;
    	}
    	if(userForm.getMobile()==null || userForm.getMobile().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("s_user.mobile.null",null,locales));
    		return msg;
    	}
    	if(userForm.getEmail()==null || userForm.getEmail().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("s_user.eamil.null",null,locales));
    		return msg;
    	}
    	
    	//业务验证
    	SUserForm sUserForm =  userService.findByLoginName(userForm.getLoginName());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("s_user.loginName.already.exists",null,locales));
    		return msg;
    	}
    	sUserForm =  userService.findByEmail(userForm.getEmail());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("s_user.eamil.already.exists",null,locales));
    		return msg;
    	}
    	sUserForm =  userService.findByMobile(userForm.getMobile());
    	if(sUserForm!=null&&!sUserForm.getId().equals(userForm.getId())){
    		msg.setWarnMsg(getBundleMessage("s_user.mobile.already.exists",null,locales));
    		return msg;
    	}
		
		//业务验证，修改的记录不存在
    	SUserForm checkUserForm =  userService.findById(userForm.getId());
    	if(checkUserForm==null){
    		msg.setWarnMsg(getBundleMessage("update.not.exists",null,locales));
    		return msg;
    	}
		
    	//补充非修改字段
    	if(StringUtils.isEmpty(userForm.getPassword())){
    		userForm.setPassword(checkUserForm.getPassword());
    	}else{
    		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    		userForm.setPassword(encoder.encodePassword(userForm.getPassword(), userForm.getLoginName()));
    	}
    	userForm.setIsDisabled(checkUserForm.getIsDisabled());
		
		//保存操作
    	if(userService.updateUser(userForm)){
    		msg.setResult(true);
    		msg.setWarnMsg(getBundleMessage("save.success",null,locales));
    	}else{
    		msg.setWarnMsg(getBundleMessage("save.fail",null,locales));
    	}
		return  msg;
		
	}
	
}
