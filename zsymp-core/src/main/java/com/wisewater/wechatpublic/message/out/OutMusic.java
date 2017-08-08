package com.wisewater.wechatpublic.message.out;

/**
 * 音乐model
 * 
 * @author Administrator
 * 
 */
public class OutMusic {
	// 音乐标题
	private String Title;
	// 音乐描述
	private String Description;
	// 音乐链接
	private String HQMusicUrl;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}

}
