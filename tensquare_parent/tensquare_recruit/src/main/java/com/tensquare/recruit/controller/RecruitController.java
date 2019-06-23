package com.tensquare.recruit.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.recruit.entity.Recruit;
import com.tensquare.recruit.service.impl.RecruitServiceImpl;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 职位 前端控制器
 * </p>
 *
 * @author GoogleSearch
 * @since 2019-06-23
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitServiceImpl recruitServiceImpl;

    /**
     * 需求分析：查询状态不为0并以创建日期降序排序，查询前12条记录
     * @return
     */
    @GetMapping("/search/newlist")
    public Result findNewlist() {
        IPage<Recruit> page = recruitServiceImpl.page(new Page<>(1, 12), null);

        return new Result(true, StatusCode.OK, "查询成功", page.getRecords());
    }

    /**
     * 需求分析：查询状态为2并以创建日期降序排序，查询前4条记录
     *
     * @return
     */
    @GetMapping("/search/recommend")
    public Result findRecommend() {
        QueryWrapper<Recruit> recruitQueryWrapper = new QueryWrapper<>();
        recruitQueryWrapper
                .eq("state", 2)
                .orderByDesc("Createtime");
        IPage<Recruit> page = recruitServiceImpl.page(new Page<>(1, 4), recruitQueryWrapper);

        return new Result(true, StatusCode.OK, "查询成功", page.getRecords());
    }

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", recruitServiceImpl.list());
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result findSearch(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             @RequestBody Map recruit) {

        IPage iPage = recruitServiceImpl.page(new Page<>(pageNum, pageSize), new QueryWrapper<>().allEq(recruit));
        Map<String, Object> map = new HashMap<>();
        map.put("total", iPage.getTotal());
        map.put("rows", iPage.getRecords());

        return new Result(true, StatusCode.OK, "查询成功", map);
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Map recruit) {
        return new Result(true, StatusCode.OK, "查询成功", recruitServiceImpl.listByMap(recruit));
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id) {
        return new Result(true, StatusCode.OK, "查询成功", recruitServiceImpl.getById(id));
    }

    @PutMapping("/{id}")
    public Result findById(@PathVariable("id") String id, Recruit recruit) {
        return new Result(true, StatusCode.OK, "更新成功", recruitServiceImpl.update(recruit, null));
    }

    @PostMapping
    public Result add(@RequestBody Recruit recruit) {
        return new Result(true, StatusCode.OK, "添加成功", recruitServiceImpl.save(recruit));
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return new Result(true, StatusCode.OK, "更新成功", recruitServiceImpl.removeById(id));
    }
}
