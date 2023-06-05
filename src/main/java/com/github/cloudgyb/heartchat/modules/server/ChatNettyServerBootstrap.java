package com.github.cloudgyb.heartchat.modules.server;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author geng
 * @since 2023/02/27 22:26:47
 */
@Component
public class ChatNettyServerBootstrap implements ApplicationRunner {
    private ChatNettyServer chatNettyServer;
    private final ChatNettyServerConfig serverConfig;

    public ChatNettyServerBootstrap(ChatNettyServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Override
    public void run(ApplicationArguments args) {
        chatNettyServer = new ChatNettyServer(serverConfig);
        chatNettyServer.start();
    }

    @PreDestroy
    public void stop() {
        chatNettyServer.stop();
    }
}
