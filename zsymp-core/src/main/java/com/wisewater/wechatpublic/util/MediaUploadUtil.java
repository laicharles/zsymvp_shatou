package com.wisewater.wechatpublic.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.wisewater.wechatpublic.message.out.UploadArticle;
import com.wisewater.wechatpublic.message.out.UploadNews;

public class MediaUploadUtil {
    
    public final static String media_upload_url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    
    public final static String news_upload_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
    
    public final static String media_uploa_type_img="image";
    public final static String media_uploa_type_voice="voice";
    public final static String media_uploa_type_video="video";
    public final static String media_uploa_type_thumb="thumb";

	/**
	 * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	 * 
	 * @param url
	 *            请求地址 form表单url地址
	 * @param filePath
	 *            文件在服务器保存路径
	 * @return String url的响应信息返回值
	 * @throws IOException
	 */
	public static String upload(String accessToken, String type, String filePath) throws IOException {

		String result = null;
		
		String url = media_upload_url.replace("ACCESS_TOKEN", accessToken);
		url = url.replace("TYPE", type);
		
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		/**
		 * 第一部分
		 */
		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		/**
		 * 设置关键值
		 */
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);

		// 请求正文信息

		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
				+ file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}

		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		if(jsonObj!=null){
		    try{
		        String mediaId = jsonObj.getString("media_id");
                
                System.out.println("---->"+jsonObj);
                System.out.println("---->"+mediaId);
                
                return mediaId;
            }catch(JSONException e){
                System.out.println("上传多媒体文件失败");
                e.printStackTrace();
                return "";
            }
		}
		
		return "";
	}
	
	public static String uploadArticles(UploadNews news ,
            String accessToken)  {
        
        String result = "";
        
        String url = news_upload_url.replace("ACCESS_TOKEN", accessToken);
        
        // 将菜单对象转成JSON字符串
        String jsonNews = JSONObject.fromObject(news).toString();
        
        // 发起POST请求创建菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonNews);
        
        if (null != jsonObject) {
            
            try{
                String mediaId = jsonObject.getString("media_id");
                
                System.out.println("---->"+jsonObject);
                System.out.println("---->"+mediaId);
                
                result = mediaId;
            }catch(JSONException e){
                result="";
                System.out.println("上传图文消息失败");
                e.printStackTrace();
            }
            
        }
        
        return result;
    }

	public static void main(String[] args) throws IOException {
	    //TODO tmh the internet file is not ok?
//		String filePath = "http://localhost:8080/zsy-webapp/resources/attached/0123456789/201504092002050.jpg";
		String filePath = "http://f.hiphotos.baidu.com/image/w%3D310/sign=d28e1568272dd42a5f0907aa333a5b2f/7dd98d1001e93901249bcd3f79ec54e736d19611.jpg";
	    
//		String filePath = "F:/zsy/dev_env/apache-tomcat-7.0.59/webapps/zsy-webapp/resources/attached/0123456789/201504092002050.jpg";
		String result = null;
		result = upload("1q5PFuDa4XMxHdBm1H0SMwkeaX8M1Ia04RnYb0IHSVwGpa99JSYwBwAmfO8g6hC54ehTwbvPXk01nz2ct78mPVLP71rC8KCy_nsV3HwLbhYQ", media_uploa_type_img, filePath);
		System.out.println(result);
		
//		boolean sucess=false;
//		UploadNews news=new UploadNews();
//		List<UploadArticle> articles=new ArrayList<UploadArticle>();
//		news.setArticles(articles);
//		
//		UploadArticle art1=new UploadArticle();
//		art1.setAuthor("morgan");
//		art1.setContent("Copyright 2012");
//		art1.setContent_source_url("http://www.baidu.com");
//		art1.setDigest("test art1test art1");
//		art1.setShow_cover_pic("0");
//		art1.setThumb_media_id("xb-TQTIgRprDr2R_MlRHQNq708qJH0nZ5EYL5an0RXGGmFi3I1Iq83Jqu1Y34XuI");
//		art1.setTitle("test art1");
//		articles.add(art1);
//		
//		UploadArticle art2=new UploadArticle();
//        art2.setAuthor("morgan");
//        art2.setContent("Copyright 2012 <em>珠海智通信息技术限公司 ALL RIGHTS RESERVED</em>&nbsp;&nbsp;<a href=\"http://www.miitbeian.gov.cn/\" target=\"_blank\">粤ICP备12058130号</a>");
//        art2.setContent_source_url("http://www.baidu.com");
//        art2.setDigest("test art2test art2");
//        art2.setShow_cover_pic("0");
//        art2.setThumb_media_id("xb-TQTIgRprDr2R_MlRHQNq708qJH0nZ5EYL5an0RXGGmFi3I1Iq83Jqu1Y34XuI");
//        art2.setTitle("test art2");
//        articles.add(art2);
//		
//        sucess = uploadArticles(news,"1q5PFuDa4XMxHdBm1H0SMwkeaX8M1Ia04RnYb0IHSVwGpa99JSYwBwAmfO8g6hC54ehTwbvPXk01nz2ct78mPVLP71rC8KCy_nsV3HwLbhYQ");
//		
//		System.out.println(sucess);
		
	}
}
