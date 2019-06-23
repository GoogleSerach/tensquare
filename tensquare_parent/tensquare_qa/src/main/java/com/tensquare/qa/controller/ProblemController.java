package com.tensquare.qa.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.base.Strings;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private IdWorker idWorker;

    @GetMapping("/waitlist/{labelId}/{pageNum}/{pageSize}")
    public Result findWaitList(@PathVariable("labelId") String labelId,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {

        IPage<Problem> problemPage = problemService.findWaitList(labelId, pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("total", problemPage.getTotal());
        map.put("rows", problemPage.getRecords());
        problemPage.getTotal();
        return new Result(true, StatusCode.OK, "查询成功", map);
    }

    @GetMapping("/hotlist/{labelId}/{pageNum}/{pageSize}")
    public Result findHotList(@PathVariable("labelId") String labelId,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {

        IPage<Problem> problemPage = problemService.findHotList(labelId, pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("total", problemPage.getTotal());
        map.put("rows", problemPage.getRecords());
        problemPage.getTotal();
        return new Result(true, StatusCode.OK, "查询成功", map);
    }

    @GetMapping("/newlist/{labelId}/{pageNum}/{pageSize}")
    public Result findNewList(@PathVariable("labelId") String labelId,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize) {

        IPage<Problem> problemPage = problemService.findNewList(labelId, pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("total", problemPage.getTotal());
        map.put("rows", problemPage.getRecords());
        problemPage.getTotal();
        return new Result(true, StatusCode.OK, "查询成功", map);
    }

    @PostMapping("/search")
    public Result findByCondisional(@RequestBody Map problem) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findByCondisional(problem));
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        problemService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result findSearch(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize) {
        IPage<Problem> problemPage = problemService.findSearch(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("total", problemPage.getTotal());
        map.put("rows", problemPage.getRecords());
        problemPage.getTotal();
        return new Result(true, StatusCode.OK, "查询成功", map);
    }

    @PutMapping("/{id}")
    public Result udpate(@PathVariable("id") String id, @RequestBody Problem problem) {
        problem.setId(id);
        problem.setUpdatetime(new Date());
        problemService.udpate(problem);

        return new Result(true, StatusCode.OK, "更新成功");
    }

    @PostMapping
    public Result add(@RequestBody Problem problem) {
        if (Strings.isNullOrEmpty(problem.getContent()) || Strings.isNullOrEmpty(problem.getTitle())) {
            throw new RuntimeException("标题或内容不能为空");
        }

        problem.setId(idWorker.nextId()+"");
        problem.setCreatetime(new Date());
        problem.setUpdatetime(new Date());
        problemService.add(problem);

        return new Result(true, StatusCode.OK, "添加成功");
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findById(id));
    }

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findAll());
    }
}
