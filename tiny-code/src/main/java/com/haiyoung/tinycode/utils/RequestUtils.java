package com.haiyoung.tinycode.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class RequestUtils {

    /**
     * 静态工具类方法获取HttpServletRequest对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            log.warn("get HttpServletRequest error, cause by : {}", e.getMessage());
            return null;
        }
    }

    /**
     * 功能描述: 解析 xml格式请求参数 TO string
     */
    public static String requestToString(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            log.warn("request to string error", e);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.warn("[RequestUtils.requestToString] close BufferedReader error {}", e);
                }
            }
        }
        return null;
    }
}
