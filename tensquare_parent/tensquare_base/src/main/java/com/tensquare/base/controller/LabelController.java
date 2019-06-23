package com.tensquare.base.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/label")
@Transactional
public class LabelController {

    @Autowired
    private LabelService labelService;

    @DeleteMapping("/{id}")
    public Result udpate(@PathVariable("id") String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PutMapping("/{id}")
    public Result udpate(@PathVariable("id") String id, @RequestBody Label label) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @GetMapping("/list")
    public Result findById() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findByState("1"));
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Map label) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findSearch(label));
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result findSearch(@PathVariable("pageNum") int pageNum,
                             @PathVariable("pageSize") int pageSize,
                             @RequestBody Map label) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Label> pageInfo = new PageInfo<>(labelService.findSearch(label));
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageInfo.getTotal(), pageInfo.getList()));
    }

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    @PostMapping
    public Result findById(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "添加成功", null);
    }

    @GetMapping("/toplist/{pageNum}/{pageSize}")
    public Result findByIdRecommend(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Label> pageInfo = new PageInfo<>(labelService.findByRecommend("1"));
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageInfo.getTotal(), pageInfo.getList()));
    }
}
