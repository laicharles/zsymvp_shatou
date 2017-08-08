package com.wisewater.bill.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.bill.pojo.PayOrder;

public interface PayOrderRepository extends BaseRepository<PayOrder, String>{
	
	@Query("select y from PayOrder y where y.token=:token and y.orderNo=:orderNo")
	public List<PayOrder> findPayOrderByOrderNo(@Param("token") String token,@Param("orderNo") String orderNo);
	
	@Query("select round(sum(totalCharge),2) from PayOrder y where y.createTime>:fromDate and y.createTime<:endDate and y.orderStatus<>3")
	public String findPayOrderByTime(@Param("fromDate") Date fromDate,@Param("endDate") Date endDate);
	
	@Query("select y from PayOrder y where y.token=:token and y.accountNo=:accountNo and  date_format(bisBackTime, '%Y-%m-%d') = :bisBackTime")
	public List<PayOrder> findpayOrderByAccountNo(@Param("token") String token,@Param("accountNo") String accountNo,@Param("bisBackTime") String totalCharge);
	
	@Query("select y from PayOrder y where y.token=:token and date_format(y.createTime, '%Y-%m-%d') = :createTime and y.orderStatus = :orderStatus")
	public List<PayOrder> findByCreateTimeAndOrderStatus(@Param("token") String token,@Param("createTime") String createTime,@Param("orderStatus") int orderStatus);
	
}
