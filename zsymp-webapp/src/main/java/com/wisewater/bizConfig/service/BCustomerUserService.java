package com.wisewater.bizConfig.service;

import java.util.List;

import com.wisewater.bizConfig.controller.BCustomerUserForm;
import com.wisewater.bizConfig.pojo.BCustomerUser;
import com.wisewater.form.utils.JqgridListForm;

 
public interface BCustomerUserService {


	/**
	 * 
	 * @param id
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午3:28:03
	 * 描述：根据ID查询用户
	 */
	BCustomerUserForm findById(String id);
	
	/**
	 * 
	 * @param email
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午4:58:50
	 * 描述：根据email查询
	 */
	BCustomerUserForm  findByEmail(String email);
	
	/**
	 * 
	 * @param higherCustomerUserId
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午4:59:00
	 * 描述：根据上级的ID来查找上级这个对象
	 */
	BCustomerUser findByHigherCustomerUserId(String higherCustomerUserId);
	
	/**
	 * 
	 * @param loginName
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午4:59:00
	 * 描述：根据登录名进行查询
	 */
	BCustomerUserForm findByLoginName(String loginName);
	
	/**
	 * 
	 * @param mobile
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午4:59:04
	 * 描述：根据手机号码查询
	 */
	BCustomerUserForm findByMobile(String mobile);
	
	/**
	 * 
	 * @param customerUserForm
	 * @return
	 * gawen.chen
	 * 2015年4月3日下午4:33:30
	 * 描述：客户管理后台，新注册用户
	 */
	int saveRegisterCustomerUser(BCustomerUserForm customerUserForm);
	
	
	
	/**
	 * 
	 * @param customerUserForm
	 * @return
	 * gawen.chen
	 * 2015年4月7日下午3:23:54
	 * 描述：保存运营用户
	 */
	boolean saveCustomerUser(BCustomerUserForm customerUserForm);
	
	
	/**
	 * 
	 * @param customerUserForm
	 * @return
	 * gawen.chen
	 * 2015年4月7日下午3:50:54
	 * 描述：更新运营用户
	 */
	boolean updateCustomerUser(BCustomerUserForm customerUserForm);
	
	/**
	 * 
	 * @param ids
	 * @return
	 * gawen.chen
	 * 2015年4月7日下午5:26:55
	 * 描述：批量删除用户
	 */
	boolean deleteUsers(String ids);
	
	
	/**
	 * 
	 * @param ids
	 * @return
	 * gawen.chen
	 * 2015年4月7日下午7:26:55
	 * 描述：检查批量操作的用户中是否包括客户运营管理员角色
	 */
	boolean checkUsersInAdmin(String ids);
	
	
	/**
	 * 
	 * @param ids
	 * @return
	 * gawen.chen
	 * 2015年4月7日下午5:27:26
	 * 描述：批量禁用用户
	 */
	boolean disableUser(String ids);
	
	/**
	 * 
	 * @param ids
	 * @return
	 * gawen.chen
	 * 2015年4月7日下午5:27:43
	 * 描述：批量启用用户
	 */
	boolean enableUser(String ids);
	
	/**
	 * 
	 * @param ids
	 * @return
	 * gawen.chen
	 * 2015年4月7日下午5:27:54
	 * 描述：初始化密码
	 */
	boolean initPassword(String ids);
	
	
	/**
	 * 
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @param loginName
	 * @param isDisabled
	 * @param mobile
	 * @param userName
	 * @param cusotomerId
	 * @return
	 * gawen.chen
	 * 2015年4月7日下午5:51:52
	 * 描述：分页查询
	 */
	JqgridListForm findAll(int pageNo, String sidx, String sord, String loginName, final int isDisabled,
			String mobile, String userName,String cusotomerId);
	
	/**
	 * 
	 * @param userForm
	 * @return
	 * gawen.chen
	 * 2015年4月17日下午5:10:34
	 * 描述：重置蜜码
	 */
	boolean saveResetPassowrd(BCustomerUserForm userForm);
	
	/**
	 * @param id
	 * @param oldPassword
	 * @return
	 * gawen.chen
	 * 2015年4月24日上午11:00:03
	 * 描述：检查旧密码
	 */
	boolean checkOldPassword(String id ,String oldPassword);
	/**
	 * @param id
	 * @param oldPassword
	 * @return
	 * gawen.chen
	 * 2015年4月24日上午11:00:03
	 * 描述：检查旧密码
	 */
	BCustomerUser findByBossLoginName(String loginName);
	
}