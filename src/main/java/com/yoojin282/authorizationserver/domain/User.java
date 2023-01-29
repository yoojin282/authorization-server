package com.yoojin282.authorizationserver.domain;

import com.yoojin282.authorizationserver.module.user.support.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.Instant;
import java.util.List;

@DynamicInsert
@Getter @Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, length = 100)
    private String email;
    @Column(length = 20)
    private String name;
    @Column
    private String password;
    @OneToMany
    private List<Role> roles;
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
        this.enabled = true;
        this.authorized = false;
    }
}
