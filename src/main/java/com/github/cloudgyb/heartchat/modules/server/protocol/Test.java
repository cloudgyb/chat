package com.github.cloudgyb.heartchat.modules.server.protocol;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author geng
 * @since 2023/03/11 16:51:54
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ImMsgProtocols.ImMsg.Builder builder = ImMsgProtocols.ImMsg.newBuilder();
        ImMsgProtocols.ImMsg helloProtobuf = builder.setId(2000)
                .setContent("hello protobuf")
                .build();
        System.out.println(helloProtobuf);
        byte[] bytes = helloProtobuf.toByteArray();
        System.out.println(bytes.length);

        ImMsgProtocols.ImMsg imMsg = ImMsgProtocols.ImMsg.parseFrom(bytes);
        System.out.println(imMsg);
        imMsg.writeTo(new FileOutputStream("serial.dat"));

        ImMsgProtocols.ImMsg imMsg1 = ImMsgProtocols.ImMsg.parseFrom(new FileInputStream("serial.dat"));

        System.out.println(imMsg1);
    }
}
