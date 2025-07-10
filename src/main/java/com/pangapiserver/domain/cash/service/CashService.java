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
    private final UserRepository userRepository;
    private final UserAuthenticationHolder holder;

    @Transactional
    public void charge(UUID userId, int amount, String description) {
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

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
    public void use(UUID userId, int amount, String description) {
        int balance = getBalance(userId);
        if (balance < amount) {
            throw new InsufficientBalanceException();
        }

        UserEntity user = userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

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
    public void refund(UUID userId, int amount, String description) {
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

        CashEntity cash = CashEntity.builder()
            .user(user)
            .type(CashType.REFUND)
            .amount(amount)
            .description(description)
            .created_at(LocalDateTime.now())
            .build();

        cashRepository.save(cash);
    }

    public DataResponse<CashResponse> getCash() {
        UserEntity user = holder.current();
        List<CashEntity> transaction = cashRepository.findAllByUser(user);

        return DataResponse.ok(
            "캐시 조회 성공",
            new CashResponse(
                getBalance(user.getId()),
                transaction
            )
        );
    }

    public int getBalance(UUID userId) {
        return cashRepository.sumByUserId(userId).orElse(0);
    }
}