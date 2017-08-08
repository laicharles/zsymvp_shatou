package com.wisewater.scheduled.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class POIUtil {

	private static Logger logger = Logger.getLogger(POIUtil.class);
	
	/**
	 * 导出Execl
	 * @param tableName 表名  String
	 * @param title 表头  String[]
	 * @param list  内容   list
	 * @param file 路径  File
	 */
	@SuppressWarnings("rawtypes")
	public static void ImportExecl(String tableName,String[] title,List list,File file){

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(tableName);
		
		sheet.setDefaultColumnWidth(20);
		//设置样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);

		Object[][] value = new Object[list.size() + 1][title.length];
		
		//插入标题
		for (int m = 0; m < title.length; m++) {
			value[0][m] = title[m];
		}
		
		//插入内容
		for (int i = 0; i < list.size(); i++) {
			List<Object> objectList = new ArrayList<Object>();
			
			//解析list每个对象的值
			Object object  = list.get(i);
			Class userCla = (Class) object.getClass();
			if(userCla.isArray()){
				int length = Array.getLength(object);
				
				for(int n=0 ; n<length ; n++) {
				   Object valObj = Array.get(object, n);
				   objectList.add(valObj);
				}
			}else{
				Field[] fsFields = userCla.getDeclaredFields();
				for (int j = 0; j < fsFields.length; j++) {
					Field field = fsFields[j];
					field.setAccessible(true);
					try {
						objectList.add(field.get(object));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			
			//根据第几行第几个插入数据
			if(objectList != null){
				for (int m = 0; m < title.length; m++) {
					value[i + 1][m] = objectList.get(m);
				}
			}

		}
		
		//开始下载表
		for (int i = 0; i < value.length; i++) {
			HSSFRow row = sheet.createRow(i);
			
			for (int j = 0; j < value[i].length; j++) {
				
				Cell cell = row.createCell(j);
				cell.setCellValue(value[i][j].toString());
				cell.setCellStyle(style);
				
			}
		}
		
		try {
			//默认导出到E盘下
			FileOutputStream out = new FileOutputStream(file);
			wb.write(out);
			out.close();
			wb.close();
			
			logger.info("导出成功!");
		} catch (FileNotFoundException e) {
			logger.info("导出失败!"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("导出失败!"+e.getMessage());
			e.printStackTrace();
		}

	}

}
