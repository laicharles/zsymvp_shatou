package com.wisewater.weixin.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.wisewater.base.BaseService;
import com.wisewater.util.service.ImageService;
import com.wisewater.weixin.controller.CClickTwForm;
import com.wisewater.weixin.pojo.CClickTw;
import com.wisewater.weixin.repository.CClickTwRepository;

@Service
public class CClickTwServiceImpl extends BaseService implements CClickTwService {

	@Autowired
	private CClickTwRepository clickTwRepository;

	@Autowired
	private ImageService imageService;

	@Override
	public CClickTwForm findByToken(String token, String twType) {
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		CClickTw clickTw = clickTwRepository.findByToken(token, twType);
		if (clickTw != null) {
			return mapper.map(clickTw, CClickTwForm.class);
		}
		return null;
	}

	@Override
	public boolean saveClickTw(CClickTwForm clickTwForm, MultipartFile file, String token) {
		String fileName = clickTwForm.getPicName();
		if (file != null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			fileName = imageService.uploadImg(request, file, token);
		}
		if (fileName != null) {
			if (fileName.split("\\.").length == 2) { // 验证图片上传是否正常
				clickTwForm.setPicName(fileName);
				CClickTw userArticle = mapper.map(clickTwForm, CClickTw.class);
				if (clickTwRepository.save(userArticle) != null) {
					return true;
				}
			}
		}
		return false;
	}

}