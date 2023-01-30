package com.yoojin282.authorizationserver.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "auth")
public class AuthServerProperties {
    private String keyPath;
    private String keyPassword;
    private String storePassword;
    private String alias;
}
