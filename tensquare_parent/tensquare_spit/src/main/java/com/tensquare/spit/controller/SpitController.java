package com.tensquare.spit.controller;

import com.tensquare.spit.entity.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    public Result add(@RequestBody Spit spit) {
        spitService.add(spit);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(id));
    }

    @PutMapping("/{id}")
    public Result udpateById(@PathVariable("id") String id, @RequestBody Spit spit) {
        spit.set_id(id);
        spitService.updateById(spit);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @GetMapping("/comment/{parentid}/{pageNum}/{pageSize}")
    public Result findByparentid(@PathVariable("parentid") String parentid,
                                 @PathVariable("pageNum") Integer pageNum,
                                 @PathVariable("pageNum") Integer pageSize) {
        Page<Spit> page = spitService.findByParentid(parentid, pageNum, pageSize);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Spit>(page.getTotalElements(), page.getContent()));
    }

    @PutMapping("/thumbup/{spitId}")
    public Result updateThumbup(@PathVariable("spitId") String spitId) {
        String userid = "11";
        String redisKey = "thumbup_" + userid;
        if (redisTemplate.opsForValue().get(redisKey) != null) {
            return new Result(true, StatusCode.OK, "不能重复点赞");
        }

        spitService.updateThumbup(spitId, 1);
        redisTemplate.opsForValue().set(redisKey, 1);

        return new Result(true, StatusCode.OK, "点赞成功");
    }
}
