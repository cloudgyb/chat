package com.github.cloudgyb.heartchat.modules.login;

/**
 * @author geng
 * @since 2023/03/01 20:51:00
 */
public class LoginResult {
    private boolean success;
    private String username;
    private String reason;
    private String token;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
