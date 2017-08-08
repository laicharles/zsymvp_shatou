package com.wisewater.wechatpublic.message.out;

import com.wisewater.wechatpublic.util.MassSendUtil;

public class MassSendAllImage extends MassSendAll{
    
    private MassSendType image;

    private String msgtype= MassSendUtil.mass_send_type_image;

    public MassSendType getImage() {
        return image;
    }

    public void setImage(MassSendType image) {
        this.image = image;
    }

    public String getMsgtype() {
        return msgtype;
    }

//    public void setMsgtype(String msgtype) {
//        this.msgtype = msgtype;
//    }
    
}
