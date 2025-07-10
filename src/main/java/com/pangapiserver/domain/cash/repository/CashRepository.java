package com.pangapiserver.domain.cash.repository;

import com.pangapiserver.domain.cash.data.CashTransactionDto;
import com.pangapiserver.domain.cash.entity.CashEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CashRepository extends JpaRepository<CashEntity, UUID>, CashQueryRepository {
}
