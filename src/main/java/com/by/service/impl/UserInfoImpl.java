package com.by.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.by.entity.UserInfo;
import com.by.mapper.UserInfoMapper;
import com.by.service.UserInfoService;

@Service
public class UserInfoImpl implements UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;

	public int addUserInfo(int height, int weight, int sex, HttpServletRequest httpServletRequest) {
		return userInfoMapper.add((int) httpServletRequest.getAttribute("userId"), height, weight, sex);
	}

	public UserInfo getUserInfo(HttpServletRequest httpServletRequest) {
		return userInfoMapper.get((int) httpServletRequest.getAttribute("userId"));
	}

}
