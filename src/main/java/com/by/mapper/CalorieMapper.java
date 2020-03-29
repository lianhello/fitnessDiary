package com.by.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.by.entity.Calorie;

@Mapper
public interface CalorieMapper {
	
	@Select("select * from calorie")
	public List<Calorie> ListAll();
	
	@Select("select * from calorie where type = #{type}")
	public List<Calorie> ListByType(int type);
	
	@Select("select * from calorie where name like '%${value}%'")
	public List<Calorie> ListSearch(String search);
}
