package com.wisewater.function.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.controller.CFeedBackPersonForm;
import com.wisewater.function.pojo.CFeedBackPerson;
import com.wisewater.function.repository.CFeedBackPersonRepository;
import com.wisewater.wechatpublic.util.StringUtil;

@Service
public class CFeedBackPersonServiceImpl extends BaseService implements CFeedBackPersonService{
	
	@Autowired
	private CFeedBackPersonRepository cFeedBackPersonRepository;
	
	
	@Override
	public JqgridListForm showAllPerson(int pageNo, String sidx, String sord, String token) {
		
		int pageSize = loadConstant.getPageSize();
		if (pageNo < 1){
			pageNo = 1;
		}
		// 默认不排序
		PageRequest page = new PageRequest(pageNo - 1, pageSize);
		
		// 设置排序方式
		if (sidx != null && sidx.length() > 0) {
			if (sord != null && sord.equalsIgnoreCase("desc")) {
				page = new PageRequest(pageNo - 1, pageSize, Direction.DESC, sidx);
			} else {
				page = new PageRequest(pageNo - 1, pageSize, Direction.ASC, sidx);
			}
		}
		
		Page<CFeedBackPerson> personPage = cFeedBackPersonRepository.findAll(page, token);
		List<CFeedBackPersonForm> pageList = new ArrayList<CFeedBackPersonForm>();
		CFeedBackPersonForm form = null;
		if(personPage!=null&&personPage.getContent()!=null){
			
			for(CFeedBackPerson person : personPage.getContent()){
				form = new CFeedBackPersonForm();
				form.setId(person.getId());
				form.setName(person.getName());
				form.setOpenID(person.getOpenID());
				form.setToken(person.getToken());
				if(person.getType()==1){
					form.setType("仅接收保修");
				}else if(person.getType()==2){
					form.setType("仅接收咨询");
				}else if(person.getType()==0){
					form.setType("全部接受");
				}
				pageList.add(form);
				
			}
			
		}
		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList);
		jqgridListForm.setTotal(personPage.getTotalPages());
		jqgridListForm.setRecords(personPage.getTotalElements());
		
		return jqgridListForm;
	}


	@Override
	public CFeedBackPersonForm findById(String id) {
		// TODO Auto-generated method stub
		if(id==null){
			return null;
		}
		
		CFeedBackPerson person = cFeedBackPersonRepository.getOne(id);
		if(person == null){
			return null;
		}
		CFeedBackPersonForm form = new CFeedBackPersonForm();
		form.setId(person.getId());
		form.setName(person.getName());
		form.setOpenID(person.getOpenID());
		form.setType(person.getType()+"");
		System.out.println(form.getType());
		form.setToken(person.getToken());
		return form;
	}


	@Override
	public CFeedBackPerson updateCFeedBackPerson(CFeedBackPersonForm cFeedBackPersonForm) {
		
		CFeedBackPerson person = mapper.map(cFeedBackPersonForm, CFeedBackPerson.class);
		
		return cFeedBackPersonRepository.saveAndFlush(person);
	}


	@Override
	public String deleteCFeedBackPersonBatch(String ids) {
		String notice = "";
		CFeedBackPerson person = null;
		CFeedBackPerson personDel = null;
		for(String id : StringUtil.strToList(ids, ";")){
			person = new CFeedBackPerson();
			person = mapper.map(findById(id), CFeedBackPerson.class);
			person.setIsLogicDel(1);
			personDel = cFeedBackPersonRepository.save(person);
			if(personDel == null){
				notice = notice + "," + person.getId();
			}
			
			
		}
		return notice;
	}


	@Override
	public boolean saveCFeedBackPerson(CFeedBackPersonForm cFeedBackPersonForm) {
		
		CFeedBackPerson person = new CFeedBackPerson();
		person = mapper.map(cFeedBackPersonForm, CFeedBackPerson.class);
		if(cFeedBackPersonRepository.saveAndFlush(person)!=null){
			return true;
		}
		
		
		
		return false;
	}

	
	
	
	
	
}
