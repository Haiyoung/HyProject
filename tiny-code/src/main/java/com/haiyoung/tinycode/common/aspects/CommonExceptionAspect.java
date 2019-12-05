package com.haiyoung.tinycode.common.aspects;

import com.alibaba.fastjson.JSON;
import com.haiyoung.tinycode.common.exception.BusinessException;
import com.haiyoung.tinycode.common.response.BaseResponse;
import com.haiyoung.tinycode.common.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class CommonExceptionAspect {

    /**
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("execution(* com.haiyoung..*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            return proceedingJoinPoint.proceed();
        } catch (BusinessException be) {
            Signature s = proceedingJoinPoint.getSignature();
            System.out.println("------------aspectJ-------------");
            log.warn("business error was happened, class = {}, method = {}, args = {}", s.getDeclaringTypeName(), s.getName(), JSON.toJSONString(proceedingJoinPoint.getArgs()), be);
            return BaseResponse.fail(be.getCode(), be.getMessage());
        } catch (Throwable e) {
            Signature s = proceedingJoinPoint.getSignature();
            log.error("unknown error was happened, class = {}, method = {}, args = {}", s.getDeclaringTypeName(), s.getName(), JSON.toJSONString(proceedingJoinPoint.getArgs()), e);
            return BaseResponse.fail(ResponseCode.FAILURE);
        }
    }
}
