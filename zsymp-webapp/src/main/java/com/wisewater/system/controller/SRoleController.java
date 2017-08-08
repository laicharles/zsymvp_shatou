package com.wisewater.system.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisewater.base.BaseController;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.service.SRoleService;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.util.tools.ZtreeForm;


@Controller
@RequestMapping("/cus")
public class SRoleController extends BaseController {

	@Autowired
	private SRoleService roleService;
	
	
	/**
	 * 
	 * @param model
	 * @param pageNo  页码，默认查询第1页
	 * @return
	 * gawen.chen
	 * 2015年3月27日下午1:43:34
	 * 描述：
	 */
    @RequestMapping("/srole_list.do")
	public String findInPage(Model model,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
    	
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
        model.addAttribute("page", pageNo);
    	
    		
		return "cus/system/srole_list";
	}
    
    /**
     * 
     * @param page  查询页码
     * @param sidx   排序字段
     * @param sord  排序方式 asc 或desc
     * @return
     * gawen.chen
     * 2015年3月27日下午1:27:22
     * 描述：分页查询角色列表
     */
    @RequestMapping("/srole_list_json.do")
	public @ResponseBody JqgridListForm findRolesJson(@RequestParam(value="page",defaultValue="1") String page,
			@RequestParam(value="sidx",required=false) String sidx,@RequestParam(value="sord",required=false)String sord){
    	
    	int pageNo = 1;
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(page);
        if( isNum.matches() )
        {
        	pageNo = Integer.parseInt(page);
        }
        
    	JqgridListForm jqgridListForm = roleService.findAll(pageNo,sidx,sord);
    	
    	
		return jqgridListForm;
	}
	
	
    /**
     * 
     * @param model
     * @return
     * gawen.chen
     * 2015年3月27日下午1:44:08
     * 描述：跳转新增页面
     */
    @RequestMapping("/srole_toAdd.do")
	public String toAddRole(Model model){
    	
		return "cus/system/srole_add";
	}
    
    /**
     * 
     * @param model
     * @param roleForm
     * @param result
     * @return
     * gawen.chen
     * 2015年3月27日下午1:44:20
     * 描述：新增操作
     */
    @RequestMapping("/srole_add.do")
	public @ResponseBody ControllerJsonMessage saveRole(Model model,SRoleForm roleForm,@RequestHeader("Accept-Language") String locales){
		
    	ControllerJsonMessage msg = new  ControllerJsonMessage();
    	//form验证
    	if(roleForm.getRoleCode()==null || roleForm.getRoleCode().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("srole.roleCode.null",null,locales));
    		return msg;
    	}
    	
    	//业务验证
    	SRoleForm sRoleForm =  roleService.findByRoleCode(roleForm.getRoleCode());
    	if(sRoleForm!=null){
    		msg.setWarnMsg(getBundleMessage("srole.roleCode.already.exists",null,locales));
    		return msg;
    	}
    	
