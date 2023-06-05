package com.github.cloudgyb.heartchat.modules.login;

import com.github.cloudgyb.heartchat.modules.common.service.JwtService;
import com.github.cloudgyb.heartchat.domain.UserEntity;
import com.github.cloudgyb.heartchat.modules.common.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author geng
 * @since 2023/03/01 20:45:48
 */
@Service
public class LoginService {
    private final UserService userService;
    private final JwtService jwtService;

    public LoginService(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public LoginResult login(LoginForm form) {
        String username = form.getUsername();
        String password = form.getPassword();
        UserEntity user = userService.getByUsername(username);
        LoginResult loginResult = new LoginResult();
        loginResult.setUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
            loginResult.setSuccess(false);
            loginResult.setReason("用户名或密码错误！");
            return loginResult;
        }
        String token = jwtService.generateToken(user);
        loginResult.setSuccess(true);
        loginResult.setToken(token);
        return loginResult;
    }

}
