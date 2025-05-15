package com.pangapiserver.presentation.auth;

import com.pangapiserver.application.auth.AuthUseCase;
import com.pangapiserver.application.auth.data.LoginRequest;
import com.pangapiserver.application.auth.data.RegisterRequest;
import com.pangapiserver.application.auth.data.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUseCase useCase;

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {
        return useCase.login(request);
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest request) {
        useCase.register(request);
    }
}
