package com.wisewater.auto.service;

import com.wisewater.auto.controller.CAutoDefaultForm;
import com.wisewater.auto.pojo.CAutoDefault;

 
public interface CAutoDefaultService {

	
	/**
	 * 更新
	 * @param cautoDefaultForm
	 * @param token
	 * @return
	 * AlexFung
	 * 2015年6月11日 下午2:27:20
	 */
	public CAutoDefault updateCAutoDefaul(CAutoDefaultForm cautoDefaultForm,String token);
	
	
	/**
	 * 查找实体
	 * @param ID
	 * @return
	 * AlexFung
	 * 2015年6月11日 下午2:27:25
	 */
	public CAutoDefaultForm findCAutoDefaulByID(String ID);
	
	
	/**
	 * 查找实体
	 * @param Token
	 * @return
	 * AlexFung
	 * 2015年6月11日 下午2:33:06
	 */
	public CAutoDefaultForm findCAutoDefaulByToken(String token);

}