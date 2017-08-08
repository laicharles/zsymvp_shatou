

package com.wisewater.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.system.service.SResourceService;
import com.wisewater.util.tools.ControllerJsonMessage;
import com.wisewater.util.tools.ZtreeForm;

@Controller
@RequestMapping("/cus")
public class SResourceController extends BaseController {

	@Autowired
	private SResourceService resourcesService;
	
	
	/**
	 * 
	 * @param model
	 * @return
	 * gawen.chen
	 * 2015年3月27日下午2:46:58
	 * 描述：资源列表数据，树状列表显示所有数据，无须分页
	 */
	@RequestMapping("/sresource_list.do")
	public String findResources(Model model){
		
		return "cus/system/sresource_list";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * gawen.chen
	 * 2015年3月27日下午2:47:38
	 * 描述：查询所有资源数据
	 */
	@RequestMapping(value="/sresource_list_json.do",method=RequestMethod.GET)
	public @ResponseBody List<SResourceJqgridForm> findRootResource(Model model){
		 return resourcesService.findAllResources();
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * gawen.chen
	 * 2015年3月27日下午2:47:58
	 * 描述：查询所有菜单，非操作数据
	 */
	@RequestMapping(value="/sresource_menu_json.do",method=RequestMethod.GET)
	public @ResponseBody List<ZtreeForm> findAllMenus(Model model){
		 return resourcesService.findAllMenus();
	}
	
	
	
	/**
	 * 
	 * @param model
	 * @param typeCode
	 * @return
	 * gawen.chen
	 * 2015年3月27日下午2:48:18
	 * 描述：跳转至新增页面
	 */
	@RequestMapping("/sresource_toAdd.do")
	public String toAddPage(Model model,@RequestParam("typeCode")String typeCode){
		
		//查询所有菜单资源类型
		/*List<SDictionaryForm> dictionaryForms = dictionaryService.findByTypeCode(typeCode);
		model.addAttribute("resTypeList", dictionaryForms);*/
		
		return "cus/system/sresource_add";
	}
	
	
	/**
	 * 
	 * @param model
	 * @param resourceForm
	 * @param result
	 * @return
	 * gawen.chen
	 * 2015年3月27日下午2:48:32
	 * 描述：保存资源数据
	 */
	@RequestMapping("/sresource_add.do")
	public @ResponseBody ControllerJsonMessage saveResource(Model model,SResourceForm resourceForm,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		//非空验证
		if(StringUtils.isEmpty(resourceForm.getResName())){
			msg.setWarnMsg(getBundleMessage("s_resources.resName.null",null,locales));
			return msg;
		}
		if(StringUtils.isEmpty(resourceForm.getReqUrl())){
			msg.setWarnMsg(getBundleMessage("s_resources.reqUrl.null",null,locales));
			return msg;
		}
		if(StringUtils.isEmpty(resourceForm.getAuthUrl())){
			msg.setWarnMsg(getBundleMessage("s_resources.authUrl.null",null,locales));
			return msg;
		}
		if(resourceForm.getParentResource()==null){
			msg.setWarnMsg(getBundleMessage("s_resources.parentResID.null",null,locales));
			return msg;
		}
		
		//保存并返回
		if(resourcesService.saveResource(resourceForm)){
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("save.success",null,locales));
			return msg;
		}else{
			msg.setWarnMsg(getBundleMessage("save.fail",null,locales));
			return msg;
		}
	}
	
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @return
	 * gawen.chen
	 * 2015年3月27日下午2:48:56
	 * 描述：编辑资源数据
	 */
	@RequestMapping("/sresource_toUpdate.do")
	public String toUpdateResource(Model model,@RequestParam("id") String id){
		
		SResourceForm resourceForm = resourcesService.findById(id);
		if(resourceForm!=null){
			model.addAttribute("resourceForm", resourceForm);
			return "cus/system/sresource_update";
		}else{
			return "redirect:/cus/sresource_list.do";
		}
        
	}
	
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @return
	 * gawen.chen
	 * 2015年3月27日下午2:48:56
	 * 描述：编辑资源数据
	 */
	@RequestMapping("/sresource_toView.do")
	public String toViewResource(Model model,@RequestParam("id") String id){
		
		SResourceForm resourceForm = resourcesService.findById(id);
		if(resourceForm!=null){
			model.addAttribute("resourceForm", resourceForm);
			return "cus/system/sresource_view";
		}else{
			return "redirect:/cus/sresource_list.do";
		}
        
	}
	
	
	
	/**
	 * 
	 * @param model
	 * @param resourceForm
	 * @param result
	 * @return
	 * gawen.chen
	 * 2015年3月27日下午2:49:14
	 * 描述：编辑资源数据
	 */
	@RequestMapping("/sresource_update.do")
	public @ResponseBody ControllerJsonMessage updateResource(Model model,SResourceForm resourceForm
			,@RequestHeader("Accept-Language") String locales){
		
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		//非空验证
		if(StringUtils.isEmpty(resourceForm.getResName())){
			msg.setWarnMsg(getBundleMessage("s_resources.resName.null",null,locales));
			return msg;
		}
		if(StringUtils.isEmpty(resourceForm.getReqUrl())){
			msg.setWarnMsg(getBundleMessage("s_resources.reqUrl.null",null,locales));
			return msg;
		}
		if(StringUtils.isEmpty(resourceForm.getAuthUrl())){
			msg.setWarnMsg(getBundleMessage("s_resources.authUrl.null",null,locales));
			return msg;
		}
		if(resourceForm.getParentResource()==null){
			msg.setWarnMsg(getBundleMessage("s_resources.parentResID.null",null,locales));
			return msg;
		}
		
		//更新数据
		if(resourcesService.saveResource(resourceForm)){
			msg.setResult(true);
			msg.setWarnMsg(getBundleMessage("save.success",null,locales));
		}else{
			msg.setWarnMsg(getBundleMessage("save.fail",null,locales));
		}	
		
		return msg;
		
	}
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @return
	 * gawen.chen
	 * 2015年3月27日下午2:49:20
	 * 描述：删除资源数据
	 */
	@RequestMapping("/sresource_delete.do")
	public @ResponseBody ControllerJsonMessage  deleteResource(Model model,@RequestParam("id") String id
			,@RequestHeader("Accept-Language") String locales){
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		
		if(resourcesService.deleteResource(id)){
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
	 * @param id
	 * @param locales
	 * @return
	 * gawen.chen
	 * 2015年4月1日上午8:56:30
	 * 描述：角色ID检查记录
	 */
	@RequestMapping("/sresource_checkResourceId.do")
	public @ResponseBody ControllerJsonMessage checkResourceId(Model model,@RequestParam("id") String id,
			@RequestHeader("Accept-Language") String locales){
		
		ControllerJsonMessage msg = new  ControllerJsonMessage();
		if(id!=null&&id.trim().length()>0){
			//业务验证
			SResourceForm resourceForm =  resourcesService.findById(id);
	    	if(resourceForm==null){
	    		msg.setWarnMsg(getBundleMessage("record.not.exists",null,locales));
	    		return msg;
	    	}else {
				msg.setResult(true);
			}
		}
		return msg;
	}
	
	
	
	
}