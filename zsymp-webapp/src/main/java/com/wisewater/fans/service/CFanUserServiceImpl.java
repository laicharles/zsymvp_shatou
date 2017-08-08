package com.wisewater.fans.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.fans.controller.CFanForm;
import com.wisewater.fans.controller.CFanUserForm;
import com.wisewater.fans.pojo.CFan;
import com.wisewater.fans.pojo.CFanUser;
import com.wisewater.fans.repository.CFanRepository;
import com.wisewater.fans.repository.CFanUserRepository;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.wechatpublic.util.StringUtil;

@Service
public class CFanUserServiceImpl extends BaseService implements CFanUserService {

	@Autowired
	private CFanUserRepository cfanuserRepository;

	@Autowired
	private CFanRepository fanRepository;

	@Override
	public CFanUserForm findById(String id) {
		if (StringUtils.isEmpty(id))
			return null;
		CFanUser fanuser = cfanuserRepository.findById(id);
		if (fanuser == null)
			return null;
		CFanUserForm fanUserForm = mapper.map(fanuser, CFanUserForm.class);
		CFanForm fan = mapper.map(fanuser.getFan(), CFanForm.class);
		fanUserForm.setFan(fan);
		return fanUserForm;
	}

	@Override
	public List<CFanUser> findByOpenId(String openId) {
		if (StringUtils.isEmpty(openId))
			return null;
		List<CFanUser> fanList = cfanuserRepository.findByOpenId(openId);
		if (fanList == null)
			return null;
		return fanList;
	}
	
	@Override
	public List<CFanUserForm> findByOpenIDAndToken(String openID, String token) {
		List<CFanUserForm> fanUserForms = null;
		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(openID)) {
			return fanUserForms;
		}
		// 组合查询方法
		List<CFanUser> fanUsers = cfanuserRepository.findByTokenAndOpenID(token, openID);
		if (fanUsers != null && fanUsers.size() > 0) {
			fanUserForms = new ArrayList<CFanUserForm>();
			for (CFanUser cFanUser : fanUsers) {
				CFanUserForm fanUserForm = mapper.map(cFanUser, CFanUserForm.class);
				CFanForm fan = mapper.map(cFanUser.getFan(), CFanForm.class);
				fanUserForm.setFan(fan);
				fanUserForms.add(fanUserForm);
			}
		}
		return fanUserForms;
	}

	@Override
	public JqgridListForm findAll(int pageNo, String sidx, String sord, String userAccount, String token) {
		if (StringUtils.isEmpty(userAccount)) {
			userAccount = "";
		}
		int pageSize = loadConstant.getPageSize();
		if (pageNo < 1) {
			pageNo = 1;
		}
		// 默认不排序
		PageRequest page = new PageRequest(pageNo - 1, pageSize);
		// 设置排序方式
		if (sidx != null && sidx.length() > 0) {
			if (sord != null && sord.equalsIgnoreCase("desc")) {
				page = new PageRequest(pageNo - 1, pageSize, Direction.DESC, sidx);
			} else {
				page = new PageRequest(pageNo - 1, pageSize, Direction.ASC, sidx);
			}
		}
		Page<CFanUser> textMtrlPage = cfanuserRepository.findAll(page, "%" + userAccount + "%", token);
		List<CFanUserForm> pageList = new ArrayList<CFanUserForm>();
		if (textMtrlPage != null && textMtrlPage.getContent() != null) {
			for (CFanUser txtMtrl : textMtrlPage.getContent()) {
				pageList.add(mapper.map(txtMtrl, CFanUserForm.class, "fanUserQuery"));
			}
		}
		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList);
		jqgridListForm.setTotal(textMtrlPage.getTotalPages());
		jqgridListForm.setRecords(textMtrlPage.getTotalElements());
		return jqgridListForm;
	}

	@Override                              //绑定用户的实际方法
	public boolean saveBindCfanUser(String userAccount, String userName, String contactAddr, String mobile,
			String token, String openID, String remarks) {
		CFan fan = fanRepository.findByOpenID(openID);//需要对绑定的openid进行查询    进行检验
		if (fan == null) {
			return false;
		}
		CFanUser fanUser = new CFanUser();//设置绑定用户的相关信息
		fanUser.setUserAccount(userAccount);
		fanUser.setUserName(userName);
		fanUser.setContactAddr(contactAddr);
		fanUser.setMobile(mobile);
		fanUser.setToken(token);
		fanUser.setFan(fan);
		fanUser.setRemarks(remarks);
		fanUser.setBindDateTime(new Date());
		fanUser.setOpenId(openID);
		if (cfanuserRepository.saveAndFlush(fanUser) != null) {//效率比save较高   存储并且刷新
			return true;
		}
		return false;
	}
	
	/**
	 * 批量删除绑定 
	 * @param idStr
	 * @return
	 */
	@Override
	public boolean deleteFanUserbatch(String idStr) {
		boolean result = false;
		if(StringUtil.isEmpty(idStr)){
			return result;
		}
		for (String id : StringUtil.strToList(idStr, ";")) {
			CFanUser user = new CFanUser();
			user = mapper.map(cfanuserRepository.findById(id), CFanUser.class);
			user.setIsLogicDel(1);
			CFanUser fanuserDel = cfanuserRepository.save(user);
			if (fanuserDel != null) {
				result = true;
			}
		}
		return result;
	}
	

	/**
	 * 
	 * 描述：删除绑定
	 * 
	 * @author AlexFung 2016年8月18日 下午1:54:51
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id) {
		if (id == null || "".equalsIgnoreCase(id)) {
			return false;
		}
		CFanUser user = cfanuserRepository.findById(id);
		if (user == null) {
			return false;
		} else {
			user.setIsLogicDel(1);
			user = cfanuserRepository.save(user);
			if (user != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * 描述：根据openID查找绑定户数
	 * 
	 * @author AlexFung 2016年8月19日 上午11:10:29
	 * @param openID
	 * @return
	 */
	@Override
	public int countBindUserByOpenID(String openID) {
		return cfanuserRepository.countBindUserByOpenID(openID);
	}

	/**
	 * 
	 * 描述：根据openID和户号查找绑定户数
	 * 
	 * @author AlexFung 2016年8月19日 上午11:11:19
	 * @param userAccount
	 * @return
	 */
	@Override
	public int countBindUserByOpenIDNUserAccount(String openID, String userAccount) {
		return cfanuserRepository.countBindUserByOpenIDNUserAccount(openID, userAccount);
	}

	@Override
	public boolean reSaveBindCfanUser(CFanUserForm cFanUserForm) {
		CFanUser cfan = cfanuserRepository.getOne(cFanUserForm.getId());
		if(cfan != null){
			/*if(cFanUserForm.getMobile() != null){
				cfan.setMobile(cFanUserForm.getMobile());
			}
			if(cFanUserForm.getContactAddr() != null){
				cfan.setContactAddr(cFanUserForm.getContactAddr());
			}*/
			cfan.setRemarks(cFanUserForm.getRemarks());
			
			if(cfanuserRepository.save(cfan)!= null){
				return true;
			}
		}
		return false;
	}

}