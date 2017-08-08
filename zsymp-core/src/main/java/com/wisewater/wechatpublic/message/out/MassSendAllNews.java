package com.wisewater.wechatpublic.message.out;

import com.wisewater.wechatpublic.util.MassSendUtil;

public class MassSendAllNews extends MassSendAll{
    
    private MassSendType mpnews;

    private String msgtype= MassSendUtil.mass_send_type_mpnews;

    public MassSendType getMpnews() {
        return mpnews;
    }

    public void setMpnews(MassSendType mpnews) {
        this.mpnews = mpnews;
    }

    public String getMsgtype() {
        return msgtype;
    }

//    public void setMsgtype(String msgtype) {
//        this.msgtype = msgtype;
//    }
    
}
