package com.wisewater.util.tools;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

/**
 * 线程类
 * @author XingXinglvcha
 * 2016年1月18日 下午4:07:09
 */
public class ControllerRunnable implements Runnable{
	
	private Logger logger = Logger.getLogger(ControllerRunnable.class);
	private String controllerUrl;
	
	public ControllerRunnable(String controllerUrl){
		this.controllerUrl = controllerUrl;
	}
	
	@SuppressWarnings({ "resource", "deprecation" })
	@Override
	public void run() {
		if(StringUtils.isEmpty(controllerUrl))
		{
			return;
		}
		try {
			controllerUrl = replaceBlank(controllerUrl);
			logger.info("开始执行url:"+controllerUrl);
			HttpGet  httpgets = new HttpGet(controllerUrl);
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httpgets);
			logger.info("执行完成;"+response);
			
		}  catch (Exception e) {
			e.printStackTrace();
		} 
    }
	
	 public static String replaceBlank(String str){
	    String dest = "";
	    if (str != null)
	    {
	      Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	      Matcher m = p.matcher(str);
	      dest = m.replaceAll("");
	    }
	    return dest;
	  }
	
}
