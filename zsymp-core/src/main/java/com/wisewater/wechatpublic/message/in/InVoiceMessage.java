package com.wisewater.wechatpublic.message.in;
/**
 * 語音消息
 * @author Administrator
 *
 */
public class InVoiceMessage extends InBaseMessage{
	//媒体ID
	private String MediaId;
	//语音格式
	private String Format;
	//语音识别结果，UTF8编码
	private String Recogintion;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getRecogintion() {
		return Recogintion;
	}
	public void setRecogintion(String recogintion) {
		Recogintion = recogintion;
	}
	
	
}
