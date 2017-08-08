package com.wisewater.cms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.cms.pojo.CCmsYywd;

public interface CCmsYywdRepository extends BaseRepository<CCmsYywd, String> {
	/**
	 * 根据token查找营业网点列表（分页）
	 * @param token
	 * @param searchField
	 * @param pager
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:54:40
	 */
	@Query("select y from CCmsYywd y where y.token=:token and y.name like %:name% and y.isLogicDel=0")
	public Page<CCmsYywd> findYywdByToken(@Param("token") String token,
			@Param("name") String searchField, Pageable pager);
	

	/**
	 * 根据token查找营业网点列表
	 * @param token
	 * @return
	 * AlexFung
	 * 2015年4月16日 上午10:53:16
	 */
	@Query("select y from CCmsYywd y where y.token=:token and y.isLogicDel=0")
	public List<CCmsYywd> findYywdByToken(@Param("token") String token);

	/**
	 * 根据token查找营业网点列表
	 * @param token
	 * @return
	 * AlexFung
	 * 2015年4月2日 下午1:54:47
	 */
	@Query("select count(*) from CCmsYywd y where y.token=:token and y.isLogicDel=0")
	public int findYywdByTokenSize(@Param("token") String token);

}