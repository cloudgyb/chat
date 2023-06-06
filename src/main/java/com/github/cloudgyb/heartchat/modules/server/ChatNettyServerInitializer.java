package com.github.cloudgyb.heartchat.modules.server;

import com.github.cloudgyb.heartchat.modules.server.protocol.ImMsgProtocols;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author geng
 * @since 2023/02/27 22:21:11
 */
public class ChatNettyServerInitializer extends ChannelInitializer<NioSocketChannel> {
    private final static int max_msg_length = 102400;

    @Override
    protected void initChannel(NioSocketChannel ch) {
        ch.pipeline()
                .addLast(new LengthFieldBasedFrameDecoder(max_msg_length,
                        0, 4))
                .addLast(new ProtobufDecoder(ImMsgProtocols.ImSendMsg.getDefaultInstance()))
                .addLast(new LoggingHandler(LogLevel.DEBUG));
    }
}
