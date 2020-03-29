package com.by.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.by.entity.WxUser;
import com.by.mapper.WxUserMapper;
import com.by.service.WxUserService;
import com.by.util.HttpUtil;
import com.by.util.RedisUtil;

@Service
public class WxUserServiceImpl implements WxUserService {
	@Resource
	private RedisUtil redisUtil;
	@Autowired
	private WxUserMapper wxUserMapper;

	private static final String APPID = "wxbcc04e1f9d620c9d";
	private static final String SECRET = "bc13056c4aea2aa0be9db19f252f8d2d";

	public String wxlogin(String code) {
		//1.使用code通过微信api换取 openid、session_key
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code="
				+ code + "&grant_type=authorization_code";
		JSONObject jo = JSONObject.parseObject(HttpUtil.httpRequest(url, "GET", null));
		System.out.println(jo + "code:" + code);
		
		String openid = jo.getString("openid");

		//2.查询数据库openid是否存在
		if(wxUserMapper.getByOpenid(openid) == null) {
			//2.1 不存在 添加到数据库
			try {
				wxUserMapper.add(openid);
			} catch (Exception e) {
				//添加数据库失败 返回null
				return null;
			}
		} else {
			// 2.2 存在 更新登录时间		
			wxUserMapper.loginCount(openid);
		}		

		//3.生成Token
		//3.1生成一个uuid 作为Token
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		//3.2将uuid作为key，openid、session_key作为value存到redis
		redisUtil.hset(uuid, "openid", jo.getString("openid"),1800);
		redisUtil.hset(uuid, "session_key", jo.getString("session_key"));
		//4.返回Token
		return uuid;
	}

	public WxUser get(String openId) {
		return wxUserMapper.getByOpenid(openId);
	}

}
