package com.service;

import com.domain.Article;
import com.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CacheConfig(cacheNames = "ArticleController")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        int i=5/0;
        return "test";
    }

}
