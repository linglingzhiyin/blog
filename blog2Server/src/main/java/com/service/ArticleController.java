package com.service;

import com.domain.Article;
import com.mapper.ArticleMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("Test接口模块")
@RestController
@CacheConfig(cacheNames = "ArticleController")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleMapper articleMapper;
    @ApiOperation(value = "根据id查询学生信息", notes = "查询数据库中某个的学生信息")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
//        int i=5/0;
        return "test";
    }

}
