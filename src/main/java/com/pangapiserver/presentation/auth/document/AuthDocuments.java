package com.pangapiserver.presentation.auth.document;

import com.pangapiserver.application.auth.data.LoginRequest;
import com.pangapiserver.application.auth.data.RefreshRequest;
import com.pangapiserver.application.auth.data.RegisterRequest;
import com.pangapiserver.application.auth.data.TokenResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Auth Api", description = "인증 / 인가 api")
public interface AuthDocuments {

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "로그인 API", description = "유저를 로그인합니다.")
    DataResponse<TokenResponse> login(LoginRequest request);

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "회원가입 API", description = "유저를 생성합니다.")
    Response register(RegisterRequest request);

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "토큰 재발급 API", description = "Bearer 토큰을 재발급합니다.")
    DataResponse<TokenResponse> refresh(RefreshRequest request);
}
