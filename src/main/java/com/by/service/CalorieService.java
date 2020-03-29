package com.by.service;

import com.alibaba.fastjson.JSONArray;
import com.by.entity.Calorie;
import com.github.pagehelper.PageInfo;

public interface CalorieService {
	JSONArray ListAll();
	JSONArray calorieJson();
	JSONArray calorieSearch(String search);
	JSONArray calorieTypeItem(int type);
	PageInfo<Calorie> calorieAll(Integer pageNum);
}
