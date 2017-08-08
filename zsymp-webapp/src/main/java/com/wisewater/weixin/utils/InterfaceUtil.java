package com.wisewater.weixin.utils;

import java.net.URL;

import org.apache.log4j.Logger;
import org.codehaus.xfire.client.Client;

/**
 * @author XingXinglvcha
 * 2016年1月5日 上午9:29:43
 */
public class InterfaceUtil {
	
	private static Logger logger = Logger.getLogger(InterfaceUtil.class);
	
	/**
	 * 通过http post方式获取接口链接返回的数据 json格式
	 * @author XingXinglvcha
	 * 2016年1月5日 上午10:20:23
	 * @param url
	 * @return
	 */
	public static String findJsonByHttpPost(String url,String method,Object...params){
		try {
			Client client = new Client(new URL(url)); //创建连接
			client.setTimeout(30000);
			Object[] results = client.invoke(method, params);
			String result = results[0].toString();
			
			StringBuffer sb = new StringBuffer();
			for (Object object : params) {
				sb.append(object+",");
			}
			logger.info("url:"+url+";method:"+method+"输入参数:"+sb.toString()+";result:"+result);
			
			return result;
		} catch (Exception e) {
			logger.error("从zsymp_webService获取到的数据出现异常：errorMsg:"+e.getMessage());
			StringBuffer sb = new StringBuffer();
			for (Object object : params) {
				sb.append(object+",");
			}
			logger.error("url:"+url+";method:"+method+"输入参数:"+sb.toString());
			e.printStackTrace();
			return "";
		}  
		
		/*HttpPost httpRequst = new HttpPost(url);//创建HttpPost对象  
		try {
			HttpParams httpParameters = new BasicHttpParams();  
			HttpConnectionParams.setConnectionTimeout(httpParameters, 600*1000);//设置请求超时 秒  
			HttpConnectionParams.setSoTimeout(httpParameters, 600*1000); //设置等待数据超时 秒 
			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			HttpResponse response = httpclient.execute(httpRequst); 
			//请求成功              
	        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
	        {
	        	HttpEntity httpEntity = response.getEntity();  
                return EntityUtils.toString(httpEntity);//取出应答字符串
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "";*/
	}
}
