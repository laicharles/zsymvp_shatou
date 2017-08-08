package com.wisewater.leavewords.service;

import java.util.List;

import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.leavewords.controller.CLeaveWordsListForm;
import com.wisewater.leavewords.controller.CLeaveWordsManageForm;
import com.wisewater.leavewords.pojo.CLeaveWordsManage;

public interface CLeaveWordsManageService {
	public JqgridListForm findLeaveWordsByLeavewords(String token,String leavewords, int pageNo,int rows,String sidx,String sord);
	
	public CLeaveWordsManageForm findById(String id);
	
	public CLeaveWordsManage updateLeavewords(String id,String replyBy,String replyContent);

	public CLeaveWordsManage saveLeaveWords(String leavewords,String nickName,String cityName,
			String content,String openId,String token,String accesstoken);
	
//	public CLeaveWordsManage saveLeaveWords(String leavewords,String name,String tel,String customerType,String fanUserId,
//			String content,String address,String openId,String token,String sMediaIdStr,String accesstoken);
	
	public List<CLeaveWordsListForm> showLeavewordsList(String token,String openId,int page,int rows);
	
	public Long findAllCount(String openId);
	
	public List<String> showPicList(String id);
	
	public String deleteLeavewordsbatch(String idStr);
}
