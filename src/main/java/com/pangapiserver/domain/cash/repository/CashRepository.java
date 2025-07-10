package com.pangapiserver.domain.cash.repository;

import com.pangapiserver.domain.cash.data.CashTransactionDto;
import com.pangapiserver.domain.cash.entity.CashEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CashRepository extends JpaRepository<CashEntity, UUID> {

    @Query("SELECT SUM(c.amount) FROM CashEntity c WHERE c.user.id = :userId")
    Optional<Integer> sumByUserId(UUID userId);

    @Query("SELECT new com.pangapiserver.domain.cash.data.CashTransactionDto(c.id, c.type, c.amount, c.created_at, c.description) " +
        "FROM CashEntity c WHERE c.user = :user")
    List<CashTransactionDto> findAllByUser(UserEntity user);
}
