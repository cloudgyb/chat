package com.github.cloudgyb.heartchat.modules.common;

/**
 * @author geng
 * @since 2023/03/05 10:59:35
 */
public class RestApiResp {
    private int code;
    private String msg;
    private Object data;

    public RestApiResp(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RestApiResp of(int code, String msg, Object data) {
        return new RestApiResp(code, msg, data);
    }

    public static RestApiResp of(Exception e) {
        String msg;
        if (e.getMessage() != null)
            msg = e.getMessage();
        else msg = e.toString();
        return new RestApiResp(500, msg, null);
    }

    public static RestApiResp ok() {
        return ok(null);
    }

    public static RestApiResp ok(Object data) {
        return of(200, "successful!", data);
    }

    public static RestApiResp ok(String msg, Object data) {
        return of(200, msg, data);
    }

    public static RestApiResp error(String msg) {
        return of(500, msg, null);
    }

    public static RestApiResp error(Object data) {
        return of(500, "failed!", data);
    }

    public static RestApiResp error(Integer code, String msg, Object data) {
        return of(code, msg, data);
    }

    public static RestApiResp error(String msg, Object data) {
        return of(500, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}