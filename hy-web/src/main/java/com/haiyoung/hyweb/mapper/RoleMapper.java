package com.haiyoung.hyweb.mapper;

import com.haiyoung.hyweb.biz.user.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {

    List<Role> roles();

    List<Role> getRolesByIds(List<String> list);
}
