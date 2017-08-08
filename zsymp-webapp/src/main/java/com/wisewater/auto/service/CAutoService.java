package com.wisewater.auto.service;

import com.wisewater.auto.controller.CAutoForm;
import com.wisewater.form.utils.JqgridListForm;

 
public interface CAutoService {


	/**
	 * 自动回复列表
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @return
	 * XingXingLvCha
	 * 2015年4月7日 上午10:23:19
	 */
	public JqgridListForm findAllCAuto(int pageNo,String sidx,String sord,
			String question,String bknowKeyword,String bknowTag,String token);
	
	
	/**
	 * 新增自动回复
	 * @param cautoForm
	 * @return
	 * XingXingLvCha
	 * 2015年4月2日 下午3:16:57
	 */
	public String saveCAuto(CAutoForm cautoForm,String token);
	
	/**
	 * 修改自动回复
	 * @param cautoForm
	 * @return
	 * XingXingLvCha
	 * 2015年4月2日 下午3:16:57
	 */
	public String updateCAuto(CAutoForm cautoForm,String token);
	
	
	/**
	 * 批量删除自动回复
	 * @param IDStr
	 * @return
	 * XingXingLvCha
	 * 2015年4月8日 下午2:09:44
	 */
	public boolean deleteCAuto(String IDStr);
	
	/**
	 * 通过id获取自动实例
	 * @param ID
	 * @return
	 * XingXingLvCha
	 * 2015年4月2日 下午8:03:08
	 */
	public CAutoForm findCAutoByID(String ID);

}