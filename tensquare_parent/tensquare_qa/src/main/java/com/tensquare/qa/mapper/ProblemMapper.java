package com.tensquare.qa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.qa.pojo.Problem;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProblemMapper extends BaseMapper<Problem> {

    @Select("select * from tb_problem p where exists(select problemid from tb_pl pl where pl.problemid = p.id and (pl.labelid = '0' or pl.labelid = #{labelId})) order by p.replytime")
    List<Problem> findNewList(String labelId, Page<Problem> pagination);

    @Select("select * from tb_problem p where exists(select problemid from tb_pl pl where pl.problemid = p.id and (pl.labelid = '0' or pl.labelid = #{labelId})) order by p.reply desc")
    List<Problem> findHotList(String labelId, Page<Problem> page);

    @Select("select * from tb_problem p where exists(select problemid from tb_pl pl where pl.problemid = p.id and (pl.labelid = '0' or pl.labelid = #{labelId})) and p.reply = 0 order by p.createtime desc")
    List<Problem> findWaitList(String labelId, Page<Problem> page);
}
