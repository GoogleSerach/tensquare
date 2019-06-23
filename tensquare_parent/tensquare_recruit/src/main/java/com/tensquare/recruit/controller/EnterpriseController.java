package com.tensquare.recruit.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.recruit.entity.Enterprise;
import com.tensquare.recruit.entity.Recruit;
import com.tensquare.recruit.service.impl.EnterpriseServiceImpl;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 企业 前端控制器
 * </p>
 *
 * @author GoogleSearch
 * @since 2019-06-23
 */
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseServiceImpl enterpriseServiceImpl;

    @Autowired
    private IdWorker idWorker;

    @PostMapping
    public Result add(@RequestBody Enterprise enterprise) {
        enterprise.setId(idWorker.nextId()+"");
        return new Result(true, StatusCode.OK, "添加成功", enterpriseServiceImpl.save(enterprise));
    }

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", enterpriseServiceImpl.list());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id) {
        return new Result(true, StatusCode.OK, "查询成功", enterpriseServiceImpl.getById(id));
    }

    @PutMapping("/{id}")
    public Result updateById(@PathVariable("id") String id, @RequestBody Enterprise enterprise) {
        enterprise.setId(id);
        return new Result(true, StatusCode.OK, "更新成功", enterpriseServiceImpl.update(enterprise, null));
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return new Result(true, StatusCode.OK, "更新成功", enterpriseServiceImpl.removeById(id));
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Map enterprise) {
        return new Result(true, StatusCode.OK, "查询成功", enterpriseServiceImpl.listByMap(enterprise));
    }

    @GetMapping("/search/hotlist")
    public Result findHotlist() {
        QueryWrapper<Enterprise> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ishot", 1);

        return new Result(true, StatusCode.OK, "查询成功", enterpriseServiceImpl.list(queryWrapper));
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result findSearch(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             @RequestBody Map enterprise) {

        IPage iPage = enterpriseServiceImpl.page(new Page<>(pageNum, pageSize), new QueryWrapper<>().allEq(enterprise));
        Map<String, Object> map = new HashMap<>();
        map.put("total", iPage.getTotal());
        map.put("rows", iPage.getRecords());

        return new Result(true, StatusCode.OK, "查询成功", map);
    }
}
