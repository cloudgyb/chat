package com.github.cloudgyb.heartchat.modules.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author geng
 * @since 2023/02/26 15:34:40
 */
public class ChatNettyServer {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final NioEventLoopGroup boss;
    private final NioEventLoopGroup worker;
    private final ChatNettyServerConfig config;

    public ChatNettyServer(ChatNettyServerConfig config) {
        this.config = config;
        this.boss = new NioEventLoopGroup();
        this.worker = new NioEventLoopGroup();
    }

    public void start() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ChannelFuture channelFuture = serverBootstrap
                .group(this.boss, this.worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChatNettyServerInitializer())
                .bind(config.getHost(), config.getPort());
        try {
            channelFuture.sync();
            logger.info("Chat Server started on {}:{}.", config.getHost(), config.getPort());
        } catch (Exception e) {
            throw new RuntimeException("Chat Server start failed!", e);
        }
    }

    public void stop() {
        worker.shutdownGracefully();
        boss.shutdownGracefully();
        logger.info("Chat Server stopped!");
    }
}
