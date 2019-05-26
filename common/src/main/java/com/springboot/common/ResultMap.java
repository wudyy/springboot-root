package com.springboot.common;


import java.util.HashMap;


/**
 * 通用返回结果集
 */
public class ResultMap extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public ResultMap() {
        this(ResultCodeType.OK);
    }

    public ResultMap(ResultCodeType codeType) {
        put("code", codeType.getCode());
        put("message", codeType.getMessage());
    }

    public ResultMap(ResultCodeType codeType, String message) {
        put("code", codeType.getCode());
        if (message != null && !"".equals(message)) {
            put("message", message);
        } else
            put("message", codeType.getMessage());
    }


    public void setResultCode(ResultCodeType codeType) {
        put("code", codeType.getCode());
        put("message", codeType.getMessage());
    }

    public int getCode() {
        return (Integer) get("code");
    }

    public void setCode(int code) {
        put("code", code);
    }

    public Object getMessage() {
        return get("message");
    }

    public void setMessage(String message) {
        put("message", message);
    }

    public void add(String key, Object value) {
        put(key, value);
    }

    public void addData(Object value) {
        put("data", value);
    }

    public boolean isSuccess() {
        return ResultCodeType.OK.getCode() == getCode();
    }

}
