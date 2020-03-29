package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.by.Application;
import com.by.mapper.ArticleMapper;
import com.by.util.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {
	@Autowired
	RedisUtil redisUtil;
	@Autowired ArticleMapper articleMapper;
	@Test
	public void test() {
		System.out.println(articleMapper.articleName());
	}

}
