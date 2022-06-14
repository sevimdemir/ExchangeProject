package com.openpayd.exchange.config;

import com.bugsnag.Bugsnag;
import com.bugsnag.BugsnagSpringConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import(BugsnagSpringConfiguration.class)
@RequiredArgsConstructor
public class BugsnagConfig {

    private final Environment environment;

    @Bean
    public Bugsnag bugsnag() {
        return new Bugsnag(environment.getProperty("api.bugsnag.key"));
    }
}