package com.by.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.by.annotation.Authorize;
import com.by.entity.UserInfo;
import com.by.service.UserInfoService;

@Controller
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
	@ResponseBody
	@Authorize
	public int addUserInfo(int height, int weight, int sex, HttpServletRequest httpServletRequest) {
		return userInfoService.addUserInfo(height, weight, sex, httpServletRequest);
	}
	
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	@ResponseBody
	@Authorize
	public UserInfo getUserInfo(HttpServletRequest httpServletRequest) {
		return userInfoService.getUserInfo(httpServletRequest);
	}
}
