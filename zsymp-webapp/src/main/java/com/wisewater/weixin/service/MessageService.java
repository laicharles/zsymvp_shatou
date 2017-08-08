package com.wisewater.weixin.service;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.auto.controller.AnswerForm;
import com.wisewater.auto.controller.CAutoDefaultForm;
import com.wisewater.auto.service.AnswerService;
import com.wisewater.auto.service.CAutoDefaultService;
import com.wisewater.bizConfig.controller.BAccessTokenForm;
import com.wisewater.bizConfig.service.BAccessTokenService;
import com.wisewater.fans.pojo.CFan;
import com.wisewater.fans.repository.CFanRepository;
import com.wisewater.fans.service.CFanService;
import com.wisewater.system.pojo.SDictionary;
import com.wisewater.system.repository.SDictionaryRepository;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.wechatpublic.message.out.OutArticle;
import com.wisewater.wechatpublic.message.out.OutNewsMessage;
import com.wisewater.wechatpublic.message.out.OutTextMessage;
import com.wisewater.wechatpublic.model.WxUser;
import com.wisewater.wechatpublic.pojo.BtnType;
import com.wisewater.wechatpublic.util.MessageUtil;
import com.wisewater.wechatpublic.util.WxUserUtil;

/**
 * 接收并处理用户发送的自定义消息
 * 
 * @author JSY
 * @date 2014-08-06
 */
@Service
public class MessageService {

	@Autowired
	private CFanRepository fanRepository;

	@Autowired
	private MenuClickService mc;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private BAccessTokenService accessTokenService;

	@Autowired
	private SDictionaryRepository dictionaryRepository;

	@Autowired
	private LoadConstant loadConstant;

	@Autowired
	private CFanService fanService;

	@Autowired
	private CAutoDefaultService cautoDefaultService;

	/**
	 * 事件消息的处理
	 * 
	 * @param content
	 * @param requestMap
	 * @return
	 */
	public String eventTypeMsg(Map<String, String> requestMap, String token) {
		// 事件KEY值，与创建自定义菜单时指定的KEY值对应
		String eventKey = requestMap.get("EventKey");
		System.out.println("事件值:" + eventKey);
		// 用户openId
		// 创建图文消息
		OutNewsMessage newsMessage = new OutNewsMessage();
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		String FromUserName = requestMap.get("FromUserName");
		CFan cfan = fanRepository.findByOpenIDAndToken(FromUserName, token);
		// 创建菜单点击事件处理器
		// MenuClickService mc = new MenuClickService();
		BtnType btnType = BtnType.valueOf(eventKey);
		switch (btnType) {
		// 水费账单
		case waterBill:
			return mc.waterBill(cfan, newsMessage, requestMap, token);
			// 信息登记
		case fansBind:
			return mc.fansBind(cfan, newsMessage, requestMap, token);
			// 缴纳水费
		case waterPay:
			return mc.waterPay(cfan, newsMessage, requestMap, token);
		case feedback:
			return mc.feedback(cfan, newsMessage, requestMap, token);
		
		default:
			break;
		}
		// 将图文消息对象转换成xml字符串
		return MessageUtil.messageToXml(newsMessage);
	}

	/**
	 * 
	 * @param requestMap
	 * @return gawen.chen 2015年4月10日下午2:14:46 描述：关注事件
	 */
	public boolean subscribe(Map<String, String> requestMap, String token) {
		String openID = requestMap.get("FromUserName");
		System.out.println("关注者的微信号:" + openID);

		BAccessTokenForm accessToken = accessTokenService
				.checkUpdateAccessTokenByToken(token);

		if (accessToken == null) { // 未完成配置
			return false;
		}

		WxUser wxUser = WxUserUtil.getUserInfo(accessToken.getAccessToken(),
				openID);

		if (wxUser != null) {
			CFan fan = fanRepository.findByOpenIDAndToken(openID, token);

			if (fan == null) {
				fan = new CFan();
			}

			// emoji表情过滤
			Pattern emojiPattern = Pattern
					.compile(
							"[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
							Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
			Matcher emojiMatcher = emojiPattern.matcher(wxUser.getNickname());
			String nickName = emojiMatcher.replaceAll("");
			nickName = nickName.replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+",
					"");

			fan.setOpenID(wxUser.getOpenid());
			fan.setNickName(nickName);
			fan.setIsSubscribe(1);
			fan.setIsLogicDel(0);
			if (wxUser.getSex() == 1) { // 男
				SDictionary gender = dictionaryRepository
						.findByLogicID("gender01");
				fan.setGender(gender);
			} else if (wxUser.getSex() == 2) { // 女
				SDictionary gender = dictionaryRepository
						.findByLogicID("gender02");
				fan.setGender(gender);
			} else { // 未知
				SDictionary gender = dictionaryRepository
						.findByLogicID("gender03");
				fan.setGender(gender);
			}
			fan.setCountry(wxUser.getCountry());
			fan.setProvinceName(wxUser.getProvince());
			fan.setCityName(wxUser.getCity());
			fan.setSubscribeDate(new Date());
			fan.setToken(token);

			fan = fanRepository.save(fan);

			fanService.saveFanUserLogicDel(fan, 0);

			return true;
		}

		return false;
	}

