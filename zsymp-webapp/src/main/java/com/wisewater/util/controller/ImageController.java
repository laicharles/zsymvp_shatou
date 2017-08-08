package com.wisewater.util.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wisewater.base.BaseController;
import com.wisewater.util.service.ImageService;
@Controller
public class ImageController extends BaseController {
	@Autowired
	private ImageService imageService;
	
	/**
	 * 上传编辑器图片(客户后台)
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cus/uploadImg.do")
	public @ResponseBody String uploadImgForCus(HttpServletRequest request,
			@RequestParam("imgFile") MultipartFile file) {
		String resultStr = imageService.uploadArticleImg(request,file,getCusTokenInSession().getToken());
		return resultStr;
	}
	
	/**
	 * 上传编辑器图片(运营后台)
	 * @param request
	 * @param file
	 * @return
	 * XingXingLvCha
	 * 2015年4月23日 上午9:47:39
	 */
	@RequestMapping(value = "/biz/uploadImg.do")
	public @ResponseBody String uploadImgForBiz(HttpServletRequest request,
			@RequestParam("imgFile") MultipartFile file) {
		String resultStr = imageService.uploadArticleImg(request,file,getBizTokenInSession());
		return resultStr;
	}
	
	/**
     * 上传图片
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/uploadImgIT.do")
    public @ResponseBody String uploadImgIT(HttpServletRequest request,
            @RequestParam("imgFile") MultipartFile file, @RequestParam("token") String token) {
        String resultStr = imageService.uploadImgIT(request, file, token);
        return resultStr;
    }
	
}
