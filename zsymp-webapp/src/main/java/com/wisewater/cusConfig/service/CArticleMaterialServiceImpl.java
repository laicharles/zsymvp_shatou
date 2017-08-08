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
import com.wisewater.cusConfig.controller.CArticleMaterialForm;
import com.wisewater.cusConfig.pojo.CArticleMaterial;
import com.wisewater.cusConfig.pojo.CArticlePage;
import com.wisewater.cusConfig.pojo.CMaterialTag;
import com.wisewater.cusConfig.repository.CArticleMaterialRepository;
import com.wisewater.cusConfig.repository.CArticlePageRepository;
import com.wisewater.cusConfig.repository.CMaterialTagRepository;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.util.service.ImageService;
import com.wisewater.util.tools.FileCopyUtil;


@Service
public class CArticleMaterialServiceImpl extends BaseService  
    implements CArticleMaterialService{

	@Autowired
	private CArticleMaterialRepository carticlematerialRepository;
    
    @Autowired
    private CMaterialTagRepository cMaterialTagRepository;
    
    @Autowired
    private CArticlePageRepository cArticlePageRepository;
    
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
                    
        Page<CArticleMaterial> articleMtrlPage = carticlematerialRepository.findAll(page, tagFinal, titleFinal, token);
        
        List<CArticleMaterialForm> pageList = new ArrayList<CArticleMaterialForm>();
        if(articleMtrlPage!=null&&articleMtrlPage.getContent()!=null){
            for (CArticleMaterial txtMtrl : articleMtrlPage.getContent()) {
                pageList.add(mapper.map(txtMtrl, CArticleMaterialForm.class));
            }
        }
        
        JqgridListForm jqgridListForm = new JqgridListForm();
        jqgridListForm.setPage(pageNo);
        jqgridListForm.setFormList(pageList);
        jqgridListForm.setTotal(articleMtrlPage.getTotalPages());
        jqgridListForm.setRecords(articleMtrlPage.getTotalElements());
        
        return jqgridListForm;
        
    }
    
    @Override
    public boolean save(CArticleMaterialForm articleMtrlForm, String token) {
        if(articleMtrlForm==null) return false;
        
        if(articleMtrlForm.getPicNameFile()!=null && articleMtrlForm.getPicNameFile().getSize()>0){
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            String picName = imageService.uploadImg(request, articleMtrlForm.getPicNameFile(), articleMtrlForm.getToken());
            articleMtrlForm.setPicName(picName);
        }else{
            
            if(token.equalsIgnoreCase(articleMtrlForm.getInheritedToken())){
                //Nothing to do
            }else{
                //copy file
                HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
                String oldUrl = request.getSession().getServletContext()
                        .getRealPath("\\") + loadConstant.getPicPath() +articleMtrlForm.getInheritedToken()+"\\"+articleMtrlForm.getPicName();
                
                String newUrl = request.getSession().getServletContext()
                        .getRealPath("\\") + loadConstant.getPicPath()+token+"\\"+articleMtrlForm.getPicName();
                FileCopyUtil.copyFile(oldUrl, newUrl);
            }
            
        }
        
        CArticleMaterial articleMtrl = mapper.map(articleMtrlForm, CArticleMaterial.class);
        
        CArticlePage artPage = mapper.map(articleMtrlForm.getCArticlePage(), CArticlePage.class);
        artPage.setToken(articleMtrlForm.getToken());
        artPage.setPageContent(imageService.handleContentImg(articleMtrlForm.getCArticlePage().getPageContent(),token));
        artPage = cArticlePageRepository.save(artPage);
        articleMtrl.setCArticlePage(artPage);
        
        String tags=articleMtrlForm.getTags();
        if(tags!=null && !tags.equalsIgnoreCase("")){
            List<CMaterialTag> tagList=new ArrayList<CMaterialTag>();     
            articleMtrl.setCMaterialTags(tagList);
                        
            tags = tags.replaceAll("，", ",");
            articleMtrl.setTags(tags);
            String[] tagArray= tags.split(",");
            List<String> list = Arrays.asList(tagArray);
            for(String tag:list){
                CMaterialTag finalTag= cMaterialTagRepository.findByValueAndToken(tag, articleMtrlForm.getToken());
                if(finalTag==null){
                    finalTag=cMaterialTagRepository.save(new CMaterialTag(tag, articleMtrlForm.getToken()));
                    
                }
                tagList.add(finalTag);
            }
        }
        
        if(carticlematerialRepository.save(articleMtrl)!=null){
            return true;
        }
        return false;
    }
    
    @Override
    public CArticleMaterialForm findById(String id) {
        if(id==null) return null;
        
        CArticleMaterial mtrl = carticlematerialRepository.findById(id);
        if(mtrl==null) return null;
                
        CArticleMaterialForm form = mapper.map(mtrl, CArticleMaterialForm.class);

        return form;
    }
    
    @Override
    public boolean deleteByIds(String ids) {
        if(ids==null) return false;
        
        String[] idArray= ids.split(",");
        List<String> idList = Arrays.asList(idArray);
        
        List<CArticleMaterial> mtrlList = carticlematerialRepository.findByIdIn(idList);
        if(mtrlList==null||mtrlList.isEmpty())return false;
        
        for (CArticleMaterial mtrl :mtrlList) {
            mtrl.setIsLogicDel(1);
        }
        
        List<CArticleMaterial> mtrlListRtn = carticlematerialRepository.save(mtrlList); 
        if(mtrlListRtn==null||mtrlListRtn.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    @Override
    public boolean update(CArticleMaterialForm articleMtrlForm, String token) {
        if(articleMtrlForm==null) return false;
        
        CArticleMaterial mtrl = carticlematerialRepository.findById(articleMtrlForm.getId());
        
        if(mtrl==null){
            return false;
        }
        
        if(articleMtrlForm.getPicNameFile()!=null && articleMtrlForm.getPicNameFile().getSize()>0){
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            String picName = imageService.uploadImg(request, articleMtrlForm.getPicNameFile(), articleMtrlForm.getToken());
            articleMtrlForm.setPicName(picName);
        }else if(mtrl.getPicName().equalsIgnoreCase(articleMtrlForm.getPicName())){
            //Nothing to do             
        }else{
            if(token.equalsIgnoreCase(articleMtrlForm.getInheritedToken())){
                //Nothing to do
            }else{
                //copy file
                HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
                String oldUrl = request.getSession().getServletContext()
                        .getRealPath("\\") + loadConstant.getPicPath() +articleMtrlForm.getInheritedToken()+"\\"+articleMtrlForm.getPicName();
                
                String newUrl = request.getSession().getServletContext()
                        .getRealPath("\\") + loadConstant.getPicPath()+token+"\\"+articleMtrlForm.getPicName();
                FileCopyUtil.copyFile(oldUrl, newUrl);
            }
        }
        
        
        
        mtrl.setTitle(articleMtrlForm.getTitle());
        mtrl.setTags(articleMtrlForm.getTags());
        mtrl.setPicName(articleMtrlForm.getPicName());
        mtrl.setPageUrl(articleMtrlForm.getPageUrl());
        mtrl.setDescription(articleMtrlForm.getDescription());
        
        CArticlePage artPage = cArticlePageRepository.findById(articleMtrlForm.getCArticlePage().getId());
        artPage.setAuthor(articleMtrlForm.getCArticlePage().getAuthor());
        artPage.setPageContent(articleMtrlForm.getCArticlePage().getPageContent());
        artPage.setOrigUrl(articleMtrlForm.getCArticlePage().getOrigUrl());
        artPage.setPageContent(imageService.handleContentImg(articleMtrlForm.getCArticlePage().getPageContent(),token));
        mtrl.setCArticlePage(artPage);
        
        mtrl.setCMaterialTags(null);
        
        String tags=articleMtrlForm.getTags();
        if(tags!=null && !tags.equalsIgnoreCase("")){
            List<CMaterialTag> tagList=new ArrayList<CMaterialTag>();     
            mtrl.setCMaterialTags(tagList);
                        
            tags = tags.replaceAll("，", ",");
            mtrl.setTags(tags);
            String[] tagArray= tags.split(",");
            List<String> list = Arrays.asList(tagArray);
            for(String tag:list){
                CMaterialTag finalTag= cMaterialTagRepository.findByValueAndToken(tag, articleMtrlForm.getToken());
                if(finalTag==null){
                    finalTag=cMaterialTagRepository.save(new CMaterialTag(tag, articleMtrlForm.getToken()));
                    
                }
                tagList.add(finalTag);
            }
        }
        
        if(carticlematerialRepository.save(mtrl)!=null){
            return true;
        }
        return false;
    }
    



}