package com.wisewater.leavewords.service;

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
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.service.CFeedBackTempService;
import com.wisewater.leavewords.controller.CLeaveWordsListForm;
import com.wisewater.leavewords.controller.CLeaveWordsManageForm;
import com.wisewater.leavewords.pojo.CLeaveWordsManage;
import com.wisewater.leavewords.repository.CLeaveWordsRepository;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.util.tools.SecurityMD5;
import com.wisewater.util.tools.WxJsUtil;
import com.wisewater.wechatpublic.util.StringUtil;

@Service
public class CLeaveWordsManageServiceImpl extends BaseService implements CLeaveWordsManageService{
	
	@Autowired
	private CLeaveWordsRepository cLeaveWordsRepository;
	
	@Autowired
	private SDictionaryRepository sDictionaryRepository;
	
	@Autowired
	private CFanUserService cFanUserService;
	
	@Autowired
	private CFeedBackTempService cFeedBackTempService;
	
	@Override
	public JqgridListForm findLeaveWordsByLeavewords(String token, String leavewords, int pageNo, int rows, String sidx,
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
		
		
		
		
		Page<CLeaveWordsManage> leavewordsPage = cLeaveWordsRepository.findLeaveWordsByLeavewords(leavewords, pager);

		List<CLeaveWordsListForm> leavewordsList = new ArrayList<CLeaveWordsListForm>();
		
		if(leavewordsPage!=null && leavewordsPage.getContent() !=null){
			CLeaveWordsManageForm form = null;
			CLeaveWordsListForm listForm = null;
			for(CLeaveWordsManage leaveword : leavewordsPage.getContent()){
				form = mapper.map(leaveword, CLeaveWordsManageForm.class);
				listForm = new CLeaveWordsListForm();
				//String createTime =form.getCreateTime().substring(0, form.getCreateTime().length()-2);
				//listForm.setCreateTime(createTime);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String createTime = sdf.format(form.getCreateTime());
				listForm.setCreateTime(createTime);
				
				listForm.setId(form.getId());
				listForm.setLeavewords(form.getLeavewords().getDicName());
				listForm.setIsReply(form.getIsReply().getDicName());
//				listForm.setCustomerType(form.getCustomerType().getDicName());
//				listForm.setFeedbackOption(form.getFeedbackOption().getDicName());
//				listForm.setFanUserAccount(form.getFanUser().getUserAccount());
//				listForm.setName(form.getFanUser().getUserName());
//				listForm.setTel(form.getFanUser().getMobile());
//				listForm.setAddress(form.getFanUser().getContactAddr());
				
				listForm.setNickName(form.getNickName());
				listForm.setCityName(form.getCityName());
				listForm.setOpenId(form.getOpenId());
				listForm.setToken(form.getToken());
				String replyTime = null;
				if(form.getReplyTime() != null){
//					replyTime =form.getReplyTime().substring(0, form.getReplyTime().length()-2);
//					replyTime =form.getReplyTime().split(".")[0];
					replyTime = sdf.format(form.getReplyTime());
				}
				//listForm.setReplyTime(replyTime);
				listForm.setReplyTime(replyTime);
				listForm.setReplyBy(form.getReplyBy());
				String deleteTime = null;
				if(form.getDeleteTime() != null){
					deleteTime = sdf.format(form.getDeleteTime()); 
				}
				listForm.setDeleteTime(deleteTime);
				leavewordsList.add(listForm);
			}
		}
		Page<CLeaveWordsListForm> pageList = new PageImpl<CLeaveWordsListForm>(leavewordsList,pager,leavewordsPage.getTotalElements());
		
		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList.getContent());
		jqgridListForm.setTotal(pageList.getTotalPages());
		jqgridListForm.setRecords(pageList.getTotalElements());
		return jqgridListForm;
	}

	@Override
	public CLeaveWordsManageForm findById(String id) {
		
		
		CLeaveWordsManage leaveword = cLeaveWordsRepository.findById(id);
		CLeaveWordsManageForm form = mapper.map(leaveword, CLeaveWordsManageForm.class);
		return form;
	}

	@Override
	public CLeaveWordsManage updateLeavewords(String id, String replyBy, String replyContent) {
		
		CLeaveWordsManage leaveword = cLeaveWordsRepository.findById(id);
		if(leaveword!=null){
			leaveword.setReplyBy(replyBy);
			leaveword.setReplyContent(replyContent);
			SDictionary dictionary = sDictionaryRepository.findByLogicID("isReply02");
			leaveword.setIsReply(dictionary);
			leaveword.setReplyTime(new Date());
			leaveword = cLeaveWordsRepository.save(leaveword);
		}
		return leaveword;
	}

	@Override
	public CLeaveWordsManage saveLeaveWords(String leavewords, String nickName, String cityName,
			 String content, String openId,String token,String accesstoken) {
		// TODO Auto-generated method stub
		CLeaveWordsManage leaveword = new CLeaveWordsManage();
	
		SDictionary sdLeavewords = sDictionaryRepository.findByLogicID(leavewords);
		SDictionary sdIsReply = sDictionaryRepository.findByLogicID("isReply01");
		leaveword.setLeavewords(sdLeavewords);
		leaveword.setNickName(nickName);
		leaveword.setCityName(cityName);
		leaveword.setContent(content);
		leaveword.setToken(token);
		leaveword.setCreateTime(new Date());
		leaveword.setIsDelete(0);
		leaveword.setIsReply(sdIsReply);
		leaveword.setOpenId(openId);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String ymd = sdf.format(new Date());
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		leaveword = cLeaveWordsRepository.save(leaveword);
		
		return leaveword;
	}
	
