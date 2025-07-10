package com.pangapiserver.application.cash.data;

import java.util.UUID;

public record CashChargeRequest(
    UUID cardId,
    int amount
) { }
