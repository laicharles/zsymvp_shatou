package com.wisewater.function.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.base.BaseController;
import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.service.CNoticeTempService;
import com.wisewater.review.pojo.SReviewPermissions;
import com.wisewater.review.service.SReviewPermissionsService;
import com.wisewater.util.tools.ControllerJsonMessage;

/**
 * 
 * 描述：公告通知模板
 * @author AlexFung
 * 2016年7月26日 下午3:09:56
 */
@Controller
public class CNoticeTempController extends BaseController{

	@Autowired
	private CNoticeTempService noticeTempService;
	
	@Autowired
	private SReviewPermissionsService sReviewPermissionsService;
	/**
     * 
     * @param model
     * @param pageNo 页码，默认查询第1页
     * @return
     * 打开列表页面
     */
    @RequestMapping("/cus/noticeTemp_list.do")
    public String findInPage(Model model, @RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if (!isNum.matches()) {
            pageNo = "1";
        }
        
        BCustomerUserForm user = getCusSessionUser();
		SReviewPermissions reviewPermissions = sReviewPermissionsService.findReviewById(user.getCurrentPermissions());
       
		model.addAttribute("page", pageNo);
		model.addAttribute("level", reviewPermissions.getLevel());
        return "cus/function/noticeTemp_list";
    }
	
	
    /**
     * 
     * @param page 查询页码
     * @param sidx 排序字段
     * @param sord 排序方式 asc 或desc
     * @return
     *         描述：分页查询角色列表
     */
    @RequestMapping("/cus/noticeTemp_list_json.do")
    public @ResponseBody JqgridListForm findNoticeTempJson(Model model, @RequestParam("page") String page,
            @RequestParam(value = "sidx", required = false) String sidx,
            @RequestParam(value = "sord", required = false) String sord) {

        int pageNo = 1;
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(page);
        if (isNum.matches())
        {
            pageNo = Integer.parseInt(page);
        }

        JqgridListForm jqgridListForm = noticeTempService.findAll(pageNo, sidx, sord, getCusTokenInSession().getToken());

        return jqgridListForm;
    }
    
    
    /**
     * 
     * @param model
     * @return
     *         描述：跳转新增页面
     */
    @RequestMapping("/cus/noticeTemp_toAdd.do")
    public String toAddNoticeTemp(Model model) {

        return "cus/function/noticeTemp_add";
    }
    
    
    /**
     * 
     * @param model
     * @param roleForm
     * @param result
     * @return
     *         描述：新增操作
     */
    @RequestMapping("/cus/noticeTemp_add.do")
    public @ResponseBody ControllerJsonMessage saveNoticeTemp(Model model, CNoticeTempForm noticeTempForm,
            @RequestHeader("Accept-Language") String locales) {

        ControllerJsonMessage msg = new ControllerJsonMessage();
        
        noticeTempForm.setToken(getCusTokenInSession().getToken());

        // 保存操作
        if (noticeTempService.save(noticeTempForm)!=null) {
            msg.setResult(true);
            msg.setWarnMsg(getBundleMessage("save.success", null, locales));
        }
        else {
            msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
        }
        return msg;
    }
    
    
    /**
     * 
     * @param model
     * @param id
     * @param pageNo
     * @return
     *         描述：跳转至编辑页面
     */
    @RequestMapping("/cus/noticeTemp_toUpdate.do")
    public String toUpdateNoticeTemp(Model model, @RequestParam("id") String id,
            @RequestParam(value = "page", defaultValue = "1") String pageNo) {

        CNoticeTempForm noticeTempForm = noticeTempService.findById(id);
        if (noticeTempForm != null) {
            model.addAttribute("noticeTempForm", noticeTempForm);
        }
        else {
            return "redirect:/cus/noticeTemp_list.do?pageNo=1";
        }

        // 验证数字
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(pageNo);
        if (!isNum.matches()) {
            pageNo = "1";
        }

        // 保存页码
        model.addAttribute("page", pageNo);

        return "cus/function/noticeTemp_update";
    }
    
    
    /**
     * 
     * @param model
     * @param roleForm
     * @param result
     * @param pageNo
     * @return
     *         描述：更新
     */
    @RequestMapping("/cus/noticeTemp_update.do")
    public @ResponseBody ControllerJsonMessage updateNoticeTemp(Model model, CNoticeTempForm noticeTempForm,
            @RequestHeader("Accept-Language") String locales) {

        ControllerJsonMessage msg = new ControllerJsonMessage();

        // 保存操作
        if (noticeTempService.update(noticeTempForm)) {
            msg.setResult(true);
            msg.setWarnMsg(getBundleMessage("save.success", null, locales));
        }
        else {
            msg.setWarnMsg(getBundleMessage("save.fail", null, locales));
        }
        return msg;

    }
    
