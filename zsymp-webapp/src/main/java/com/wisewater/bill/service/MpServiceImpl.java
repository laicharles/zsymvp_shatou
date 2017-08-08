package com.wisewater.bill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisewater.base.BaseService;
import com.wisewater.bill.pojo.Mp;
import com.wisewater.bill.repository.MpRepository;
@Service
public class MpServiceImpl extends BaseService implements MpService{

	@Autowired
	private MpRepository mpRepository;
	@Override
	public Mp findMpByToken(String token) {
        List<Mp> list = mpRepository.findMpByToken(token);
		
		if (list!=null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
