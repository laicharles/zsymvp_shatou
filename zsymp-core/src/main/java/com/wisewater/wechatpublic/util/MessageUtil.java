package com.wisewater.wechatpublic.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wisewater.wechatpublic.message.out.OutArticle;
import com.wisewater.wechatpublic.message.out.OutImageMessage;
import com.wisewater.wechatpublic.message.out.OutMusicMessage;
import com.wisewater.wechatpublic.message.out.OutNewsMessage;
import com.wisewater.wechatpublic.message.out.OutTextMessage;
import com.wisewater.wechatpublic.message.out.OutVideoMessage;
import com.wisewater.wechatpublic.message.out.OutVoiceMessage;

public class MessageUtil {
	// 请求消息类型：文本
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	// 转发请求消息类型：多客服系统
	public static final String REQ_MESSAGE_TYPE_TRANSFER = "transfer_customer_service";
	// 请求消息类型：图片
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	// 请求消息类型：语音
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	// 请求消息类型：视频
	public static final String REQ_MESSAGE_TYPE_VEDIO = "vedio";
	// 请求消息类型：地理位置
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	// 请求消息类型：链接
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	// 请求消息类型：事件推送
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	// 请求消息类型：模板消息发送
	public static final String REQ_MESSAGE_TYPE_TEMPLATE = "TEMPLATESENDJOBFINISH";

	// 事件类型：subscribe（订阅）
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	// 事件类型：unsubscribe（取消订阅）
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	// 事件类型：scan（关注用户扫描带参数二维码）
	public static final String EVENT_TYPE_SCAN = "scan";
	// 事件类型：LOCATION（上报地理位置）
	public static final String EVENT_TYPE_LOCATION = "location";
	// 事件类型：CLICK（自定义菜单）
	public static final String EVENT_TYPE_CLICK = "CLICK";

	// 响应消息类型：文本
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	// 响应消息类型：图片
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	// 响应消息类型：语音
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	// 响应消息类型：视频
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	// 响应消息类型：音乐
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	// 响应消息类型：图片
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 解析微信发来的请求
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到XML根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
		// 遍历所有子节点，并装到map中
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}

	/**
	 * 扩展xstream使其支持CDATA
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				boolean cdata = true;

				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writText(QuickWriter writer, String text) {
					if (cdata) {
						System.out.println("有进入这里<![CDATA[]]>");
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}

				}
			};
		}
	});

	/**
	 * 文本消息对象转xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String messageToXml(OutTextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 图片消息对象转xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String messageToXml(OutImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}

	/**
	 * 语音消息对象转xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String messageToXml(OutVoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}

	/**
	 * 视频消息对象转xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String messageToXml(OutVideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}

	/**
	 * 音乐消息对象转xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String messageToXml(OutMusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * 图文消息对象转xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String messageToXml(OutNewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new OutArticle().getClass());
		return xstream.toXML(newsMessage);
	}

}