    	//保存操作
    	if(roleService.save(roleForm)){
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
     * 2015年3月27日下午1:44:30
     * 描述：跳转至编辑页面 
     */
    @RequestMapping("/srole_toUpdate.do")
	public String toUpdateRole(Model model,@RequestParam("id") String id ,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
		
    	SRoleForm roleForm = roleService.findById(id);
    	if(roleForm!=null){
    		model.addAttribute("roleForm", roleForm);
    	}else{
    		return "redirect:/cus/srole_list.do?pageNo=1";
    	}
    	
    	//验证数字
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
    	
        //保存页码
        model.addAttribute("page", pageNo);
        
		return "cus/system/srole_update";
	}
    
    /**
     * 
     * @param model
     * @param roleForm
     * @param result
     * @param pageNo
     * @return
     * gawen.chen
     * 2015年3月27日下午1:45:04
     * 描述：处理编辑请求
     */
	@RequestMapping("/srole_update.do")
	public  @ResponseBody ControllerJsonMessage updateRole(Model model,SRoleForm roleForm,@RequestHeader("Accept-Language") String locales){
		
    	ControllerJsonMessage msg = new  ControllerJsonMessage();
    	
		//form校验
		if(roleForm.getRoleCode()==null||roleForm.getRoleCode().trim().equals("")){
    		msg.setWarnMsg(getBundleMessage("srole.roleCode.null",null,locales));
    		return msg;
		}
		
		//业务验证，修改的记录不存在
    	SRoleForm checkRoleForm =  roleService.findById(roleForm.getId());
    	if(checkRoleForm==null){
    		msg.setWarnMsg(getBundleMessage("update.not.exists",null,locales));
    		return msg;
    	}
		
		//业务验证
    	SRoleForm sRoleForm =  roleService.findByRoleCode(roleForm.getRoleCode());
    	if(sRoleForm!=null&&!sRoleForm.getId().equals(roleForm.getId())){
    		
    		msg.setWarnMsg(getBundleMessage("srole.roleCode.already.exists",null,locales));
    		return msg;
    	}
		
		//保存操作
    	if(roleService.update(roleForm)){
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
	 * 2015年3月27日下午1:45:19
	 * 描述：删除操作
	 */
	@RequestMapping("/srole_delete.do")
	public @ResponseBody ControllerJsonMessage deleteRole(Model model,@RequestParam("ids") String ids
			,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		if(roleService.deleteByIds(ids)){
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
	 * @param roleCode
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月1日上午8:56:17
	 * 描述：根据角色代码检查
	 */
	@RequestMapping("/srole_checkRoleCode.do")
	public @ResponseBody ControllerJsonMessage checkRoleCode(Model model,@RequestParam("roleCode") String roleCode,@Param("id")String id,
			@RequestHeader("Accept-Language") String locales){
		
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		if(roleCode!=null&&roleCode.trim().length()>0){
			//业务验证
	    	SRoleForm sRoleForm =  roleService.findByRoleCode(roleCode);
	    	if(sRoleForm!=null && !sRoleForm.getId().equals(id)){
	    		msg.setWarnMsg(getBundleMessage("srole.roleCode.already.exists",null,locales));
	    		return msg;
	    	}else{
	    		msg.setResult(true);
	    		msg.setWarnMsg(getBundleMessage("srole.roleCode.canUse",null,locales));
	    		return msg;
	    	}
		}else{
			msg.setWarnMsg(getBundleMessage("srole.roleCode.null",null,locales));
		}
		return msg;
	}
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月1日上午8:56:30
	 * 描述：角色ID检查记录
	 */
	@RequestMapping("/srole_checkRoleId.do")
	public @ResponseBody ControllerJsonMessage checkRoleId(Model model,@RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales){
		
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		if(id!=null&&id.trim().length()>0){
			//业务验证
	    	SRoleForm sRoleForm =  roleService.findById(id);
	    	if(sRoleForm==null){
	    		msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
	    		return msg;
	    	}else{
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
	 * @return
	 * gawen.chen
	 * 2015年4月3日上午11:50:37
	 * 描述：角色资源配置
	 */
    @RequestMapping("/srole_toAuthority.do")
	public String toAuthorityRole(Model model,@RequestParam("id") String id ,@RequestParam(value="pageNo",defaultValue="1") String pageNo){
		
    	SRoleForm roleForm = roleService.findById(id);
    	if(roleForm!=null){
    		model.addAttribute("roleForm", roleForm);
    	}else{
    		return "redirect:/cus/srole_list.do?pageNo=1";
    	}
    	
    	//验证数字
    	Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if(!isNum.matches()){
        	pageNo = "1";
        }
        
        
        //资源json转换
        ObjectMapper mapper = new ObjectMapper();
        List<ZtreeForm> ztreeForms =  roleService.findAllMenusByRoleId(id);
        String jsonStr="";
		try {
			if(ztreeForms!=null&&!ztreeForms.isEmpty()){
				jsonStr = mapper.writeValueAsString(ztreeForms);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        model.addAttribute("ztreeForms", jsonStr);
        
        
        
        //保存页码
        model.addAttribute("page", pageNo);
        
		return "cus/system/srole_authority";
	}
	
    /**
     * 
     * @param model
     * @param roleId
     * @param resourceIds
     * @param locales
     * @return
     * gawen.chen
     * 2015年4月3日上午11:50:50
     * 描述：角色资源配置
     */
    @RequestMapping(value="/srole_saveAuthorityRole.do",method=RequestMethod.POST)
	public @ResponseBody ControllerJsonMessage authorityRole(Model model,@RequestParam("roleId") String roleId 
			,@RequestParam("resourceIds") String resourceIds,@RequestHeader("Accept-Language") String locales){
		
    	ControllerJsonMessage msg = new  ControllerJsonMessage();
    	SRoleForm roleForm = roleService.findById(roleId);
    	if(roleForm!=null){
    		model.addAttribute("roleForm", roleForm);
    	}else{
    		return msg;
    	}
        
    	//权限保存
    	if(roleService.saveAuthorityRole(roleId,resourceIds)){
    		msg.setResult(true);
    		msg.setWarnMsg(getBundleMessage("srole.authority.success",null,locales));
    	}else{
    		msg.setWarnMsg(getBundleMessage("srole.authority.fail",null,locales));
    	}
    	
    	
		return msg;
	}
    
    

	
}