package com.by.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.by.annotation.Authorize;
import com.by.service.UserCalorieService;

@Controller
public class UserCalorieController {
	@Autowired
	private UserCalorieService userCalorieService;
	
	@RequestMapping(value = "/addUserCalorie", method = RequestMethod.POST)
	@ResponseBody
	@Authorize
	public JSONObject addUserCalorie(@RequestBody String userCalorie,HttpServletRequest httpServletRequest) {
//		JSONArray uCJsonArray = JSONArray.parseArray(userCalorie);
		JSONObject j = JSONObject.parseObject(userCalorie);
		JSONArray uCJsonArray = j.getJSONArray("userCalorie");
		return userCalorieService.addUserCalorie(uCJsonArray, httpServletRequest);
	}
}
