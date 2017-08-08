package com.wisewater.bill.service;

import com.wisewater.bill.pojo.Mp;


public interface MpService {

	Mp findMpByToken(String token);
}
