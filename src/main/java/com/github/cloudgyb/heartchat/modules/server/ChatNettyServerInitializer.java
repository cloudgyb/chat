package com.github.cloudgyb.heartchat.modules.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author geng
 * @since 2023/02/27 22:21:11
 */
public class ChatNettyServerInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) {
        ch.pipeline()
                .addLast(new LoggingHandler(LogLevel.DEBUG));
    }
}
