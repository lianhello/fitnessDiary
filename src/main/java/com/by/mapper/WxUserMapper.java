package com.by.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.by.entity.WxUser;

@Mapper
public interface WxUserMapper {

	@Insert("insert into wxuser (openid) values (#{openid})")
	public int add(String openid);
	
	@Update("update wxuser set count = count+1 where openid = #{openid}")
	public int loginCount(String openid);
	
	@Select("select * from wxuser where openid = #{openid}")
	public WxUser getByOpenid(String openid);
	
	
	
	
}
