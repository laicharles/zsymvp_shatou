package com.wisewater.bill.service;

import com.wisewater.bill.pojo.PayConfig;

public interface payConfigService {
	
	PayConfig fingPayConfigByToken(String token);

}
