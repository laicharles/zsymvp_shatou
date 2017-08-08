package com.wisewater.wechatpublic.message.out;

/**
 * 音乐消息
 * 
 * @author Administrator
 * 
 */
public class OutMusicMessage extends OutBaseMessage {
	// 音乐
	private OutMusic Music;

	public OutMusic getMusic() {
		return Music;
	}

	public void setMusic(OutMusic music) {
		Music = music;
	}

}
