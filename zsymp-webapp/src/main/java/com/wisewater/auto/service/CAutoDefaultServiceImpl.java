package com.wisewater.auto.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wisewater.auto.controller.CAutoDefaultForm;
import com.wisewater.auto.pojo.CAutoDefault;
import com.wisewater.auto.repository.CAutoDefaultRepository;
import com.wisewater.base.BaseService;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.util.service.ImageService;

@Service
public class CAutoDefaultServiceImpl extends BaseService implements
		CAutoDefaultService {

	@Autowired
	private CAutoDefaultRepository cautoDefaultRepository;

	@Autowired
	private ImageService imageService;

	@Autowired
	private SDictionaryRepository dictionaryRepository;

	@Override
	public CAutoDefault updateCAutoDefaul(CAutoDefaultForm cautoDefaultForm,
			String token) {
		SDictionary dic = dictionaryRepository
				.findByLogicID(cautoDefaultForm.getSdictionary()
						.getLogicID());
		CAutoDefault cautoDefault = mapper.map(cautoDefaultForm,
				CAutoDefault.class);
		if (dic != null) {
			cautoDefault.setSdictionary(dic);
		}
		if (cautoDefaultForm.getPicNameFile() != null
				&& cautoDefaultForm.getPicNameFile().getSize() > 0) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			String picName = imageService.uploadIT(request,
					cautoDefaultForm.getPicNameFile(), token);
			cautoDefault.setPicName(picName);
		}
		return cautoDefaultRepository.saveAndFlush(cautoDefault);
	}

	@Override
	public CAutoDefaultForm findCAutoDefaulByID(String ID) {
		CAutoDefault cautoDefault = cautoDefaultRepository.findOne(ID);
		return mapper.map(cautoDefault, CAutoDefaultForm.class);
	}

	@Override
	public CAutoDefaultForm findCAutoDefaulByToken(String token) {
		List<CAutoDefault> list = cautoDefaultRepository
				.findDefaultByToken(token);
		if (list != null && list.size() > 0) {
			return mapper.map(list.get(0), CAutoDefaultForm.class);
		}
		return null;
	}


}