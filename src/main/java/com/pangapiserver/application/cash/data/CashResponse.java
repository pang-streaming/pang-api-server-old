package com.pangapiserver.application.cash.data;

import com.pangapiserver.domain.cash.data.CashTransactionDto;
import com.pangapiserver.domain.cash.entity.CashEntity;

import java.util.List;

public record CashResponse(
    int balance,
    List<CashTransactionDto> transactions
) {}
