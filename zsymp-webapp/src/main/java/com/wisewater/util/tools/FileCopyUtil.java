package com.wisewater.util.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileCopyUtil {
	public static void copy(String sourFolder, String desFolder) {
		File sourFile = null, desFile = null;
	    sourFile = new File(sourFolder);
	    if (!sourFile.isDirectory() || !sourFile.exists()) {
	        System.out.println("源文件夹不存在");
	    }else{ 
	    	desFile = new File(desFolder);
	    	desFile.mkdir();
	    	File[] fl = sourFile.listFiles();
	    	File file = desFile;
		    if (!file.exists()) // 如果文件夹不存在
		        file.mkdir(); // 建立新的文件夹
		    for (int i = 0; i < fl.length; i++) {
		        if (fl[i].isFile()) { // 如果是文件类型就复制文件
		            try {
		                FileInputStream fis = new FileInputStream(fl[i]);
		                FileOutputStream out = new FileOutputStream(new File(file
		                        .getPath()
		                        + File.separator + fl[i].getName()));
		                int count = fis.available();
		                byte[] data = new byte[count];
		                if ((fis.read(data)) != -1) {
		                    out.write(data); // 复制文件内容
		                }
		                out.close(); // 关闭输出流
		                fis.close(); // 关闭输入流
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		        if (fl[i].isDirectory()) { // 如果是文件夹类型
		            copy(sourFolder, file.getPath() + File.separator
		                    + fl[i].getName()); // 递归调用方法本身
		        }
		    }
	    }
	}   
	
	
	
	/** 
	* 复制单个文件 
	* @param oldPath String 原文件路径 如：c:/fqf.txt 
	* @param newPath String 复制后路径 如：f:/fqf.txt 
	* @return boolean 
	*/ 
	public static boolean copyFile(String oldPath, String newPath) { 
		try { 
			File oldfile = new File(oldPath); 
			if (oldfile.exists()) 
			{ 
				//文件存在时 
				InputStream inStream = new FileInputStream(oldPath); //读入原文件 
				File file=new File(newPath);
				
		        if(!file.getParentFile().exists()){
		        	file.getParentFile().mkdirs();
		        }
				FileOutputStream fs = new FileOutputStream(newPath); 
				byte[] buffer = new byte[1444]; 
				int byteread = 0; 
				while ( (byteread = inStream.read(buffer)) != -1) 
				{ 
					fs.write(buffer, 0, byteread); 
				}	
				inStream.close(); 
				fs.close();
			}
			else
			{
				URL fileUrl = new URL(oldPath);
				 URLConnection con = fileUrl.openConnection();  
		        //设置请求超时为5s  
		        con.setConnectTimeout(5000);  
		        con.setReadTimeout(5000);
				InputStream inStream = con.getInputStream(); //读入原文件 
				File file=new File(newPath);
				
		        if(!file.getParentFile().exists()){
		        	file.getParentFile().mkdirs();
		        }
				FileOutputStream fs = new FileOutputStream(newPath); 
				byte[] buffer = new byte[1444]; 
				int byteread = 0; 
				while ( (byteread = inStream.read(buffer)) != -1) 
				{ 
					fs.write(buffer, 0, byteread); 
				}	
				inStream.close(); 
				fs.close();
				//Thread.sleep(800);
			}
			
			
		}catch (Exception e) { 
			System.out.println("复制单个文件操作出错"); 
			e.printStackTrace(); 
			return false;
		}
		return true;

	} 
	
	/**
	 * 获取所有IMG标签的SRC路径
	 * @param content
	 * @return
	 * XingXingLvCha
	 * 2015年4月20日 下午1:50:21
	 */
	public static String[] getImgs(String content) {
		String img = "";
		Pattern p_image;
		Matcher m_image;
		String str = "";
		String[] images = null;
		String regEx_img = "(<img.*src\\s*=\\s*(.*?)[^>]*?>)";
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(content);
		while (m_image.find()) {
			img = m_image.group();
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)")
					.matcher(img);
			while (m.find()) {
				String tempSelected = m.group(1).replaceAll("\'", "")
						.replaceAll("\"", "");
				if ("".equals(str)) {
					str = tempSelected;
				} else {
					String temp = tempSelected;
					str = str + "," + temp;
				}
			}

		}

		if (!"".equals(str)) {

			images = str.split(",");

		}

		return images;

	}
	
}
