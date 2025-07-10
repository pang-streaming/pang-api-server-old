package com.pangapiserver.domain.cash.entity;

import com.pangapiserver.domain.account.enumeration.AccountType;
import com.pangapiserver.domain.cash.enumeration.CashType;
import com.pangapiserver.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cash")
@Builder
public class CashEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private CashType type;

    private int amount;

    private LocalDateTime created_at;

    private String description;
}
