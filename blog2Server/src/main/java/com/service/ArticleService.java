package com.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.domain.Article;
import com.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/articleList")
    public ResponseEntity<Map<String, Object>> articleList(@RequestParam(required = true, defaultValue = "1") Integer page
            , @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        Map reMap = new HashMap<String, String>();
        Page<Article> pageArticle = new Page<Article>(page, pageSize);
        pageArticle = (Page<Article>) articleMapper.selectPage(pageArticle, null);
        reMap.put("result", pageArticle);
        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }

    @GetMapping("/admin/article/detail")
    public ResponseEntity<Map<String, Object>> articleById(Integer articleId) {
        Map reMap = new HashMap<String, String>();
        Article article = articleMapper.selectById(articleId);
        reMap.put("article", article);
        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }

    @PostMapping("/admin/articleList")
    public ResponseEntity<Map<String, Object>> adminArticleList(
            @RequestParam(required = true, defaultValue = "1") Integer page
            , @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        Map reMap = new HashMap<String, String>();
        Page<Article> pageArticle = new Page<Article>(page, pageSize);
        pageArticle = (Page<Article>) articleMapper.selectPage(pageArticle, null);
        reMap.put("result", pageArticle);
        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }

    //添加
    @RequestMapping(value = "/admin/article", method = RequestMethod.POST
            , produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Map<String, Object>> articleAddDo(@RequestBody Article article) {
        Map reMap = new HashMap<String, String>();
        article.setTime(new Date());
        try {
            if (articleMapper.insert(article) > 0) {
                reMap.put("succ", "发表文章成功！");
            } else {
                reMap.put("error", "发表文章失败！");
            }
        } catch (Exception e) {
            reMap.put("error", "发表文章失败！");
        }
        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }
}
