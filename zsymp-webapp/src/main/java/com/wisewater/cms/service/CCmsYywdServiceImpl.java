package com.wisewater.cms.service;

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
import com.wisewater.cms.controller.CCmsYywdForm;
import com.wisewater.cms.pojo.CCmsYywd;
import com.wisewater.cms.repository.CCmsYywdRepository;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.wechatpublic.util.StringUtil;

@Service
public class CCmsYywdServiceImpl extends BaseService implements CCmsYywdService {

	@Autowired
	private CCmsYywdRepository ccmsyywdRepository;
	
	/**
	 * 
	 * 描述：根据token查找营业网点
	 * @author AlexFung
	 * 2016年8月16日 下午5:47:02
	 * @param token
	 * @return
	 */
	public List<CCmsYywd> findYywdByToken(String token){
		return ccmsyywdRepository.findYywdByToken(token);
	}

	/**
	 * 根据token查找营业网点列表
	 * 
	 * @param token
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @param searchField
	 * @return AlexFung 2015年4月2日 下午1:55:03
	 */
	@Override
	public JqgridListForm findYywdByToken(String token, int pageNo,
			String sidx, String sord, String searchField) {
		int pageSize = loadConstant.getPageSize();

		Pageable pager = new PageRequest(pageNo - 1, pageSize);

		// 设置排序方式
		if (sidx != null && sidx.length() > 0) {
			if (sord != null && sord.equalsIgnoreCase("desc")) {
				pager = new PageRequest(pageNo - 1, pageSize, Direction.DESC,
						sidx);
			} else {
				pager = new PageRequest(pageNo - 1, pageSize, Direction.ASC,
						sidx);
			}
		}

		Page<CCmsYywd> yywdPage = ccmsyywdRepository.findYywdByToken(token,
				searchField, pager);
		List<CCmsYywdForm> yywdFormList = new ArrayList<CCmsYywdForm>();
		if (yywdPage != null && yywdPage.getContent() != null) {
			for (CCmsYywd yywd : yywdPage.getContent()) {
				yywdFormList.add(mapper.map(yywd, CCmsYywdForm.class));
			}
		}
		PageImpl<CCmsYywdForm> pageList = new PageImpl<CCmsYywdForm>(
				yywdFormList, pager, yywdPage.getTotalElements());
		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList.getContent());
		jqgridListForm.setTotal(pageList.getTotalPages());
		jqgridListForm.setRecords(pageList.getTotalElements());
		return jqgridListForm;
	}

	/**
	 * 根据ID查找营业网点详细信息
	 * 
	 * @param id
	 * @return AlexFung 2015年4月2日 下午1:55:09
	 */
	@Override
	public CCmsYywdForm findYywdById(String id) {
		CCmsYywd yywd = ccmsyywdRepository.getOne(id);
		CCmsYywdForm yywdForm = new CCmsYywdForm();
		yywdForm = mapper.map(yywd, CCmsYywdForm.class);
		return yywdForm;
	}

	/**
	 * 更新营业网点
	 * 
	 * @param yywdFrom
	 * @return AlexFung 2015年4月2日 下午1:55:16
	 */
	@Override
	public CCmsYywd updateYywd(CCmsYywdForm yywdFrom) {
		CCmsYywd yywd = mapper.map(yywdFrom, CCmsYywd.class);
		return ccmsyywdRepository.saveAndFlush(yywd);
	}

	/**
	 * 批量删除营业网点(逻辑删除)
	 * 
	 * @param idStr
	 * @return AlexFung 2015年4月2日 下午1:55:22
	 */
	@Override
	public String deleteYywdbatch(String idStr) {
		String notice = "";
		for (String id : StringUtil.strToList(idStr, ";")) {
			CCmsYywd yywd = new CCmsYywd();
			yywd = mapper.map(findYywdById(id), CCmsYywd.class);
			yywd.setIsLogicDel(1);
			CCmsYywd yywdDel = ccmsyywdRepository.save(yywd);
			if (yywdDel == null) {
				notice = notice + "," + yywd.getName();
			}
		}
		return notice;
	}

	/**
	 * 新增营业网点
	 * 
	 * @param yywdForm
	 * @return AlexFung 2015年4月2日 下午1:55:28
	 */
	@Override
	public boolean saveYywd(CCmsYywdForm yywdForm) {
		CCmsYywd yywd = new CCmsYywd();
		yywd = mapper.map(yywdForm, CCmsYywd.class);
		if (ccmsyywdRepository.saveAndFlush(yywd) != null) {
			return true;
		}
		return false;
	}


}