package com.github.cloudgyb.heartchat.modules.common.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * 邮件发送服务
 *
 * @author geng
 * @since 2023/03/05 15:34:26
 */
@Service
public class MailSendService {
    private final MailProperties mailProperties;
    private final JavaMailSender mailSender;


    public MailSendService(MailProperties mailProperties,
                           JavaMailSender mailSender) {
        this.mailProperties = mailProperties;
        this.mailSender = mailSender;
    }

    public void sendEmail(String subject, String to, String content, boolean isHtml) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setFrom(mailProperties.getUsername());
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(content, isHtml);

        mailSender.send(mimeMessage);
    }
}
