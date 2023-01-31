package com.yoojin282.authorizationserver.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Builder
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Client {
    @Id
    @Column(length = 100, nullable = false)
    private String id;
    @Column(length = 100, nullable = false)
    private String clientId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Instant clientIdIssuedAt;
    @Column(length = 200)
    private String clientSecret;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Instant clientSecretExpiresAt;
    @Column(length = 200, nullable = false)
    private String clientName;

    @Column(length = 1000, nullable = false)
    private String clientAuthenticationMethods;

    @Column(length = 1000, nullable = false)
    private String authorizationGrantTypes;

    @Column(length = 1000)
    private String redirectUris;

    @Column(length = 1000, nullable = false)
    private String scopes;

    @Column(length = 2000, nullable = false)
    private String clientSettings;

    @Column(length = 2000, nullable = false)
    private String tokenSettings;

}
