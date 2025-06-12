package com.pangapiserver.infrastructure.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterCardResponse {
    private String state;
    private String errorMessage;
    private String errno;
    private String errnoDetail;
    private String encBill;
    private String billAuthNo;
    private String cardno;
    private String cardname;
    private String remoteaddr;
}
