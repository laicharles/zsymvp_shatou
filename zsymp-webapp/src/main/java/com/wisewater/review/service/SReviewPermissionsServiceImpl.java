package com.wisewater.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.review.pojo.SReviewPermissions;
import com.wisewater.review.repository.SReviewPermissionsRepository;

@Service
public class SReviewPermissionsServiceImpl extends BaseService implements SReviewPermissionsService{
	
	@Autowired
	private SReviewPermissionsRepository reviewRepository;
		
	public SReviewPermissions findReviewById(String id){
		return reviewRepository.findById(id);
	}

	@Override
	public List<SReviewPermissions> findAll() {
		return reviewRepository.findAll();
	}
	
}
