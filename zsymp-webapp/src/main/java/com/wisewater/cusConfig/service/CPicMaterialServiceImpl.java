package com.wisewater.cusConfig.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wisewater.base.BaseService;
import com.wisewater.cusConfig.controller.CPicMaterialForm;
import com.wisewater.cusConfig.pojo.CMaterialTag;
import com.wisewater.cusConfig.pojo.CPicMaterial;
import com.wisewater.cusConfig.repository.CMaterialTagRepository;
import com.wisewater.cusConfig.repository.CPicMaterialRepository;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.util.service.ImageService;
import com.wisewater.util.tools.FileCopyUtil;


@Service
public class CPicMaterialServiceImpl extends BaseService  
    implements CPicMaterialService{

	@Autowired
	private CPicMaterialRepository cpicmaterialRepository;
    
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
                    
        Page<CPicMaterial> picMtrlPage = cpicmaterialRepository.findAll(page, tagFinal, titleFinal,token);
        
        List<CPicMaterialForm> pageList = new ArrayList<CPicMaterialForm>();
        if(picMtrlPage!=null&&picMtrlPage.getContent()!=null){
            for (CPicMaterial pgMtrl : picMtrlPage.getContent()) {
                pageList.add(mapper.map(pgMtrl, CPicMaterialForm.class));
            }
        }
        
        JqgridListForm jqgridListForm = new JqgridListForm();
        jqgridListForm.setPage(pageNo);
        jqgridListForm.setFormList(pageList);
        jqgridListForm.setTotal(picMtrlPage.getTotalPages());
        jqgridListForm.setRecords(picMtrlPage.getTotalElements());
        
        return jqgridListForm;
    }
    
    @Override
    public boolean save(CPicMaterialForm picMtrlForm, String token) {
        if(picMtrlForm==null) return false;
        
        if(picMtrlForm.getPicNameFile()!=null && picMtrlForm.getPicNameFile().getSize()>0){
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            String picName = imageService.uploadImg(request, picMtrlForm.getPicNameFile(), picMtrlForm.getToken());
            picMtrlForm.setPicName(picName);
        }else{
            
            if(token.equalsIgnoreCase(picMtrlForm.getInheritedToken())){
                //Nothing to do
            }else{
                //copy file
                HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
                String oldUrl = request.getSession().getServletContext()
                        .getRealPath("\\") + loadConstant.getPicPath() +picMtrlForm.getInheritedToken()+"\\"+picMtrlForm.getPicName();
                
                String newUrl = request.getSession().getServletContext()
                        .getRealPath("\\") + loadConstant.getPicPath()+token+"\\"+picMtrlForm.getPicName();
                FileCopyUtil.copyFile(oldUrl, newUrl);
            }
            
        }
        
        CPicMaterial picMtrl = mapper.map(picMtrlForm, CPicMaterial.class);
        
        String tags=picMtrlForm.getTags();
        if(tags!=null && !tags.equalsIgnoreCase("")){
            List<CMaterialTag> tagList=new ArrayList<CMaterialTag>();     
            picMtrl.setCMaterialTags(tagList);
                        
            tags = tags.replaceAll("，", ",");
            picMtrl.setTags(tags);
            String[] tagArray= tags.split(",");
            List<String> list = Arrays.asList(tagArray);
            for(String tag:list){
                CMaterialTag finalTag= cMaterialTagRepository.findByValueAndToken(tag,picMtrlForm.getToken());
                if(finalTag==null){
                    finalTag=cMaterialTagRepository.save(new CMaterialTag(tag,picMtrlForm.getToken()));
                    
                }
                tagList.add(finalTag);
            }
        }
        
        if(cpicmaterialRepository.save(picMtrl)!=null){
            return true;
        }
        return false;
    }
    
    @Override
    public CPicMaterialForm findById(String id) {
        if(id==null) return null;
        
        CPicMaterial mtrl = cpicmaterialRepository.findById(id);
        if(mtrl==null) return null;
                
        CPicMaterialForm form = mapper.map(mtrl, CPicMaterialForm.class);
        
        return form;
    }
    
    @Override
    public boolean deleteByIds(String ids) {
        if(ids==null) return false;
        
        String[] idArray= ids.split(",");
        List<String> idList = Arrays.asList(idArray);
        
        List<CPicMaterial> mtrlList = cpicmaterialRepository.findByIdIn(idList);
        if(mtrlList==null||mtrlList.isEmpty())return false;
        
        for (CPicMaterial mtrl :mtrlList) {
            mtrl.setIsLogicDel(1);
        }
        
        List<CPicMaterial> mtrlListRtn = cpicmaterialRepository.save(mtrlList); 
        if(mtrlListRtn==null||mtrlListRtn.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    @Override
    public boolean update(CPicMaterialForm picMtrlForm, String token) {
        if(picMtrlForm==null) return false;
        
        CPicMaterial mtrl = cpicmaterialRepository.findById(picMtrlForm.getId());
        
        if(mtrl==null){
            return false;
        }
        
        if(picMtrlForm.getPicNameFile()!=null && picMtrlForm.getPicNameFile().getSize()>0){
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            String picName = imageService.uploadImg(request, picMtrlForm.getPicNameFile(), picMtrlForm.getToken());
            picMtrlForm.setPicName(picName);
        }else if(mtrl.getPicName().equalsIgnoreCase(picMtrlForm.getPicName())){
            //Nothing to do             
        }else{
            if(token.equalsIgnoreCase(picMtrlForm.getInheritedToken())){
                //Nothing to do
            }else{
                //copy file
                HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
                String oldUrl = request.getSession().getServletContext()
                        .getRealPath("\\") + loadConstant.getPicPath() +picMtrlForm.getInheritedToken()+"\\"+picMtrlForm.getPicName();
                
                String newUrl = request.getSession().getServletContext()
                        .getRealPath("\\") + loadConstant.getPicPath()+token+"\\"+picMtrlForm.getPicName();
                FileCopyUtil.copyFile(oldUrl, newUrl);
            }
        }
        
        mtrl.setTitle(picMtrlForm.getTitle());
        mtrl.setPicName(picMtrlForm.getPicName());
        mtrl.setThumbPicName(picMtrlForm.getThumbPicName());
        mtrl.setTags(picMtrlForm.getTags());
        //XXX tmh update the createdBy and createdDatetime
        
        mtrl.setCMaterialTags(null);
        
        String tags=picMtrlForm.getTags();
        if(tags!=null && !tags.equalsIgnoreCase("")){
            List<CMaterialTag> tagList=new ArrayList<CMaterialTag>();     
            mtrl.setCMaterialTags(tagList);
                        
            tags = tags.replaceAll("，", ",");
            mtrl.setTags(tags);
            String[] tagArray= tags.split(",");
            List<String> list = Arrays.asList(tagArray);
            for(String tag:list){
                CMaterialTag finalTag= cMaterialTagRepository.findByValueAndToken(tag, picMtrlForm.getToken());
                if(finalTag==null){
                    finalTag=cMaterialTagRepository.save(new CMaterialTag(tag, picMtrlForm.getToken()));
                    
                }
                tagList.add(finalTag);
            }
        }
        
        if(cpicmaterialRepository.save(mtrl)!=null){
            return true;
        }
        return false;
    }
    



}