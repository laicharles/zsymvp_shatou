package com.wisewater.system.service;

import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.controller.SUserForm;

public interface SUserService {

	/**
	 * 
	 * @param pageNo  页码，默认第一页是是从1开始。
	 * @param sidx   排序字段
	 * @param sord    排序方式，desc或者asc
	 * @param loginName  登录名
	 * @param isDisabled  用户状态，启用或禁用
	 * @param mobile   手机号码
	 * @param userName  真实姓名 
	 * @return
	 * gawen.chen
	 * 2015年4月01日下午13:17:10
	 * 描述：分页查询所有记录
	 */
	JqgridListForm findAll(int pageNo,String sidx,String sord,String loginName,int isDisabled,String mobile,String userName);
	
	/**
	 * 
	 * @param userForm
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午3:26:35
	 * 描述：保存用户
	 */
	boolean saveUser(SUserForm userForm); 
	
	/**
	 * 
	 * @param userForm
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午3:26:43
	 * 描述：修改用户信息
	 */
	boolean updateUser(SUserForm userForm);
	
	/**
	 * 
	 * @param ids
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午3:26:55
	 * 描述：批量删除用户
	 */
	boolean deleteUsers(String ids);
	
	/**
	 * 
	 * @param ids
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午3:27:26
	 * 描述：批量禁用用户
	 */
	boolean disableUser(String ids);
	
	/**
	 * 
	 * @param ids
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午3:27:43
	 * 描述：批量启用用户
	 */
	boolean enableUser(String ids);
	
	/**
	 * 
	 * @param ids
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午3:27:54
	 * 描述：初始化密码
	 */
	boolean initPassword(String ids);
	
	/**
	 * 
	 * @param id
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午3:28:03
	 * 描述：根据ID查询用户
	 */
	SUserForm findById(String id);
	
	/**
	 * 
	 * @param email
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午4:58:50
	 * 描述：根据email查询
	 */
	SUserForm  findByEmail(String email);
	
	/**
	 * 
	 * @param loginName
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午4:59:00
	 * 描述：根据登录名进行查询
	 */
	SUserForm findByLoginName(String loginName);
	
	/**
	 * 
	 * @param mobile
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午4:59:04
	 * 描述：根据手机号码查询
	 */
	SUserForm findByMobile(String mobile);
	
	/**
	 * 
	 * @param userId
	 * @param roleIds
	 * @return
	 * gawen.chen
	 * 2015年4月3日上午11:31:44
	 * 描述：为用户配置角色
	 */
	boolean updateRoleUser(String userId,String roleIds);
	
	/**
	 * 
	 * @param userId
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午1:43:39
	 * 描述：根据用户检查其授权的角色
	 */
	JqgridListForm findUserAllRole(String userId);
}
