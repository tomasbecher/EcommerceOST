package com.ost.ecommerce.security.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity(name = "refresh_token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;

    @Column(name = "user_id", nullable = false)
    private Integer userId;
}
