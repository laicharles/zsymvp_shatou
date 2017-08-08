package com.wisewater.wechatpublic.message.out;

import com.wisewater.wechatpublic.util.MassSendUtil;

/**
 * Write a summarising sentence of this class first. Explain its responsibility afterwards. 
 * Don't explain how it works, but rather what its purpose is. You may want to give usage examples, 
 * if helpful.
 * <p/>
 * TODO(tmh) Write sensible documentation for this class.
 * <p/>
 */

public class MassSendListText extends MassSendList{
    
    private MassSendTypeText text;

    private String msgtype= MassSendUtil.mass_send_type_text;

    public MassSendTypeText getText() {
        return text;
    }

    public void setText(MassSendTypeText text) {
        this.text = text;
    }

    public String getMsgtype() {
        return msgtype;
    }

//    public void setMsgtype(String msgtype) {
//        this.msgtype = msgtype;
//    }
    
}

