package com.by.service;

import javax.servlet.http.HttpServletRequest;

import com.by.entity.UserInfo;

public interface UserInfoService {
	int addUserInfo(int height, int weight, int sex, HttpServletRequest httpServletRequest);

	UserInfo getUserInfo(HttpServletRequest httpServletRequest);

}
