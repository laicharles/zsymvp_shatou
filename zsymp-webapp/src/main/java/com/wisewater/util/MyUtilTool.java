package com.wisewater.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;


public class MyUtilTool {
	private static Logger logger = Logger.getLogger(MyUtilTool.class);
	/**
	 * 日期格式参数
	 * @author 11
	 */
	public static class DATE_PARAM{
		public static String STANDARD_DATE = "yyyy-MM-dd HH:mm:ss";
		public static String STANDARD_DATE_SIMPLE = "yyyy-MM-dd";
		public static String CHN_DATE = "yyyy年MM月dd日 HH时mm分ss秒";
		public static String CHN_DATE_SIMPLE = "yyyy年MM月dd日";
		public static String CHN_DATE_SIMPLE_XXX = "yyyyMMdd-hhmmss-0000";
	}
	
	/**
	 * 日期处理工具
	 * @author 11
	 */
	public static class DATE{
	
		/**
		 * 获取当前时间
		 * @return
		 */
		public static Long getCurrentTime() {
			return (new Date()).getTime();
		}
		
		/**
		 * 获取当前日期的格式化字符串
		 * @param formateStr
		 * @return
		 */
		public static String currentDateString(String formateStr) {
			if (StringUtils.isEmpty(formateStr)) {
				formateStr = MyUtilTool.DATE_PARAM.STANDARD_DATE;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formateStr);
			try {
				return simpleDateFormat.format(new Date());
			} catch (Exception e) {	
				logger.error("日期格式化异常");
			}
			return null;
		}

		/**
		 * 日期格式化
		 * @param date
		 * @return String
		 */
		public static String dateToString(Date date,String formateStr) {
			if (StringUtils.isEmpty(formateStr)) {
				formateStr = MyUtilTool.DATE_PARAM.STANDARD_DATE;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formateStr);
			try {
				return simpleDateFormat.format(date);
			} catch (Exception e) {
				logger.error("日期格式化异常");
			}
			return null;
		}

		/**
		 * 字符串转换成日期
		 * 
		 * @param dateStr
		 * @return
		 */
		public static Date stringToDate(String dateStr,String formateStr) {
			if (StringUtils.isEmpty(formateStr)) {
				formateStr = MyUtilTool.DATE_PARAM.STANDARD_DATE;
			}
			if (dateStr != null && dateStr.length() > 0) {
				dateStr = formatDateStr(dateStr);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formateStr);
				try {
					return simpleDateFormat.parse(dateStr);
				} catch (ParseException e) {
					logger.error("日期格式化异常");
				}
			}
			return null;
		}
		
		
		/**
		 * 格式化日期字符串
		 * @param dateStr
		 * @return
		 */
		private static String formatDateStr(String dateStr){
			String[] strs = dateStr.split("-");
			String str ="";
			for (int i = 0; i < strs.length; i++) {
				String ele = strs[i];
				if (i>0) {
					if (ele.length()==1) {
						ele = "0"+ele;
					}
					str=str+"-"+ele;
				}else {
					str=ele;
				}
			}
			return str;
		}
		
		/**
		 * 将SQL DATE类型转换成Date类型
		 * 
		 * @param sqlDate
		 * @return
		 */
		public static Date sqlDateToDate(java.sql.Date sqlDate) {
			long time = sqlDate.getTime();
			Date date = new Date(time);
			return date;
		}

		/**
		 * 将Util Date类型转换成为Sql Date
		 * 
		 * @return
		 */
		public static Timestamp currentDateToSQLTimestamp() {
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;
		}
		
		/**
		 * 将Util Date类型转换成为Sql Date
		 * @param date
		 * @return
		 */
		public static Timestamp dateToSQLTimestamp(Date date){
			if (date!=null) {
				Timestamp timestamp = new Timestamp(date.getTime());
				return timestamp;
			}
			return null;
		}

		/**
		 * 将timestamp转换成为Date类型
		 * @param timestamp
		 * @return Date
		 */
		public static Date SQLTimestampToDate(Timestamp timestamp){
			if (timestamp!=null) {
				Date date = new Date(timestamp.getTime());
				return date;
			}
			return null;
		}
		
