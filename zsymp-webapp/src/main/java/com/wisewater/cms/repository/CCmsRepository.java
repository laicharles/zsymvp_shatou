package com.wisewater.cms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cms.pojo.CCms;

public interface CCmsRepository extends BaseRepository<CCms, String> {
	
	/**
	 * 初级权限，能看见所有的。除开二级审核（为审核和审核不成功）
	 * @param token
	 * @param cmsType
	 * @param pager
	 * @return
	 */
	@Query("select c from CCms c where c.token=:token and c.isLogicDel=0 and c.cmsType.logicID=:cmsType and submitOpenId = :openId "
		 + " or c.token=:token and c.isLogicDel=0 and c.cmsType.logicID=:cmsType and c.auditorStatus.logicID = 'reviewStatus2'")
	public Page<CCms> findCmsByTokenSubmit(@Param("token") String token, @Param("cmsType") String cmsType,@Param("openId") String openId, Pageable pager);
	
	/**
	 * 一级权限，能看到自己的，成功的，还有自己下级的
	 * @param token
	 * @param cmsType
	 * @param pager
	 * @return
	 */
	@Query("select c from CCms c where c.token=:token and c.isLogicDel=0 and c.cmsType.logicID=:cmsType and submitOpenId = :openId "
		 + " or c.token=:token and c.isLogicDel=0 and c.cmsType.logicID=:cmsType and c.auditorStatus.logicID = 'reviewStatus2' "
		 + " or c.token=:token and c.isLogicDel=0 and c.cmsType.logicID=:cmsType and auditorLevel != :level ")
	public Page<CCms> findCmsByTokenReviewONE(@Param("token") String token, @Param("cmsType") String cmsType,@Param("openId") String openId,@Param("level") String level, Pageable pager);

	
	
	/**
	 * 根据token查找服务公告列表（分页）
	 * 
	 * @param token
	 * @param pager
	 * @return AlexFung 2015年4月1日 上午11:24:24
	 */
	@Query("select y from CCms y where y.token=:token and y.cmsType.logicID=:cmsType and y.isLogicDel=0 and y.auditorStatus.logicID = 'reviewStatus2'")
	public Page<CCms> findCmsByToken(@Param("token") String token, @Param("cmsType") String cmsType, Pageable pager);

	/**
	 * 根据token查找服务公告列表
	 * 
	 * @param token
	 * @return AlexFung 2015年4月16日 上午10:41:41
	 */
	@Query("select y from CCms y where y.token=:token and y.isLogicDel=0")
	public List<CCms> findCmsByToken(@Param("token") String token);

	/**
	 * 根据token查找服务公告列表
	 * 
	 * @param token
	 * @return AlexFung 2015年4月1日 上午11:24:30
	 */
	@Query("select count(*) from CCms y where y.token=:token and y.isLogicDel=0")
	public int findCmsByTokenSize(@Param("token") String token);
	
	@Query("select y from CCms y where y.token=:token and y.isLogicDel=0 and y.cmsType.logicID=:cmsType and y.auditorStatus.logicID = 'reviewStatus2'")
	public List<CCms> findByTokenAndCmsType(@Param("token") String token,@Param("cmsType") String cmsType);

}