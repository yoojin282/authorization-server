package com.yoojin282.authorizationserver.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter @Setter
public class Authorization {
    @Id
    @Column(length = 100, nullable = false)
    private String id;
    @Column(length = 100, nullable = false)
    private String registeredClientId;
    @Column(length = 200, nullable = false)
    private String principalName;
    @Column(length = 100, nullable = false)
    private String authorizationGrantType;
    @Column(length = 1000)
    private String authorizedScopes;
    @Column(columnDefinition = "text")
    private String attributes;
    @Column(length = 500)
    private String state;
    @Column(columnDefinition = "text")
    private String authorizationCodeValue;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Instant authorizationCodeIssuedAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Instant authorizationCodeExpiresAt;
    @Column(columnDefinition = "text")
    private String authorizationCodeMetadata;

    @Column(columnDefinition = "text")
    private String accessTokenValue;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Instant accessTokenIssuedAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Instant accessTokenExpiresAt;
    @Column(columnDefinition = "text")
    private String accessTokenMetadata;
    @Column(length = 100)
    private String accessTokenType;
    @Column(length = 1000)
    private String accessTokenScopes;

    @Column(columnDefinition = "text")
    private String refreshTokenValue;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Instant refreshTokenIssuedAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Instant refreshTokenExpiresAt;
    @Column(columnDefinition = "text")
    private String refreshTokenMetadata;

    @Column(columnDefinition = "text")
    private String oidcIdTokenValue;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Instant oidcIdTokenIssuedAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Instant oidcIdTokenExpiresAt;
    @Column(columnDefinition = "text")
    private String oidcIdTokenMetadata;
}
