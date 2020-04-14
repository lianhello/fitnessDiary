package com.by.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.by.entity.UserInfo;

@Mapper
public interface UserInfoMapper {

	@Insert("insert into userinfo values(#{uid},#{height},#{weight},#{sex}")
	int add(@Param("uid") int uid, @Param("height") int height, @Param("weight") int weight, @Param("sex") int sex);

	@Update("update userinfo set height=#{height},weight=#{weight},sex=#{sex} where uid = #{uid}")
	int update(@Param("uid") int uid, @Param("height") int height, @Param("weight") int weight, @Param("sex") int sex);

	@Select("select * from userinfo where uid = #{uid}")
	UserInfo get(int uid);
}
