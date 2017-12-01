package com.haiyoung.hyweb.biz.user;

import com.haiyoung.hyweb.biz.BizModel;

/**
 * Created by Haiyoung on 2017/11/30.
 */
public class User extends BizModel{

    private static final long serialVersionUID = -8546196035551829201L;

    private Integer id;

    private String userId;

    private String userName;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
