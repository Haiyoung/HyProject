package com.haiyoung.tinycode.common.response;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-12-04 20:04
 * @Version 1.0
 */
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> resp = new BaseResponse();
        resp.setCode(ResponseCode.SUCCESS.getCode());
        resp.setMsg(ResponseCode.SUCCESS.getMsg());
        resp.setData(data);
        return resp;
    }

    public static <T> BaseResponse<T> fail(ResponseCode responseCode) {
        BaseResponse<T> resp = new BaseResponse();
        resp.setCode(responseCode.getCode());
        resp.setMsg(responseCode.getMsg());
        return resp;
    }

    public static <T> BaseResponse<T> fail(String code, String msg) {
        BaseResponse<T> resp = new BaseResponse();
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public BaseResponse() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.msg = ResponseCode.SUCCESS.getMsg();
    }

}
