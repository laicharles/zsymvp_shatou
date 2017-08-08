package com.wisewater.auto.service;

import java.util.List;

import com.wisewater.auto.controller.CSelfTagForm;

 
public interface CSelfTagService {


	/**
	 * 获取所有标签
	 * @return
	 * XingXingLvCha
	 * 2015年4月8日 下午4:28:32
	 */
	public List<CSelfTagForm> findAllCSelfTag(String token);

}