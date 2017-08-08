package com.wisewater.cms.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.wisewater.cms.controller.CCmsYywdForm;
import com.wisewater.cms.pojo.CCmsYywd;
import com.wisewater.form.utils.JqgridListForm;

public interface CCmsYywdService {
	
	/**
	 * 
	 * 描述：根据token查找营业网点
	 * @author AlexFung
	 * 2016年8月16日 下午5:47:02
	 * @param token
	 * @return
	 */
	public List<CCmsYywd> findYywdByToken(String token);

	/**
	 * 根据token查找营业网点列表
	 * @param token
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @param searchField
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:55:03
	 */
	public JqgridListForm findYywdByToken(String token, int pageNo,
			String sidx, String sord, String searchField);

	/**
	 * 根据ID查找营业网点详细信息
	 * @param id
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:55:09
	 */
	public CCmsYywdForm findYywdById(String id);

	/**
	 * 更新营业网点
	 * @param yywdFrom
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:55:16
	 */
	public CCmsYywd updateYywd(CCmsYywdForm yywdFrom);

	/**
	 * 批量删除营业网点(逻辑删除)
	 * @param idStr
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:55:22
	 */
	public String deleteYywdbatch(String idStr);

	/**
	 * 新增营业网点
	 * @param yywdForm
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:55:28
	 */
	public boolean saveYywd(CCmsYywdForm yywdForm);
	
}