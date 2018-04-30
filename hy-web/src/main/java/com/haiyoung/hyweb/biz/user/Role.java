package com.haiyoung.hyweb.biz.user;

import com.haiyoung.hyweb.biz.BizModel;

public class Role extends BizModel{

    private static final long serialVersionUID = -7727374584809823485L;

    private String roleId;

    private String roleName;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
