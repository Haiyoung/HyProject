package com.haiyoung.tinycode.common.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResponseCodeEnum {
    /**
     * 响应成功
     */
    SUCCESS("0000", "成功"),

    SYSTEM_ERROR("0001", "系统错误"),

    PARAM_ERROR("0002", "非法参数"),

    AUTHENTICATION_ERROR("0003", "系统未登录"),

    AUTHORIZATION_ERROR("0004", "系统未授权"),

    REQUEST_TIMEOUT("0005", "请求超时"),

    REQUEST_METHOD_NOT_SUPPORT("0006", "请求方法[%s]不支持"),

    // 9开头为保留段，9999为未知异常
    UNCAUGHT_ERROR("9999", "系统超时，请稍后重试！");

  private String code;

  private String message;

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
