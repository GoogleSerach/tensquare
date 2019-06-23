package com.tensquare.base.service;

import com.tensquare.base.mapper.LabelMapper;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;
import java.util.Map;

@Service
public class LabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findByRecommend(String recommend) {
        return labelMapper.findByRecommend(recommend);
    }

    public Label findById(String id) {
        return labelMapper.findById(id);
    }

    public List<Label> findAll() {
        return labelMapper.findAll();
    }

    public void add(Label label) {
        label.setId(idWorker.nextId()+"");
        label.setFans(0l);
        if(label.getLabelname() == null || label.getLabelname().isEmpty()) {
            throw new RuntimeException("标签名不能为空");
        }
        if(label.getRecommend() == null || label.getRecommend().isEmpty() ||
                (label.getRecommend() != "0" && label.getRecommend() != "1")) {
            label.setRecommend("0");
        }
        labelMapper.add(label);
    }

    public List<Label> findByState(String state) {
        return labelMapper.findByState(state);
    }

    public void update(Label label) {
        labelMapper.update(label);
    }

    public void deleteById(String id) {
        labelMapper.deleteById(id);
    }

    public List<Label> findSearch(Map label) {
        return labelMapper.findSearch(label);
    }
}
