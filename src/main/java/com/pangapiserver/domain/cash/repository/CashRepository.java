package com.pangapiserver.domain.cash.repository;

import com.pangapiserver.domain.cash.entity.CashEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CashRepository extends JpaRepository<CashEntity, UUID>, CashQueryRepository {
}
