package com.wisewater.feedback.service;

import java.util.List;

import com.wisewater.feedback.controller.CFeedBackListForm;
import com.wisewater.feedback.controller.CFeedBackManageForm;
import com.wisewater.feedback.pojo.CFeedBackManage;
import com.wisewater.form.utils.JqgridListForm;

public interface CFeedBackManageService {
	public JqgridListForm findFeedBackByFeedbackType(String token,String feedbackType, int pageNo,int rows,String sidx,String sord);
	
	public CFeedBackManageForm findById(String id);
	
	public CFeedBackManage updateFeedback(String id,String replyBy,String replyContent);
	
	public CFeedBackManage saveFeedBack(String feedbackType,String name,String tel,String customerType,String fanUserId,String feedbackOption,
			String content,String address,String openId,String token,String sMediaIdStr,String accesstoken);
	
	public CFeedBackManage saveFeedBacks(String feedbackType,String title,String content,String peopleName,String peopelAddr,String token,String openId,String peopleTel,String sMediaIdStr,String accesstoken);
	
	public List<CFeedBackListForm> showFeedbackList(String feedbackType,String token,String openId,int page,int rows);
	
	public Long findAllCount(String openId);
	
	public List<String> showPicList(String id);
	
	public String deleteFeedbackbatch(String idStr);
}
