package com.wisewater.review.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisewater.base.BaseRepository;
import com.wisewater.review.pojo.SReviewPermissions;

public interface SReviewPermissionsRepository extends BaseRepository<SReviewPermissions, String> {

	@Query("select r from SReviewPermissions r where r.id=:id")
	public SReviewPermissions findById(@Param("id") String id);
	
}
