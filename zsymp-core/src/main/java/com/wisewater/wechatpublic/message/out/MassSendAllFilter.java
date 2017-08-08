package com.wisewater.wechatpublic.message.out;

/**
 * Write a summarising sentence of this class first. Explain its responsibility afterwards. 
 * Don't explain how it works, but rather what its purpose is. You may want to give usage examples, 
 * if helpful.
 * <p/>
 * TODO(tmh) Write sensible documentation for this class.
 * <p/>
 */
public class MassSendAllFilter {
    
    private boolean is_to_all; //TODO tmh hardcode it to true;
    
    private String group_id;

    public boolean isIs_to_all() {
        return is_to_all;
    }

    public void setIs_to_all(boolean is_to_all) {//TODO tmh remove this method;
        this.is_to_all = is_to_all;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {//TODO tmh remove this method;
        this.group_id = group_id;
    }
    
}
