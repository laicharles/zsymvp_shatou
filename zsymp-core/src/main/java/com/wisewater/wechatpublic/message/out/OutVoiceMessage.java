package com.wisewater.wechatpublic.message.out;
/**
 * 语音消息
 * @author Administrator
 *
 */
public class OutVoiceMessage extends OutBaseMessage{
	//语音
	private OutVoice Voice;

	public OutVoice getVoice() {
		return Voice;
	}

	public void setVoice(OutVoice voice) {
		Voice = voice;
	}
	
}
