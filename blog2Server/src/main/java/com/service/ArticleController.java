package com.service;

import com.domain.Article;
import com.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ArticleController {
    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        Article article = new Article();
        article.setContent("adasdasdasd");
        article.setClick(1);
        article.setTime(new Date());
        article.setCatalogId(1);
        article.setKeywords("asdasdasd");
        article.setTitle("哈哈哈哈哈");
        article.setDesci("哈哈哈哈哈");
        articleMapper.insert(article);
        return "test";
    }
}
