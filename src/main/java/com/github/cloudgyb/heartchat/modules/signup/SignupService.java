package com.github.cloudgyb.heartchat.modules.signup;

import cn.hutool.core.util.RandomUtil;
import com.github.cloudgyb.heartchat.modules.common.service.MailSendService;
import com.github.cloudgyb.heartchat.domain.UserEntity;
import com.github.cloudgyb.heartchat.modules.common.service.UserService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author geng
 * @since 2023/03/04 21:16:31
 */
@Service
public class SignupService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String captchaCachePrefix = "captcha:";
    private final RedisTemplate<String, String> redisTemplate;
    private final UserService userService;
    private final MailSendService mailSendService;

    public SignupService(RedisTemplate<String, String> redisTemplate,
                         UserService userService, MailSendService mailSendService) {
        this.redisTemplate = redisTemplate;
        this.userService = userService;
        this.mailSendService = mailSendService;
    }

    public boolean sendEmailCaptcha(String email) {
        int randNum = RandomUtil.randomInt(1000, 9999);
        redisTemplate.opsForValue().set(captchaCachePrefix + randNum, email);
        String mailText = "您正在使用该邮件注册<b>心聊</b>新用户，感谢您的支持！<br>" +
                "您的验证码：<b>" + randNum + "</b>";
        try {
            mailSendService.sendEmail("心聊-注册码", email, mailText, true);
            return true;
        } catch (MessagingException e) {
            log.error("邮件发送失败！", e);
            return false;
        }
    }

    public SignupResult signup(SignupForm form) {
        String captcha = form.getCaptcha();
        String s = redisTemplate.opsForValue().get(captchaCachePrefix + captcha);
        SignupResult signupResult = new SignupResult();
        if (s == null || !s.equals(form.getEmail())) {
            signupResult.setSuccess(false);
            signupResult.setReason("验证码错误！");
            return signupResult;
        }
        redisTemplate.delete(captchaCachePrefix + captcha);
        UserEntity user = new UserEntity();
        user.setUsername(form.getUsername());
        user.setAvatar("");
        user.setPassword(form.getPassword());
        user.setCid(form.getCid());
        user.setNickname(form.getUsername());
        userService.save(user);
        signupResult.setSuccess(true);
        signupResult.setUserId(user.getId());
        signupResult.setUsername(user.getUsername());
        return signupResult;
    }

}
