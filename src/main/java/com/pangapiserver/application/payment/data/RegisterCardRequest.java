package com.pangapiserver.application.payment.data;

import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.enumeration.Role;

import java.time.LocalDate;

public record RegisterCardRequest(
        String name,
        String phone,
        String cardNumber,
        String expiredYear,
        String expiredMonth,
        String birth,
        String cardPassword
) {}