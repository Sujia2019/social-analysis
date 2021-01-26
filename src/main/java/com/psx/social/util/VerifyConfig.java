package com.psx.social.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VerifyConfig {
    @Bean
    public Verify getVerify() {
        return new Verify();
    }

}