package com.wisewater.util.service;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;
@Service
public class CommonServiceImpl implements CommonService{

	/**
	 * get方法中文乱码 转码
	 * @param target
	 * @return
	 * XingXingLvCha
	 * 2015年4月8日 上午11:03:02
	 */
	public String convert(String target) { 
        try {  
            return new String(target.trim().getBytes("ISO-8859-1"), "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            return target;  
        }  
    }
	
	/**
	 * 字符串转码
	 * @param parm
	 * @return
	 * XingXingLvCha
	 * 2015年4月8日 上午11:06:02
	 */
	@Override
	public String encodeGetParm(String parm){
		return parm == null ? null : convert(parm);
	}
	
	/**
	 * like查询
	 * @param s
	 * @return
	 * XingXingLvCha
	 * 2015年4月8日 上午11:11:35
	 */
	@Override
	public String createLikeSearch(String s) {
		if (s == null || "".equals(s)) {
			return "";
		}
		if (s.indexOf("%") >= 0) {
			return s;
		}
		return "%" + s + "%";
	}

	
}
