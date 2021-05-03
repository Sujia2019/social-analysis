package com.psx.social;

import com.psx.social.websocket.WebSocketHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableScheduling
@SpringBootApplication
@EnableCaching
@EnableWebSocket
@MapperScan("com.psx.social.dao")
public class SocialApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SocialApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
//        解决WebSocket不能注入的问题
        WebSocketHandler.setApplicationContext(configurableApplicationContext);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SocialApplication.class);
    }

}
