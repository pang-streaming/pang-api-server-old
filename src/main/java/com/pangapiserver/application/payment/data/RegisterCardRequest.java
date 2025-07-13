package com.pangapiserver.application.payment.data;

public record RegisterCardRequest(
        String name,
        String phone,
        String cardNumber,
        String expiredYear,
        String expiredMonth,
        String birth,
        String cardPassword
) {}