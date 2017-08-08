package com.wisewater.wechatpublic.message.in;

/**
 * 视频消息
 * 
 * @author Administrator
 * 
 */
public class InVideoMessage extends InBaseMessage {
	// 视频消息媒体ID
	private String MediaId;
	// 视频消息缩略图的媒体ID
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
