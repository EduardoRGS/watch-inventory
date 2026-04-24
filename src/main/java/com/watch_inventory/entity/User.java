package com.watch_inventory.entity;

import com.watch_inventory.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.watch_inventory.entity.enums.Role.ROLE_USER;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "IDX_USER_USERNAME", columnList = "username", unique = true)
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (email != null) email = email.trim().toLowerCase();
        if (role == null) role = ROLE_USER;
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}
