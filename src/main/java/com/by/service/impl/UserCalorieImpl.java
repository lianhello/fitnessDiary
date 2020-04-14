package com.by.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.by.entity.Calorie;
import com.by.entity.UserCalorie;
import com.by.mapper.CalorieMapper;
import com.by.mapper.UserCalorieMapper;
import com.by.service.UserCalorieService;

@Service
public class UserCalorieImpl implements UserCalorieService {
	@Autowired
	private UserCalorieMapper userCalorieMapper;
	@Autowired
	private CalorieMapper calorieMapper;

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

	public JSONArray showUserCalorie(HttpServletRequest httpServletRequest) {
		List<UserCalorie> uCList = userCalorieMapper.show((int) httpServletRequest.getAttribute("userId"));
		JSONArray jA = new JSONArray();
		int value = 0;
		
		for (UserCalorie uC : uCList) {
			JSONObject jO = new JSONObject();
			
			Calorie c = calorieMapper.getById(uC.getC_id());
			jO.put("name", c.getName());
			value = c.getCalorie() * uC.getGram() / c.getGram();
			jO.put("value", value);
//	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			jO.put("time", simpleDateFormat.format(uC.getCreate_time()));
			jA.add(jO);
		}
		return jA;
	}
	
	public JSONArray showToday(HttpServletRequest httpServletRequest) {
		List<UserCalorie> uCList = userCalorieMapper.showToday((int) httpServletRequest.getAttribute("userId"));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		JSONArray jA = new JSONArray();
		int value = 0;
		for (UserCalorie uC : uCList) {
			JSONObject jO = new JSONObject();					
			Calorie c = calorieMapper.getById(uC.getC_id());
			value = c.getCalorie() * uC.getGram() / c.getGram();
			jO.put("value", value);
	        
			jO.put("time", simpleDateFormat.format(uC.getCreate_time()));
			jA.add(jO);
		}
		return jA;
	}
}













