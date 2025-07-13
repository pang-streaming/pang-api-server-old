package com.pangapiserver.domain.account.entity;


import com.pangapiserver.domain.account.enumeration.AccountType;
import com.pangapiserver.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private UserEntity user;

    private String accountNumber;

    private String accountBankCode;

    private String accountName;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}
