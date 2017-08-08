package com.wisewater.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wisewater.base.BaseService;
import com.wisewater.cusConfig.pojo.CMp;
import com.wisewater.cusConfig.repository.CMpRepository;
import com.wisewater.system.pojo.SysNoticeBean;

@Service
public class SysNoticeServiceImpl extends BaseService implements
		SysNoticeService {

	@Autowired
	private CMpRepository cmpRepository;

	/**
	 * 系统通知
	 * @param token
	 * @return
	 */
	@Override
	public List<SysNoticeBean> findNotices(String token) {
		// 系统通知列表
		List<SysNoticeBean> noticeList = new ArrayList<SysNoticeBean>();
		// 获取未配置公众号信息
		CMp cmp = cmpRepository.findByToken(token);
		if (cmp != null && StringUtils.isEmpty(cmp.getAppID())) {
			noticeList.add(new SysNoticeBean("你还未配置公众号信息，请进行配置！", loadConstant
					.getWebSitePath() + "/cus/cmp_toAdd.do"));
		}
		return noticeList;
	}

}