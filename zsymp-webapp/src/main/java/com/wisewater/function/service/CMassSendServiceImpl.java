package com.wisewater.function.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.cusConfig.pojo.CArticleMaterial;
import com.wisewater.cusConfig.pojo.CPicMaterial;
import com.wisewater.cusConfig.pojo.CTextMaterial;
import com.wisewater.cusConfig.repository.CArticleMaterialRepository;
import com.wisewater.cusConfig.repository.CPicMaterialRepository;
import com.wisewater.cusConfig.repository.CTextMaterialRepository;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.function.controller.CMassSendForm;
import com.wisewater.function.pojo.CMassSend;
import com.wisewater.function.repository.CMassSendRepository;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.util.tools.FileCopyUtil;
import com.wisewater.wechatpublic.message.out.MassSendAll;
import com.wisewater.wechatpublic.message.out.MassSendAllFilter;
import com.wisewater.wechatpublic.message.out.MassSendAllImage;
import com.wisewater.wechatpublic.message.out.MassSendAllNews;
import com.wisewater.wechatpublic.message.out.MassSendAllText;
import com.wisewater.wechatpublic.message.out.MassSendPreview;
import com.wisewater.wechatpublic.message.out.MassSendPreviewImage;
import com.wisewater.wechatpublic.message.out.MassSendPreviewNews;
import com.wisewater.wechatpublic.message.out.MassSendPreviewText;
import com.wisewater.wechatpublic.message.out.MassSendType;
import com.wisewater.wechatpublic.message.out.MassSendTypeText;
import com.wisewater.wechatpublic.message.out.UploadArticle;
import com.wisewater.wechatpublic.message.out.UploadNews;
import com.wisewater.wechatpublic.util.ImgUploadUrlUtil;
import com.wisewater.wechatpublic.util.MassSendUtil;
import com.wisewater.wechatpublic.util.MediaUploadUtil;

@Service
public class CMassSendServiceImpl extends BaseService implements CMassSendService {

	@Autowired
	private CMassSendRepository cmasssendRepository;

	@Autowired
	private CTextMaterialRepository cTextMaterialRepository;

	@Autowired
	private CPicMaterialRepository cPicMaterialRepository;

	@Autowired
	private CArticleMaterialRepository cArticleMaterialRepository;

	@Autowired
	private BAccessTokenService accessTokenService;

	@Autowired
	private SDictionaryRepository dictionaryRepository;
	
	private Logger logger = Logger.getLogger(CMassSendServiceImpl.class);
	/**
	 * 
	 * 描述：查找所有群发记录
	 * 
	 * @author AlexFung 2016年7月26日 上午11:55:33
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @param title
	 * @param token
	 * @return
	 */
	@Override
	public JqgridListForm findAll(int pageNo, String sidx, String sord, String title, String token) {
		if (StringUtils.isEmpty(title)) {
			title = "";
		}
		int pageSize = loadConstant.getPageSize();
		if (pageNo < 1) {
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
		Page<CMassSend> massSendPage = cmasssendRepository.findAll(page, "%" + title + "%", token);
		List<CMassSendForm> pageList = new ArrayList<CMassSendForm>();
		if (massSendPage != null && massSendPage.getContent() != null) {
			for (CMassSend mSend : massSendPage.getContent()) {
				pageList.add(mapper.map(mSend, CMassSendForm.class));
			}
		}
		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList);
		jqgridListForm.setTotal(massSendPage.getTotalPages());
		jqgridListForm.setRecords(massSendPage.getTotalElements());
		return jqgridListForm;
	}

