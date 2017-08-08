package com.wisewater.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.wisewater.base.BaseController;
import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.system.controller.SResourceForm;
import com.wisewater.system.controller.SUserForm;
import com.wisewater.system.pojo.SysNoticeBean;
import com.wisewater.system.service.SResourceService;
import com.wisewater.system.service.SUserService;
import com.wisewater.system.service.SysNoticeService;

import org.apache.log4j.Logger;

@Controller
@SessionAttributes({"user","menus"})
public class LoginController extends BaseController {

	@Autowired
	private SUserService userService;
	
	@Autowired
	private SResourceService resourceService;
	
	@Autowired
	private SysNoticeService sysNoticeService;
	
	Logger logger = Logger.getLogger(LoginController.class);
	
	
	/**
	 * 系统默认进入客户管理登录请求
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/","/cus"}, method = RequestMethod.GET)
	public String welcome(Model model){
		return "redirect:/cus/welcome.do";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * gawen.chen
	 * 2015年3月24日上午10:17:46
	 * 描述：跳转至登录页面
	 */
	@RequestMapping(value = { "/cus/welcome.do"}, method = RequestMethod.GET)
	public String cusLogin(Model model){
		return "cus/login";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * gawen.chen
	 * 2015年3月24日上午10:18:22
	 * 描述：验证请求成功后，跳转至客户管理后台页面
	 */
	@RequestMapping(value="/cus/main.do")
	public String cusMain(Model model){
		BCustomerUserForm user = getCusSessionUser();
		List<SResourceForm> menus = getCusSessionResource();
		System.out.println(getCusTokenInSession());
		System.out.println(getCusTokenInSession().getToken());
		SResourceForm menuFrom = resourceService.findAuthMenu(menus,"CUS_001",getCusTokenInSession().getToken());  //查询运营后台
		logger.info("用户"+user.getLoginName()+"登陆客户后台！");
		List<SysNoticeBean> noticeList = sysNoticeService.findNotices(getCusTokenInSession().getToken());
		if(user!=null){
			model.addAttribute("user", user);
			model.addAttribute("menus", menuFrom);
			model.addAttribute("noticeList",noticeList);
			return "cus/main";
		}else return "redirect:/cus/welcome.do";
	}
	
	/**
	 * 
	 * @param error
	 * @param logout
	 * @return
	 * gawen.chen
	 * 2015年3月24日上午10:18:47
	 * 描述：客户管理后台退出页面，跳转回登录页面
	 */
	@RequestMapping(value="/cus/logOut.do")
	public ModelAndView cusLogOut(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout){
		ModelAndView model = new ModelAndView();
		if (error != null) {
			logger.info("用户名密码不可用!");
			model.addObject("error", "用户名密码不可用!");
		}

		if (logout != null) {
			logger.info("成功退出系统！");
			model.addObject("msg", "成功退出系统！");
		}
		model.setViewName("cus/login");

		return model;
	}
	
	
	
	/**
	 * 
	 * @param model
	 * @return
	 * gawen.chen
	 * 2015年3月24日上午10:19:19
	 * 描述：运营后台登录页面请求
	 */
	@RequestMapping(value = { "/biz"}, method = RequestMethod.GET)
	public String bussines(Model model){
		return "redirect:/biz/welcome.do";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * gawen.chen
	 * 2015年3月24日上午10:19:43
	 * 描述：
	 */
	@RequestMapping(value = { "/biz/welcome.do"}, method = RequestMethod.GET)
	public String bizLogin(Model model){
		return "biz/login";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * gawen.chen
	 * 2015年3月24日上午10:19:47
	 * 描述：登录成功后的主页面
	 */
	@RequestMapping(value="/biz/main.do")
	public String bizMain(Model model){
		
		SUserForm user = getBizSessionUser();
		List<SResourceForm> menus = getBizSessionResource();
		SResourceForm menuFrom = resourceService.findAuthMenu(menus,"BIZ_001",getBizTokenInSession());  //查询运营后台
		if(user!=null){
			model.addAttribute("user", user);
			model.addAttribute("menus", menuFrom);
			return "biz/main";
		}else return "redirect:/biz/welcome.do";
	}
	
	/**
	 * 
	 * @param error
	 * @param logout
	 * @return
	 * gawen.chen
	 * 2015年3月24日上午10:20:01
	 * 描述：运营后台登录退出登录页面
	 */
	@RequestMapping(value="/biz/logOut.do")
	public ModelAndView logOut(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout){
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "用户名密码不可用!");
		}

		if (logout != null) {
			model.addObject("msg", "成功退出系统！");
		}
		model.setViewName("biz/login");

		return model;
	}
	
	/**
	 * 验证验证码是否正确，忽略大小写
	 * @param sCode
	 * @param request
	 * @return
	 * XingXingLvCha
	 * 2015年9月29日 上午11:43:34
	 */
	@RequestMapping(value="/checkCode.do")
	@ResponseBody
	public String checkCode(String sCode,HttpServletRequest request){
		if("".equals(sCode))
		{
			return "0";
		}
		HttpSession session = request.getSession();
		if(!sCode.toUpperCase().equals(session.getAttribute("checkCode").toString().toUpperCase()))
		{
			return "0";
		}
		
		return "1";
	}
	
}
