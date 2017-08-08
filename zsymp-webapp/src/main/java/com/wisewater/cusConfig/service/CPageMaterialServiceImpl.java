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
import com.wisewater.cusConfig.controller.CPageMaterialForm;
import com.wisewater.cusConfig.pojo.CMaterialTag;
import com.wisewater.cusConfig.pojo.CPageMaterial;
import com.wisewater.cusConfig.repository.CMaterialTagRepository;
import com.wisewater.cusConfig.repository.CPageMaterialRepository;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.util.service.ImageService;


@Service
public class CPageMaterialServiceImpl extends BaseService  
    implements CPageMaterialService{

	@Autowired
	private CPageMaterialRepository cpagematerialRepository;
    
    @Autowired
    private CMaterialTagRepository cMaterialTagRepository;
    
    @Autowired
    private ImageService imageService;

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
                    
        Page<CPageMaterial> pageMtrlPage = cpagematerialRepository.findAll(page, tagFinal, titleFinal, token);
        
        List<CPageMaterialForm> pageList = new ArrayList<CPageMaterialForm>();
        if(pageMtrlPage!=null&&pageMtrlPage.getContent()!=null){
            for (CPageMaterial pgMtrl : pageMtrlPage.getContent()) {
                pageList.add(mapper.map(pgMtrl, CPageMaterialForm.class));
            }
        }
        
        JqgridListForm jqgridListForm = new JqgridListForm();
        jqgridListForm.setPage(pageNo);
        jqgridListForm.setFormList(pageList);
        jqgridListForm.setTotal(pageMtrlPage.getTotalPages());
        jqgridListForm.setRecords(pageMtrlPage.getTotalElements());
        
        return jqgridListForm;
    }
    
    @Override
    public boolean save(CPageMaterialForm pageMtrlForm, String token) {
        if(pageMtrlForm==null) return false;
        CPageMaterial pageMtrl = mapper.map(pageMtrlForm, CPageMaterial.class);
        
        pageMtrl.setPageContent(imageService.handleContentImg(pageMtrlForm.getPageContent(),token));
        
        String tags=pageMtrlForm.getTags();
        if(tags!=null && !tags.equalsIgnoreCase("")){
            List<CMaterialTag> tagList=new ArrayList<CMaterialTag>();     
            pageMtrl.setCMaterialTags(tagList);
                        
            tags = tags.replaceAll("，", ",");
            pageMtrl.setTags(tags);
            String[] tagArray= tags.split(",");
            List<String> list = Arrays.asList(tagArray);
            for(String tag:list){
                CMaterialTag finalTag= cMaterialTagRepository.findByValueAndToken(tag, pageMtrlForm.getToken());
                if(finalTag==null){
                    finalTag=cMaterialTagRepository.save(new CMaterialTag(tag, pageMtrlForm.getToken()));
                    
                }
                tagList.add(finalTag);
            }
        }
        
        if(cpagematerialRepository.save(pageMtrl)!=null){
            return true;
        }
        return false;
    }
    
    @Override
    public CPageMaterialForm findById(String id) {
        if(id==null) return null;
        
        CPageMaterial mtrl = cpagematerialRepository.findById(id);
        if(mtrl==null) return null;
                
        CPageMaterialForm form = mapper.map(mtrl, CPageMaterialForm.class);
        
        return form;
    }
    
    @Override
    public boolean deleteByIds(String ids) {
        if(ids==null) return false;
        
        String[] idArray= ids.split(",");
        List<String> idList = Arrays.asList(idArray);
        
        List<CPageMaterial> mtrlList = cpagematerialRepository.findByIdIn(idList);
        if(mtrlList==null||mtrlList.isEmpty())return false;
        
        for (CPageMaterial mtrl :mtrlList) {
            mtrl.setIsLogicDel(1);
        }
        
        List<CPageMaterial> mtrlListRtn = cpagematerialRepository.save(mtrlList); 
        if(mtrlListRtn==null||mtrlListRtn.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    @Override
    public boolean update(CPageMaterialForm pageMtrlForm, String token) {
        if(pageMtrlForm==null) return false;
        CPageMaterial mtrl = cpagematerialRepository.findById(pageMtrlForm.getId());
        
        if(mtrl==null){
            return false;
        }
        
        mtrl.setTitle(pageMtrlForm.getTitle());
        mtrl.setPageContent(pageMtrlForm.getPageContent());
        mtrl.setTags(pageMtrlForm.getTags());
        
        mtrl.setCMaterialTags(null);
        
        mtrl.setPageContent(imageService.handleContentImg(pageMtrlForm.getPageContent(),token));
        
        String tags=pageMtrlForm.getTags();
        if(tags!=null && !tags.equalsIgnoreCase("")){
            List<CMaterialTag> tagList=new ArrayList<CMaterialTag>();     
            mtrl.setCMaterialTags(tagList);
                        
            tags = tags.replaceAll("，", ",");
            mtrl.setTags(tags);
            String[] tagArray= tags.split(",");
            List<String> list = Arrays.asList(tagArray);
            for(String tag:list){
                CMaterialTag finalTag= cMaterialTagRepository.findByValueAndToken(tag, pageMtrlForm.getToken());
                if(finalTag==null){
                    finalTag=cMaterialTagRepository.save(new CMaterialTag(tag, pageMtrlForm.getToken()));
                    
                }
                tagList.add(finalTag);
            }
        }
        
        if(cpagematerialRepository.save(mtrl)!=null){
            return true;
        }
        return false;
    }
    
}