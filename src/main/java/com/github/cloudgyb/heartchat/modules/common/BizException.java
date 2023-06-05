package com.github.cloudgyb.heartchat.modules.common;

/**
 * @author geng
 * @since 2023/03/05 11:09:14
 */
public class BizException extends RuntimeException {
    private Integer code;
    private String msg;

    public BizException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
