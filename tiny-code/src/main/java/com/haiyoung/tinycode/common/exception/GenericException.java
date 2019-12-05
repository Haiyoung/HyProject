package com.haiyoung.tinycode.common.exception;

import com.haiyoung.tinycode.common.response.BaseResponse;
import com.haiyoung.tinycode.common.response.ResponseCode;

/**
 * 业务信息类异常，日志输出级别为INFO
 */
public class GenericException extends CustomException {

    private static final long serialVersionUID = -4627812591813648904L;

    public GenericException(BaseResponse<?> response) {
        super(response.getCode(), response.getMsg());
    }

    public GenericException(ResponseCode responseCode) {
        super(responseCode);
    }

    public GenericException(ResponseCodeEnum responseCode) {
        super(responseCode);
    }

    public GenericException(String code, String message) {
        super(code, message);
    }

    public GenericException(int code, String message) {
        super(code, message);
    }

}
