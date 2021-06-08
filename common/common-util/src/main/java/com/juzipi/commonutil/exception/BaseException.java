package com.juzipi.commonutil.exception;

/**
 * @Author juzipi
 * @Date 2021/4/29 9:55
 * @Info 基础异常
 */
public class BaseException extends RuntimeException {


    //所属模块
    private String module;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误码对应的参数
     */
    private Object args;

    /**
     * 错误消息
     */
    private String message;


    public BaseException(String module, Integer code, Object args, String message) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.message = message;
    }


    public BaseException(String module, Integer code, String message) {
        this.module = module;
        this.code = code;
        this.message = message;
    }

    public BaseException(String module, String message) {
        this.module = module;
        this.message = message;
    }

    public BaseException(Integer code, Object args) {
        this.code = code;
        this.args = args;
    }

    public BaseException(String message) {
        this.message = message;
    }


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getArgs() {
        return args;
    }

    public void setArgs(Object args) {
        this.args = args;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