		/**
		 * 与系统时间比较
		 * @param expiredTime
		 * @return boolean
		 */
		public static boolean checkExpiredTime(Date expiredTime) {
			if (expiredTime != null) {
				Date nowDate = new Date();
				if (expiredTime.before(nowDate)) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		}
		
	
		/**
		 * 日期计算
		 * @param field
		 * @param calCount
		 * @return
		 */
		public static Date calculateDate(Date orgDate,int field,int calCount){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(orgDate);
			calendar.add(field, calCount);
			orgDate = calendar.getTime();
			return orgDate;
		}
		
		/**
		 * 设置修改日期内容
		 * @param orgDate
		 * @param field
		 * @param setValue
		 * @return
		 */
		public static Date calculateSetDate(Date orgDate,int field,int setValue){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(orgDate);
			calendar.set(field, setValue);
			orgDate = calendar.getTime();
			return orgDate;
		}
	}
	
	/**
	 * 字符串处理工具
	 * @author 11
	 */
	public static class STRING{
		/**
		 * 验证字符串是否为空
		 * 
		 * @param str
		 * @return
		 */
		public static boolean checkStrNull(String str) {
			if (str != null && str.trim().length() > 0) {
				return false;
			} else {
				return true;
			}
		}

		
		private static final int MAX_GENERATE_COUNT = 99999;
		private static int generateCount = 0;
		public static synchronized String getUniqueString() {
			if (generateCount > MAX_GENERATE_COUNT)
				generateCount = 0;
			String uniqueNumber = Long.toString(System.currentTimeMillis()) + Integer.toString(generateCount);
			generateCount++;
			return uniqueNumber;
		}
		
