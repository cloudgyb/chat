package com.github.cloudgyb.heartchat.config;


import com.github.cloudgyb.heartchat.modules.common.BizException;
import com.github.cloudgyb.heartchat.modules.common.RestApiResp;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Set;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public RestApiResp MissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String parameterName = e.getParameterName();
        return RestApiResp.error(String.format("丢失请求参数:%s！", parameterName));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED)
    public RestApiResp HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String method = e.getMethod();
        return RestApiResp.error(String.format("不支持%s方法！", method));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public RestApiResp httpRequestParamTypeMismatchException(MethodArgumentTypeMismatchException e) {
        MethodParameter parameter = e.getParameter();
        String parameterName = parameter.getParameterName();
        return RestApiResp.error(String.format("参数'%s'数据类型错误！", parameterName));
    }


    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public RestApiResp httpRequestParamTypeMismatchException(MissingServletRequestPartException e) {
        String requestPartName = e.getRequestPartName();
        return RestApiResp.error(String.format("必须上传文件，参数'%s'！", requestPartName));
    }


    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public RestApiResp businessException(BizException e) {
        if (e.getCode() == null) {
            return RestApiResp.error(e.getMessage());
        }
        return RestApiResp.error(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public RestApiResp handlerNoFoundException(Exception e) {
        log.error(e.getMessage());
        return RestApiResp.error("路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public RestApiResp handleMultipartException(MultipartException e) {
        log.error(e.getMessage());
        return RestApiResp.error("客户端请求错误，请选择上传的文件！");
    }


    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public RestApiResp validatedBindException(BindException e) {
        log.error(e.getMessage());
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return RestApiResp.error(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = "";
        if (fieldError != null) {
            String field = fieldError.getField();
            message = field + fieldError.getDefaultMessage();
        }
        return RestApiResp.error(message);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public Object validExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String message = "";
        for (ConstraintViolation<?> violation : constraintViolations) {
            String s = violation.getPropertyPath().toString();
            int index = s.indexOf('.');
            if (index > 0) {
                s = s.substring(index + 1);
            }
            message = s + violation.getMessage();
            break;
        }
        log.error(e.getMessage());
        return RestApiResp.error(message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public Object HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        log.error(e.getMessage());
        return RestApiResp.error("上传参数无效");
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public RestApiResp handleException(Throwable e) {
        log.error(e.getMessage(), e);
        return RestApiResp.error("系统错误");
    }
}
