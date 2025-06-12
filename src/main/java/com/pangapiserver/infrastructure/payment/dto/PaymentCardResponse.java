package com.pangapiserver.infrastructure.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentCardResponse {
    private String state;
    private String errorMessage;
    private String CSTURL;
    private String price;
    private String mul_no;
}
