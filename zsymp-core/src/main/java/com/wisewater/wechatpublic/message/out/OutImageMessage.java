package com.wisewater.wechatpublic.message.out;
/**
 * 图片消息
 * @author Administrator
 *
 */
public class OutImageMessage  extends OutBaseMessage{
	//图片
	private OutImage Image;

	public OutImage getImage() {
		return Image;
	}

	public void setImage(OutImage image) {
		Image = image;
	}
	
}
