package com.github.cloudgyb.heartchat.modules.signup;

/**
 * @author geng
 * @since 2023/03/04 21:39:00
 */
public class SignupResult {
    private boolean success;
    private String username;
    private String userId;

    private String reason;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
