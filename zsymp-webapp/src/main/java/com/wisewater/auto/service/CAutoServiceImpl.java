package com.wisewater.auto.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wisewater.base.BaseService;
import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.util.service.CommonService;
import com.wisewater.util.service.ImageService;
import com.wisewater.wechatpublic.util.StringUtil;
import com.wisewater.auto.controller.CAutoForm;
import com.wisewater.auto.controller.CAutoKeywordForm;
import com.wisewater.auto.controller.CSelfTagForm;
import com.wisewater.auto.pojo.CAuto;
import com.wisewater.auto.pojo.CAutoAr;
import com.wisewater.auto.pojo.CAutoImgTx;
import com.wisewater.auto.pojo.CAutoKeyword;
import com.wisewater.auto.pojo.CSelfTag;
import com.wisewater.auto.repository.CAutoArRepository;
import com.wisewater.auto.repository.CAutoImgTxRepository;
import com.wisewater.auto.repository.CAutoKeywordRepository;
import com.wisewater.auto.repository.CAutoRepository;
import com.wisewater.auto.repository.CSelfTagRepository;

@Service
public class CAutoServiceImpl extends BaseService implements CAutoService {

	@Autowired
	private CAutoRepository cautoRepository;

	@Autowired
	private SDictionaryRepository sdictionaryRepository;

	@Autowired
	private CAutoKeywordRepository cautoKeywordRepository;

	@Autowired
	private CSelfTagRepository cselfTagRepository;

	@Autowired
	private CAutoImgTxRepository cautoImgTxRepository;

	@Autowired
	private CAutoArRepository cautoArRepository;

	@Autowired
	private ImageService imageService;

	@Autowired
	private CommonService commonService;

	/**
	 * 自动回复列表
	 * 
	 * @param pageNo
	 * @param sidx
	 * @param sord
	 * @return XingXingLvCha 2015年4月7日 上午10:23:19
	 */
	@Override
	public JqgridListForm findAllCAuto(int pageNo, String sidx, String sord, String question, String keyword,
			String tag, String token) {

		question = commonService.createLikeSearch(commonService.encodeGetParm(question));
		keyword = commonService.encodeGetParm(keyword);
		tag = commonService.encodeGetParm(tag);

		if (StringUtils.isEmpty(sidx)) {
			sidx = "createTime";
		}
		if (StringUtils.isEmpty(sord)) {
			sord = "asc";
		}

		int pageSize = loadConstant.getPageSize();
		Pageable page = new PageRequest(pageNo - 1, pageSize);
		// 设置排序方式
		if (sidx != null && sidx.length() > 0) {
			if (sord != null && sord.equalsIgnoreCase("desc")) {
				page = new PageRequest(pageNo - 1, pageSize, Direction.DESC, sidx);
			} else {
				page = new PageRequest(pageNo - 1, pageSize, Direction.ASC, sidx);
			}
		}

		Page<CAuto> cautoPage = cautoRepository.findAllCAuto(question, keyword, tag, token, page);

		List<CAutoForm> cautoFormList = new ArrayList<CAutoForm>();

		if (cautoPage != null && cautoPage.getContent() != null) {
			for (CAuto cauto : cautoPage.getContent()) {
				CAutoForm cautoForm = new CAutoForm();
				cautoForm.setId(cauto.getId());
				cautoForm.setSDictionary(cauto.getSDictionary());
				cautoForm.setQuestion(cauto.getQuestion());
				cautoForm.setCreateTime(cauto.getCreateTime().getTime() / 1000);
				StringBuffer cautoKeywordStr = new StringBuffer();
				for (CAutoKeyword cAutoKeyword : cauto.getCAutoKeywords()) {
					cautoKeywordStr.append(cAutoKeyword.getKeyName());
					cautoKeywordStr.append(";");
				}
				cautoForm.setCautoKeywordStr(cautoKeywordStr.toString());

				StringBuffer cselfTagStr = new StringBuffer();
				for (CSelfTag cSelfTag : cauto.getCSelfTags()) {
					cselfTagStr.append(cSelfTag.getName());
					cselfTagStr.append(";");
				}
				cautoForm.setCselfTagStr(cselfTagStr.toString());

				cautoFormList.add(cautoForm);
				cautoForm = null;
			}
		}

		PageImpl<CAutoForm> pageList = new PageImpl<CAutoForm>(cautoFormList, page, cautoPage.getTotalElements());

		JqgridListForm jqgridListForm = new JqgridListForm();
		jqgridListForm.setPage(pageNo);
		jqgridListForm.setFormList(pageList.getContent());
		jqgridListForm.setTotal(pageList.getTotalPages());
		jqgridListForm.setRecords(pageList.getTotalElements());
		return jqgridListForm;
	}

