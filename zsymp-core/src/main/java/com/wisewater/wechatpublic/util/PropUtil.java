package com.wisewater.wechatpublic.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropUtil {
	//根据key读取value
	 public static String readValue(String filePath,String key) {
	  Properties props = new Properties();
	        try {
	         InputStream in = new BufferedInputStream (new FileInputStream(filePath));
	         props.load(in);
	         String value = props.getProperty (key);
	            System.out.println(key+value);
	            return value;
	        } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	        }
	 }
}
