package com.wisewater.auto.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.auto.controller.AnswerContext;
import com.wisewater.auto.controller.AnswerForm;
import com.wisewater.auto.controller.AnswerSessionSingLeton;
import com.wisewater.auto.controller.CAutoDefaultForm;
import com.wisewater.auto.pojo.CAuto;
import com.wisewater.auto.pojo.CAutoImgTx;
import com.wisewater.auto.pojo.CAutoKeyword;
import com.wisewater.base.BaseService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.wechatpublic.kf.Kfonlinelist;
import com.wisewater.wechatpublic.util.KfUtil;

@Service
public class AnswerServiceImpl extends BaseService implements AnswerService {

	private String token;

	@Autowired
	private CAutoKeywordService cautoKeywordService;

	@Autowired
	private CAutoDefaultService cautoDefaultService;

	@Autowired
	private BAccessTokenService baccessTokenService;

	@Override
	public AnswerForm answerMessage(String content, String fromUserName, String token, String createTime) {
		this.token = token;
		AnswerSessionSingLeton sessionSingLeton = AnswerSessionSingLeton.getInstance();

		HashMap<String, List<AnswerContext>> hashMap = sessionSingLeton.getHashMap();
		AnswerForm message = new AnswerForm();

		// 判断是否数字和上下文
		if (isInteger(content)) {
			List<AnswerContext> contextSession = (List<AnswerContext>) hashMap.get(token + fromUserName);
			if (contextSession != null && contextSession.size() > 0) {
				int inputNum = Integer.parseInt(content);
				if (inputNum > 0 && inputNum <= contextSession.size()) {
					return answerfilter(message, contextSession.get(inputNum - 1));
				} else {
					message.setAnswerType(1);
					message.setContent("您输入的编号不存在，请重新输入！");
					return message;
				}

			} else {
				// 找不到 回复通用语
				message.setAnswerType(1);
				message.setContent("温馨提示:\n列表已经过时，请重新输入！");
				return message;
			}

		}

		List<AnswerContext> answerContextList = installAnswer(content, token);
		if (answerContextList.size() == 0) // 找不到 回复通用语或转到多客服系统
		{

			BAccessTokenForm baccessTokenForm = baccessTokenService.checkUpdateAccessTokenByToken(token);
			Kfonlinelist kfonlinelist = KfUtil.findKfonlineList(baccessTokenForm.getAccessToken());
			if (kfonlinelist == null || kfonlinelist.getKf_online_list() == null
					|| kfonlinelist.getKf_online_list().size() <= 0) // 没有在线客服
			{
				message.setAnswerType(1);
				CAutoDefaultForm cautoDefaultForm = cautoDefaultService.findCAutoDefaulByToken(token);
				if (cautoDefaultForm != null) {
					message.setContent(cautoDefaultForm.getAutoReply());
				} else {
					message.setContent("对不起，系统找不到您想要的内容！");
				}
			} else {
				message.setAnswerType(3); // 有在线客服，转到多客服系统
			}

			return message;

		} else if (answerContextList.size() == 1) {
			// 只有一条
			return answerfilter(message, answerContextList.get(0));
		} else {
			// 多条
			int num = 1;
			StringBuffer contextBuffer = new StringBuffer();
			message.setAnswerType(1);
			contextBuffer.append("请回复以下您想了解的内容编号：\n");
			for (AnswerContext answerContext : answerContextList) {
				contextBuffer.append(num).append(".");
				contextBuffer.append(answerContext.getQuestion()).append("\n");
				num++;
			}
			message.setContent(contextBuffer.toString());
			hashMap.put(token + fromUserName, answerContextList);

			return message;
		}

	}

