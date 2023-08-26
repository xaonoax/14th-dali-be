package com.dali.dali.global.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.naver")
@Component
public class NaverProperties {
    private String clientId;
    private String clientSecret;
}
