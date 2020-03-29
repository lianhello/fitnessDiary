package com.by.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.by.entity.Calorie;
import com.by.service.CalorieService;
import com.by.service.CalorieTypeService;
import com.github.pagehelper.PageInfo;

@Controller
public class CalorieController {
	@Autowired
	private CalorieService calorieService;
	@Autowired
	private CalorieTypeService calorieTypeService;
	
//	@RequestMapping(value = "/calorieAll", method = RequestMethod.GET)
//	@ResponseBody
//	public JSONArray calorieAll() {
//		return calorieService.calorieJson();
//	}
	
	@RequestMapping(value = "/calorieSearch", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray calorieSearch(String search) {
		return calorieService.calorieSearch(search);
	}
	
	@RequestMapping(value = "/calorieType", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray calorieType() {
		return calorieTypeService.ListAll();
	}
	
	@RequestMapping(value = "/calorieTypeItem", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray calorieTypeItem(int type) {
		return calorieService.calorieTypeItem(type);
	}
	
	@RequestMapping(value = "/calorieAll", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<Calorie> calorieAll(Integer pageNum) {
		return calorieService.calorieAll(pageNum);
	}
}
