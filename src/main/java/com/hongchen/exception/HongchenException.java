package com.hongchen.exception;


/**
 * Created by lichongda on 2017/3/28.
 * 自定义业务异常处理类
 */
public class HongchenException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public HongchenException(String message) {
        super(message);
    }
}
