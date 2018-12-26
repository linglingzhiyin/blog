package com.service;

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

    @RequestMapping("/articleList")
    public List<Article> articleList() {
        if (articleMapper == null)
            return null;
        List<Article> articles = articleMapper.selectList(null);
        return articles;
    }

    //添加
    @RequestMapping(value = "/admin/article", method = RequestMethod.POST
    ,produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Map<String, Object>> articleAddDo(@RequestBody Article article) {
        Map reMap = new HashMap<String, String>();
        article.setTime(new Date());
        try {
            if (articleMapper.insert(article)>0) {
                reMap.put("succ", "发表文章成功！");
            } else {
                reMap.put("error", "发表文章失败！");
            }
        }catch (Exception e){
            reMap.put("error", "发表文章失败！");
        }
        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }
}
