package com.wisewater.function.service;

import com.wisewater.function.pojo.CTemplateId;

public interface CTemplateIdService {

	public CTemplateId findByToken(String token);

	public CTemplateId updateTemplateId(CTemplateId templateId);
}
