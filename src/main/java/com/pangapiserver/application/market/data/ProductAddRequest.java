package com.pangapiserver.application.market.data;

public record ProductAddRequest(
    String image,
    String name,
    String description,
    int price,
    String fileUrl
) {}