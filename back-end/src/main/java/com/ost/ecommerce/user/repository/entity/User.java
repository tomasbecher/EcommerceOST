package com.ost.ecommerce.user.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "receive_newsletter", nullable = false)
    private Boolean receiveNewsletter;

    public User(String username, Integer personId, String password, Boolean receiveNewsletter) {
        this.username = username;
        this.personId = personId;
        this.password = password;
        this.receiveNewsletter = receiveNewsletter;
        this.lastLogin = LocalDateTime.now();
        this.enabled = true;
    }
}
