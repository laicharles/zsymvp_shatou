
package com.wisewater.system.service;

import java.util.List;

import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.controller.SRoleForm;
import com.wisewater.util.tools.ZtreeForm;

 
public interface SRoleService {

	/**
	 * 
	 * @param pageNo  页码，默认第一页是是从1开始。
	 * @param sidx   排序字段
	 * @param sord    排序方式，desc或者asc
	 * @return
	 * gawen.chen
	 * 2015年3月28日下午1:52:10
	 * 描述：分页查询所有记录
	 */
	public JqgridListForm findAll(int pageNo,String sidx,String sord);
	
	public List<SRoleForm> findAll();
	
	/**
	 * 
	 * @param roleForm
	 * @return
	 * gawen.chen
	 * 2015年4月1日上午10:49:42
	 * 描述：保存数据
	 */
	public boolean save(SRoleForm roleForm);
	
	/**
	 * 
	 * @param roleForm
	 * @return
	 * gawen.chen
	 * 2015年4月1日上午10:49:49
	 * 描述：更新数据
	 */
	public boolean update(SRoleForm roleForm);
	
	/**
	 * 
	 * @param ids
	 * @return
	 * gawen.chen
	 * 2015年3月28日下午2:35:19
	 * 描述：批量删除
	 */
	public boolean deleteByIds(String ids);
	
	/**
	 * 
	 * @param id
	 * @return
	 * gawen.chen
	 * 2015年4月1日上午10:50:03
	 * 描述：根据ID查询
	 */
	public SRoleForm findById(String id);
	
	/**
	 * 
	 * @param roleCode
	 * @return
	 * gawen.chen
	 * 2015年3月28日下午1:56:28
	 * 描述：根据角色代码查询角色
	 */
	SRoleForm findByRoleCode(String roleCode);
	
	/**
	 * 
	 * @param roleId
	 * @param resourceIds
	 * @return
	 * gawen.chen
	 * 2015年4月1日上午10:50:52
	 * 描述：
	 */
	boolean saveAuthorityRole(String roleId,String resourceIds);
	
	/**
	 * 
	 * @param roleId
	 * @return
	 * gawen.chen
	 * 2015年4月1日下午1:26:48
	 * 描述：根据角色ID 初始化菜单树
	 */
	List<ZtreeForm> findAllMenusByRoleId(String roleId);

	
}