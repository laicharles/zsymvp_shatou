package com.wisewater.wechatpublic.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	private StringUtil() {
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;

		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static String trimToEmpty(String str) {
		return str != null ? str.trim() : "";
	}

	public static final boolean isInt(String orginal) {
		String regex = "^(-|\\+)?\\d+$";
		return isMatch(regex, orginal);
	}

	public static final boolean isFloat(String orginal) {
		if (isInt(orginal))
			return true;
		String regex = "^(-|\\+)?\\d+\\.\\d*$";
		return isMatch(regex, orginal);
	}

	private static final boolean isMatch(String regex, String orginal) {
		if (orginal == null || orginal.trim().equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher isNum = pattern.matcher(orginal);
		return isNum.matches();
	}

	/**
	 * 数字验证
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 这是典型的随机洗牌算法。 流程是从备选数组中选择一个放入目标数组中，将选取的数组从备选数组移除（放至最后，并缩小选择区域） 算法时间复杂度O(n)
	 * 
	 * @return 随机8为不重复数组
	 */
	public static String generateNumber() {
		String no = "";
		// 初始化备选数组
		int[] defaultNums = new int[10];
		for (int i = 0; i < defaultNums.length; i++) {
			defaultNums[i] = i;
		}

		Random random = new Random();
		int[] nums = new int[LENGTH];
		// 默认数组中可以选择的部分长度
		int canBeUsed = 10;
		// 填充目标数组
		for (int i = 0; i < nums.length; i++) {
			// 将随机选取的数字存入目标数组
			int index = random.nextInt(canBeUsed);
			nums[i] = defaultNums[index];
			// 将已用过的数字扔到备选数组最后，并减小可选区域
			swap(index, canBeUsed - 1, defaultNums);
			canBeUsed--;
		}
		if (nums.length > 0) {
			for (int i = 0; i < nums.length; i++) {
				no += nums[i];
			}
		}

		return no;
	}

	private static final int LENGTH = 8;

	private static void swap(int i, int j, int[] nums) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static String generateNumber2() {
		String no = "";
		int num[] = new int[8];
		int c = 0;
		for (int i = 0; i < 8; i++) {
			num[i] = new Random().nextInt(10);
			c = num[i];
			for (int j = 0; j < i; j++) {
				if (num[j] == c) {
					i--;
					break;
				}
			}
		}
		if (num.length > 0) {
			for (int i = 0; i < num.length; i++) {
				no += num[i];
			}
		}
		return no;
	}

	public static final String inputStream2String(InputStream in)
			throws UnsupportedEncodingException, IOException {
		if (in == null)
			return "";

		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n, "UTF-8"));
		}
		return out.toString();
	}

	// 字符串位数不够补空格，补零在右边补零
	public static String normsStr(String src, int num, String car)
			throws UnsupportedEncodingException {
		String norm = car;
		StringBuffer sbf = new StringBuffer(src);
		for (int i = 0; i < num - src.getBytes("gbk").length; i++) {
			if (norm == null) {
				car = " ";
				sbf.append(car);
			} else if (car.equals("0")) {
				sbf = new StringBuffer(car + new String(sbf));
			} else {
				sbf.append(car);
			}
		}
		return new String(sbf);
	}

	public static String generateID() {
		int r1 = (int) (Math.random() * (10));// 产生5个0-9的随机数
		int r2 = (int) (Math.random() * (10));
		int r3 = (int) (Math.random() * (10));
		int r4 = (int) (Math.random() * (10));
		int r5 = (int) (Math.random() * (10));
		long now = System.currentTimeMillis();// 一个13位的时间戳
		return String.valueOf(now) + String.valueOf(r1) + String.valueOf(r2)
				+ String.valueOf(r3) + String.valueOf(r4) + String.valueOf(r5);
	}

	// 字符串截断后转成list<String>
	public static List<String> strToList(String strContent, String reg) {
		String[] str = strContent.split(reg);
		List<String> idList = new ArrayList<String>();
		for (String s : str) {
			idList.add(s);
		}
		return idList;
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	public static double percentFmatter(double a) {
		DecimalFormat df = new DecimalFormat("#####0.00");
		return Double.valueOf(df.format(a));
	}
	
	/**
	 * 隐藏字符串部分信息
	 * @param address
	 * @return
	 */
	public static String hideString(String address){
		if(StringUtils.isEmpty(address)){
			return "";
		}
		int len = address.length();
		if(len==1){
			return "*";
		}else if(len==2){
			return address.substring(0, 1)+"*";
		}else if(len<=5){
			return address.substring(0, len-2)+"**";
		}else if(len<=10){
			return address.substring(0, len-3)+"***";
		}
		return address.substring(0, len-6)+"*****";
	}
	
	/**
	 * 隐藏字符串部分信息
	 * @param address
	 * @return
	 */
	public static String hideUserName(String userName){
		if(StringUtils.isEmpty(userName)){
			return "";
		}
		int len = userName.length();
		return "*"+userName.substring(1, len);
		/*if(len==1){
			return "*";
		}else if(len==2){
			return "*"+userName.substring(1, len);
		}else if(len==3){
			return "*"+userName.substring(1, len);
		}else if(len<=5){
			return "**"+userName.substring(len-2, len);
		}else if(len<=10){
			return "***"+userName.substring(len-3, len);
		}
		return "*****"+userName.substring(len-5, len);*/
	}
	
	public static void main(String[] args) {
		System.out.println(hideUserName(null));
		
	}
	
	public static String starUtil(int times){
		String star = "";
		for(int i=1;i<=times;i++){
			star += star+"*";
		}
		return star;
	}
	
	/***
	 * 隐藏手机中间四位数，例：137****2563
	 * @param mobile
	 * @return
	 */
	public static String hideMobile(String mobile){
		if(StringUtils.isEmpty(mobile)){
			return "";
		}
		return mobile.substring(0, 3)+"****"+mobile.substring(7, mobile.length());
	}
	

	public static String hideEmail(String email){
		if(StringUtils.isEmpty(email)){
			return "";
		}
		return "***"+email.substring(email.indexOf("@"), email.length());
	}
}
