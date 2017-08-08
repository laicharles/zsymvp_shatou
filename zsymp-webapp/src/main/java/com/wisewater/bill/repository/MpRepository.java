package com.wisewater.bill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.bill.pojo.Mp;

public interface MpRepository extends BaseRepository<Mp, String>{

	@Query("select y from Mp y where y.token=:token")
	public List<Mp> findMpByToken(@Param("token") String token);
}
