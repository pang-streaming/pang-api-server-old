package com.pangapiserver.domain.cash.service;

import com.pangapiserver.domain.cash.data.CashTransactionDto;
import com.pangapiserver.domain.cash.entity.CashEntity;
import com.pangapiserver.domain.cash.enumeration.CashType;
import com.pangapiserver.domain.cash.exception.InsufficientBalanceException;
import com.pangapiserver.domain.cash.repository.CashRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashService {
    private final CashRepository repository;

    /** 입금 */
    public void deposit(UserEntity user, int amount, String description) {
        CashEntity cash = CashEntity.builder()
            .user(user)
            .type(CashType.CHARGE)
            .amount(amount)
            .description(description)
            .created_at(LocalDateTime.now())
            .build();

        repository.save(cash);
    }

    /** 출금 */
    public void withdraw(UserEntity user, int amount, String description) {
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

        repository.save(cash);
    }

    public void refund(UserEntity user, int amount, String description) {
        CashEntity cash = CashEntity.builder()
            .user(user)
            .type(CashType.REFUND)
            .amount(amount)
            .description(description)
            .created_at(LocalDateTime.now())
            .build();

        repository.save(cash);
    }

    public int getBalance(UserEntity user) {
        return repository.sumAmountByUserId(user.getId()).orElse(0);
    }

    public List<CashTransactionDto> getTransactions(UserEntity user) {
        return repository.findAllByUser(user);
    }
}