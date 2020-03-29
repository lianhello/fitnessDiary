package com.by.service;

import com.by.entity.WxUser;

public interface WxUserService {

	String wxlogin(String code);
	
	WxUser get(String openId);
}
