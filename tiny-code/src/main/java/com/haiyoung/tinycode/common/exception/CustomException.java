package com.haiyoung.tinycode.common.exception;

import com.haiyoung.tinycode.common.response.ResponseCode;

public class CustomException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    
    private final String code;

    private final String message;

    public CustomException(ResponseCode responseCode) {
	super(responseCode.getMsg());
	this.code = responseCode.getCode();
	this.message = responseCode.getMsg();
    }

    public CustomException(ResponseCodeEnum responseCode) {
	super(responseCode.getMessage());
	this.code = responseCode.getCode();
	this.message = responseCode.getMessage();
    }
    
    public CustomException(String code, String message) {
	super(message);
	this.code = code;
	this.message = message;
    }

    public CustomException(int code, String message) {
	super(message);
	this.code = String.valueOf(code);
	this.message = message;
    }

    public String getCode() {
	return code;
    }

    public String getMsg() {
	return message;
    }

}
