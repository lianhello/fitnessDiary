package com.by.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserCalorieMapper {

	@Insert("insert into user_calorie (`c_id`,`u_id`,`gram`) values (#{c_id},#{u_id},#{gram}) ")
	public void add(@Param("c_id") int c_id, @Param("u_id") int u_id, @Param("gram") int gram);

}