    /**
     * 
     * @param model
     * @param id
     * @param pageNo
     * @return
     *         描述：删除操作
     */
    @RequestMapping("/cus/noticeTemp_delete.do")
    public @ResponseBody ControllerJsonMessage deleteNoticeTemp(Model model, @RequestParam("ids") String ids
            , @RequestHeader("Accept-Language") String locales) {
        ControllerJsonMessage msg = new ControllerJsonMessage();
        if (noticeTempService.deleteByIds(ids)) {
            msg.setResult(true);
            msg.setWarnMsg(getBundleMessage("delete.success", null, locales));
        }
        else {
            msg.setWarnMsg(getBundleMessage("delete.fail", null, locales));
        }
        return msg;
    }
    
    /**
     * 发送预览
     * @param model
     * @param OpenID
     * @param id
     * @param locales
     * @return
     * XingXingLvCha
     * 2015年9月6日 上午11:26:11
     */
    @RequestMapping("/cus/noticeTemp_preview.do")
    public  @ResponseBody ControllerJsonMessage preview(Model model,
    		@RequestParam(value="id") String id, @RequestHeader("Accept-Language") String locales){
    	
    	ControllerJsonMessage msg = new ControllerJsonMessage();
    	BCustomerUserForm user = getCusSessionUser();
    	if (noticeTempService.previewNoticeTempById(id, user,"",user.getBinOpendId(), getCusTokenInSession().getToken())) {
            msg.setResult(true);
            msg.setWarnMsg(getBundleMessage("send.success", null, locales));
        }
        else {
            msg.setWarnMsg(getBundleMessage("send.fail", null, locales));
        }
        return msg;
    }
    
    /**
     * 
     * @param model
     * @param id
     * @param pageNo
     * @return
     *         描述：发送
     */
    @RequestMapping("/cus/noticeTemp_send.do")
    public @ResponseBody ControllerJsonMessage sendNoticeTemp(Model model, @RequestParam("id") String id
            , @RequestHeader("Accept-Language") String locales) {
        ControllerJsonMessage msg = new ControllerJsonMessage();
        if (noticeTempService.sendNoticeTempById(id, getCusTokenInSession().getToken())) {
            msg.setResult(true);
            msg.setWarnMsg(getBundleMessage("send.success", null, locales));
        }
        else {
            msg.setWarnMsg(getBundleMessage("send.fail", null, locales));
        }
        return msg;
    }
    
    /**
     * 
     * @param model
     * @param id
     * @param pageNo
     * @return
     *         描述：发送给所有绑定用户
     */
    @RequestMapping("/cus/noticeTemp_send_bind.do")
    public @ResponseBody ControllerJsonMessage sendNoticeTempForBindUser(Model model, @RequestParam("id") String id
            , @RequestHeader("Accept-Language") String locales) {
        ControllerJsonMessage msg = new ControllerJsonMessage();
        if (noticeTempService.sendNoticeTempByIdForBindUser(id, getCusTokenInSession().getToken())) {
            msg.setResult(true);
            msg.setWarnMsg(getBundleMessage("send.success", null, locales));
        }
        else {
            msg.setWarnMsg(getBundleMessage("send.fail", null, locales));
        }
        return msg;
    }
}
