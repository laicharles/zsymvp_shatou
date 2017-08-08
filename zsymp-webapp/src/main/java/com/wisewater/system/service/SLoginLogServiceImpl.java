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
import com.wisewater.system.controller.SLoginLogForm;
import com.wisewater.system.pojo.SLoginLog;
import com.wisewater.system.repository.SLoginLogRepository;


@Service
public class SLoginLogServiceImpl extends BaseService  
    implements SLoginLogService{


	@Autowired
	private SLoginLogRepository sloginlogRepository;
	
	/**
	 * 
	 * 描述：后台登录日志
	 * @author AlexFung
	 * 2016年8月3日 下午2:50:12
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @return
	 */
	@Override
	public JqgridListForm findSLoginLog(int pageNo, String sidx, String sord) {
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
		Page<SLoginLog> loginLogPage = sloginlogRepository.findSLoginLog(pager);
		List<SLoginLogForm> loginLogFormList = new ArrayList<SLoginLogForm>();
		if (loginLogPage != null && loginLogPage.getContent() != null) {
			for (SLoginLog loginLog : loginLogPage.getContent()) {
				loginLogFormList.add(mapper.map(loginLog, SLoginLogForm.class));
			}
		}
		PageImpl<SLoginLogForm> pageList = new PageImpl<SLoginLogForm>(loginLogFormList, pager,
				loginLogPage.getTotalElements());
		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList.getContent());
		jqgridListForm.setTotal(pageList.getTotalPages());
		jqgridListForm.setRecords(pageList.getTotalElements());
		return jqgridListForm;
	}

}