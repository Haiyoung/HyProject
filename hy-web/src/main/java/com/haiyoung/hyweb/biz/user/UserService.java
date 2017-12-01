package com.haiyoung.hyweb.biz.user;

import com.haiyoung.hyweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Haiyoung on 2017/11/30.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(String userId){
        return userMapper.getUserById(userId);
    }
}
