package com.wisewater.wechatpublic.util;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisewater.wechatpublic.message.out.MassSendAll;
import com.wisewater.wechatpublic.message.out.MassSendAllFilter;
import com.wisewater.wechatpublic.message.out.MassSendAllNews;
import com.wisewater.wechatpublic.message.out.MassSendPreview;
import com.wisewater.wechatpublic.message.out.MassSendToDel;
import com.wisewater.wechatpublic.message.out.MassSendType;

public class MassSendUtil {
    
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
    
    public final static String mass_sendall_url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
    public final static String mass_send_del_url = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN";
    public final static String mass_send_pre_url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";

    public final static String mass_send_type_mpnews = "mpnews";
    public final static String mass_send_type_text = "text";
    public final static String mass_send_type_image = "image";
    
    public static String massSendAll(MassSendAll all ,
            String accessToken)  {
        
        String msg_id = "";
        
        String url = mass_sendall_url.replace("ACCESS_TOKEN", accessToken);
        
        // 将菜单对象转成JSON字符串
        String jsonMass= JSONObject.fromObject(all).toString();
        
        log.info("群发内容："+jsonMass);
        
        // 发起POST请求创建菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMass);
        
        log.info("输出结果jsonObject:---->"+jsonObject);
        
        if (null != jsonObject) {
            
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            
            if (0 == errorCode) {
                msg_id = jsonObject.getString("msg_id");
            } else {
                msg_id = "";
                log.error("群发消息提交失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        
        return msg_id;
    }
    
    public static boolean massSendDel(MassSendToDel del ,
            String accessToken)  {
        
        boolean result = false;
        
        String url = mass_send_del_url.replace("ACCESS_TOKEN", accessToken);
        
        // 将菜单对象转成JSON字符串
        String jsonDel= JSONObject.fromObject(del).toString();
        
        // 发起POST请求创建菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonDel);
        
        if (null != jsonObject) {
            
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            
            log.info("---->"+jsonObject);
            
            if (0 == errorCode) {
                result = true;
            } else {
                result = false;
                log.error("删除群发消息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        
        return result;
    }
    
    public static String massSendPreview(MassSendPreview pre ,
            String accessToken)  {
        
        String msgId = "";
        
        String url = mass_send_pre_url.replace("ACCESS_TOKEN", accessToken);
        
        // 将菜单对象转成JSON字符串
        String jsonPre= JSONObject.fromObject(pre).toString();
        
        // 发起POST请求创建菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonPre);
        
        if (null != jsonObject) {
            
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            
            log.info("---->"+jsonObject);
            
            if (0 == errorCode) {
                msgId = "dummy";
            } else {
                msgId = "";
                log.error("预览消息提交失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        
        return msgId;
    }
    
    public static void main1(String[] args) {
        MassSendAllNews news=new MassSendAllNews();
        
        MassSendAllFilter filter = new MassSendAllFilter();
        filter.setIs_to_all(false);
        filter.setGroup_id("106");
        
        news.setFilter(filter);
        MassSendType type=new MassSendType();
        type.setMedia_id("4louFGhtDCfhrLcnLEThpZXFy4I9zK_-eLdc9R52cKI7AsFh51mJx3eY78CsA1jA");
        news.setMpnews(type);
        
        System.out.println(massSendAll(news,"1cMVnhPrBRHyzTpoXdWwWHByDMhWM7zl2fE_nh2LZ6KhXWTULDjwtzz14K7t_T5S4lPdytDxx65TODEbTWNa66QykExFyBsMXPQ4iOR6OUk" ));
    }
    
    
}
