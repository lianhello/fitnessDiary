package com.by.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.by.entity.CalorieType;
import com.by.mapper.CalorieTypeMapper;
import com.by.service.CalorieTypeService;

@Service
public class CalorieTypeImpl implements CalorieTypeService {
	@Autowired
	private CalorieTypeMapper calorieTypeMapper;

	@Override
	@Cacheable("CalorieType")
	public JSONArray ListAll() {

		List<CalorieType> calorieTypeList = calorieTypeMapper.ListAll();
		JSONArray calorieTypeArray = new JSONArray();
		for(CalorieType cT : calorieTypeList) {
			JSONObject cJ = new JSONObject();
			cJ.put("id", cT.getId());
			cJ.put("name", cT.getName());
			calorieTypeArray.add(cJ);
		}
		return calorieTypeArray;
	}

}
