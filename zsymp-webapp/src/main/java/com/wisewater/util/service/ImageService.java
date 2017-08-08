package com.wisewater.util.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	/**
	 * 上传图片
	 * @param request
	 * @param file
	 * @param token
	 * @return jsonString
	 * AlexFung
	 * 2015年4月8日 下午4:07:09
	 */
	public String uploadArticleImg(HttpServletRequest request, MultipartFile file,String token);
	
	
	/**
	 * 上传图片
	 * @param request
	 * @param file
	 * @param token
	 * @return picName
	 * AlexFung
	 * 2015年4月8日 下午4:07:09
	 */
	public String uploadImg(HttpServletRequest request, MultipartFile file,String token);
	
	
	/**
	 * 上传图文图片
	 * @param request
	 * @param file
	 * @return
	 * XingXingLvCha
	 * 2015年4月2日 下午6:54:30
	 */
	public String uploadIT(HttpServletRequest request, MultipartFile file,String token);
	
	
	/**
     * 上传图文图片
     * @param request
     * @param file
     * @return
     * tmh
     */
    public String uploadImgIT(HttpServletRequest request, MultipartFile file,String token);
    
    /**
	 * 处理文本编辑器里图片复制
	 * @param content
	 * @param token
	 * XingXingLvCha
	 * 2015年4月20日 下午2:09:41
	 */
	public String handleContentImg(String content,String token);
}
