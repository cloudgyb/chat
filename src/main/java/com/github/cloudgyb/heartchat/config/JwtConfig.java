package com.github.cloudgyb.heartchat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

/**
 * @author geng
 * @since 2023/03/02 20:52:52
 */
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secretKey;
    private Duration duration;
    private List<String> skipValidateUrls;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<String> getSkipValidateUrls() {
        return skipValidateUrls;
    }

    public void setSkipValidateUrls(List<String> skipValidateUrls) {
        this.skipValidateUrls = skipValidateUrls;
    }
}
