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
import com.wisewater.system.controller.SOperateLogForm;
import com.wisewater.system.pojo.SOperateLog;
import com.wisewater.system.repository.SOperateLogRepository;

@Service
public class SOperateLogServiceImpl extends BaseService implements SOperateLogService {

	@Autowired
	private SOperateLogRepository soperatelogRepository;

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
	public JqgridListForm findSOperateLog(int pageNo, String sidx, String sord) {
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
		Page<SOperateLog> operateLogPage = soperatelogRepository.findSOperateLog(pager);
		List<SOperateLogForm> operateLogFormList = new ArrayList<SOperateLogForm>();
		if (operateLogPage != null && operateLogPage.getContent() != null) {
			for (SOperateLog operateLog : operateLogPage.getContent()) {
				operateLogFormList.add(mapper.map(operateLog, SOperateLogForm.class));
			}
		}
		PageImpl<SOperateLogForm> pageList = new PageImpl<SOperateLogForm>(operateLogFormList, pager,
				operateLogPage.getTotalElements());
		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList.getContent());
		jqgridListForm.setTotal(pageList.getTotalPages());
		jqgridListForm.setRecords(pageList.getTotalElements());
		return jqgridListForm;
	}

}