package com.service;

import com.domain.Article;
import com.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping("/articleList")
    public List<Article> articleList() {
        if (articleMapper==null)
            return null;
        List<Article> articles=articleMapper.selectList(null);
        return articles;
    }

}
