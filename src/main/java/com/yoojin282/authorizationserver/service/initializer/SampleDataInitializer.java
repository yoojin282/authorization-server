package com.yoojin282.authorizationserver.service.initializer;

import com.yoojin282.authorizationserver.domain.User;
import com.yoojin282.authorizationserver.module.client.JpaRegisteredClientRepository;
import com.yoojin282.authorizationserver.module.user.UserService;
import com.yoojin282.authorizationserver.module.user.support.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class SampleDataInitializer implements InitializingBean {
    private final UserService userService;
    private final JpaRegisteredClientRepository jpaRegisteredClientRepository;
    private final PasswordEncoder passwordEncoder;

    public void init() {
        initUser();
        initClient();
    }

    private void initUser() {
        userService.save(User.builder()
                .email("user@example.com")
                .name("일반사용자")
                .password(passwordEncoder.encode("user-password"))
                .enabled(true)
                .authorized(true)
                .roles(Role.USER.name().toLowerCase())
                .build());
        userService.save(User.builder()
                .email("admin@example.com")
                .name("관리자")
                .password(passwordEncoder.encode("admin-password"))
                .enabled(true)
                .authorized(true)
                .roles(roleListToString(new Role[] {Role.USER, Role.ADMIN}))
                .build());
        userService.save(User.builder()
                .email("sys.admin@example.com")
                .name("최고관리자")
                .password(passwordEncoder.encode("sys.admin-password"))
                .enabled(true)
                .authorized(true)
                .roles(roleListToString(new Role[] {Role.USER, Role.ADMIN, Role.SYSTEM_ADMIN}))
                .build());
    }

    private void initClient() {
        TokenSettings tokenSettings = TokenSettings.builder()
                .refreshTokenTimeToLive(Duration.ofDays(30L))
                .accessTokenTimeToLive(Duration.ofMinutes(30L))
                .build();
        RegisteredClient registeredClient = RegisteredClient.withId("test-client")
                .clientId("test-client")
                .clientSecret(passwordEncoder.encode("secret"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .tokenSettings(tokenSettings)
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true)
                        .build())
                .build();
        jpaRegisteredClientRepository.save(registeredClient);

        tokenSettings = TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofDays(1L))
                .build();
        registeredClient = RegisteredClient.withId("resource-client")
                .clientId("resource-client")
                .clientSecret(passwordEncoder.encode("secret"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .scope("client")
                .scope("user")
                .tokenSettings(tokenSettings)
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false)
                        .build())
                .build();
        jpaRegisteredClientRepository.save(registeredClient);
    }

    @Override
    public void afterPropertiesSet() {
        init();
    }

    private String roleListToString(Role[] array) {
        return Arrays.stream(array).map(role ->
                role.name().toLowerCase()).collect(Collectors.joining(","));
    }
}
