package com.tensquare.base.mapper;

import com.tensquare.base.pojo.Label;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface LabelMapper {

    Label findById(String id);

    List<Label> findAll();

    void add(Label label);

    List<Label> findByRecommend(String recommend);

    List<Label> findByState(String state);

    void update(Label label);

    void deleteById(String id);

    List<Label> findSearch(Map label);
}
