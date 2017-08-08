package com.wisewater.util.tools;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class SecurityMD5 {

	public static String getMD5Str(String str) {
		MessageDigest md;
		try {
			// 生成一个MD5加密计算摘要
			md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			String pwd = new BigInteger(1, md.digest()).toString(16);

			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String makeMD5(String str) {  
		StringBuffer md5StrBuff = null;
		
		try {
			MessageDigest messageDigest = null;  
			messageDigest = MessageDigest.getInstance("MD5");  
			messageDigest.reset();  
			messageDigest.update(str.getBytes("UTF-8"));  

			byte[] byteArray = messageDigest.digest();  
			md5StrBuff = new StringBuffer();  

			for (int i = 0; i < byteArray.length; i++) {  
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  {
					md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
				}else{
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  

		return md5StrBuff.toString();  
	}  

	public static void main(String[] args) {
		String string = "daxin7C8FA3D7EB23A1CFC1B3B91894770492";
		String pwd1 = SecurityMD5.makeMD5(string).toUpperCase();
		String pwd2 = SecurityMD5.getMD5Str(string);
		System.out.println(pwd1+";length1:"+pwd1.length());
		System.out.println(pwd2+";length2:"+pwd2.length());
	}

	public static String getUUID(int num){ 
		String s = UUID.randomUUID().toString(); 
		//去掉“-”符号 
		String str = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
		return str.substring(0, num);
	} 
}
