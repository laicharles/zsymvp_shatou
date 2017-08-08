package com.wisewater.playStatement.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisewater.form.utils.JqgridListForm;
import com.wisewater.playStatement.service.PayStatementService;

/**
 * 报表统计
 * @author Xiong
 * @date 2017年4月6日 上午10:05:35
 */
@Controller
public class PayStatementController {

	@Autowired
	private PayStatementService payStatementService;
	
	/**
	 * 跳转页面
	 */
	@RequestMapping("/cus/{id}/statement.do")
	public String statementType(@PathVariable int id){
		if(id==1){
			return "/cus/payStatement/statementDayCount";
		}else if(id==2){
			return "/cus/payStatement/statementDayReport";
		}else if(id==3){
			return "/cus/payStatement/statementMonthReport";
		}
		return "/m/error/error";
	}
	
	
	/**
	 * 微信日统计
	 * @param date
	 * @return
	 */
	@RequestMapping("/cus/statementDayCount.do")
	public @ResponseBody JqgridListForm weChatDayCount(Model model,
			@RequestParam(value="page",defaultValue="1") String page,
			@RequestParam(value="date",required=false) String date,
			@RequestParam(value="userType",required=false) String userType){
		
		date = verifyDate(date,"yyyy-MM-dd");
		
		if (userType == null || "".equals(userType)) {
			userType = "0";
		}
		
		int pageNo = verifyPageNo(page);
		
		JqgridListForm jqgridListForm = payStatementService.weChatCommWebService(pageNo, date, userType, "weChatDayCount");
		
		return jqgridListForm;
	}
	
	@RequestMapping("/cus/statementDayCountDownload.do")
	public ResponseEntity<byte[]> weChatDayCountDownload(@RequestParam(value="date",required=false) String date,
			@RequestParam(value="userType",required=false) String userType) 
			throws UnsupportedEncodingException{
		
		date = verifyDate(date,"yyyy-MM-dd");
		
		if (userType == null || "".equals(userType)) {
			userType = "0";
		}
		
		String str = payStatementService.weChatDayCountDownload(date,userType);
		
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String(("微信缴费日统计表" + ".csv").getBytes("UTF-8"),
				"iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return new ResponseEntity<byte[]>(str.getBytes("gb2312"), headers,
				HttpStatus.CREATED);
	}
	
	/**
	 * 微信日统计明细
	 * @param date
	 * @return
	 */
	@RequestMapping("/cus/statementDayReport.do")
	public @ResponseBody JqgridListForm weChatDayReport(Model model,
			@RequestParam(value="page",defaultValue="1") String page,
			@RequestParam(value="date",required=false) String date,
			@RequestParam(value="userType",required=false) String userType){
		
		date = verifyDate(date,"yyyy-MM-dd");
		
		if (userType == null || "".equals(userType)) {
			userType = "0";
		}
		
		int pageNo = verifyPageNo(page);
		
		JqgridListForm jqgridListForm = payStatementService.weChatCommWebService(pageNo, date, userType, "weChatDayReport");
		
		return jqgridListForm;
	}
	
	@RequestMapping("/cus/statementDayReportDownload.do")
	public ResponseEntity<byte[]> weChatDayReport(@RequestParam(value="date",required=false) String date,
			@RequestParam(value="userType",required=false) String userType) 
			throws UnsupportedEncodingException{
		
		date = verifyDate(date,"yyyy-MM-dd");
		
		if (userType == null || "".equals(userType)) {
			userType = "0";
		}
		
		String str = payStatementService.weChatDayReportDownload(date,userType);
		
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String(("微信缴费日统计明细表" + ".csv").getBytes("UTF-8"),
				"iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return new ResponseEntity<byte[]>(str.getBytes("gb2312"), headers,
				HttpStatus.CREATED);
	}
	
	
	/**
	 * 微信日统计明细
	 * @param date
	 * @return
	 */
	@RequestMapping("/cus/statementMonthReport.do")
	public @ResponseBody JqgridListForm weChatMonthReport(Model model,
			@RequestParam(value="page",defaultValue="1") String page,
			@RequestParam(value="date1",required=false) String date1,		
			@RequestParam(value="date2",required=false) String date2,
			@RequestParam(value="userType",required=false) String userType){
		
		date1 = verifyDate(date1,"yyyy-MM-dd");
		date2 = verifyDate(date2,"yyyy-MM-dd");
		
		if (userType == null || "".equals(userType)) {
			userType = "0";
		}
		
		int pageNo = verifyPageNo(page);
		
		JqgridListForm jqgridListForm = payStatementService.weChatMonthWebService(pageNo, date1,date2, userType,"weChatMonthReport");
		
		return jqgridListForm;
	}
	
	@RequestMapping("/cus/statementMonthReportDownload.do")
	public ResponseEntity<byte[]> weChatMonthReport(@RequestParam(value="date1",required=false) String date1,
			@RequestParam(value="date2",required=false) String date2,
			@RequestParam(value="userType",required=false) String userType) 
			throws UnsupportedEncodingException{
		
		date1 = verifyDate(date1,"yyyy-MM-dd");
		date2 = verifyDate(date2,"yyyy-MM-dd");
		
		if (userType == null || "".equals(userType)) {
			userType = "0";
		}
		
		String str = payStatementService.weChatMonthReportDownload(date1,date2,userType);
		
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String(("微信缴费月统计表" + ".csv").getBytes("UTF-8"),
				"iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return new ResponseEntity<byte[]>(str.getBytes("gb2312"), headers,
				HttpStatus.CREATED);
	}
	
	/**
	 * 校验日期
	 * @param type
	 * @return
	 */
	public String verifyDate(String date,String type){
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		if(date == null || date.equals("")){
			date = sdf.format(new Date());
		}
		return date;
	}
	
	/**
	 * 校验页码
	 * @param page
	 * @return
	 */
	public int verifyPageNo(String page){
		
		int pageNo = 1;
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(page);
        if(isNum.matches()){
        	pageNo = Integer.parseInt(page);
        }
        return pageNo;
        
	}
}
