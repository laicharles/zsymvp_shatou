package com.wisewater.scheduled.pojo;

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
 * 销账类
 * @author Xiong
 * @date 2017年7月10日 下午1:45:12
 */
@Entity
@Table(name="cm_ordercheck")
public class OrderCheck implements Serializable{
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
	 * 订单状态
	 * 0:失败,已收钱却销账失败
	 * 1:成功,已收钱并销账成功
	 * 2:预留
	 * 3:未支付
	 * 4:已付问,已收钱却有问题订单
	 */
	private int orderStatus;
	
	/**
	 * 微信支付订单号
	 */
	private String transactionId;
	
	/**
	 * 销账地址路径
	 */
	private String wxpaySuccessUrl;
	
	/**
	 * 附加数据
	 * 原样返回
	 */
	private String attach;
	
	/**
	 * 销账密钥
	 */
	private String tradno;
	
	/**
	 * 收费金额
	 */
	private String totalFee;
	
	/**
	 * 是否删除
	 */
	private int isLogicDel;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 最后销账时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	
	public OrderCheck(){}
	public OrderCheck(String orderNo, String accountNo, String accountName,
			int orderStatus, String totalFee, Date createTime) {
		super();
		this.orderNo = orderNo;
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.orderStatus = orderStatus;
		this.totalFee = totalFee;
		this.createTime = createTime;
	}
	public OrderCheck(String id, String token, String orderNo,
			String accountNo, String accountName, int orderStatus,
			String transactionId, String wxpaySuccessUrl, String attach,
			String tradno, String totalFee, int isLogicDel, String remark,
			Date createTime) {
		super();
		this.id = id;
		this.token = token;
		this.orderNo = orderNo;
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.orderStatus = orderStatus;
		this.transactionId = transactionId;
		this.wxpaySuccessUrl = wxpaySuccessUrl;
		this.attach = attach;
		this.tradno = tradno;
		this.totalFee = totalFee;
		this.isLogicDel = isLogicDel;
		this.remark = remark;
		this.createTime = createTime;
	}

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

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getWxpaySuccessUrl() {
		return wxpaySuccessUrl;
	}

	public void setWxpaySuccessUrl(String wxpaySuccessUrl) {
		this.wxpaySuccessUrl = wxpaySuccessUrl;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTradno() {
		return tradno;
	}

	public void setTradno(String tradno) {
		this.tradno = tradno;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public int getIsLogicDel() {
		return isLogicDel;
	}

	public void setIsLogicDel(int isLogicDel) {
		this.isLogicDel = isLogicDel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
