package com.wisewater.feedback.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wisewater.base.BaseService;
import com.wisewater.cms.controller.CCmsYywdForm;
import com.wisewater.cms.pojo.CCms;
import com.wisewater.fans.controller.CFanUserForm;
import com.wisewater.fans.pojo.CFanUser;
import com.wisewater.fans.service.CFanUserService;
import com.wisewater.feedback.controller.CFeedBackListForm;
import com.wisewater.feedback.controller.CFeedBackManageForm;
import com.wisewater.feedback.pojo.CFeedBackManage;
import com.wisewater.feedback.repository.CFeedBackRepository;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.service.CFeedBackTempService;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.util.tools.SecurityMD5;
import com.wisewater.util.tools.WxJsUtil;
import com.wisewater.wechatpublic.util.StringUtil;

@Service
public class CFeedBackManageServiceImpl extends BaseService implements CFeedBackManageService{
	
	@Autowired
	private CFeedBackRepository cFeedBackRepository;
	
	@Autowired
	private SDictionaryRepository sDictionaryRepository;
	
	@Autowired
	private CFanUserService cFanUserService;
	
	@Autowired
	private CFeedBackTempService cFeedBackTempService;
	
	@Autowired
	private LoadConstant loadConstant;
	
	@Override
	public JqgridListForm findFeedBackByFeedbackType(String token, String feedbackType, int pageNo, int rows, String sidx,
			String sord) {
		// TODO Auto-generated method stub
		
		int pageSize = loadConstant.getPageSize();
		
		Pageable pager = new PageRequest(pageNo - 1, pageSize);
		
		//设置排序方式
		if(sidx!=null&&sidx.length()>0){
			String colId = "";
			if("fanUser".equals(sidx)){
				colId = "fanUser.userAccount";
			}else{
				colId = sidx;
			}
			
    		if(sord!=null&&sord.equalsIgnoreCase("desc")){
    			pager = new PageRequest(pageNo-1, rows,Direction.DESC,colId);
    		}else{
    			pager = new PageRequest(pageNo-1, rows,Direction.ASC,colId);
    		}
    	}

		Page<CFeedBackManage> feedbackPage = cFeedBackRepository.findFeedBackByFeedbackType(feedbackType, pager);
		List<CFeedBackListForm> feedbackList = new ArrayList<CFeedBackListForm>();
		if(feedbackPage!=null && feedbackPage.getContent() !=null){
			CFeedBackManageForm form = null;
			CFeedBackListForm listForm = null;
			for(CFeedBackManage feedback : feedbackPage.getContent()){
				form = mapper.map(feedback, CFeedBackManageForm.class);     //CFeedBackManage  ->CFeedBackManageForm
				listForm = new CFeedBackListForm();
				//创建的时间 
				listForm.setCreateTime(form.getCreateTime());
				//ID值
				listForm.setId(form.getId());
				//反馈的类型
				listForm.setFeedbackType(form.getFeedbackType().getDicName());
				//反馈是否被答复
				listForm.setIsReply(form.getIsReply().getDicName());
				//设置人员的类型
				//listForm.setCustomerType(form.getCustomerType().getDicName());
				//设置答复的时间
				listForm.setReplyTime(form.getReplyTime());
				//设置回复人名字
				listForm.setReplyBy(form.getReplyBy());
				//设置反馈人的姓名
				listForm.setFanUserName(form.getName());
				//设置反馈人的联系电话
				listForm.setFanUserTel(form.getTel());
				//设置反馈的标题
				listForm.setTitle(form.getTitle());
				//设置反馈人的openId
				listForm.setOpenId(form.getOpenId());
				//设置反馈人地址
				listForm.setAddr(form.getAddress());
				feedbackList.add(listForm);
			}
		}
		Page<CFeedBackListForm> pageList = new PageImpl<CFeedBackListForm>(feedbackList,pager,feedbackPage.getTotalElements());
		
		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList.getContent());
		jqgridListForm.setTotal(pageList.getTotalPages());
		jqgridListForm.setRecords(pageList.getTotalElements());
		return jqgridListForm;
	}

	@Override
	public CFeedBackManageForm findById(String id) {
		
		
		CFeedBackManage feedback = cFeedBackRepository.findById(id);
		CFeedBackManageForm form = mapper.map(feedback, CFeedBackManageForm.class);
		return form;
	}

	@Override
	public CFeedBackManage updateFeedback(String id, String replyBy, String replyContent) {
		
		CFeedBackManage feedback = cFeedBackRepository.findById(id);
		if(feedback!=null){
			feedback.setReplyBy(replyBy);
			feedback.setReplyContent(replyContent);
			SDictionary dictionary = sDictionaryRepository.findByLogicID("isReply02");
			feedback.setIsReply(dictionary);
			feedback.setReplyTime(new Date());
			feedback = cFeedBackRepository.save(feedback);
		}
		return feedback;
	}

	@Override
	public CFeedBackManage saveFeedBack(String feedbackType, String name, String tel, String customerType,String fanUserId, String feedbackOption, 
			String content, String address, String openId,String token, String sMediaIdStr,String accesstoken) {
		// TODO Auto-generated method stub
		CFeedBackManage feedback = new CFeedBackManage();
	
		SDictionary sdFeedbackType = sDictionaryRepository.findByLogicID(feedbackType);
		SDictionary sdCustomerType = sDictionaryRepository.findByLogicID(customerType);
		SDictionary sdFeedbackOption = sDictionaryRepository.findByLogicID(feedbackOption);
		SDictionary sdIsReply = sDictionaryRepository.findByLogicID("isReply01");
		CFanUserForm fanUserForm = cFanUserService.findById(fanUserId);
		CFanUser fanUser = mapper.map(fanUserForm, CFanUser.class);
		feedback.setFeedbackType(sdFeedbackType);
		feedback.setName(name);
		feedback.setTel(tel);
		feedback.setCustomerType(sdCustomerType);
		feedback.setFanUser(fanUser);
		feedback.setFeedbackOption(sdFeedbackOption);
		feedback.setContent(content);
		feedback.setAddress(address);		
		feedback.setToken(token);
		feedback.setCreateTime(new Date());
		feedback.setIsDelete(0);
		feedback.setIsReply(sdIsReply);
		feedback.setOpenId(openId);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String ymd = sdf.format(new Date());
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String savePath = request.getSession().getServletContext()
				.getRealPath("\\")
				+ "resources\\attached\\"+token+"\\feedback\\";
	
		String picName = "";
		if (sMediaIdStr != null && !"".equals(sMediaIdStr)) {
			for (String str : sMediaIdStr.split(",")) {
				// 时间_openId_6位随机数
				String fileName = ymd + "_" + openId + "_" + SecurityMD5.getUUID(6) + ".jpg";
				try {
					WxJsUtil.saveImageToDisk(str, savePath, fileName, accesstoken);
				} catch (Exception e) {
					e.printStackTrace();
				}
				picName = picName + fileName + ",";
			}
			picName = picName.substring(0, picName.length() - 1);
		}
		feedback.setImg(picName);
		feedback = cFeedBackRepository.save(feedback);
		
		return feedback;
	}
	
	@Override
	public CFeedBackManage saveFeedBacks(String feedbackType,String title,String content,String peopleName,String peopelAddr,
			String token,String openId,String peopleTel,String sMediaIdStr,String accesstoken){
		CFeedBackManage feedback = new CFeedBackManage();
		SDictionary sdIsReply = sDictionaryRepository.findByLogicID("isReply01");
		SDictionary sdFeedbackType = sDictionaryRepository.findByLogicID(feedbackType);
		//SDictionary sdFeedbackOption = sDictionaryRepository.findByLogicID("feedbackOption02");
		feedback.setTitle(title);
		feedback.setContent(content);
		feedback.setName(peopleName);
		feedback.setAddress(peopelAddr);
		feedback.setToken(token);
		feedback.setOpenId(openId);
		feedback.setFeedbackType(sdFeedbackType);
		feedback.setCreateTime(new Date());
		feedback.setIsDelete(0);
		feedback.setIsReply(sdIsReply);
		feedback.setTel(peopleTel);
		//feedback.setFeedbackOption(sdFeedbackOption);
		//图片上传的代码
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String ymd = sdf.format(new Date());
		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String realPath = this.getClass().getResource("/").getFile().toString();
		realPath = realPath.substring(1,realPath.lastIndexOf("/WEB-INF/classes/"));
		String savePath = realPath+"/resources/attached/" + token + "/feedback/";

	
		String picName = "";
		if (sMediaIdStr != null && !"".equals(sMediaIdStr)) {
			for (String str : sMediaIdStr.split(",")) {
				// 时间_openId_6位随机数
				String fileName = ymd + "_" + openId + "_" + SecurityMD5.getUUID(6) + ".jpg";
				try {
					WxJsUtil.saveImageToDisk(str, savePath, fileName, accesstoken);
				} catch (Exception e) {
					e.printStackTrace();
				}
				picName = picName + fileName + ",";
			}
			picName = picName.substring(0, picName.length() - 1);
		}
		feedback.setImg(picName);
		feedback = cFeedBackRepository.save(feedback);
		return feedback;
	}
	@Override
	public List<CFeedBackListForm> showFeedbackList(String feedbackType,String token, String openId, int page, int rows) {
		Pageable pager =  new PageRequest(page-1, rows,Direction.DESC,"createTime");

		Page<CFeedBackManage> feedbackPage = cFeedBackRepository.showFeedbackList(openId,feedbackType,pager);
		
		List<CFeedBackListForm> feedbackList = new ArrayList<CFeedBackListForm>();
		for(CFeedBackManage feedback : feedbackPage.getContent()){
			CFeedBackManageForm form = null;
			CFeedBackListForm listForm = null;
			form = mapper.map(feedback, CFeedBackManageForm.class);
			listForm = new CFeedBackListForm();
			listForm.setCreateTime(form.getCreateTime());
			listForm.setId(form.getId());
			listForm.setFeedbackType(form.getFeedbackType().getDicName());
			listForm.setIsReply(form.getIsReply().getDicName());
			listForm.setToken(token);
			//listForm.setCustomerType(form.getCustomerType().getDicName());
			//listForm.setFeedbackOption(form.getFeedbackOption().getDicName());
			listForm.setTitle(form.getTitle());
			listForm.setFanUserName(form.getName());
			feedbackList.add(listForm);
		}
		return feedbackList;
	}

	@Override
	public Long findAllCount(String openId) {
		
		return cFeedBackRepository.findAllCount(openId);
	}

	@Override
	public List<String> showPicList(String id) {
		// TODO Auto-generated method stub
		CFeedBackManage feedback = cFeedBackRepository.findById(id);
		List<String> imgStrList = new ArrayList<String>();
		if (feedback != null) {
			String imgList = feedback.getImg();
			if (imgList != "" || imgList != null) {
				for (String str : imgList.split(",")) {
					imgStrList.add(str);
				}
			}
		}
		return imgStrList;
	}

	@Override
	public String deleteFeedbackbatch(String idStr) {
		String notice = "";
		for (String id : StringUtil.strToList(idStr, ";")) {
			CFeedBackManage feedback = new CFeedBackManage();
			feedback = mapper.map(findById(id),CFeedBackManage.class);
			feedback.setIsDelete(1);
			CFeedBackManage feedbackDel = cFeedBackRepository.save(feedback);
			if(feedbackDel==null){
				notice = notice + "," + feedbackDel.getFanUser().getUserAccount();
			}
		}
		return notice;
	}
	
	
}
