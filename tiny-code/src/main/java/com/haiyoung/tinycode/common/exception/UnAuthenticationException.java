package com.haiyoung.tinycode.common.exception;

import com.haiyoung.tinycode.common.response.ResponseCode;

public class UnAuthenticationException extends CustomException {

    private static final long serialVersionUID = 1L;

    public UnAuthenticationException(ResponseCode responseCode) {
        super(responseCode);
    }

    public UnAuthenticationException(ResponseCodeEnum responseCode) {
        super(responseCode);
    }

    public UnAuthenticationException(String code, String message) {
        super(code, message);
    }

    public UnAuthenticationException(int code, String message) {
        super(code, message);
    }

}
