package com.springboot.common;

/**
 * 接口返回数据枚举
 */
public enum ResultCodeType {


    OK(200, "处理成功"),
    PARAM_ERROR(400, "参数异常"),
    SERVER_ERROR(500, "服务器异常"),
    BIZ_ERROR(501, "业务异常");
    private int code;
    private String message;

    ResultCodeType(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
