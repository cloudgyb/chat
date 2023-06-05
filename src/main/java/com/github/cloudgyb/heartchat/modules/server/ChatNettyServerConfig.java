package com.github.cloudgyb.heartchat.modules.server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author geng
 * @since 2023/02/27 22:16:49
 */
@ConfigurationProperties(prefix = "chat.server")
@Configuration
public class ChatNettyServerConfig {
    private String host;
    private int port;

    public ChatNettyServerConfig() {
    }

    public ChatNettyServerConfig(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
