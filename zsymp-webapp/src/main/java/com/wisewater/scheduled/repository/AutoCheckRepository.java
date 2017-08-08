package com.wisewater.scheduled.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.scheduled.pojo.OrderCheck;

/**
 * 销账Dao
 * @author Xiong
 * @date 2017年7月10日 下午2:27:35
 */
public interface AutoCheckRepository extends BaseRepository<OrderCheck, String>{
	
	/**
	 * 根据OrderNo查询用户
	 * @param orderNo
	 * @return
	 */
	@Query("select o from OrderCheck o where o.orderNo = :orderNo and date_format(o.createTime, '%Y-%m-%d') = :createTime ")
	public OrderCheck findByOrderNo(@Param("orderNo")String orderNo,@Param("createTime")String createTime);
	
	@Query("select o from OrderCheck o where o.token = :token and date_format(o.createTime, '%Y-%m-%d') = :createTime ")
	public List<OrderCheck> findByCreateTime(@Param("token") String token ,@Param("createTime")String createTime);

	@Query("select orderNo,accountNo,accountName,orderStatus,totalFee,createTime from OrderCheck y where y.token=:token "
			 + " and date_format(y.createTime, '%Y-%m-%d') = :createTime and y.orderStatus <> :orderStatus")
	public List<OrderCheck> findByNotAccount(@Param("token") String token,@Param("createTime") String createTime,@Param("orderStatus") int orderStatus);
}
