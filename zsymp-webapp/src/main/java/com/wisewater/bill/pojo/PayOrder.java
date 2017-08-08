package com.wisewater.bill.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 微信支付订单
 * @author TalonYeung
 * 2015年12月22日 下午12:17:14
 */
@Entity
@Table(name="cm_payorder")
public class PayOrder implements Serializable{
	private static final long serialVersionUID = 10000L;

	@Id
	@GeneratedValue(generator="uuidgenerator")
	@GenericGenerator(name="uuidgenerator",strategy="uuid2")
	private String id;
	
	/**
	 * 公众号token
	 */
	private String token;
	
	/**
	 * 订单号
	 * 唯一约束
	 */
	private String orderNo;
	
	/**
	 * 户号
	 */
	private String accountNo;
	
	/**
	 * 户名
	 */
	private String accountName;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 总金额
	 * 单位:元
	 */
	private String totalCharge;
	
	
	/**
	 * 用户微信ID
	 */
	private String openId;
	
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/**
	 * 删除时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date bisBackTime;
	
	/**
	 * 订单状态
	 * 0:失败,已收钱却销账失败
	 * 1:成功,已收钱并销账成功
	 * 2:预留
	 * 3:未支付
	 * 4:已付问,已收钱却有问题订单
	 */
	private int orderStatus;
	
	/**
	 * 支付方式
	 */
	private String payType;
	
	/**
	 * 账单编号
	 * 水司确保唯一
	 */
	private String billNo;
	
	/**
	 * 收费所编号
	 */
	private String tollboothCode;
	
	/**
	 * 附加数据
	 * 原样返回
	 */
	private String attach;
	
	/**
	 * 微信支付订单号
	 */
	private String transactionId;
	
	
	/**
	 * 备注
	 */
	private String remark;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getTotalCharge() {
		return totalCharge;
	}


	public void setTotalCharge(String totalCharge) {
		this.totalCharge = totalCharge;
	}


	public String getOpenId() {
		return openId;
	}


	public void setOpenId(String openId) {
		this.openId = openId;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getBisBackTime() {
		return bisBackTime;
	}


	public void setBisBackTime(Date bisBackTime) {
		this.bisBackTime = bisBackTime;
	}


	public int getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}


	public String getPayType() {
		return payType;
	}


	public void setPayType(String payType) {
		this.payType = payType;
	}


	public String getBillNo() {
		return billNo;
	}


	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


	public String getTollboothCode() {
		return tollboothCode;
	}


	public void setTollboothCode(String tollboothCode) {
		this.tollboothCode = tollboothCode;
	}


	public String getAttach() {
		return attach;
	}


	public void setAttach(String attach) {
		this.attach = attach;
	}


	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
