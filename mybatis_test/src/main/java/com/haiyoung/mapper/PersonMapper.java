package com.haiyoung.mapper;

import com.haiyoung.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}