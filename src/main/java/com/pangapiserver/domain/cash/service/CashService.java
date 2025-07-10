package com.pangapiserver.domain.cash.service;

import com.pangapiserver.application.auth.data.TokenResponse;
import com.pangapiserver.domain.cash.data.CashResponse;
import com.pangapiserver.domain.cash.entity.CashEntity;
import com.pangapiserver.domain.cash.enumeration.CashType;
import com.pangapiserver.domain.cash.exception.InsufficientBalanceException;
import com.pangapiserver.domain.cash.repository.CashRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.exception.UserNotFoundException;
import com.pangapiserver.domain.user.repository.UserRepository;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import com.pangapiserver.infrastructure.security.token.enumeration.TokenType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CashService {
    private final CashRepository cashRepository;

    @Transactional
    public void charge(UserEntity user, int amount, String description) {
        CashEntity cash = CashEntity.builder()
            .user(user)
            .type(CashType.CHARGE)
            .amount(amount)
            .description(description)
            .created_at(LocalDateTime.now())
            .build();

        cashRepository.save(cash);
    }

    @Transactional
    public void use(UserEntity user, int amount, String description) {
        int balance = getBalance(user);
        if (balance < amount) {
            throw new InsufficientBalanceException();
        }

        CashEntity cash = CashEntity.builder()
            .user(user)
            .type(CashType.USE)
            .amount(-amount)
            .description(description)
            .created_at(LocalDateTime.now())
            .build();

        cashRepository.save(cash);
    }

    @Transactional
    public void refund(UserEntity user, int amount, String description) {
        CashEntity cash = CashEntity.builder()
            .user(user)
            .type(CashType.REFUND)
            .amount(amount)
            .description(description)
            .created_at(LocalDateTime.now())
            .build();

        cashRepository.save(cash);
    }

    public int getBalance(UserEntity user) {
        return cashRepository.sumByUserId(user.getId()).orElse(0);
    }

    public List<CashEntity> getTransactions(UserEntity user) {
        return cashRepository.findAllByUser(user);
    }
}