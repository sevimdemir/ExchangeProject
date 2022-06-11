package com.openpayd.exchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContext {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
