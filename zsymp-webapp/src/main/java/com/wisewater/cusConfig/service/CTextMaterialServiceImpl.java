package com.wisewater.cusConfig.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.cusConfig.controller.CTextMaterialForm;
import com.wisewater.cusConfig.pojo.CMaterialTag;
import com.wisewater.cusConfig.pojo.CTextMaterial;
import com.wisewater.cusConfig.repository.CMaterialTagRepository;
import com.wisewater.cusConfig.repository.CTextMaterialRepository;
import com.wisewater.form.utils.JqgridListForm;

@Service
public class CTextMaterialServiceImpl extends BaseService  
    implements CTextMaterialService{

	@Autowired
	private CTextMaterialRepository ctextmaterialRepository;
	
    @Autowired
    private CMaterialTagRepository cMaterialTagRepository;

    @Override
    public JqgridListForm findAll(int pageNo, String sidx, String sord, String tag, String title, String token) {
        
        if(StringUtils.isEmpty(tag)){tag="";}
        if(StringUtils.isEmpty(title)){title="";}
        
        int pageSize =  loadConstant.getPageSize();
        if(pageNo<1)pageNo=1;
    
        tag = tag.replaceAll("，", ",");
        final String tagFinal = "%"+tag+"%";
        final String titleFinal = "%"+title+"%";
        
        //默认不排序
        PageRequest page = new PageRequest(pageNo-1, pageSize);
        
        //设置排序方式
        if(sidx!=null&&sidx.length()>0){
            if(sord!=null&&sord.equalsIgnoreCase("desc")){
                 page = new PageRequest(pageNo-1, pageSize,Direction.DESC,sidx);
            }else{
             page = new PageRequest(pageNo-1, pageSize,Direction.ASC,sidx);
            }
        }
                    
        Page<CTextMaterial> textMtrlPage = ctextmaterialRepository.findAll(page, tagFinal, titleFinal, token);
        
        List<CTextMaterialForm> pageList = new ArrayList<CTextMaterialForm>();
        if(textMtrlPage!=null&&textMtrlPage.getContent()!=null){
            for (CTextMaterial txtMtrl : textMtrlPage.getContent()) {
                pageList.add(mapper.map(txtMtrl, CTextMaterialForm.class));
            }
        }
        
        JqgridListForm jqgridListForm = new JqgridListForm();
        jqgridListForm.setPage(pageNo);
        jqgridListForm.setFormList(pageList);
        jqgridListForm.setTotal(textMtrlPage.getTotalPages());
        jqgridListForm.setRecords(textMtrlPage.getTotalElements());
        
        return jqgridListForm;
        
    }
    
    @Override
    public boolean save(CTextMaterialForm textMtrlForm) {
        if(textMtrlForm==null) return false;
        CTextMaterial textMtrl = mapper.map(textMtrlForm, CTextMaterial.class);
        
        String tags=textMtrlForm.getTags();
        if(tags!=null && !tags.equalsIgnoreCase("")){
            List<CMaterialTag> tagList=new ArrayList<CMaterialTag>();     
            textMtrl.setCMaterialTags(tagList);
                        
            tags = tags.replaceAll("，", ",");
            textMtrl.setTags(tags);
            String[] tagArray= tags.split(",");
            List<String> list = Arrays.asList(tagArray);
            for(String tag:list){
                CMaterialTag finalTag= cMaterialTagRepository.findByValueAndToken(tag, textMtrlForm.getToken());
                if(finalTag==null){
                    finalTag=cMaterialTagRepository.save(new CMaterialTag(tag, textMtrlForm.getToken()));
                    
                }
                tagList.add(finalTag);
            }
        }
        
        if(ctextmaterialRepository.save(textMtrl)!=null){
            return true;
        }
        return false;
    }
    
    @Override
    public CTextMaterialForm findById(String id) {
        if(id==null) return null;
        
        CTextMaterial mtrl = ctextmaterialRepository.findById(id);
        if(mtrl==null) return null;
                
        CTextMaterialForm form = mapper.map(mtrl, CTextMaterialForm.class);
        
        return form;
    }
    
    @Override
    public boolean deleteByIds(String ids) {
        if(ids==null) return false;
        
        String[] idArray= ids.split(",");
        List<String> idList = Arrays.asList(idArray);
        
        List<CTextMaterial> mtrlList = ctextmaterialRepository.findByIdIn(idList);
        if(mtrlList==null||mtrlList.isEmpty())return false;
        
        for (CTextMaterial mtrl :mtrlList) {
            mtrl.setIsLogicDel(1);
        }
        
        List<CTextMaterial> mtrlListRtn = ctextmaterialRepository.save(mtrlList); 
        if(mtrlListRtn==null||mtrlListRtn.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    @Override
    public boolean update(CTextMaterialForm textMtrlForm) {
        if(textMtrlForm==null) return false;
        CTextMaterial mtrl = ctextmaterialRepository.findById(textMtrlForm.getId());
        
        if(mtrl==null){
            return false;
        }
        
        mtrl.setTitle(textMtrlForm.getTitle());
        mtrl.setTextContent(textMtrlForm.getTextContent());
        mtrl.setTags(textMtrlForm.getTags());
        
        mtrl.setCMaterialTags(null);
        
        String tags=textMtrlForm.getTags();
        if(tags!=null && !tags.equalsIgnoreCase("")){
            List<CMaterialTag> tagList=new ArrayList<CMaterialTag>();     
            mtrl.setCMaterialTags(tagList);
                        
            tags = tags.replaceAll("，", ",");
            mtrl.setTags(tags);
            String[] tagArray= tags.split(",");
            List<String> list = Arrays.asList(tagArray);
            for(String tag:list){
                CMaterialTag finalTag= cMaterialTagRepository.findByValueAndToken(tag, textMtrlForm.getToken());
                if(finalTag==null){
                    finalTag=cMaterialTagRepository.save(new CMaterialTag(tag, textMtrlForm.getToken()));
                    
                }
                tagList.add(finalTag);
            }
        }
        
        if(ctextmaterialRepository.save(mtrl)!=null){
            return true;
        }
        return false;
    }
    



}