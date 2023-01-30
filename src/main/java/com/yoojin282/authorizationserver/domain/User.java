package com.yoojin282.authorizationserver.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.Instant;

@DynamicInsert
@Getter @Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 100)
    private String email;
    @Column(length = 20)
    private String name;
    @Column
    private String password;
    @Column(length = 50)
    private String roles;
    @Column(columnDefinition = "tinyint(1) default 1")
    private Boolean enabled;
    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean authorized;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }
}
