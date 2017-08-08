package com.wisewater.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.fans.pojo.CFan;
import com.wisewater.system.pojo.SFansOperateLog;
import com.wisewater.system.repository.SFansOperateLogRepository;
import com.wisewater.util.tools.LoadConstant;
import com.wisewater.wechatpublic.message.out.OutArticle;
import com.wisewater.wechatpublic.message.out.OutNewsMessage;
import com.wisewater.wechatpublic.util.MessageUtil;
import com.wisewater.weixin.pojo.CClickTw;
import com.wisewater.weixin.repository.CClickTwRepository;
/**
 * 按钮点击事件处理
 * @author Alex
 * @date 2015-01-12
 */
@Service
public class MenuClickService {
	@Autowired
	private LoadConstant lc;
	
	@Autowired
	private CClickTwRepository clickTwRepository;
	

	
	/**
	 * 水费账单
	 * @param cfan
	 * @param newsMessage
	 * @param requestMap
	 * @param token
	 * @return
	 * gawen.chen
	 * 2015年4月10日下午2:14:17
	 * 描述：
	 */
	public String waterBill(CFan cfan, OutNewsMessage newsMessage, Map<String, String> requestMap,String token){
		List<OutArticle> articleList = new ArrayList<OutArticle>();
		OutArticle article = new OutArticle(); 
		CClickTw clickTw = clickTwRepository.findByToken(token,"twType02");
		if(clickTw==null){
			clickTw = new CClickTw();
			clickTw.setTitle("请配置水费帐单图文配置");
			clickTw.setDescription("请配置水费帐单图文配置");
		}
        article.setTitle(clickTw.getTitle());  
        article.setDescription(clickTw.getDescription());  
        
        String picPath = lc.getWebSitePath()+lc.getPicPath()+"/"+token+"/"+clickTw.getPicName();
        if("banding.jpg".equals(clickTw.getPicName()) || "waterbill.jpg".equals(clickTw.getPicName()) || 
 			   "watercost.jpg".equals(clickTw.getPicName())){
 				picPath = lc.getWebSitePath()+lc.getPicPath()+"management/"+ clickTw.getPicName();
 		}
        
        article.setPicUrl(picPath);
        if(cfan!=null){
        	article.setUrl(lc.getWebSitePath()+"/m/waterBill_bindList.do?openID="+requestMap.get("FromUserName")+"&token="+token);
        }
        articleList.add(article);  
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setToUserName(requestMap.get("FromUserName"));
		newsMessage.setFromUserName(requestMap.get("ToUserName"));
		newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setArticles(articleList);
 
        return MessageUtil.messageToXml(newsMessage);
	}
	
	/**
	 * 户号绑定
	 * @param cfan
	 * @param newsMessage
	 * @param requestMap
	 * @param token
	 * gawen.chen 修改
	 * 2015年4月21日上午9:03:17
	 * @return
	 */
	public String fansBind(CFan cfan, OutNewsMessage newsMessage, Map<String, String> requestMap,String token){
		List<OutArticle> articleList = new ArrayList<OutArticle>();
		OutArticle article = new OutArticle(); 
		CClickTw clickTw = clickTwRepository.findByToken(token,"twType01");
		if(clickTw==null){
			clickTw = new CClickTw();
			clickTw.setTitle("请配置户号绑定图文");
			clickTw.setDescription("请配置户号绑定图文");
		}
        article.setTitle(clickTw.getTitle());  
        article.setDescription(clickTw.getDescription());  
        
        String picPath = lc.getWebSitePath()+lc.getPicPath()+"/"+token+"/"+clickTw.getPicName();
        if("banding.jpg".equals(clickTw.getPicName()) || "waterbill.jpg".equals(clickTw.getPicName()) || 
 			   "watercost.jpg".equals(clickTw.getPicName())){
 				picPath = lc.getWebSitePath()+lc.getPicPath()+"management/"+ clickTw.getPicName();
 		}
        article.setPicUrl(picPath);
        if(cfan!=null){
        	article.setUrl(lc.getWebSitePath()+"/m/cfanuser_bindList.do?openID="+requestMap.get("FromUserName")+"&token="+token);
        }
        articleList.add(article);  
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setToUserName(requestMap.get("FromUserName"));
		newsMessage.setFromUserName(requestMap.get("ToUserName"));
		newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setArticles(articleList);

        return MessageUtil.messageToXml(newsMessage);
	}
	
	/**
	 * 缴纳水费
	 * @param cfan
	 * @param newsMessage
	 * @param requestMap
	 * @param token
	 * gawen.chen 修改
	 * 2015年4月21日上午9:03:17
	 * @return
	 */
	public String waterPay(CFan cfan, OutNewsMessage newsMessage, Map<String, String> requestMap,String token){
		List<OutArticle> articleList = new ArrayList<OutArticle>();
		OutArticle article = new OutArticle(); 
		CClickTw clickTw = clickTwRepository.findByToken(token,"twType03");
		if(clickTw==null){
			clickTw = new CClickTw();
			clickTw.setTitle("请配置缴纳水费图文");
			clickTw.setDescription("请配置缴纳水费图文");
		}
        article.setTitle(clickTw.getTitle());  
        article.setDescription(clickTw.getDescription());  
        String picPath = lc.getWebSitePath()+lc.getPicPath()+"/"+token+"/"+clickTw.getPicName();
        if("banding.jpg".equals(clickTw.getPicName()) || "waterbill.jpg".equals(clickTw.getPicName()) || 
 			   "watercost.jpg".equals(clickTw.getPicName())){
 				picPath = lc.getWebSitePath()+lc.getPicPath()+"management/"+ clickTw.getPicName();
 		}
        article.setPicUrl(picPath);
        if(cfan!=null){
        	article.setUrl(lc.getWebSitePath()+"/m/wxPayJudgement.do?openID=" + requestMap.get("FromUserName")+"&token=" + token);
        }
        articleList.add(article);  
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setToUserName(requestMap.get("FromUserName"));
		newsMessage.setFromUserName(requestMap.get("ToUserName"));
		newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setArticles(articleList);

        return MessageUtil.messageToXml(newsMessage);
	}
	
	public String feedback(CFan cfan, OutNewsMessage newsMessage, Map<String, String> requestMap,String token){
		List<OutArticle> articleList = new ArrayList<OutArticle>();
		OutArticle article = new OutArticle(); 
		CClickTw clickTw = clickTwRepository.findByToken(token,"twType04");
		if(clickTw==null){
			clickTw = new CClickTw();
			clickTw.setTitle("请配置互动反馈图文");
			clickTw.setDescription("请配置互动反馈图文");
		}
        article.setTitle(clickTw.getTitle());  
        article.setDescription(clickTw.getDescription());  
        article.setPicUrl(lc.getWebSitePath()+lc.getPicPath()+"/"+token+"/"+clickTw.getPicName());
        if(cfan!=null){
        	article.setUrl(lc.getWebSitePath()+"/m/showFeedbackAdd.do?openID=" + requestMap.get("FromUserName")+"&token=" + token);
        }
        articleList.add(article);  
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setToUserName(requestMap.get("FromUserName"));
		newsMessage.setFromUserName(requestMap.get("ToUserName"));
		newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setArticles(articleList);

        return MessageUtil.messageToXml(newsMessage);
	}
	
}
