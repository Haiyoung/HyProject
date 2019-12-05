package com.haiyoung.tinycode.common.exception;

import com.haiyoung.tinycode.common.response.BaseResponse;
import com.haiyoung.tinycode.common.response.ResponseCode;

/**
 * 业务类异常，日志输出级别为WARN
 */
public class BusinessException extends CustomException {

    private static final long serialVersionUID = 8677167814587230312L;

    public BusinessException(BaseResponse<?> response) {
        super(response.getCode(), response.getMsg());
    }

    public BusinessException(ResponseCode responseCode) {
        super(responseCode);
    }

    public BusinessException(ResponseCodeEnum responseCode) {
        super(responseCode);
    }

    public BusinessException(String code, String message) {
        super(code, message);
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }

}
