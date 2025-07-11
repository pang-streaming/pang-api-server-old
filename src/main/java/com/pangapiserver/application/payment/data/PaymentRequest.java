package com.pangapiserver.application.payment.data;

import java.util.UUID;

public record PaymentRequest(
    String name,
    UUID cardId,
    Number price
) {}
