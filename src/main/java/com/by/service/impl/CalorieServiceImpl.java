package com.by.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.by.entity.Calorie;
import com.by.entity.CalorieType;
import com.by.mapper.CalorieMapper;
import com.by.mapper.CalorieTypeMapper;
import com.by.service.CalorieService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CalorieServiceImpl implements CalorieService {
	@Autowired
	private CalorieMapper calorieMapper;
	@Autowired
	private CalorieTypeMapper calorieTypeMapper;

	@Cacheable("calorieTypeItem")
	public JSONArray calorieTypeItem(int type) {
		List<Calorie> cL = calorieMapper.listByType(type);
		JSONArray calorieArray = new JSONArray();
		for (Calorie c : cL) {
			JSONObject calorieJson = new JSONObject();
			calorieJson.put("id", c.getId());
			calorieJson.put("name", c.getName());
			calorieJson.put("calorie", c.getCalorie());
			calorieJson.put("gram", c.getGram());
			calorieArray.add(calorieJson);
		}
		return calorieArray;
	}

	// @Cacheable("calorieSearch")
	public JSONArray calorieSearch(String search) {
		List<Calorie> calorieList = calorieMapper.listSearch(search);
		JSONArray calorieArray = new JSONArray();
		for (Calorie c : calorieList) {
			JSONObject calorieJson = new JSONObject();
			calorieJson.put("id", c.getId());
			calorieJson.put("name", c.getName());
			calorieJson.put("calorie", c.getCalorie());
			calorieJson.put("gram", c.getGram());
			calorieArray.add(calorieJson);
		}
		return calorieArray;
	}

	@Cacheable("CalorieListAll")
	public JSONArray ListAll() {
		JSONArray calorieArray = new JSONArray();
		JSONArray array = new JSONArray();
		List<Calorie> calorieList = calorieMapper.listAll();
		String type = "";
		for (Calorie c : calorieList) {
			if (c.getType() != type) {
				type = c.getType();
				array.add(type);
			}
			/**
			 * 循环使用/重复使用 同个jsonObject会导致数据错误 在确定自己的操作是安全的重复引用之后，可以通过这种方式暂时关闭循环引用检测
			 * JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect)
			 */

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("name", c.getName());
			jsonObject.put("calorie", c.getCalorie());
			jsonObject.put("gram", c.getGram());
			jsonObject.put("type", c.getType());

			calorieArray.add(jsonObject);
		}
		return calorieArray;
	}

	@Cacheable("CalorieJson")
	public JSONArray calorieJson() {

		JSONArray calorieTypeArray = new JSONArray();
		List<CalorieType> calorieTypeList = calorieTypeMapper.ListAll();

		for (CalorieType cT : calorieTypeList) {
			List<Calorie> calorieList = calorieMapper.listByType(cT.getId());

			JSONArray calorieArray = new JSONArray();

			for (Calorie c : calorieList) {
				JSONObject calorieJson = new JSONObject();
				calorieJson.put("name", c.getName());
				calorieJson.put("calorie", c.getCalorie());
				calorieJson.put("gram", c.getGram());
				calorieArray.add(calorieJson);
			}

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("children", calorieArray);
			jsonObject.put("type", cT.getName());
			calorieTypeArray.add(jsonObject);

		}
		return calorieTypeArray;
	}

	@Cacheable("calorieAll")
	public PageInfo<Calorie> calorieAll(Integer pageNum) {
		Integer pageSize = 50;

		PageHelper.startPage(pageNum, pageSize);
		List<Calorie> calorieList = calorieMapper.listAll();
		PageInfo<Calorie> pageInfo = new PageInfo<>(calorieList);
		return pageInfo;
	}

}
