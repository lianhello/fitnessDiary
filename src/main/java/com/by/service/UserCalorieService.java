package com.by.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface UserCalorieService {
	JSONObject addUserCalorie(JSONArray userCalorie, HttpServletRequest httpServletRequest);
}
