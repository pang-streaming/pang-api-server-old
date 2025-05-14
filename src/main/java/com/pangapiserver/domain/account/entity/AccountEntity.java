package com.pangapiserver.domain.account.entity;



import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.Getter;
import lombok.Builder;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.pangapiserver.domain.account.enumeration.AccountType;

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
