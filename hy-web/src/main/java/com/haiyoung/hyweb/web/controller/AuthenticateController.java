package com.haiyoung.hyweb.web.controller;

import com.haiyoung.hyweb.biz.user.User;
import com.haiyoung.hyweb.biz.user.UserService;
import com.haiyoung.hyweb.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AuthenticateController {

    @Autowired
    private UserService userService;

    @RequestMapping("/authenticate")
    public boolean authenticate(
            HttpServletRequest request,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "password") String password){

        if(StringUtils.isNotEmpty(userId)){
            User user = userService.getUserById(userId);
            if(user != null){
                if(password.equals(user.getPassword())){
                    buildSession(request, user);
                    return true;
                }
            }
        }
        return false;
    }

    private void buildSession(HttpServletRequest request, User user){
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("userId", user.getUserId());
        httpSession.setAttribute("userName", user.getUserName());
/*        //添加关于用户角色的控制
        List<Role> roles = userService.getRoles(user.getId());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roles.size(); i++) {
            if (i != 0)
                sb.append(",");
            sb.append(roles.get(i).getId());
        }
        session.setAttribute(WebConstants.SessionUserRoles, sb.toString());*/
    }
}
