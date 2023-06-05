package com.github.cloudgyb.heartchat.modules.signup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * 注册表单
 *
 * @author geng
 * @since 2023/03/05 10:06:45
 */
public class SignupForm {
    @NotBlank
    @Length(max = 20)
    private String username;
    @NotBlank
    @Length(max = 20)
    private String password;
    @Email
    @Length(max = 30)
    private String email;
    @NotBlank
    private String captcha;

    private String cid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