	/**
	 * 
	 * 描述：保存群发素材
	 * 
	 * @author AlexFung 2016年7月26日 上午11:56:11
	 * @param massSendForm
	 * @return
	 */
	@Override
	public boolean saveSendMass(CMassSendForm massSendForm) {
		if (massSendForm == null)
			return false;
		CMassSend massSend = mapper.map(massSendForm, CMassSend.class);
		SDictionary sendStusEnt = dictionaryRepository.findByTypeCodeAndDicValue("sendStatus", "1");// 1-未发送
		massSend.setSendStatus(sendStusEnt);
		if (cmasssendRepository.save(massSend) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 描述：根据id删除群发记录
	 * 
	 * @author AlexFung 2016年7月26日 上午11:57:19
	 * @param ids
	 * @return
	 */
	@Override
	public boolean deleteByIds(String ids) {
		if (ids == null)
			return false;
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		List<CMassSend> mtrlList = cmasssendRepository.findByIdIn(idList);
		if (mtrlList == null || mtrlList.isEmpty()) {
			return false;
		}
		for (CMassSend mtrl : mtrlList) {
			mtrl.setIsLogicDel(1);
		}
		List<CMassSend> mtrlListRtn = cmasssendRepository.save(mtrlList);
		if (mtrlListRtn == null || mtrlListRtn.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * 描述：群发
	 * 
	 * @author AlexFung 2016年7月26日 上午11:58:12
	 * @param id
	 * @param token
	 * @return
	 */
	@Override
	public String sendMass(String id, String token) {
		String msgId = "";
		if (id == null) {
			return "";
		}
		CMassSend massSend = cmasssendRepository.findById(id);
		if (massSend == null) {
			return "";
		}
		BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
		if (accessTokenForm == null) {
			return "";
		}
		CMassSendForm massSendForm = mapper.map(massSend, CMassSendForm.class);
		MassSendAll massSendAll = constructMassSendAll(massSendForm, accessTokenForm.getAccessToken(), token);
		if (null != massSendAll) {
			msgId = MassSendUtil.massSendAll(massSendAll, accessTokenForm.getAccessToken());
		}
		return msgId;
	}

	/**
	 * 
	 * 描述：构造群发
	 * 
	 * @author AlexFung 2016年7月26日 上午11:59:14
	 * @param massSendForm
	 * @param accessToken
	 * @param token
	 * @return
	 */
	private MassSendAll constructMassSendAll(CMassSendForm massSendForm, String accessToken, String token) {
		if (massSendForm == null) {
			return null;
		}
		String mtrlType = massSendForm.getMaterialType();
		String mainMtrlID = massSendForm.getMaterialID();
		if (mainMtrlID == null) {
			return null;
		}
		MassSendAllFilter filter = new MassSendAllFilter();
		filter.setIs_to_all(true);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		// 附件路径
		String filePath = request.getSession().getServletContext().getRealPath("\\") + "resources\\attached\\";
		logger.info("constructMassSendAll这个是图片路径："+filePath);
		if (mtrlType.equalsIgnoreCase(MassSendUtil.mass_send_type_image)) {
			// 图片群发
			String imageMediaId = getImageMediaId(mainMtrlID, accessToken,
					token, filePath);
			if (StringUtils.isEmpty(imageMediaId)) {
				return null;
			}
			MassSendAllImage allImg = new MassSendAllImage();
			MassSendType type = new MassSendType();
			type.setMedia_id(imageMediaId);
			allImg.setImage(type);
			allImg.setFilter(filter);
			return allImg;
		}
		if (mtrlType.equalsIgnoreCase(MassSendUtil.mass_send_type_mpnews)) {
			// 图文群发
			String newsMediaId = getNewsMediaId(mainMtrlID, accessToken, token, filePath);
			if (StringUtils.isEmpty(newsMediaId)) {
				return null;
			}
			MassSendAllNews massSendNews = new MassSendAllNews();
			MassSendType type = new MassSendType();
			type.setMedia_id(newsMediaId);
			massSendNews.setMpnews(type);
			massSendNews.setFilter(filter);
			return massSendNews;
		}
		if (mtrlType.equalsIgnoreCase(MassSendUtil.mass_send_type_text)) {
			// 文本群发
			MassSendAllText alltext = new MassSendAllText();
			String content;
			CTextMaterial ctext = cTextMaterialRepository.findById(mainMtrlID);
			if (null != ctext) {
				content = ctext.getTextContent();
			} else {
				return null;
			}
			MassSendTypeText text = new MassSendTypeText();
			text.setContent(content);
			alltext.setText(text);
			alltext.setFilter(filter);
			return alltext;
		}
		return null;
	}



	/**
	 * 
	 * 描述：群发预览
	 * 
	 * @author AlexFung 2016年7月26日 下午2:05:43
	 * @param id
	 * @param token
	 * @param openID
	 * @return
	 */
	@Override
	public String previewSendMass(String id, String token, String openID) {
		String msgId = "";
		if (id == null) {
			return "";
		}
		CMassSend ms = cmasssendRepository.findById(id);
		if (ms != null) {
			BAccessTokenForm accessTokenForm = accessTokenService.checkUpdateAccessTokenByToken(token);
			if (accessTokenForm == null) {
				return "";
			}
			MassSendPreview massSendPreview = constructMassSendPreview(mapper.map(ms, CMassSendForm.class),
					accessTokenForm.getAccessToken(), token, openID);
			if (null != massSendPreview) {
				msgId = MassSendUtil.massSendPreview(massSendPreview, accessTokenForm.getAccessToken());
			}
		} else {
			return "";
		}
		return msgId;
	}

	/**
	 * 
	 * 描述：构造预览群发
	 * 
	 * @author AlexFung 2016年7月26日 下午2:08:19
	 * @param form
	 * @param accessToken
	 * @param token
	 * @param openID
	 * @return
	 */
	private MassSendPreview constructMassSendPreview(CMassSendForm form, String accessToken, String token,
			String openID) {
		if (form == null) {
			return null;
		}
		String mtrlType = form.getMaterialType();
		String mainMtrlID = form.getMaterialID();
		if (mainMtrlID == null) {
			return null;
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String filePath = request.getSession().getServletContext().getRealPath("\\") + "resources\\attached\\";
		logger.info("constructMassSendPreview这个是图片路径："+filePath);
		if (mtrlType.equalsIgnoreCase(MassSendUtil.mass_send_type_image)) {
			// 图片群发预览
			String imageMediaId = getImageMediaId(mainMtrlID, accessToken,
					token, filePath);
			if (StringUtils.isEmpty(imageMediaId)) {
				return null;
			}
			MassSendPreviewImage preImg = new MassSendPreviewImage();
			MassSendType type = new MassSendType();
			type.setMedia_id(imageMediaId);
			preImg.setImage(type);
			preImg.setTouser(openID);
			return preImg;
		}
		if (mtrlType.equalsIgnoreCase(MassSendUtil.mass_send_type_mpnews)) {
			// 图文群发预览
			String newsMediaId = getNewsMediaId(mainMtrlID, accessToken, token, filePath);
			if (StringUtils.isEmpty(newsMediaId)) {
				return null;
			}
			MassSendPreviewNews previewNews = new MassSendPreviewNews();
			MassSendType type = new MassSendType();
			type.setMedia_id(newsMediaId);
			previewNews.setMpnews(type);
			previewNews.setTouser(openID);
			return previewNews;

		}
		if (mtrlType.equalsIgnoreCase(MassSendUtil.mass_send_type_text)) {
			// 文本群发预览
			MassSendPreviewText pretext = new MassSendPreviewText();
			String content;
			CTextMaterial ctext = cTextMaterialRepository.findById(mainMtrlID);
			if (null != ctext) {
				content = ctext.getTextContent();
			} else {
				return null;
			}
			MassSendTypeText text = new MassSendTypeText();
			text.setContent(content);
			pretext.setText(text);
			pretext.setTouser(openID);
			return pretext;
		}
		return null;
	}

	/**
	 * 
	 * 描述：更新群发状态
	 * @author AlexFung
	 * 2016年7月26日 下午2:27:53
	 * @param id
	 * @param msgId
	 * @param sendStatus
	 * @return
	 */
	@Override
	public boolean updateSendMassStatus(String id, String msgId, String sendStatus) {
		CMassSend massSend = cmasssendRepository.findById(id);
		if (massSend == null){
			return false;
		}
		massSend.setMpMsgID(msgId);
		massSend.setSendDateTime(new Date());
		SDictionary sendStusEnt = dictionaryRepository.findByTypeCodeAndDicValue("sendStatus", sendStatus);
		massSend.setSendStatus(sendStusEnt);
		if (cmasssendRepository.save(massSend) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 描述：获取图片mediaId
	 * @author AlexFung
	 * 2016年7月26日 下午2:23:00
	 * @param mainMtrlID
	 * @param accessToken
	 * @param token
	 * @param filePath
	 * @return
	 */
	private String getImageMediaId(String mainMtrlID, String accessToken,
			String token, String filePath) {
		String imageMediaId = "";
		CPicMaterial cpic = cPicMaterialRepository.findById(mainMtrlID);
		if (null != cpic) {
			filePath += token + "\\" + cpic.getPicName();
			try {
				imageMediaId = MediaUploadUtil.upload(accessToken, MediaUploadUtil.media_uploa_type_img, filePath);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
		return imageMediaId;
	}

	/**
	 * 
	 * 描述：获取图文mediaId
	 * @author AlexFung
	 * 2016年7月26日 下午2:20:02
	 * @param mainMtrlID
	 * @param accessToken
	 * @param token
	 * @param filePath
	 * @return
	 */
	private String getNewsMediaId(String mainMtrlID, String accessToken, String token, String filePath) {
		String ids[] = mainMtrlID.split(",");
		if (ids == null || ids.length == 0) {
			return null;
		}
		UploadNews news = new UploadNews();
		List<UploadArticle> articles = new ArrayList<UploadArticle>();
		news.setArticles(articles);
		for (String id : ids) {
			CArticleMaterial c = cArticleMaterialRepository.findById(id);
			if (null != c) {
				String finalFilePath = filePath + token + "\\" + c.getPicName();
				try {
					String picMediaId = MediaUploadUtil.upload(accessToken, MediaUploadUtil.media_uploa_type_img,
							finalFilePath);
					UploadArticle uploadArticle = new UploadArticle();
					uploadArticle.setAuthor(c.getCArticlePage().getAuthor());
					uploadArticle.setContent(c.getCArticlePage().getPageContent());
					String[] imgurls = FileCopyUtil.getImgs(uploadArticle.getContent());
					if (imgurls != null && imgurls.length > 0) {
						String content = uploadArticle.getContent();
						for (String sourseUrl : imgurls) {
							
							String url = sourseUrl.substring(sourseUrl.lastIndexOf("/zsymp-webapp"));
							url = "http://127.0.0.1" + url;
							
							String newUrl = ImgUploadUrlUtil.upload(accessToken, url);
							content = content.replace(sourseUrl, newUrl);
						}
						uploadArticle.setContent(content);
					}
					uploadArticle.setContent_source_url(c.getCArticlePage().getOrigUrl());
					uploadArticle.setDigest(c.getDescription());
					uploadArticle.setShow_cover_pic("0");
					uploadArticle.setThumb_media_id(picMediaId);
					uploadArticle.setTitle(c.getTitle());
					articles.add(uploadArticle);
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			} else {
				return null;
			}
		}
		String newsMediaId = MediaUploadUtil.uploadArticles(news, accessToken);
		if (newsMediaId == null || newsMediaId.equalsIgnoreCase("")) {
			return null;
		}
		return newsMediaId;
	}
}