	/**
	 * 组成上下文类
	 * 
	 * @param content
	 * @param token
	 * @return XingXingLvCha 2015年4月11日 上午11:06:39
	 */
	private List<AnswerContext> installAnswer(String content, String token) {
		List<AnswerContext> answerContextList = new ArrayList<AnswerContext>();
		AnswerContext answerContext = null;
		// 完全匹配自动回复
		List<CAutoKeyword> cautoKeywordList = cautoKeywordService.findCAutoByKeyName(content, token);
		if (cautoKeywordList != null && cautoKeywordList.size() > 0) {
			for (CAutoKeyword cautoKeyword : cautoKeywordList) {
				answerContext = new AnswerContext();
				answerContext.setQuestion(cautoKeyword.getCAuto().getQuestion());
				answerContext.setType(1);
				CAuto cauto = mapper.map(cautoKeyword.getCAuto(), CAuto.class);
				CAutoImgTx cai = null;
				if (cautoKeyword.getCAuto().getCAutoImgTx() != null) {
					cai = mapper.map(cautoKeyword.getCAuto().getCAutoImgTx(), CAutoImgTx.class);
				}
				answerContext.setClassT(cauto);
				answerContext.setClassM(cai);
				answerContext.setObjectID(cauto.getId());
				answerListAdd(answerContextList, answerContext);
			}
		}

		return answerContextList;
	}

	/**
	 * 筛选回复内容
	 * 
	 * @param message
	 * @param answerContext
	 * @return XingXingLvCha 2015年4月11日 下午2:22:55
	 */
	public AnswerForm answerfilter(AnswerForm message, AnswerContext answerContext) {
		if (answerContext.getType() == 1) // 自动回复
		{
			return answerCAuto(message, (CAuto) answerContext.getClassT(), (CAutoImgTx) answerContext.getClassM());
		}
		return message;
	}

	/**
	 * 自动回复
	 * 
	 * @param message
	 * @param cauto
	 * @return XingXingLvCha 2015年4月11日 上午11:30:54
	 */
	private AnswerForm answerCAuto(AnswerForm message, CAuto cauto, CAutoImgTx cai) {

		if ("answerType1".equals(cauto.getSDictionary().getLogicID())) {
			message.setAnswerType(1);
			message.setContent(cauto.getTextContent());
			return message;
		}
		if ("answerType2".equals(cauto.getSDictionary().getLogicID())) {
			message.setAnswerType(2);
			CAutoImgTx cautoImgTx = cai;
			message.setTitle(cautoImgTx.getTitle());
			message.setDescription(cautoImgTx.getDescription());
			StringBuffer picUrlBuffer = new StringBuffer();
			picUrlBuffer.append(loadConstant.getWebSitePath()).append(loadConstant.getPicPath());
			picUrlBuffer.append(token).append("/").append(cautoImgTx.getPicName());
			message.setPicUrl(picUrlBuffer.toString());
			if (!"".equals(cautoImgTx.getPageUrl().trim())) {
				message.setUrl(cautoImgTx.getPageUrl());
			} else {
				StringBuffer urlBuffer = new StringBuffer();
				urlBuffer.append(loadConstant.getWebSitePath());
				urlBuffer.append("/article/auto.do?ID=");
				urlBuffer.append(cautoImgTx.getId());
				message.setUrl(urlBuffer.toString());
			}

		}
		return message;
	}

	/**
	 * 检查上下文是否有重复
	 * 
	 * @param answerList
	 * @param answer
	 * @return XingXingLvCha 2015年4月17日 下午4:37:49
	 */
	private List<AnswerContext> answerListAdd(List<AnswerContext> answerList, AnswerContext answer) {
		if (answerList.size() > 0) {
			boolean isFind = false;
			for (AnswerContext answerContext : answerList) {
				if (answerContext.getObjectID().equals(answer.getObjectID())) {
					isFind = true;
					break;
				}
			}
			if (!isFind) {
				answerList.add(answer);
			}
		} else {
			answerList.add(answer);
		}

		return answerList;
	}

	/*
	 * 判断是否为整数 并且是小于10的整数
	 * 
	 * @param str 传入的字符串
	 * 
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		boolean result = false;
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		result = pattern.matcher(str).matches();
		if (result) {
			if (Integer.parseInt(str) >= 10) {
				result = false;
			}
		}
		return result;
	}

	// 过滤特殊字符
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

}
