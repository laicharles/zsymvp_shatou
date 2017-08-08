package com.wisewater.auto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.auto.controller.CSelfTagForm;
import com.wisewater.auto.pojo.CSelfTag;
import com.wisewater.auto.repository.CSelfTagRepository;

@Service
public class CSelfTagServiceImpl extends BaseService implements CSelfTagService {

	@Autowired
	private CSelfTagRepository cselftagRepository;

	/**
	 * 获取所有标签
	 * 
	 * @return XingXingLvCha 2015年4月8日 下午4:28:32
	 */
	@Override
	public List<CSelfTagForm> findAllCSelfTag(String token) {
		List<CSelfTag> cSelfTagList = cselftagRepository.findAllCSelfTag(token);
		List<CSelfTagForm> cSelfTagFormList = new ArrayList<CSelfTagForm>();
		if (cSelfTagList.size() > 0) {
			for (CSelfTag cSelfTag : cSelfTagList) {
				cSelfTagFormList.add(mapper.map(cSelfTag, CSelfTagForm.class));
			}
		}

		return cSelfTagFormList;
	}

}