	// 取消关注
	public boolean unSubscribe(Map<String, String> requestMap, String token) {
		String openID = requestMap.get("FromUserName");

		System.out.println("取消关注");

		CFan fan = fanRepository.findByOpenIDAndToken(openID, token);
		if (fan != null) {
			fan.setIsSubscribe(0);
			fan.setUnsubscribeDate(new Date());
			fan = fanRepository.save(fan);
			if (fan != null) {
				fanService.saveFanUserLogicDel(fan, 1);
			}
			return true;
		}
		return false;

	}

	/**
	 * 查询用户是否绑定
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isbind(Map<String, String> requestMap) {
		System.out.println("查询该微信用户是否绑定户号");
		/*
		 * List<SUser> userList = userRepository.selectUserBywx(requestMap
		 * .get("FromUserName")); if (null != userList && userList.size() > 0) {
		 * return true; }
		 */
		return false;
	}

	/**
	 * 统一回复
	 * 
	 * @return
	 */
	public String allReply(Map<String, String> requestMap, String token) {
		System.out.println("统一回复allReply");
		String ToUserName = requestMap.get("ToUserName");
		String FromUserName = requestMap.get("FromUserName");
		
		// 其他水司的关注时回复
		CAutoDefaultForm cautoDefaultForm = cautoDefaultService
				.findCAutoDefaulByToken(token);
		if (cautoDefaultForm != null) {
			// 文本
			if ("answerType1".equals(cautoDefaultForm.getSdictionary()
					.getLogicID())) {
				// 创建文本消息
				OutTextMessage textMessage = new OutTextMessage();
				// gyh
				textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				textMessage.setContent(cautoDefaultForm.getTextContent());
				textMessage.setToUserName(FromUserName);
				textMessage.setFromUserName(ToUserName);
				textMessage.setCreateTime(new Date().getTime());
				return MessageUtil.messageToXml(textMessage);
			} else if ("answerType2".equals(cautoDefaultForm.getSdictionary()
					.getLogicID())) // 图文
			{
				// 创建图文消息
				OutNewsMessage newsMessage = new OutNewsMessage();
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				List<OutArticle> articleList = new ArrayList<OutArticle>();

				OutArticle article = new OutArticle();
				article.setTitle(cautoDefaultForm.getTitle());
				article.setDescription(cautoDefaultForm.getDescription());
				if (cautoDefaultForm.getPageUrl() != null
						&& !"".equals(cautoDefaultForm.getPageUrl())) {
					article.setUrl(cautoDefaultForm.getPageUrl());
				} else {
					article.setUrl(loadConstant.getWebSitePath()
							+ "/cautoDefault_article.do?token=" + token);
				}
				article.setPicUrl(loadConstant.getWebSitePath()
						+ loadConstant.getPicPath() + token + "/"
						+ cautoDefaultForm.getPicName());
				articleList.add(article);
				// 设置图文消息个数
				newsMessage.setArticleCount(articleList.size());
				newsMessage.setToUserName(FromUserName);
				newsMessage.setFromUserName(ToUserName);
				newsMessage.setCreateTime(new Date().getTime());
				// 设置图文消息包含的图文集合
				newsMessage.setArticles(articleList);
				return MessageUtil.messageToXml(newsMessage);
			} else {
				return null;
			}
		} else {
			// 创建文本消息
			OutTextMessage textMessage = new OutTextMessage();
			// gyh
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(convert(loadConstant.getAllReply()));
			textMessage.setToUserName(FromUserName);
			textMessage.setFromUserName(ToUserName);
			textMessage.setCreateTime(new Date().getTime());
			return MessageUtil.messageToXml(textMessage);
		}

	}

	/**
	 * 模板消息发送完成
	 * 
	 * @param requestMap
	 * @param token
	 * @return XingXingLvCha 2015年9月10日 下午2:24:28
	 */
	public String templateFinishTypeMsg(Map<String, String> requestMap,
			String token) {
		// String ToUserName = requestMap.get("ToUserName");
		// String FromUserName = requestMap.get("FromUserName");
		String CreateTime = formatTime(requestMap.get("CreateTime"));
		String MsgID = requestMap.get("MsgID");
		String Status = requestMap.get("Status");
		Client client;
		try {
			client = new Client(new URL(loadConstant.getWebServicePath()));
			client.invoke("templateFinish", new Object[] { MsgID, CreateTime,
					Status, token });

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 文字内容的消息处理
	 * 
	 * @param requestMap
	 * @param token
	 * @return XingXingLvCha 2015年4月9日 下午8:02:13
	 */
	public String textTypeMsg(Map<String, String> requestMap, String token) {
		System.out.println("文字内容的消息处理");
		String ToUserName = requestMap.get("ToUserName");
		String FromUserName = requestMap.get("FromUserName");
		String CreateTime = requestMap.get("CreateTime");
		String Content = requestMap.get("Content");
		System.out.println(formatTime(CreateTime));
		AnswerForm answer = answerService.answerMessage(Content, FromUserName,
				token, formatTime(CreateTime));
		if (answer.getAnswerType() == 1) {
			// 创建文本消息
			OutTextMessage textMessage = new OutTextMessage();
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(answer.getContent());
			textMessage.setToUserName(FromUserName);
			textMessage.setFromUserName(ToUserName);
			textMessage.setCreateTime(new Date().getTime());
			return MessageUtil.messageToXml(textMessage);
		}
		if (answer.getAnswerType() == 2) {
			// 创建图文消息
			OutNewsMessage newsMessage = new OutNewsMessage();
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			List<OutArticle> articleList = new ArrayList<OutArticle>();
			OutArticle article = new OutArticle();
			article.setTitle(answer.getTitle());
			article.setDescription(answer.getDescription());
			article.setUrl(answer.getUrl());
			article.setPicUrl(answer.getPicUrl());
			articleList.add(article);
			// 设置图文消息个数
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setToUserName(FromUserName);
			newsMessage.setFromUserName(ToUserName);
			newsMessage.setCreateTime(new Date().getTime());
			// 设置图文消息包含的图文集合
			newsMessage.setArticles(articleList);
			return MessageUtil.messageToXml(newsMessage);
		}
		if (answer.getAnswerType() == 3) {
			// 转给多客服
			OutTextMessage transterKf = new OutTextMessage();
			transterKf.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TRANSFER);
			transterKf.setToUserName(FromUserName);
			transterKf.setFromUserName(ToUserName);
			transterKf.setCreateTime(new Date().getTime());
			return MessageUtil.messageToXml(transterKf);
		}

		return null;
	}

	/**
	 * 图片消息的处理
	 * 
	 * @param content
	 * @param requestMap
	 * @return
	 */
	public String imageTypeMsg(Map<String, String> requestMap) {
		System.out.println("图片消息的处理");
		// 创建文本消息
		OutTextMessage textMessage = new OutTextMessage();
		return MessageUtil.messageToXml(textMessage);
	}

	/**
	 * 地理位置信息的处理
	 * 
	 * @param content
	 * @param requestMap
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String locationTypeMsg(Map<String, String> requestMap)
			throws UnsupportedEncodingException {
		System.out.println("地理位置信息的处理");
		// 创建文本消息
		OutTextMessage textMessage = new OutTextMessage();
		return MessageUtil.messageToXml(textMessage);
	}

	/**
	 * 链接信息的处理
	 * 
	 * @param content
	 * @param requestMap
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String linkTypeMsg(Map<String, String> requestMap)
			throws UnsupportedEncodingException {
		System.out.println("链接信息的处理");
		// 创建文本消息
		OutTextMessage textMessage = new OutTextMessage();
		return MessageUtil.messageToXml(textMessage);
	}

	/**
	 * 语音信息的处理
	 * 
	 * @param content
	 * @param requestMap
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String voiceTypeMsg(Map<String, String> requestMap)
			throws UnsupportedEncodingException {
		System.out.println("语音信息的处理");
		// 创建文本消息
		OutTextMessage textMessage = new OutTextMessage();
		return MessageUtil.messageToXml(textMessage);
	}

	/**
	 * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）
	 *
	 * @param createTime
	 *            消息创建时间
	 * @return
	 */
	public static String formatTime(String createTime) {
		// 将微信传入的CreateTime转换成long类型，再乘以1000
		long msgCreateTime = Long.parseLong(createTime) * 1000L;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(msgCreateTime));
	}

	/**
	 * get方法中文乱码 转码
	 * 
	 * @param target
	 * @return XingXingLvCha 2015年4月8日 上午11:03:02
	 */
	public static String convert(String target) {
		try {
			return new String(target.trim().getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return target;
		}
	}

}
