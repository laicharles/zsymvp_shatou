package com.wisewater.function.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.function.pojo.CTemplateId;
import com.wisewater.function.repository.CTemplateIdRepository;

@Service
public class CTemplateIdServiceImpl extends BaseService implements CTemplateIdService{
	@Autowired
	private CTemplateIdRepository templateIdRepository;

	@Override
	public CTemplateId findByToken(String token) {
		return templateIdRepository.findByToken(token);
	}

	@Override
	public CTemplateId updateTemplateId(CTemplateId templateId) {
		return templateIdRepository.saveAndFlush(templateId);
	}
}
