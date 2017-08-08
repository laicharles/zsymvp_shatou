package com.wisewater.weixin.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.system.pojo.SFansOperateLog;
import com.wisewater.system.repository.SFansOperateLogRepository;
import com.wisewater.wechatpublic.util.MessageUtil;

/**
 * 核心服务类
 * 
 * @author JSY
 * @date 2014-08-06
 */
@Service
public class CoreService {

	@Autowired
	private MessageService ms;

	@Autowired
	private SFansOperateLogRepository fansOperateLogRepository;

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request, String token) {
		String respXml = null;
		try {
			System.out.print(request);
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 统一回复（系统统一回复，不支持用户自定义）
			// respXml = ms.allReply(requestMap);
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 用户openId
			String openId = requestMap.get("FromUserName");
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				System.out.println("用户发送了文本消息！");
				respXml = ms.textTypeMsg(requestMap, token);
				fansOperateLogRepository.save(new SFansOperateLog("用户发送文本消息", "文本消息", openId, new Date()));
				// gyh（自动回复/命令库/知识库）
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				System.out.println("用户发送了图片消息！");
				fansOperateLogRepository.save(new SFansOperateLog("用户发送图片消息", "图片消息", openId, new Date()));
				// gyh（自动回复/命令库/知识库）
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				System.out.println("用户发送了地理位置消息！");
				fansOperateLogRepository.save(new SFansOperateLog("用户发送地理位置消息", "地理位置消息", openId, new Date()));
				// gyh（自动回复/命令库/知识库）
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				System.out.println("用户发送了链接消息！");
				fansOperateLogRepository.save(new SFansOperateLog("用户发送链接消息", "链接消息", openId, new Date()));
				// gyh（自动回复/命令库/知识库）
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				System.out.println("用户发送了音频消息！");
				fansOperateLogRepository.save(new SFansOperateLog("用户发送音频消息","音频消息", openId, new Date()));
				// gyh（自动回复/命令库/知识库）
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				System.out.println("事件类型:" + eventType + "类型");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					System.out.println("用户关注微信公众号！");
					fansOperateLogRepository.save(new SFansOperateLog("用户关注微信公众号",  "关注", openId, new Date()));
					if (ms.subscribe(requestMap, token)) { // 关注成功回复
						respXml = ms.allReply(requestMap, token);
					} else { // 关注失败时回复
						// todo
						System.out.println("用户关注微信公众号失败失败失败！");
					}
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					System.out.println("用户取消关注微信公众号！");
					fansOperateLogRepository.save(new SFansOperateLog("用户取消关注微信公众号",  "取消关注", openId, new Date()));
					if (!ms.unSubscribe(requestMap, token)) { // 取消关注失败时的回复
						// respXml = ms.allReply(requestMap);
					}
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					System.out.println("用户点击了自定义菜单！");
					fansOperateLogRepository.save(new SFansOperateLog("用户点击功能菜单",  "点击菜单", requestMap.get("FromUserName"), new Date()));
					respXml = ms.eventTypeMsg(requestMap, token);
				}
				// 模板消息通知
				else if (eventType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEMPLATE)) {
					System.out.println("模板消息发送！");
					fansOperateLogRepository.save(new SFansOperateLog("用户点击模板发送菜单",  "点击菜单", requestMap.get("FromUserName"), new Date()));
					respXml = ms.templateFinishTypeMsg(requestMap, token);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}