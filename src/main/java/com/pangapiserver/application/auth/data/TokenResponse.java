package com.pangapiserver.application.auth.data;

public record TokenResponse(
    String accessToken,
    String refreshToken
) { }
