package com.pangapiserver.application.donation.data.request;

public record DonationCreateRequest(
    String price,
    String content,
    String voice_id
)
{ }
