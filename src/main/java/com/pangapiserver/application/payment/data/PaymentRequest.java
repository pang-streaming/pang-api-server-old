package com.pangapiserver.application.payment.data;

import java.util.UUID;

public record PaymentRequest(
    String name,
    UUID card_id,
    Number price
) {}