		/**
		 * 将字符串中的中文转成nicode码
		 * @param str
		 * @return
		 */
		public static String chineseToUnicode(String str){
			String result="";
			for (int i = 0; i < str.length(); i++){
	            int chr1 = (char) str.charAt(i);
	            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)
	                result+="\\u" + Integer.toHexString(chr1);
	            }else{
	            	result+=str.charAt(i);
	            }
	        }
			try {
				result = new String(result.getBytes(),"utf-8");
			} catch (Exception e) {
				System.out.println("不支持的编码格式");
			}
			return result;
		}

		

		/**
		 * 对字符串进行正则验证
		 * @param str
		 * @param reg
		 * @return Boolean
		 */
		public static Boolean checkStrignReg(String str, String reg) {
			boolean regCheck = str.matches(reg);
			return regCheck;
		}
		
		/**
		 * 替换字符
		 * @param str
		 * @param oldChar
		 * @param newChar
		 * @return
		 */
		public static String replace(String str,String oldChar,String newChar){
			if (StringUtils.isNotEmpty(str)) {
				str = str.replace(oldChar, newChar);
			}
			return str;
		}
		
		/**
		 * MD5加密字符串
		 * @param password
		 * @return
		 */
		public static String cipherPassword(String password){
			String cipherPassword = DigestUtils.md5Hex(password);
			return cipherPassword;
		}

		/**
		 * 取出字符串中含有的空格
		 * @param str
		 * @return
		 */
		public static String removeBlanksInString(String str) {
			if (str != null && str.trim().length() > 0) {
				str = str.replace(" ", "");
			}
			return str;
		}
		
		/**
		 * 将字符串分割成为
		 * @param str
		 * @param sign
		 * @return String[]
		 */
		public static String[] seperateStringToStrings(String str, String sign) {
			if (str != null && str.trim().length() > 0) {
				if (str.contains(sign)) {
					return str.split(sign);
				}
			}
			return null;
		}

		/**
		 * 将字符串以字符分割
		 * @param orgStr
		 * @param splitRange
		 * @param sign
		 * @return
		 */
		public static String splitWithSign(String orgStr,Integer splitRange,String sign){
			if (StringUtils.isNotEmpty(orgStr)) {
				Integer length = orgStr.length();
				if (length > splitRange) {
					Integer splitNums = length/splitRange;
					Integer lastMod = length % splitRange;
					if (lastMod>0) {
						splitNums = splitNums+1;
					}
					
					String[] results = new String[splitNums];
					
					for (int i = 0; i < splitNums; i++) {
						Integer startIndex = i*splitRange;
						Integer endIndex = (i+1)*splitRange;
						if (endIndex>length) {
							endIndex = length;
						}
						results[i] = orgStr.substring(startIndex,endIndex);
					}
					
					String newStr = "";
					
					int length2 = results.length;
					if (ArrayUtils.isNotEmpty(results)) {
						for (int i = 0; i < length2; i++) {
							if(i==0){
								newStr = results[i];
							}else {
								newStr = newStr +sign+ results[i];
							}
						}
					}
					
					return newStr;
				}
			}
			return orgStr;
		}
	}
	
	/**
	 * 集合类处理工具
	 * 
	 * @author 11
	 */
	public static class COLLECTION {
		public static Long[] longsListToLongs(List<Long> longList) {
			if (CollectionUtils.isNotEmpty(longList)) {
				Long[] longArray = new Long[longList.size()];
				for (int i = 0; i < longList.size(); i++) {
					longArray[i] = longList.get(i);
				}
				return longArray;
			}
			return null;
		}

		public static Object[] objectsListToObjects(List<Object> objList) {
			if (CollectionUtils.isNotEmpty(objList)) {
				Object[] array = new Object[objList.size()];
				array = objList.toArray(array);
				return array;
			}
			return null;
		}
	}
	
	public static class ARRAY{
		public static boolean checkIntInInts(int[] results, int i) {
			if (ArrayUtils.isNotEmpty(results)) {
				for (Integer integer : results) {
					if (integer > i) {
						return true;
					}
				}
			}
			return false;
		}


		/**
		 * 验证数组中是否包含不为空的内容
		 * @param strings
		 * @return
		 */
		public static boolean checkStringsAllNull(String...strings){
			if (ArrayUtils.isNotEmpty(strings)) {
				for (String string : strings) {
					if (StringUtils.isNotEmpty(string)) {
						return true;
					}
				}
			}
			return false;
		}
		
		/**
		 * 将Integer类型数组转换成为Long类型数组
		 * @param orgArrays
		 * @return
		 */
		public static Long[] integersToLongs(Integer[] orgArrays){
			if (ArrayUtils.isNotEmpty(orgArrays)) {
				Long[] results = new Long[orgArrays.length];
				for (int i = 0; i < orgArrays.length; i++) {
					results[i] = new Long(orgArrays[i]);
				}
				return results;
			}
			return null;
		}
		
		/**
		 * 将String类型数组，转换成为Long类型数组
		 * @param orgArrays
		 * @return
		 */
		public static Long[] stringsToLongs(String[] orgArrays){
			if (ArrayUtils.isNotEmpty(orgArrays)) {
				Long[] results = new Long[orgArrays.length];
				for (int i = 0; i < orgArrays.length; i++) {
					results[i] = new Long(orgArrays[i]);
				}
				return results;
			}
			return null;
		}
		
		/**
		 * 将对象数组转成字符串
		 * @param array
		 * @return
		 */
		public static String objectsToStrings(Object[] array,String sepSign) {
			if (array != null && array.length > 0) {
				String result = "";
				for (int i = 0; i < array.length; i++) {
					if (i==0) {
						result = array[0].toString();
					}else {
						result = result+sepSign+array[i];
					}
				}
				return result;
			}
			return null;
		}
		

		/**
		 * 验证字符串数组中是否存在字符串
		 * @param strs
		 * @param str
		 * @return Boolean
		 */
		public static Boolean checkStringInStrings(String[] strs, String str) {
			if (strs != null && strs.length > 0) {
				for (String string : strs) {
					if (string.equals(str)) {
						return true;
					}
				}
			}
			return false;
		}

		/**
		 * 对象数组转成字符数组
		 * @param objArray
		 * @return
		 */
		public static String[] objectsToStrings(Object[] objArray) {
			if (ArrayUtils.isNotEmpty(objArray)) {
				String[] strArray = new String[objArray.length];
				for (int i = 0; i < objArray.length; i++) {
					strArray[i] = (String) objArray[i];
				}
				return strArray;
			}
			return null;
		}
		
		/**
		 * 将字符串数组中的元素合并成为一个字符串
		 */
		public static String comb2Str(String[] strs, String sign) {
			String combStr = "";
			if (strs != null && strs.length > 0) {
				for (int i = 0; i < strs.length; i++) {
					combStr = strs[i] + sign + combStr;
				}
			}
			return combStr;
		}
	}
	
	public static class JSON{
		/**
		 * 将JSONArray字符串转成Sting数组
		 * @param jsonArrayStr
		 * @return
		 */
		public static Integer[] jsonArrayToIntegerArray(String jsonArrayStr){
			if (StringUtils.isNotEmpty(jsonArrayStr)) {
				JSONArray jsonArray = JSONArray.fromObject(jsonArrayStr);
				Integer[] array = new Integer[jsonArray.size()];
				array = (Integer[]) jsonArray.toArray(array);
				return array;
			}
			return null;
		}
	}
	
	public static void main(String[] args) {
		String value = MyUtilTool.DATE.currentDateString(null);
		System.out.println(value);
	}
}