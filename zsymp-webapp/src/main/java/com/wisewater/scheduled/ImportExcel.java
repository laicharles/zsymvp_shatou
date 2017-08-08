package com.wisewater.scheduled;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wisewater.scheduled.service.AutoCheckService;
import com.wisewater.scheduled.util.POIUtil;

/**
 * 导出数据
 * @author Xiong
 * @date 2017年7月11日 下午6:18:13
 */
@Component
public class ImportExcel {
	
	@Autowired
	private AutoCheckService autoCheckService;
	
	@SuppressWarnings("rawtypes")
	@Scheduled(cron = "0 40 23 * * ?")
	public void orderExcel(){
		
		SimpleDateFormat sdfSimple = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdfSimple.format(new Date());
		
		List autoCheckList = autoCheckService.findByNotAccount("225fb8d5394a44e983e7f6354954a21e", date, 1);
		String[] title = {"订单号","户号","户名","状态","金额","缴费时间"};
		File file = new File("D://zsy//billData//未销账数据_"+date+".xls");
		
		POIUtil.ImportExecl("未销账数据_"+date, title, autoCheckList, file);
		
	}
}
