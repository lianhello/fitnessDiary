package com.by.service;

import com.alibaba.fastjson.JSONArray;
import com.by.entity.Article;

public interface ArticleService {

	JSONArray articleName();
	Article article(int id);
}
