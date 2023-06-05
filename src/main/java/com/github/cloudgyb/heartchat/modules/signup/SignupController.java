package com.github.cloudgyb.heartchat.modules.signup;

import com.github.cloudgyb.heartchat.modules.common.RestApiResp;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册
 *
 * @author geng
 * @since 2023/03/04 21:11:15
 */
@RestController
@Validated
public class SignupController {
    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public SignupResult signup(@RequestBody @Validated SignupForm form) {
        return signupService.signup(form);
    }

    @PostMapping("/signup/captcha")
    public RestApiResp sendSignupCaptcha(@RequestParam @NotBlank @Email String email) {
        if (signupService.sendEmailCaptcha(email)) {
            return RestApiResp.ok();
        }
        return RestApiResp.error("邮件发送失败！");
    }
}
