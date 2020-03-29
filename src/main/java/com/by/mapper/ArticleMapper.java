package com.by.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.by.entity.Article;

@Mapper
public interface ArticleMapper {

	@Select("select * from article where id = #{id}")
	Article getArticle(int id);

	@Select("select id,name,`show` from article")
	List<Article>articleName();
	
	
}
