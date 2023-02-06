package com.yoojin282.authorizationserver.module.user;

import com.yoojin282.authorizationserver.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OidcUserInfoService {
    private final UserRepository userRepository;

    public OidcUserInfo loadUser(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("사용자를 찾을수 없습니다."));
        return OidcUserInfo.builder()
                .name(user.getName())
                .build();
    }


}
