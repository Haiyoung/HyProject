package com.haiyoung.tinycode.common.exception;

import com.haiyoung.tinycode.common.response.ResponseCode;

public class NoAuthorizationException extends CustomException{

    private static final long serialVersionUID = 1L;

    public NoAuthorizationException(ResponseCode responseCode) {
        super(responseCode);
    }

    public NoAuthorizationException(ResponseCodeEnum responseCode) {
        super(responseCode);
    }

    public NoAuthorizationException(String code, String message) {
        super(code, message);
    }

    public NoAuthorizationException(int code, String message) {
        super(code, message);
    }
}
