package com.pangapiserver.infrastructure.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentCardResponse (
    String state,
    String errorMessage,

    @JsonProperty("CSTURL")
    String cstUrl,

    String price,

    @JsonProperty("mul_no")
    String mulNo
) { }