	/**
	 * 新增自动回复
	 * 
	 * @param cautoForm
	 * @return XingXingLvCha 2015年4月2日 下午3:16:57
	 */
	@Override
	public String saveCAuto(CAutoForm cautoForm, String token) {
		if (cautoForm == null) {
			return "false";
		}
		CAuto cauto = new CAuto();
		cauto.setIsLogicDel(0);
		cauto.setCreateTime(new Date());
		cauto.setToken(token);
		cauto.setQuestion(cautoForm.getQuestion());
		// 数据字典 回复类型
		SDictionary sDictionary = sdictionaryRepository.findByLogicID(cautoForm.getAnswerType());
		cauto.setSDictionary(sDictionary);
		// 文本
		if ("answerType1".equals(sDictionary.getLogicID())) {
			cauto.setTextContent(cautoForm.getTextContent());
		}
		// 图文
		if ("answerType2".equals(sDictionary.getLogicID())) {
			CAutoImgTx cAutoImgTx = new CAutoImgTx();
			cAutoImgTx.setTitle(cautoForm.getTitle());
			cAutoImgTx.setPageUrl(cautoForm.getPageUrl());
			cAutoImgTx.setDescription(cautoForm.getDescription());
			cAutoImgTx.setToken(token);

			if (cautoForm.getPicName() != null) {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
						.getRequest();
				String picName = imageService.uploadIT(request, cautoForm.getPicName(), token);
				cAutoImgTx.setPicName(picName);
			}
			CAutoAr cAutoAr = new CAutoAr();
			cAutoAr.setAuthor(cautoForm.getAuthor());
			cAutoAr.setOrigUrl(cautoForm.getOrigUrl());
			cAutoAr.setPageContent(imageService.handleContentImg(cautoForm.getPageContent(), token));
			cAutoAr.setToken(token);
			cAutoAr = cautoArRepository.saveAndFlush(cAutoAr);
			cAutoImgTx.setCAutoAr(cAutoAr);

			cAutoImgTx = cautoImgTxRepository.saveAndFlush(cAutoImgTx);
			cauto.setCAutoImgTx(cAutoImgTx);
		}

		// 添加标签
		List<CSelfTag> cSelfTags = new ArrayList<CSelfTag>();
		List<CSelfTagForm> cSelfTagFormList = cautoForm.getCSelfTags();
		if (cSelfTagFormList != null && cSelfTagFormList.size() > 0) {
			for (CSelfTagForm cSelfTagForm : cSelfTagFormList) {
				if (cSelfTagForm.getName() != null && !"".equals(cSelfTagForm.getName())) {
					CSelfTag cSelfTag = mapper.map(cSelfTagForm, CSelfTag.class);
					if (cselfTagRepository.findByNameByToken(cSelfTag.getName(), token) == null) {
						cSelfTag.setToken(token);
						cSelfTag = cselfTagRepository.saveAndFlush(cSelfTag);
					} else {
						cSelfTag = cselfTagRepository.findByNameByToken(cSelfTag.getName(), token);
					}

					cSelfTags.add(cSelfTag);
				}
			}
		}

		cauto.setCSelfTags(cSelfTags);

		cauto = cautoRepository.saveAndFlush(cauto);
		// 添加关键词
		List<CAutoKeywordForm> CAutoKeywordFormList = cautoForm.getCAutoKeywords();
		if (CAutoKeywordFormList != null && CAutoKeywordFormList.size() > 0) {
			for (CAutoKeywordForm cAutoKeywordForm : CAutoKeywordFormList) {
				if (cAutoKeywordForm.getKeyName() != null && !"".equals(cAutoKeywordForm.getKeyName())) {
					CAutoKeyword cAutoKeyword = mapper.map(cAutoKeywordForm, CAutoKeyword.class);
					cAutoKeyword.setCAuto(cauto);
					cAutoKeyword.setToken(token);
					cautoKeywordRepository.saveAndFlush(cAutoKeyword);
				}

			}
		}

		return "true";
	}

