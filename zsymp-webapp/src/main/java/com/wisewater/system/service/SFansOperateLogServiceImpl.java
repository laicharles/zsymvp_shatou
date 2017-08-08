package com.wisewater.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.controller.SFansOperateLogForm;
import com.wisewater.system.pojo.SFansOperateLog;
import com.wisewater.system.repository.SFansOperateLogRepository;

@Service
public class SFansOperateLogServiceImpl extends BaseService implements SFansOperateLogService {

	@Autowired
	private SFansOperateLogRepository fansOperatelogRepository;

	/**
	 * 
	 * 描述：查看后台操作日志
	 * @author AlexFung
	 * 2016年8月3日 下午1:52:36
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @return
	 */
	@Override
	public JqgridListForm findSFansOperateLog(int pageNo, String sidx, String sord) {
		int pageSize = loadConstant.getPageSize();
		Pageable pager = new PageRequest(pageNo - 1, pageSize);
		// 设置排序方式
		if (sidx != null && sidx.length() > 0) {
			if (sord != null && sord.equalsIgnoreCase("desc")) {
				pager = new PageRequest(pageNo - 1, pageSize, Direction.DESC, sidx);
			} else {
				pager = new PageRequest(pageNo - 1, pageSize, Direction.ASC, sidx);
			}
		}
		Page<SFansOperateLog> fansOperateLogPage = fansOperatelogRepository.findSFansOperateLog(pager);
		List<SFansOperateLogForm> fansOperateLogFormList = new ArrayList<SFansOperateLogForm>();
		if (fansOperateLogPage != null && fansOperateLogPage.getContent() != null) {
			for (SFansOperateLog fansOperateLog : fansOperateLogPage.getContent()) {
				fansOperateLogFormList.add(mapper.map(fansOperateLog, SFansOperateLogForm.class));
			}
		}
		PageImpl<SFansOperateLogForm> pageList = new PageImpl<SFansOperateLogForm>(fansOperateLogFormList, pager,
				fansOperateLogPage.getTotalElements());
		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList.getContent());
		jqgridListForm.setTotal(pageList.getTotalPages());
		jqgridListForm.setRecords(pageList.getTotalElements());
		return jqgridListForm;
	}

}