//	@Override
//	public CLeaveWordsManage saveLeaveWords(String leavewords, String name, String tel, String customerType,
//			String fanUserId, String content, String address, String openId,String token, String sMediaIdStr,String accesstoken) {
//		// TODO Auto-generated method stub
//		CLeaveWordsManage leaveword = new CLeaveWordsManage();
//	
//		SDictionary sdLeavewords = sDictionaryRepository.findByLogicID(leavewords);
//		SDictionary sdCustomerType = sDictionaryRepository.findByLogicID(customerType);
////		SDictionary sdFeedbackOption = sDictionaryRepository.findByLogicID(feedbackOption);
//		SDictionary sdIsReply = sDictionaryRepository.findByLogicID("isReply01");
//		CFanUserForm fanUserForm = cFanUserService.findById(fanUserId);
//		CFanUser fanUser = mapper.map(fanUserForm, CFanUser.class);
//		leaveword.setLeavewords(sdLeavewords);
//		leaveword.setName(name);
//		leaveword.setTel(tel);
//		leaveword.setCustomerType(sdCustomerType);
//		leaveword.setFanUser(fanUser);
////		leaveword.setFeedbackOption(sdFeedbackOption);
//		leaveword.setContent(content);
//		leaveword.setAddress(address);		
//		leaveword.setToken(token);
//		leaveword.setCreateTime(new Date());
//		leaveword.setIsDelete(0);
//		leaveword.setIsReply(sdIsReply);
//		leaveword.setOpenId(openId);
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
//		String ymd = sdf.format(new Date());
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		//修改保存路径为 leaveword
////		String savePath = request.getSession().getServletContext()
////				.getRealPath("\\")
////				+ "resources\\attached\\"+token+"\\feedback\\";
//		String savePath = request.getSession().getServletContext()
//				.getRealPath("\\")
//				+ "resources\\attached\\"+token+"\\leaveword\\";
//	
//		String picName = "";
//		if (sMediaIdStr != null && !"".equals(sMediaIdStr)) {
//			for (String str : sMediaIdStr.split(",")) {
//				// 时间_openId_6位随机数
//				String fileName = ymd + "_" + openId + "_" + SecurityMD5.getUUID(6) + ".jpg";
//				try {
//					WxJsUtil.saveImageToDisk(str, savePath, fileName, accesstoken);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				picName = picName + fileName + ",";
//			}
//			picName = picName.substring(0, picName.length() - 1);
//		}
//		leaveword.setImg(picName);
//		leaveword = cLeaveWordsRepository.save(leaveword);
//		
//		return leaveword;
//	}

	@Override
	public List<CLeaveWordsListForm> showLeavewordsList(String token, String openId, int page, int rows) {

		Pageable pager =  new PageRequest(page-1, rows,Direction.DESC,"createTime");
		Page<CLeaveWordsManage> leavewordPage = cLeaveWordsRepository.showLeavewordsList(openId, pager);
		List<CLeaveWordsListForm> leavewordList = new ArrayList<CLeaveWordsListForm>();
		for(CLeaveWordsManage leaveword : leavewordPage.getContent()){
			CLeaveWordsManageForm form = null;
			CLeaveWordsListForm listForm = null;
			form = mapper.map(leaveword, CLeaveWordsManageForm.class);
			listForm = new CLeaveWordsListForm();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime = sdf.format(form.getCreateTime());
			listForm.setCreateTime(createTime);
			
			listForm.setId(form.getId());
			listForm.setLeavewords(form.getLeavewords().getDicName());
			listForm.setIsReply(form.getIsReply().getDicName());
			leavewordList.add(listForm);
		}
		
		return leavewordList;
	}

	@Override
	public Long findAllCount(String openId) {
		
		return cLeaveWordsRepository.findAllCount(openId);
	}

	@Override
	public List<String> showPicList(String id) {
		// TODO Auto-generated method stub
		CLeaveWordsManage leaveword = cLeaveWordsRepository.findById(id);
		List<String> imgStrList = new ArrayList<String>();
		if(leaveword!=null){
			String imgList = leaveword.getImg();
			for(String str : imgList.split(",")){
				imgStrList.add(str);
			}
		}
		
		return imgStrList;
	}

	@Override
	public String deleteLeavewordsbatch(String idStr) {
		String notice = "";
		for (String id : StringUtil.strToList(idStr, ";")) {
			CLeaveWordsManage leaveword = new CLeaveWordsManage();
			leaveword = mapper.map(findById(id),CLeaveWordsManage.class);
			leaveword.setIsDelete(1);
			CLeaveWordsManage leavewordDel = cLeaveWordsRepository.save(leaveword);
			if(leavewordDel==null){
				notice = notice + "," + leavewordDel.getOpenId();
			}
		}
		return notice;
	}

	
	
	
	
	
}
