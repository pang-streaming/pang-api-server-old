package com.pangapiserver.infrastructure.payment.dto;

import lombok.Builder;
import lombok.Data;

public record PaymentCardResponse (
    String state,
    String errorMessage,
    String CSTURL,
    String price,
    String mul_no
) { }
