package com.haiyoung.tinycode.controller;

import com.haiyoung.tinycode.common.response.BaseResponse;
import com.haiyoung.tinycode.common.exception.BusinessException;
import com.haiyoung.tinycode.common.exception.ResponseCodeEnum;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SHY
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PingController {
	
    @GetMapping("/ping")
    public BaseResponse<String> ping() {
        return BaseResponse.success("pong");
    }

    @GetMapping("/ping2")
    public BaseResponse<String> ping2() {
        try {
            return BaseResponse.success(1/0+"");
        }catch (Exception e){
            throw new BusinessException(ResponseCodeEnum.PARAM_ERROR.getCode(), ResponseCodeEnum.PARAM_ERROR.getMessage());
        }
    }
}
