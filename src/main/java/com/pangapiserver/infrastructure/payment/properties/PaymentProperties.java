package com.pangapiserver.infrastructure.payment.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.pg")
public class PaymentProperties {
    private String linkkey;
    private String linkval;
    private String sellerid;
}
