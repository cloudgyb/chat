package com.github.cloudgyb.heartchat.modules.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geng
 * @since 2023/03/01 20:48:29
 */
@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResult login(@RequestBody LoginForm loginForm) {
        return loginService.login(loginForm);
    }
}
