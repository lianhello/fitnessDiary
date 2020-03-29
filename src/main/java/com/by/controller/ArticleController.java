package com.by.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.by.entity.Article;
import com.by.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	ArticleService articleService;

	@RequestMapping(value = "/articleName", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray articleName() {
		return articleService.articleName();
	}

	@RequestMapping(value = "/articleMain", method = RequestMethod.GET)
	@ResponseBody
	public Article articleMain(int id) {
		return articleService.article(id);
	}
}
