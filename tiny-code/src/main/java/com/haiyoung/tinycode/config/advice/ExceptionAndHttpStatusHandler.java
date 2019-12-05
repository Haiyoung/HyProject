package com.haiyoung.tinycode.config.advice;

import com.alibaba.fastjson.JSON;
import com.haiyoung.tinycode.common.response.BaseResponse;
import com.haiyoung.tinycode.common.exception.BusinessException;
import com.haiyoung.tinycode.common.exception.GenericException;
import com.haiyoung.tinycode.common.exception.NoAuthorizationException;
import com.haiyoung.tinycode.common.exception.ResponseCodeEnum;
import com.haiyoung.tinycode.common.exception.ServiceException;
import com.haiyoung.tinycode.common.exception.UnAuthenticationException;
import com.haiyoung.tinycode.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * 统一异常处理类 </br> 1.根据BaseResponse的code设置对应的HttpStatus,使响应信息更清晰 </br>
 * 2.业务外的异常统一返回体,如RequestBody装配错误,请求方式不对,404 NOT FOUND等
 *
 * @author SHY
 */
@Slf4j
@ControllerAdvice
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExceptionAndHttpStatusHandler
implements ResponseBodyAdvice<BaseResponse<Object>>, ErrorController {

    private static final String ERROR_PATH = "/error";

    private static final String PING = "/ping";

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse<Object> missingServletRequestParameterException(
            MissingServletRequestParameterException e, HttpServletRequest request){
        log.info("request [{}] occur MissingServletRequestParameterException:", request.getRequestURI(), e);
        String msg = "Missing parameter: " + e.getParameterName();
        return BaseResponse.fail(ResponseCodeEnum.PARAM_ERROR.getCode(), msg);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse<Object> httpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.warn(
            "The request [{}] occur HttpRequestMethodNotSupportedException:",
            request.getRequestURI(),
            e);
        return BaseResponse.fail(
            ResponseCodeEnum.REQUEST_METHOD_NOT_SUPPORT.getCode(),
            String.format(ResponseCodeEnum.REQUEST_METHOD_NOT_SUPPORT.getMessage(), e.getMethod()));
    }

    /** 统一参数验证异常处理 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<String> validExceptionHandler(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("The request [{}] occur BindException:", request.getRequestURI(), e);
        return BaseResponse.fail(
            ResponseCodeEnum.PARAM_ERROR.getCode(),
            e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * get 方法参数检验异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<String> constraintViolationException(
        ConstraintViolationException e, HttpServletRequest request) {
        log.warn("The request [{}] occur ConstraintViolationException:", request.getRequestURI(), e);
        return BaseResponse.fail(
            ResponseCodeEnum.PARAM_ERROR.getCode(),
            e.getConstraintViolations().iterator().next().getMessageTemplate());
    }

    /** 统一服务层异常处理 */
    @ExceptionHandler(value = ServiceException.class)
    public BaseResponse<String> serviceException(HttpServletRequest request, ServiceException e) {
        log.error("The request [{}] occur ServiceException:", request.getRequestURI(), e);
        return BaseResponse.fail(e.getCode(), e.getMsg());
    }

    /** 统一业务异常处理 */
    @ExceptionHandler(value = BusinessException.class)
    public BaseResponse<String> businessException(HttpServletRequest request, BusinessException e) {
        log.warn("The request [{}] occur BusinessException:", request.getRequestURI(), e);
        return BaseResponse.fail(e.getCode(), e.getMsg());
    }

    /** 统一业务信息异常处理 */
    @ExceptionHandler(value = GenericException.class)
    public BaseResponse<String> genericException(HttpServletRequest request, GenericException e) {
        log.info("The request [{}] occur GenericException:", request.getRequestURI(), e);
        return BaseResponse.fail(e.getCode(), e.getMsg());
    }

    /** 统一处理UnAuthenticationException */
    @ExceptionHandler(value = UnAuthenticationException.class)
    public BaseResponse<String> unAuthenticationException(
            HttpServletRequest request, UnAuthenticationException e) {
        log.info("The request [{}] occur UnAuthenticationException:", request.getRequestURI(), e);
        return BaseResponse.fail(e.getCode(), e.getMsg());
    }

    /** 统一处理NoAuthorizationException */
    @ExceptionHandler(value = NoAuthorizationException.class)
    public BaseResponse<String> noAuthorizationException(
            HttpServletRequest request, NoAuthorizationException e) {
        log.info("The request [{}] occur NoAuthorizationException:", request.getRequestURI(), e);
        return BaseResponse.fail(e.getCode(), e.getMsg());
    }

    /** 统一异常处理 */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse<String> exception(
            HttpServletRequest request, Exception e, HttpServletResponse response) {
        log.error(
            "The request [{}] occur exception, params: [{}]",
            request.getRequestURI(),
            RequestUtils.requestToString(request),
            e);
        return BaseResponse.fail(
            ResponseCodeEnum.UNCAUGHT_ERROR.getCode(), ResponseCodeEnum.UNCAUGHT_ERROR.getMessage());
    }

    /** 处理框架异常错误 */
    @RequestMapping(
        value = ERROR_PATH,
        method = {
            RequestMethod.GET,
            RequestMethod.POST,
            RequestMethod.DELETE,
            RequestMethod.PATCH,
            RequestMethod.PUT
        })
    public BaseResponse<String> error(HttpServletResponse response, Exception ex, HttpServletRequest httpServletRequest) {
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        return BaseResponse.fail(ResponseCodeEnum.UNCAUGHT_ERROR.getCode(), status.getReasonPhrase());
    }

    /** 处理所有返回值为BaseResponse类型的controller */
    @Override
    public boolean supports(
            MethodParameter returnType, Class<? extends HttpMessageConverter<?>> var2) {
        return returnType.getMethod().getReturnType().isAssignableFrom(BaseResponse.class);
    }

    @Override
    public BaseResponse<Object> beforeBodyWrite(
        BaseResponse<Object> br,
        MethodParameter var2,
        MediaType var3,
        Class<? extends HttpMessageConverter<?>> var4,
        ServerHttpRequest req,
        ServerHttpResponse res) {
            ServletServerHttpRequest sshr = (ServletServerHttpRequest) req;
            HttpServletRequest request = sshr.getServletRequest();
            String uri = request.getRequestURI();
            if (!PING.endsWith(uri)) {
                log.info("uri: {}, Response: {}", uri, JSON.toJSONString(br));
            }
            return br;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
