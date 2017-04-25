package com.hongchen.exception;

import com.hongchen.constants.Constants;
import com.hongchen.controller.base.ResponseBase;
import com.hongchen.util.DateUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * Created by lichongda on 2017/3/16.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler{
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    /**
     * 全局异常拦截,统一返回错误消息
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseBase<String> exceptionHandle(Exception ex) {
        System.out.print("message=" + ex);
        String message = ex.getMessage();
        ResponseBase<String> responseBaseVo = new ResponseBase<String>();
        if (ex instanceof HongchenException) {
            responseBaseVo.setCode(Constants.BUSINESS_ERROR);
            logger.error(message);
        } else if (ex instanceof BindException){
            BindException bindException = (BindException) ex;
            message = getBindingErrors(bindException.getBindingResult());
            responseBaseVo.setCode(Constants.PARAMETER_VALIDATION_ERROR);
            logger.error(message);
        }else if (ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
            message = getBindingErrors(methodArgumentNotValidException.getBindingResult());
            responseBaseVo.setCode(Constants.PARAMETER_VALIDATION_ERROR);
            logger.error(message);
        } else if(ex instanceof UnauthorizedException){
            UnauthorizedException e = (UnauthorizedException) ex;
            message = "无权限操作    "+ e.getMessage();
            responseBaseVo.setCode(6);
        }else {
            responseBaseVo.setCode(Constants.SYSTEM_ERROR);
            logger.error(message,ex);
        }
        responseBaseVo.setError(message);
        // SysLogUtil.log(Constants.OPERATION_FAILED,new Date(),message);
        return responseBaseVo;
    }

    /**
     * 组装参数校验错误信息
     * @param bindingResult
     * @return
     */
    private String getBindingErrors(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder errorMsg = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errorMsg.append(fieldError.getDefaultMessage()).append(";");
        }
        return errorMsg.toString();
    }

    /**
     * 参数绑定
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtil.str2Date(text));
            }
        });
    }

}
