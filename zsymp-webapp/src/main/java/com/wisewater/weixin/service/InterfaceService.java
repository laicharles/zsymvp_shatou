package com.wisewater.weixin.service;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisewater.cusConfig.pojo.TimeSet;
import com.wisewater.fans.controller.CFanUserForm;
import com.wisewater.util.tools.SecurityMD5;
import com.wisewater.weixin.utils.InterfaceUtil;

/**
 * @author XingXinglvcha
 * 2016年1月4日 下午4:38:02
 */
@Transactional
@Service
public class InterfaceService {

	/**
	 * 绑定户号户名
	 * 值0 户名户号不正确   失败
	 * 值1 成功
	 * 值2 接口出问题
	 * @author XingXinglvcha
	 * 2016年1月5日 上午11:46:24
	 * @param url 接口地址
	 * @param token
	 * @param bindUserForm
	 * @return
	 * @throws Exception 
	 */
	public int bindUser(String url,String method,CFanUserForm fanUserForm) throws Exception{
		int result = bindOrSyn(url,method,fanUserForm);
		if(result == 2)
		{
			//出错处理
			throw new Exception("bindUser方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		return result;
	}
	
	/**
	 * 查看用户详细信息
	 * @param url 地址
	 * @param method 方法名
	 * @param account_no 户号
	 * @return
	 */
	public String dateiled(String url,String method,String account_no){
		
		return InterfaceUtil.findJsonByHttpPost(url, method, account_no);
	}
	
	/**
	 * 获取最新一期账单
	 * @author ZXX
	 * 2017-03-31 11:03:12
	 * @param url
	 * @param token
	 * @param accountNo 户号
	 * @param openId
	 * @return
	 * @throws Exception 
	 */
	public String findNewWaterBill(String url,String method,String accountNo) throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url, method, accountNo);
		if(result == null)
		{
			//处理出错 
			throw new Exception("findNewWaterBill方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		// 保存账单数据
		
		return result;
	}
	
	/**
	 * 获取用户需要缴纳费用明细
	 * @author zxx
	 * 2017-03-31 11:03:12
	 * @param url
	 * @param token
	 * @param accountNo 户号
	 * @param openId
	 * @return
	 * @throws Exception 
	 */
	public String getCharge(String url,String method,String accountNo) throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url, method, accountNo); 
		if(result == null)
		{
			//处理出错 
			throw new Exception("getCharge方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		// 保存账单数据
		
		return result;
	}
	/**
	 * 获取用户需要缴纳费用明细
	 * @author zxx
	 * 2017-03-31 11:03:12
	 * @param url
	 * @param token
	 * @param accountNo 户号
	 * @param openId
	 * @return
	 * @throws Exception 
	 */
	public String getPay(String url,String method,String accountNo) throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url, method, accountNo);
		if(result == null)
		{
			//处理出错 
			throw new Exception("getPay方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		// 保存账单数据
		
		return result;
	}
	
	/**
	 * 传递反馈信息给水司
	 * @author zxx
	 * 2017-03-31 11:03:12
	 * @param url
	 * @param token
	 * @param accountNo 户号
	 * @param openId
	 * @return
	 * @throws Exception 
	 */
	public String postWorker(String url,String method,String customerName,String address,
			                   String phone,String typeCode,String remark) throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url,method,customerName,address,phone,typeCode,remark);
		if(result == null)
		{
			//处理出错 
			throw new Exception("postWorker方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		// 保存账单数据
		
		return result;
	}
	
	/**
	 * 获取微信缴费日统计表接口
	 * @author Xiong
	 * @date 2017-04-06
	 * @param url
	 * @param method
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public String weChatDayCount(String url,String method,String date,String userType) throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url, method, date,userType);
		if(result == null)
		{
			//处理出错 
			throw new Exception("weChatDayCount方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		// 保存账单数据
		
		return result;
	}
	
	/**
	 * 微信缴费日明细表接口
	 * @author Xiong
	 * @date 2017-04-06
	 * @param url
	 * @param method
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public String weChatDayReport(String url,String method,String date) throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url, method, date);
		if(result == null)
		{
			//处理出错 
			throw new Exception("weChatDayReport方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		// 保存账单数据
		
		return result;
	}
	
	/**
	 * 微信缴费月报表接口
	 * @author Xiong
	 * @date 2017-04-06
	 * @param url
	 * @param method
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public String weChatMonthReport(String url,String method,String date1,String date2,String userType) throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url, method, date1,date2,userType);
		if(result == null)
		{
			//处理出错 
			throw new Exception("weChatMonthReport方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		// 保存账单数据
		
		return result;
	}
	
	/**
	 * 获取的月结的时间限制
	 * @param url
	 * @param method
	 * @return
	 * ZXX
	 * 2017年5月15日 上午11:03:28
	 */
	public String select_month(String url,String method)throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url,method);
		if(result == null){
			//处理出错
			throw new Exception("select_month方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		
		return result;
	}
	
	/**
	 * 销帐调用
	 * @author XingXinglvcha
	 * 2016年1月5日 下午3:20:44
	 * @param url
	 * @param method
	 * @param accountNo
	 * @param order_no
	 * @param total_charge
	 * @param checkNo
	 * @param sfrq 收费日期
	 * @return
	 */
	public int wxPayReturn(String url,String method,String accountNo,String total_charge,String checkNo,String sfrq)throws Exception{

		String result = InterfaceUtil.findJsonByHttpPost(url, method, accountNo,total_charge,checkNo,sfrq);
		int results = 0;
		if(result !=null && !result.equals("")){
			results =  Integer.parseInt(result);
		}else{
			//出错处理
			throw new Exception("wxPayReturn方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		return results;
	}
	
	
	/**
	 * 获取日结的时间限制
	 * @param url
	 * @param method
	 * @return
	 * ZXX
	 * 2017年5月15日 上午11:03:28
	 */
	public String select_day(String url,String method)throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url,method);
		if(result == null){
			//处理出错
			throw new Exception("select_day方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		
		return result;
	}
	
	
	/**
	 * 设置日结的时间
	 * @param url
	 * @param method
	 * @return
	 * ZXX
	 * 2017年5月15日 上午11:03:28
	 */
	public String set_day(String url,String method,String time1,String time2,String status)throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url,method,time1,time2,status);
		if(result == null){
			//处理出错
			throw new Exception("set_day方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		
		return result;
	}
	
	
	
	/**
	 * 设置日结的时间
	 * @param url
	 * @param method
	 * @return
	 * ZXX
	 * 2017年5月15日 上午11:03:28
	 */
	public String set_month(String url,String method,String id,String status,String month1,String month2,String userType)throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url,method,id,status,month1,month2,userType);
		if(result == null){
			//处理出错
			throw new Exception("set_day方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		
		return result;
	}
	
	
	/**
	 * 反馈读表数给予水司
	 * @param url
	 * @param method
	 * @return
	 * ZXX
	 * 2017年5月15日 上午11:03:28
	 */
	public String saveMeter(String url,String method,String hno, String openID, String pnum,
			String time, String longitude, String latitude, String photoPath)throws Exception{
		String result = InterfaceUtil.findJsonByHttpPost(url,method,hno,openID,pnum,time,longitude,latitude,photoPath);
		if(result == null){
			//处理出错
			throw new Exception("saveMeter方法请求返回参数为空,错误在"+this.getClass().getName());
		}
		
		return result;
	}
	
	
	
	
	/**
	 * 通过code查询并检查接口链接是否可用
	 * @author XingXinglvcha
	 * 2016年1月4日 下午6:06:06
	 * @param code
	 * @return
	 */
	/*private InterfaceForm checkInterface(String token,String code){
		InterfaceForm result = new InterfaceForm();
		CInterfaceConfig cInterfaceConfig = cInterfaceConfigDao.findInterfaceByCode(code,token);
		if(cInterfaceConfig != null && cInterfaceConfig.getIsValid()==1 && cInterfaceConfig.getOnOff()==1)
		{
			result.setUse(true);
			result.setUrl(cInterfaceConfig.getUrl());
		}
		else
		{
			result.setUse(false);
			result.setUrl("");
		}
		
		return result;
	}*/
	
	
	
	/**
	 * 调用http并返回成功与否结果
	 * 值0 失败
	 * 值1 成功
	 * 值2 接口出问题
	 * @author XingXinglvcha
	 * 2016年1月5日 上午10:58:26
	 * @param url
	 * @param token
	 * @param bindUserForm
	 * @return
	 */
	private static int bindOrSyn(String url,String method,CFanUserForm fanUserForm){
		int result = 0;
		
		String account_no = fanUserForm.getUserAccount();
		String mobile = fanUserForm.getMobile();
		
		String return_code = InterfaceUtil.findJsonByHttpPost(url, method, account_no , mobile);
		if(!return_code.equals("0"))
		{
			if(account_no.equals(return_code))
			{
				result = 1;
			}
			else if(return_code.equals("") || return_code == null)
			{
				result = 2;
			}
		}
		else
		{
			result = 0;
		}
		
		return result;
	}
}
