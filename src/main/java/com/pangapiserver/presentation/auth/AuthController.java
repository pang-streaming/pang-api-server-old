package com.pangapiserver.presentation.auth;

import com.pangapiserver.application.auth.AuthUseCase;
import com.pangapiserver.application.auth.data.LoginRequest;
import com.pangapiserver.application.auth.data.RefreshRequest;
import com.pangapiserver.application.auth.data.RegisterRequest;
import com.pangapiserver.application.auth.data.TokenResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.presentation.auth.document.AuthDocuments;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthDocuments {
    private final AuthUseCase useCase;

    @Override
    @PostMapping("/login")
    public DataResponse<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return useCase.login(request);
    }

    @Override
    @PostMapping("/register")
    public Response register(@Valid @RequestBody RegisterRequest request) {
        return useCase.register(request);
    }

    @Override
    @PostMapping("/refresh")
    public DataResponse<TokenResponse> refresh(@Valid @RequestBody RefreshRequest request) {
        return useCase.refresh(request);
    }
}