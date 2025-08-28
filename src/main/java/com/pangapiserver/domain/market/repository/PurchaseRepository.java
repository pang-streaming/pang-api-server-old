package com.pangapiserver.domain.market.repository;

import com.pangapiserver.domain.market.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, UUID> {
}
