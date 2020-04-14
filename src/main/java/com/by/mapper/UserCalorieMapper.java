package com.by.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.by.entity.UserCalorie;

@Mapper
public interface UserCalorieMapper {

	@Insert("insert into user_calorie (`c_id`,`u_id`,`gram`) values (#{c_id},#{u_id},#{gram}) ")
	public void add(@Param("c_id") int c_id, @Param("u_id") int u_id, @Param("gram") int gram);

	@Select("select * from user_calorie where u_id = #{u_id}")
	public List<UserCalorie> show(int u_id);

	@Select("SELECT * FROM user_calorie where date_format(create_time,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') and u_id = #{u_id}")
	public List<UserCalorie> showToday(int u_id);
}
