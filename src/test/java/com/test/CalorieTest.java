package com.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.junit4.SpringRunner;

import com.by.Application;
import com.by.config.PageHelperConfig;
import com.by.entity.Calorie;
import com.by.mapper.CalorieMapper;
import com.by.service.CalorieService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CalorieTest {
	@Autowired
	private CalorieService calorieService;
	@Autowired
	private CalorieMapper calorieMapper;
	@Autowired
	
	
	@Cacheable("test")
	@Test
	public void test() {
		PageHelper.startPage(1,10);
		List<Calorie> list = calorieMapper.ListAll();
		
        PageInfo<Calorie> pageInfo = new PageInfo<>(list);
//		return pageInfo;
	}

}
