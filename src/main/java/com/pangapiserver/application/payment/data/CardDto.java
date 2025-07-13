package com.pangapiserver.application.payment.data;

import java.util.UUID;

public record CardDto (
    UUID cardId,
    String provider,
    String name
) { }
