package com.haiyoung.hyweb.mapper;

import com.haiyoung.hyweb.biz.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Haiyoung on 2017/11/30.
 */
@Mapper
@Repository
public interface UserMapper {

    User getUserById(@Param("userId") String userId);

    List<User> findAll();
}
