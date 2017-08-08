package com.wisewater.fans.service;

import java.util.List;

import com.wisewater.fans.controller.CFanForm;
import com.wisewater.fans.pojo.CFan;
import com.wisewater.form.utils.JqgridListForm;

 
public interface CFanService {

	/**
	 * 
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @param nickName
	 * @param bindStatus
	 * @param token
	 * @return
	 * gawen.chen
	 * 2015年4月10日下午7:05:57
	 * 描述：查询所有
	 */
	JqgridListForm findAll(int pageNo, String sidx, String sord,
			String nickName,  String hasBind,String token);
	
	/**
	 * 
	 * @param id
	 * @return
	 * gawen.chen
	 * 2015年4月10日下午7:06:01
	 * 描述：根据ID进行查询
	 */
	CFanForm findById(String id);
	
	/**
	 * 
	 * @param nickName
	 * @return
	 * gawen.chen
	 * 2015年4月10日下午7:06:01
	 * 描述：根据ID进行查询
	 */
	List<CFan> findByNickName(String nickName);
	
	/**
	 * 
	 * @param openID
	 * @return
	 * gawen.chen
	 * 2015年4月10日下午7:06:01
	 * 描述：根据openID进行查询
	 */
	CFanForm findByOpenID(String openID);
	
	/**
	 * 
	 * @param token
	 * @return
	 * gawen.chen
	 * 2015年4月10日下午7:06:58
	 * 描述：同步某一token下的所有粉丝
	 */
	boolean saveSynAllFans(String token);
	
	
	/**
	 * 
	 * @param openID
	 * @param token
	 * @return
	 * gawen.chen
	 * 2015年4月21日上午9:11:14
	 * 描述：根据openID和token查询
	 */
	CFanForm findByOpenIDAndToken(String openID,String token);
	
	/**
     * @param fan
     * @param isLogicDel
     */
    public void saveFanUserLogicDel(CFan fan, int isLogicDel) ;
}