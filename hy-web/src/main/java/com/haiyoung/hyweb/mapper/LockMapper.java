package com.haiyoung.hyweb.mapper;

import com.haiyoung.hyweb.biz.lock.Lock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LockMapper {

    Lock getLockById(@Param("id") String id);

    List<Lock> findAll();
}
