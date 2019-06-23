package com.tensquare.gathering.controller;


import com.tensquare.gathering.entity.Gathering;
import com.tensquare.gathering.service.impl.GatheringServiceImpl;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import util.IdWorker;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 活动 前端控制器
 * </p>
 *
 * @author GoogleSearch
 * @since 2019-06-23
 */
@RestController
@RequestMapping("/gathering")
public class GatheringController {

    @Autowired
    private GatheringServiceImpl gatheringServiceImpl;

    @Autowired
    private IdWorker idWorker;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", gatheringServiceImpl.list());
    }

    @Cacheable(value = "gathering", key = "#id")  //gathering全局标识，id是下面标识
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id) {
        return new Result(true, StatusCode.OK, "查询成功", gatheringServiceImpl.getById(id));
    }

    @CacheEvict(value = "gathering", key = "#id")
    @PutMapping("/{id}")
    public Result udpateById(@PathVariable("id") String id, @RequestBody Gathering gathering) {
        gathering.setId(id);
        return new Result(true, StatusCode.OK, "更新成功", gatheringServiceImpl.update(gathering, null));
    }

    @CacheEvict(value = "gathering", key = "#id")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        return new Result(true, StatusCode.OK, "更新成功", gatheringServiceImpl.removeById(id));
    }
}
