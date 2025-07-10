package com.pangapiserver.infrastructure.payment.dto;

import lombok.Builder;
import lombok.Data;


public record RegisterCardResponse (
    String state,
    String errorMessage,
    String errno,
    String errnoDetail,
    String encBill,
    String billAuthNo,
    String cardno,
    String cardname,
    String remoteaddr
) {}
