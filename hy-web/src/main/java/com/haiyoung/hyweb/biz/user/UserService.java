package com.haiyoung.hyweb.biz.user;

import com.haiyoung.hyweb.mapper.RoleMapper;
import com.haiyoung.hyweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Haiyoung on 2017/11/30.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    public User getUserById(String userId){
        return userMapper.getUserById(userId);
    }

    public List<Role> getUsers(){
        return roleMapper.roles();
    }

    public List<Role> getUsers(List<String> list){
        return roleMapper.getRolesByIds(list);
    }
}
