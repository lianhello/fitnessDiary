package com.by.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.by.mapper.UserCalorieMapper;
import com.by.service.UserCalorieService;

@Service
public class UserCalorieImpl implements UserCalorieService {
	@Autowired
	private UserCalorieMapper userCalorieMapper;

	public JSONObject addUserCalorie(JSONArray userCalorie, HttpServletRequest httpServletRequest) {
		JSONObject j = new JSONObject();
		try {
			for (int i = 0; i < userCalorie.size(); i++) {
				JSONObject uCJsonObject = userCalorie.getJSONObject(i);
				userCalorieMapper.add((int) uCJsonObject.get("id"), (int) httpServletRequest.getAttribute("userId"),
						(int) uCJsonObject.get("gram"));
			}
		} catch (Exception e) {
			j.put("code", 401);
			j.put("msg", "err");
			e.printStackTrace();
			return j;
		}
		j.put("code", 200);
		j.put("msg", "success");
		return j;
	}

}
