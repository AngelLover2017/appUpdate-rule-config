package com.example.demo.entity;

import java.util.HashMap;
import java.util.Map;

public class ReturnMessage {
    private int code;  // 状态码。200 OK，其他失败
    private String message;  // 返回信息
    private Map<String, Object> data = new HashMap<String, Object>();

    public ReturnMessage() {
    }

    public ReturnMessage(int code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ReturnMessage success() {
        return new ReturnMessage().setCode(200);
    }

    public static ReturnMessage fail(int failCode) {
        return new ReturnMessage().setCode(failCode);
    }

    public int getCode() {
        return code;
    }

    public ReturnMessage setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ReturnMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ReturnMessage setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public ReturnMessage setParam(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return "ReturnMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}