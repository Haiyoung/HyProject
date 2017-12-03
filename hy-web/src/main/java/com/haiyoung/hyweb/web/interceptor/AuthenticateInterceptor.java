package com.haiyoung.hyweb.web.interceptor;

import com.haiyoung.hyweb.util.JsonUtils;
import com.haiyoung.hyweb.util.Message;
import com.haiyoung.hyweb.util.WebUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticateInterceptor extends HandlerInterceptorAdapter{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("ServletPath:"+request.getServletPath());
        System.out.println("ContentPath:"+request.getContextPath());
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        String userId = (String) WebUtils.getSessionAttribute(request,"userId");
//        String userIdName = (String) WebUtils.getCookie(request,"userId").getValue();
        if(WebUtils.getSessionAttribute(request,"userId") == null){
//        if(WebUtils.getCookie(request,"userId") == null){
            String servletPath = request.getServletPath();
            if(servletPath.startsWith("/api/")){
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(JsonUtils.toJson(Message.NoAuthorityMessage));
            } else if(servletPath.startsWith("/page/")){
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath == null ?"":contextPath
                        +"/page/login"+"?url="
                        + WebUtils.getCurrentUrl(request));
            }
            return false;
        }
        return true;
    }
}
