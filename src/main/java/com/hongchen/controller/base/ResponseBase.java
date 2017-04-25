package com.hongchen.controller.base;

/**
 * Created by lichongda on 2017/3/27.
 */
public class ResponseBase<T> {
    private int code;
    private String error;
    private T data;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }



    public ResponseBase<T> success(T t){
        ResponseBase<T> responseBase = new ResponseBase<>();
        responseBase.setCode(0);
        responseBase.setData(t);
        return responseBase;
    }

    public ResponseBase<T> error(String msg,int code){
        ResponseBase<T> responseBase = new ResponseBase<>();
        responseBase.setCode(code);
        responseBase.setError(msg);
        return responseBase;
    }

    public ResponseBase<T> error(String msg){
        ResponseBase<T> responseBase = new ResponseBase<>();
        responseBase.setCode(1);//错误码
        responseBase.setError(msg);
        return responseBase;
    }
}
