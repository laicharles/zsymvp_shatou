package com.wisewater.wechatpublic.message.out;
/**
 * 文本消息
 * @author Administrator
 *
 */
public class OutTextMessage extends OutBaseMessage{
	//回复消息的内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