	/**
	 * 修改自动回复
	 * 
	 * @param cautoForm
	 * @return XingXingLvCha 2015年4月2日 下午3:16:57
	 */
	@Override
	public String updateCAuto(CAutoForm cautoForm, String token) {
		if (cautoForm == null) {
			return "false";
		}

		CAuto cauto = cautoRepository.findOne(cautoForm.getId());
		if (cauto == null) {
			return "false";
		}

		cauto.setIsLogicDel(0);
		cauto.setQuestion(cautoForm.getQuestion());
		// 数据字典 回复类型
		SDictionary sDictionary = sdictionaryRepository.findByLogicID(cautoForm.getAnswerType());
		cauto.setSDictionary(sDictionary);
		// 文本
		if ("answerType1".equals(sDictionary.getLogicID())) {
			cauto.setTextContent(cautoForm.getTextContent());
		}
		// 图文
		if ("answerType2".equals(sDictionary.getLogicID())) {
			CAutoImgTx cAutoImgTx = cauto.getCAutoImgTx();
			cAutoImgTx.setTitle(cautoForm.getTitle());
			cAutoImgTx.setPageUrl(cautoForm.getPageUrl());
			cAutoImgTx.setDescription(cautoForm.getDescription());

			if (cautoForm.getPicName() != null && cautoForm.getPicName().getSize() > 0) {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
						.getRequest();
				String picName = imageService.uploadIT(request, cautoForm.getPicName(), token);
				cAutoImgTx.setPicName(picName);
			}

			CAutoAr cAutoAr = cAutoImgTx.getCAutoAr();
			cAutoAr.setAuthor(cautoForm.getAuthor());
			cAutoAr.setOrigUrl(cautoForm.getOrigUrl());
			cAutoAr.setPageContent(imageService.handleContentImg(cautoForm.getPageContent(), token));
			cAutoAr = cautoArRepository.saveAndFlush(cAutoAr);
			cAutoImgTx.setCAutoAr(cAutoAr);

			cAutoImgTx = cautoImgTxRepository.saveAndFlush(cAutoImgTx);
			cauto.setCAutoImgTx(cAutoImgTx);
		}

		// 标签
		List<CSelfTag> cSelfTags = cauto.getCSelfTags();
		List<CSelfTagForm> cSelfTagFormList = cautoForm.getCSelfTags();

		// 删掉的标签
		if (cSelfTags != null && cSelfTags.size() > 0 && cSelfTagFormList != null && cSelfTagFormList.size() > 0) {
			boolean ifFind = false;
			for (CSelfTag cSelfTag : cSelfTags) {
				ifFind = false;
				for (CSelfTagForm cSelfTagForm : cSelfTagFormList) {
					if (cSelfTagForm.getId() != null && cSelfTag.getId().equals(cSelfTagForm.getId())) {
						ifFind = true;
						break;
					}
				}
				if (!ifFind) {
					cSelfTags.remove(cSelfTag);
				}
			}
		}

		// 新增的标签
		if (cSelfTagFormList != null && cSelfTagFormList.size() > 0) {
			for (CSelfTagForm cSelfTagForm : cSelfTagFormList) {
				if (cSelfTagForm.getName() != null && !"".equals(cSelfTagForm.getName())) {
					if (cSelfTagForm.getId() == null || "".equals(cSelfTagForm.getId())) {
						CSelfTag cSelfTag = mapper.map(cSelfTagForm, CSelfTag.class);
						if (cselfTagRepository.findByNameByToken(cSelfTag.getName(), token) == null) {
							cSelfTag.setToken(token);
							cSelfTag = cselfTagRepository.saveAndFlush(cSelfTag);
						} else {
							cSelfTag = cselfTagRepository.findByNameByToken(cSelfTag.getName(), token);
						}

						cSelfTags.add(cSelfTag);
					}

				}
			}
		}

		cauto.setCSelfTags(cSelfTags);

		cauto = cautoRepository.saveAndFlush(cauto);
		// 关键词
		List<CAutoKeyword> CAutoKeywords = cauto.getCAutoKeywords();
		List<CAutoKeywordForm> CAutoKeywordFormList = cautoForm.getCAutoKeywords();

		// 删掉的关键词
		if (CAutoKeywords != null && CAutoKeywords.size() > 0 && CAutoKeywordFormList != null
				&& CAutoKeywordFormList.size() > 0) {
			boolean isFind = false;
			for (CAutoKeyword cAutoKeyword : CAutoKeywords) {
				isFind = false;
				for (CAutoKeywordForm cAutoKeywordForm : CAutoKeywordFormList) {
					if (cAutoKeywordForm.getId() != null && cAutoKeyword.getId().equals(cAutoKeywordForm.getId())) {
						isFind = true;
						break;
					}
				}
				if (!isFind) {
					cautoKeywordRepository.delete(cAutoKeyword);
				}
			}
		}

		// 新增的关键词
		if (CAutoKeywordFormList != null && CAutoKeywordFormList.size() > 0) {
			for (CAutoKeywordForm cAutoKeywordForm : CAutoKeywordFormList) {
				if (cAutoKeywordForm.getKeyName() != null && !"".equals(cAutoKeywordForm.getKeyName())) {
					if (cAutoKeywordForm.getId() == null || "".equals(cAutoKeywordForm.getId())) {
						CAutoKeyword cAutoKeyword = mapper.map(cAutoKeywordForm, CAutoKeyword.class);
						cAutoKeyword.setCAuto(cauto);
						cAutoKeyword.setToken(token);
						cautoKeywordRepository.saveAndFlush(cAutoKeyword);
					}

				}

			}
		}

		return "true";
	}

