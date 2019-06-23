package com.tensquare.article.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.article.entity.Article;
import com.tensquare.article.service.impl.ArticleServiceImpl;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author GoogleSearch
 * @since 2019-06-23
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleServiceImp;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    public Result add(@RequestBody Article article) {
        article.setId(idWorker.nextId()+"");
        article.setThumbup(0);
        return new Result(true, StatusCode.OK, "添加成功", articleServiceImp.save(article));
    }

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", articleServiceImp.list());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id) {
        String redisKey = "article_" + id;
        Article article = (Article) redisTemplate.opsForValue().get(redisKey);
        if (article == null) {
            article = articleServiceImp.getById(id);
            redisTemplate.opsForValue().set(redisKey, article, 1, TimeUnit.DAYS);   //一天后过期
        }

        return new Result(true, StatusCode.OK, "查询成功", article);
    }

    @PutMapping("/{id}")
    public Result udpateById(@PathVariable("id") String id, @RequestBody Article article) {
        article.setId(id);
        redisTemplate.delete("article_" + id);
        return new Result(true, StatusCode.OK, "更新成功", articleServiceImp.update(article, null));
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return new Result(true, StatusCode.OK, "更新成功", articleServiceImp.removeById(id));
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Map article) {
        return new Result(true, StatusCode.OK, "查询成功", articleServiceImp.listByMap(article));
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result findSearch(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             @RequestBody Map article) {

        IPage iPage = articleServiceImp.page(new Page<>(pageNum, pageSize), new QueryWrapper<>().allEq(article));
        Map<String, Object> map = new HashMap<>();
        map.put("total", iPage.getTotal());
        map.put("rows", iPage.getRecords());

        return new Result(true, StatusCode.OK, "查询成功", map);
    }

    @PutMapping("/thumbup/{id}")
    public Result updateThumbup(@PathVariable("id") String id) {
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .eq("id", id)
                .setSql("thumbup = thumbup + 1");
        return new Result(true, StatusCode.OK, "点赞成功", articleServiceImp.update(updateWrapper));
    }
}
