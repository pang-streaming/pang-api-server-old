package com.pangapiserver.domain.cash.repository;

import com.pangapiserver.domain.cash.entity.CashEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CashRepository extends JpaRepository<CashEntity, UUID> {

    @Query("SELECT SUM(c.amount) FROM CashEntity c WHERE c.user.id = :userId")
    Optional<Integer> sumByUserId(UUID userId);
}
