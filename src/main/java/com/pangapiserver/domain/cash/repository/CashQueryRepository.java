package com.pangapiserver.domain.cash.repository;

import com.pangapiserver.domain.cash.data.CashTransactionDto;
import com.pangapiserver.domain.user.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CashQueryRepository {
    Optional<Integer> sumAmountByUserId(UUID userId);
    List<CashTransactionDto> findAllByUser(UserEntity user);
}
