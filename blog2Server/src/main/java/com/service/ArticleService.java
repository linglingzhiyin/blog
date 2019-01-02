package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.domain.Article;
import com.domain.Comment;
import com.mapper.ArticleMapper;
import com.mapper.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CacheConfig(cacheNames = "ArticleService")
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/article/{articleId}")
    public ResponseEntity<Map<String, Object>> detail(@PathVariable Integer articleId, @RequestParam(required = true, defaultValue = "1") Integer page
            , @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        Map reMap = new HashMap<String, String>();
        Page<Comment> result = (Page<Comment>) commentMapper.selectPage(new Page<Comment>(page, pageSize)
                , new QueryWrapper<Comment>().eq("article_id", articleId));
        reMap.put("comments",result);
        Article article=articleMapper.selectById(articleId);
        article.setClick(article.getClick()+1);
        articleMapper.updateById(article);
        reMap.put("article",article);
        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }

    //返回所有文章
//    @Cacheable(key = "'articleList'")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Article> findArticleAll() {
        List<Article> articles = articleMapper.selectList(null);
        log.info("文章的数量" + articles.size());
        return articles;
    }

    //    @Cacheable(key = "'keywordAll'")
    @GetMapping("/keywordList")
    public ResponseEntity<Map<String, Integer>> keywordAll() {
        Map<String, Integer> keywords = new HashMap<String, Integer>();
        List<Article> articles = findArticleAll();
        keywords.put("Count", articles.size());
        for (Article a : articles) {
            if (a.getKeywords().equals("")) a.setKeywords("none");
            if (keywords.get(a.getKeywords()) == null || keywords.get(a.getKeywords()) == 0)
                keywords.put(a.getKeywords(), 1);
            else
                keywords.put(a.getKeywords(), keywords.get(a.getKeywords()) + 1);
        }

        return new ResponseEntity<Map<String, Integer>>(keywords, HttpStatus.OK);
    }

    @GetMapping("/articleList/{keyword}")
    public ResponseEntity<Map<String, Object>> findArticleByKeyword(@PathVariable String keyword, @RequestParam(required = true, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        Map reMap = new HashMap<String, String>();
        if (!keyword.equals("")) {
            Page<Article> pageArticle = new Page<Article>(page, pageSize);
            pageArticle = (Page<Article>) articleMapper.selectPage(pageArticle, new QueryWrapper<Article>().eq("keywords", keyword));
            reMap.put("result", pageArticle);
            return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
        } else
            return articleList(page, pageSize);
    }

    @GetMapping("/admin/commentList")
    public ResponseEntity<Map<String, Object>> adminArticleComment(
            @RequestParam(required = true, defaultValue = "1") Integer page
            , @RequestParam(required = false, defaultValue = "10") Integer pageSize
            , Integer articleId) {
        Map reMap = new HashMap<String, String>();

        Page<Comment> result = (Page<Comment>) commentMapper.selectPage(new Page<Comment>(page, pageSize)
                , new QueryWrapper<Comment>().eq("article_id", articleId));
        reMap.put("result", result);
        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }

    //    @Cacheable(key = "'articleList'")
    @GetMapping("/articleList")
    public ResponseEntity<Map<String, Object>> articleList(@RequestParam(required = true, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return this.adminArticleList(page, pageSize);
    }

    //    @Cacheable(key = "'adminArticleList'")
    @PostMapping("/admin/articleList")
    public ResponseEntity<Map<String, Object>> adminArticleList(@RequestParam(required = true, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Map reMap = new HashMap<String, String>();
        Page<Article> pageArticle = new Page<Article>(page, pageSize);
        pageArticle = (Page<Article>) articleMapper.selectPage(pageArticle, null);
        reMap.put("result", pageArticle);
        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }

    @PutMapping("/admin/article")
    public ResponseEntity<Map<String, Object>> articleBEdit(@RequestBody Article article) {
        Map reMap = new HashMap<String, String>();
        articleMapper.updateById(article);
        reMap.put("succ", "修改文章成功！");

        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }

    @GetMapping("/admin/article")
    public ResponseEntity<Map<String, Object>> articleById(Integer articleId) {
        Map reMap = new HashMap<String, String>();
        Article article = articleMapper.selectById(articleId);
        reMap.put("article", article);
        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }

    //添加
    @RequestMapping(value = "/admin/article", method = RequestMethod.POST
            , produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<Map<String, Object>> articleAddDo(@RequestBody Article article) {
        Map reMap = new HashMap<String, String>();
        article.setTime(new Date());
        if (articleMapper.insert(article) > 0) {
            reMap.put("succ", "发表文章成功！");
        } else {
            reMap.put("error", "发表文章失败！");
        }
        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }

    @DeleteMapping("/admin/article")
    public ResponseEntity<Map<String, Object>> articleDel(Integer articleId) {
        Map<String, Object> reMap = new HashMap<String, Object>();
        Integer result = articleMapper.deleteById(articleId);
        if (result == 1) {
            reMap.put("result", "删除文章成功");
        } else {
            reMap.put("result", "删除文章失败");
        }
        return new ResponseEntity<Map<String, Object>>(reMap, HttpStatus.OK);
    }
}
