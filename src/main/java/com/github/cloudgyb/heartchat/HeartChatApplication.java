package com.github.cloudgyb.heartchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.cloudgyb.heartchat.mapper")
public class HeartChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeartChatApplication.class, args);
    }

}
