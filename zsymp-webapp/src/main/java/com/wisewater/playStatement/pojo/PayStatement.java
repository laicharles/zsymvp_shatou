package com.wisewater.playStatement.pojo;

import com.wisewater.base.BaseForm;

/**
 * 报表实体类
 * @author Xiong
 * @date 2017年4月6日 上午9:29:33
 */
public class PayStatement extends BaseForm {
	
	private static final long serialVersionUID = 1L;

	//用户号
	private String userno;
	
	//订单编号
	private String orderNo;
	
	//订单编号
    private String orderSatus;
	
	//用水性质
	private String usort;
	
	//户数
	private String usercnt;
	
	//抄表月份
	private String cmonth;
	
	//水量
	private String waternum;
	
	//水费
	private String waterfee;
	
	//水费违约金
	private String penalty;
	
	//污水费
	private String sewagefee;
	
	//污水费滞纳金
	private String latefee;
	
	//垃圾费
	private String garbagefee;
	
	//总费用
	private String totalfee;
	
	//收费日期
    private String sdate;
    
    private String weixintotalfee;

    public PayStatement(){}
	public PayStatement(String userno, String usort, String orderNo,String usercnt,
			String cmonth, String waternum, String waterfee, String penalty,String orderSatus,
			String sewagefee, String latefee, String garbagefee,
			String totalfee, String sdate,String weixintotalfee) {
		super();
		this.userno = userno;
		this.orderNo = orderNo;
		this.orderSatus = orderSatus;
		this.usort = usort;
		this.usercnt = usercnt;
		this.cmonth = cmonth;
		this.waternum = waternum;
		this.waterfee = waterfee;
		this.penalty = penalty;
		this.sewagefee = sewagefee;
		this.latefee = latefee;
		this.garbagefee = garbagefee;
		this.totalfee = totalfee;
		this.sdate = sdate;
		this.weixintotalfee = weixintotalfee;
	}
	
	
	public String getUserno() {
		return userno;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public String getOrderSatus() {
		return orderSatus;
	}
	public void setOrderSatus(String orderSatus) {
		this.orderSatus = orderSatus;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getUsort() {
		return usort;
	}
	public void setUsort(String usort) {
		this.usort = usort;
	}
	public String getUsercnt() {
		return usercnt;
	}
	public void setUsercnt(String usercnt) {
		this.usercnt = usercnt;
	}
	public String getCmonth() {
		return cmonth;
	}
	public void setCmonth(String cmonth) {
		this.cmonth = cmonth;
	}
	public String getWaternum() {
		return waternum;
	}
	public void setWaternum(String waternum) {
		this.waternum = waternum;
	}
	public String getWaterfee() {
		return waterfee;
	}
	public void setWaterfee(String waterfee) {
		this.waterfee = waterfee;
	}
	public String getPenalty() {
		return penalty;
	}
	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}
	public String getSewagefee() {
		return sewagefee;
	}
	public void setSewagefee(String sewagefee) {
		this.sewagefee = sewagefee;
	}
	public String getLatefee() {
		return latefee;
	}
	public void setLatefee(String latefee) {
		this.latefee = latefee;
	}
	public String getGarbagefee() {
		return garbagefee;
	}
	public void setGarbagefee(String garbagefee) {
		this.garbagefee = garbagefee;
	}
	public String getTotalfee() {
		return totalfee;
	}
	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getWeixintotalfee() {
		return weixintotalfee;
	}
	public void setWeixintotalfee(String weixintotalfee) {
		this.weixintotalfee = weixintotalfee;
	}

}
