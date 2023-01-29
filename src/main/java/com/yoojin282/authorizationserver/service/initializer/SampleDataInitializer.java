package com.yoojin282.authorizationserver.service.initializer;

import com.yoojin282.authorizationserver.domain.User;
import com.yoojin282.authorizationserver.module.user.UserService;
import com.yoojin282.authorizationserver.module.user.support.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class SampleDataInitializer implements InitializingBean {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void init() {
        initUser();
    }

    private void initUser() {
        userService.save(User.builder()
                .email("user@example.com")
                .name("일반사용자")
                .password(passwordEncoder.encode("user-password"))
                .enabled(true)
                .authorized(true)
                .roles(List.of(Role.USER))
                .build());
        userService.save(User.builder()
                .email("admin@example.com")
                .name("관리자")
                .password(passwordEncoder.encode("admin-password"))
                .enabled(true)
                .authorized(true)
                .roles(List.of(Role.USER, Role.ADMIN))
                .build());
        userService.save(User.builder()
                .email("sys.admin@example.com")
                .name("관리자")
                .password(passwordEncoder.encode("sys.admin-password"))
                .enabled(true)
                .authorized(true)
                .roles(List.of(Role.USER, Role.ADMIN, Role.SYSTEM_ADMIN))
                .build());
    }

    @Override
    public void afterPropertiesSet() {
        init();
    }
}
