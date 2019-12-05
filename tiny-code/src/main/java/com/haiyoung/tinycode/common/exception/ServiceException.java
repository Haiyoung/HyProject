package com.haiyoung.tinycode.common.exception;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-12-04 20:22
 * @Version 1.0
 */

import com.haiyoung.tinycode.common.response.ResponseCode;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;

    public ServiceException(String code, String msg) {
        super(code + ":" + msg);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String code, String msg, Throwable cause) {
        super(code + ":" + msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(ResponseCode responseCode) {
        super(responseCode.getCode() + ":" + responseCode.getMsg());
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public ServiceException(ResponseCode responseCode, Throwable cause) {
        super(responseCode.getCode() + ":" + responseCode.getMsg(), cause);
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
