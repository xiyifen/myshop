package com.xiyifen.myshop.common.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xiyifen.myshop.common.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;

/**
 * 全局统一异常处理
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE) // 表示此handler级别是最高的，有异常时优先执行
public class GlobalExceptionHandler {



    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseResult handleAuthorizationException(UnauthorizedException e){
        log.error("你没有权限: {}",e);
        return new ResponseResult().error(401,"您没有权限访问！");

    }

    @ExceptionHandler(value = MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleMyException(MyException e){
        log.error("系统错误：{}",e);
        return new ResponseResult().error(500,e.getMessage());
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseResult handleTokenExpiredException(TokenExpiredException e){
            log.error("token校验不通过：{}",e.getMessage());
            return new ResponseResult().error(500,e.getMessage());
    }
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseResult handleAuthenticationException(AuthenticationException e){
            log.error("token校验不通过：{}",e.getMessage());
            return new ResponseResult().error(500,e.getMessage());
    }



    @ExceptionHandler(value = Exception.class)
    public ResponseResult handleException(Exception e){
        log.error("系统内部异常，异常信息：{}",e);
        return new ResponseResult().error(500,e.getMessage());
    }

    /**
     *  @valid 修饰的属性
     *  校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(BindException.class)
    public ResponseResult validationBodyException(BindException exception){

        BindingResult result = exception.getBindingResult();
        StringBuilder sb=new StringBuilder();
        if (result.hasErrors()) {

            List<ObjectError> errors = result.getAllErrors();



            errors.forEach(p ->{
                FieldError fieldError = (FieldError) p;
                log.error("Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                        "},errorMessage{"+fieldError.getDefaultMessage()+"}");
                sb.append(fieldError.getDefaultMessage()+";");

            });

        }
        return new ResponseResult().error(500,sb.toString());
    }

    /**
     * 参数类型转换错误
     *
     * @param exception 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseResult parameterTypeException(HttpMessageConversionException exception){

        log.error(exception.getCause().getLocalizedMessage());
        return new ResponseResult().error(500,"类型转换错误");

    }


}
