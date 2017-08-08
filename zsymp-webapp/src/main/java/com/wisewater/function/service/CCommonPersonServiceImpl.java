package com.wisewater.function.service;

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
import com.wisewater.function.controller.CCommonPersonForm;
import com.wisewater.function.pojo.CCommonPerson;
import com.wisewater.function.repository.CCommonPersonRepository;
import com.wisewater.wechatpublic.util.StringUtil;

@Service
public class CCommonPersonServiceImpl extends BaseService implements CCommonPersonService{

	@Autowired
	private CCommonPersonRepository cCommonPersonRepository;
	
	@Override
	public JqgridListForm findCCommonPersonByToken(String token, int pageNo,
			String sidx, String sord) {
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
		Page<CCommonPerson> cCommonPersonPage = cCommonPersonRepository.findCCommonPersonByToken(token,
				pager);
		List<CCommonPersonForm> cCommonPersonFormList = new ArrayList<CCommonPersonForm>();
		if (cCommonPersonPage != null && cCommonPersonPage.getContent() != null) {
			for (CCommonPerson cCommonPerson : cCommonPersonPage.getContent()) {
				cCommonPersonFormList.add(mapper.map(cCommonPerson, CCommonPersonForm.class));
			}
		}
		PageImpl<CCommonPersonForm> pageList = new PageImpl<CCommonPersonForm>(
				cCommonPersonFormList, pager, cCommonPersonPage.getTotalElements());
		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList.getContent());
		jqgridListForm.setTotal(pageList.getTotalPages());
		jqgridListForm.setRecords(pageList.getTotalElements());
		return jqgridListForm;
	}

	@Override
	public List<CCommonPersonForm> findCCommonPersonByToken(String token){
		List<CCommonPersonForm> cCommonPersonFormList = new ArrayList<CCommonPersonForm>();
		List<CCommonPerson> cCommonPersonList = cCommonPersonRepository.findCCommonPersonByToken(token);
		if(cCommonPersonList != null && cCommonPersonList.size()>0)
		{
			CCommonPersonForm cCommonPersonForm = null;
			for(CCommonPerson cCommonPerson : cCommonPersonList)
			{
				cCommonPersonForm = mapper.map(cCommonPerson, CCommonPersonForm.class);
				cCommonPersonFormList.add(cCommonPersonForm);
			}
		}
	
		return cCommonPersonFormList;
	}
	
	
	@Override
	public CCommonPersonForm findCCommonPersonById(String id) {
		CCommonPerson cCommonPerson = cCommonPersonRepository.getOne(id);
		CCommonPersonForm cCommonPersonForm = new CCommonPersonForm();
		cCommonPersonForm = mapper.map(cCommonPerson, CCommonPersonForm.class);
		return cCommonPersonForm;
	}

	@Override
	public CCommonPerson updateCCommonPerson(CCommonPersonForm cCommonPersonForm) {
		CCommonPerson cCommonPerson = mapper.map(cCommonPersonForm, CCommonPerson.class);
		return cCommonPersonRepository.saveAndFlush(cCommonPerson);
	}

	/**
	 * 批量删除资讯动态(逻辑删除)
	 */
	@Override
	public String deleteCCommonPersonbatch(String idStr) {
		String notice = "";
		for (String id : StringUtil.strToList(idStr, ";")) {
			CCommonPerson cCommonPerson = new CCommonPerson();
			cCommonPerson = mapper.map(findCCommonPersonById(id), CCommonPerson.class);
			cCommonPerson.setIsLogicDel(1);
			CCommonPerson cCommonPersonDel = cCommonPersonRepository.save(cCommonPerson);
			if (cCommonPersonDel == null) {
				notice = notice + "," + cCommonPerson.getOpenID();
			}
		}
		return notice;
	}

	/**
	 * 新增
	 */
	@Override
	public boolean saveCCommonPerson(CCommonPersonForm cCommonPersonForm) {
		CCommonPerson cCommonPerson = new CCommonPerson();
		cCommonPerson = mapper.map(cCommonPersonForm, CCommonPerson.class);
		if (cCommonPersonRepository.saveAndFlush(cCommonPerson) != null) {
			return true;
		}
		return false;
	}
}
