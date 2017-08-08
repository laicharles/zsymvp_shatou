package com.wisewater.fans.service;

import java.util.List;

import com.wisewater.fans.controller.CFanUserForm;
import com.wisewater.fans.pojo.CFanUser;
import com.wisewater.form.utils.JqgridListForm;

/**
 * @author Administrator
 * @date 2017年3月29日 下午2:44:27
 */
public interface CFanUserService {

	/**
	 * 
	 * @param id
	 * @return gawen.chen 2015年4月10日下午7:06:01 描述：根据ID进行查询
	 */
	CFanUserForm findById(String id);
	
	/**
	 * 
	 * @param id
	 * @return gawen.chen 2015年4月10日下午7:06:01 描述：根据ID进行查询
	 */
	List<CFanUser> findByOpenId(String openId);

	/**
	 * 
	 * @param openID
	 * @param token
	 * @return gawen.chen 2015年4月16日下午4:17:18 描述：根据openID查询粉丝记录
	 */
	List<CFanUserForm> findByOpenIDAndToken(String openID, String token);

	/**
	 * 
	 * @param fanUserForm
	 * @return gawen.chen 2015年4月21日上午9:21:03 描述：保存绑定用户信息
	 */
	boolean saveBindCfanUser(String userAccount, String userName, String contactAddr, String mobile, String token,
			String openID, String remarks);

	/**
	 * 
	 * 描述：根据id删除绑定
	 * 
	 * @author AlexFung 2016年8月19日 上午11:09:08
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id);
	
	/**
	 * 描述：根据id批量删除绑定
	 * @param idStr
	 * @return
	 */
	public boolean deleteFanUserbatch(String idStr);

	/**
	 * 
	 * 描述：查找所有绑定
	 * 
	 * @author AlexFung 2016年8月19日 上午11:09:46
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @param bindSatus
	 * @param userAccount
	 * @param token
	 * @return
	 */
	public JqgridListForm findAll(int pageNo, String sidx, String sord, String userAccount,
			String token);

	/**
	 * 
	 * 描述：根据openID查找绑定户数
	 * 
	 * @author AlexFung 2016年8月19日 上午11:10:29
	 * @param openID
	 * @return
	 */
	public int countBindUserByOpenID(String openID);

	/**
	 * 
	 * 描述：根据openID和户号查找绑定户数
	 * 
	 * @author AlexFung 2016年8月19日 上午11:11:19
	 * @param userAccount
	 * @return
	 */
	public int countBindUserByOpenIDNUserAccount(String openID, String userAccount);
	
	/**
	 * 描述：重新保存一次（上一次是绑定用户时）
	 * @param cFanUserForm
	 * @return
	 */
	public boolean reSaveBindCfanUser(CFanUserForm cFanUserForm);
}