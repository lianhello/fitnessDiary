package com.by.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.by.entity.Article;
import com.by.mapper.ArticleMapper;
import com.by.service.ArticleService;
import com.by.util.RedisUtil;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Resource
	RedisUtil redisUtil;
	@Autowired
	ArticleMapper articleMapper;	
	
	@Cacheable(value = "articleName")
	public JSONArray articleName() {
		List<Article> article = articleMapper.articleName();
		JSONArray articleNameArray = new JSONArray();
		for(Article a: article) {
			JSONObject articleJSON = new JSONObject();
			if(a.getShow()==1) {
				articleJSON.put("id", a.getId());
				articleJSON.put("name", a.getName());
			}
			articleNameArray.add(articleJSON);
		}
		return articleNameArray;		
	}
	
	@Cacheable(value = "articleMain")
	public Article article(int id) {
		return articleMapper.getArticle(id);
	}
}
