package com.by.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.by.entity.CalorieType;

@Mapper
public interface CalorieTypeMapper {
	
	@Select("select * from Calorie_type")
	public List<CalorieType> ListAll();
}
