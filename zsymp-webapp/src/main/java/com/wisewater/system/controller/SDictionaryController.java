package com.wisewater.system.controller;

import java.util.List;

import com.wisewater.base.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.wisewater.system.service.SDictionaryService;

@Controller
public class SDictionaryController extends BaseController {

	@Autowired
	private SDictionaryService sdictionaryService;
   
	/**
	 * 通过大类获取列表
	 * @param typeCode
	 * @return
	 * XingXingLvCha
	 * 2015年3月31日 下午8:22:04
	 */
	@RequestMapping(value = { "/system/dictionary_list.do"}, method = RequestMethod.POST)
	public @ResponseBody List<SDictionaryForm> findByTypeCode(String typeCode){
		List<SDictionaryForm> sDictionaryForm = sdictionaryService.findByTypeCode(typeCode);
		return sDictionaryForm;
	}
}