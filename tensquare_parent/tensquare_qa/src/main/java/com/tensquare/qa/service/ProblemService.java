package com.tensquare.qa.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.qa.mapper.ProblemMapper;
import com.tensquare.qa.pojo.Problem;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    public IPage<Problem> findSearch(Integer pageNum, Integer pageSize) {
        return problemMapper.selectPage(new Page<>(pageNum, pageSize), null);
    }

    public List<Problem> findAll() {
        return problemMapper.selectList(null);
    }

    public Problem findById(String id) {
        return problemMapper.selectById(id);
    }

    public void add(Problem problem) {
        problemMapper.insert(problem);
    }

    public void udpate(Problem problem) {
        problemMapper.updateById(problem);
    }

    public void deleteById(String id) {
        problemMapper.deleteById(id);
    }

    public List<Problem> findByCondisional(Map problem) {
        return problemMapper.selectByMap(problem);
    }

    public IPage<Problem> findNewList(String labelId, Integer pageNum, Integer pageSize) {
        //将page传过去再封装records返回即可得到getTotal
        Page<Problem> page = new Page<>(pageNum, pageSize);
        return page.setRecords(problemMapper.findNewList(labelId, page));
    }

    public IPage<Problem> findHotList(String labelId, Integer pageNum, Integer pageSize) {
        Page<Problem> page = new Page<>(pageNum, pageSize);
        return page.setRecords(problemMapper.findHotList(labelId, page));
    }

    public IPage<Problem> findWaitList(String labelId, Integer pageNum, Integer pageSize) {
        Page<Problem> page = new Page<>(pageNum, pageSize);
        return page.setRecords(problemMapper.findWaitList(labelId, page));
    }
}
