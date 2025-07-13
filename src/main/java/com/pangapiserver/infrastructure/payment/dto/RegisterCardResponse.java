package com.pangapiserver.infrastructure.payment.dto;

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