	/**
	 * 批量删除自动回复
	 * 
	 * @param IDStr
	 * @return XingXingLvCha 2015年4月8日 下午2:09:44
	 */
	@Override
	public boolean deleteCAuto(String IDStr) {
		for (String id : StringUtil.strToList(IDStr, ";")) {
			CAuto cauto = cautoRepository.getOne(id);
			cauto.setIsLogicDel(1);
			List<CSelfTag> cSelfTag = cauto.getCSelfTags();
			cSelfTag.removeAll(cSelfTag);
			cauto.setCSelfTags(cSelfTag);

			List<CAutoKeyword> CAutoKeywords = cauto.getCAutoKeywords();
			if (CAutoKeywords.size() > 0) {
				for (CAutoKeyword cAutoKeyword : CAutoKeywords) {
					cautoKeywordRepository.delete(cAutoKeyword);
				}
			}

			cauto = cautoRepository.saveAndFlush(cauto);
		}

		return true;
	}

	/**
	 * 通过id获取自动实例
	 * 
	 * @param ID
	 * @return XingXingLvCha 2015年4月2日 下午8:03:08
	 */
	@Override
	public CAutoForm findCAutoByID(String ID) {
		CAutoForm cAutoForm = new CAutoForm();
		CAuto cauto = cautoRepository.findOne(ID);
		cAutoForm.setId(cauto.getId());
		cAutoForm.setQuestion(cauto.getQuestion());
		cAutoForm.setAnswerType(cauto.getSDictionary().getLogicID());
		// 文本
		if ("answerType1".equals(cAutoForm.getAnswerType())) {
			cAutoForm.setTextContent(cauto.getTextContent());
		}
		// 图文
		if ("answerType2".equals(cAutoForm.getAnswerType())) {
			CAutoImgTx cAutoImgTx = cauto.getCAutoImgTx();
			if (cAutoImgTx != null) {
				cAutoForm.setTitle(cAutoImgTx.getTitle());
				cAutoForm.setDescription(cAutoImgTx.getDescription());
				cAutoForm.setPageUrl(cAutoImgTx.getPageUrl());
				cAutoForm.setPicUrl(cAutoImgTx.getPicName());

				CAutoAr cAutoAr = cAutoImgTx.getCAutoAr();
				if (cAutoAr != null) {
					cAutoForm.setAuthor(cAutoAr.getAuthor());
					cAutoForm.setOrigUrl(cAutoAr.getOrigUrl());
					cAutoForm.setPageContent(cAutoAr.getPageContent().replaceAll("&", "&amp;").replaceAll("<", "&lt;")
							.replaceAll(">", "&gt;"));
				}
			}

		}

		// 关键字
		List<CAutoKeyword> CAutoKeywords = cauto.getCAutoKeywords();
		List<CAutoKeywordForm> cAutoKeywordFormList = new ArrayList<CAutoKeywordForm>();
		if (CAutoKeywords != null && CAutoKeywords.size() > 0) {
			for (CAutoKeyword cAutoKeyword : CAutoKeywords) {
				cAutoKeywordFormList.add(mapper.map(cAutoKeyword, CAutoKeywordForm.class));
			}
			cAutoForm.setCAutoKeywords(cAutoKeywordFormList);
		}

		// 标签
		List<CSelfTag> CSelfTags = cauto.getCSelfTags();
		List<CSelfTagForm> cSelfTagFormList = new ArrayList<CSelfTagForm>();
		if (CSelfTags != null && CSelfTags.size() > 0) {
			for (CSelfTag cSelfTag : CSelfTags) {
				cSelfTagFormList.add(mapper.map(cSelfTag, CSelfTagForm.class));
			}
			cAutoForm.setCSelfTags(cSelfTagFormList);
		}

		return cAutoForm;
	}

}