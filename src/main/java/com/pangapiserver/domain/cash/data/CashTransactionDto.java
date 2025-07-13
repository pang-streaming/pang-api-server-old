package com.pangapiserver.domain.cash.data;

import com.pangapiserver.domain.cash.enumeration.CashType;

import java.time.LocalDateTime;
import java.util.UUID;

public record CashTransactionDto(
    UUID id,
    CashType type,
    int amount,
    LocalDateTime createdAt,
    String description
) {}