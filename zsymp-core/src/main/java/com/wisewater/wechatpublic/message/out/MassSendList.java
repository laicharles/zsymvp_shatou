package com.wisewater.wechatpublic.message.out;

import java.util.List;

/**
 * Write a summarising sentence of this class first. Explain its responsibility afterwards. 
 * Don't explain how it works, but rather what its purpose is. You may want to give usage examples, 
 * if helpful.
 * <p/>
 * TODO(tmh) Write sensible documentation for this class.
 * <p/>
 */
public class MassSendList {
    
    private List<String> touser;

    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

}
