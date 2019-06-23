package com.tensquare.spit.dao;

import com.tensquare.spit.entity.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
* 吐槽数据访问层
* @author Administrator
*
*/
public interface SpitDao extends MongoRepository<Spit, String> {

    Page<Spit> findByParentid(String id, Pageable pageable);
}
