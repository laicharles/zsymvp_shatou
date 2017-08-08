package com.wisewater.review.service;

import java.util.List;

import com.wisewater.review.pojo.SReviewPermissions;

public interface SReviewPermissionsService {

	SReviewPermissions findReviewById(String id);
	
	List<SReviewPermissions> findAll();
	
}
