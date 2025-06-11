package com.pangapiserver.application.auth;

import com.pangapiserver.application.auth.data.LoginRequest;
import com.pangapiserver.application.auth.data.RegisterRequest;
import com.pangapiserver.application.auth.data.TokenResponse;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.exception.UserPasswordIncorrectException;
import com.pangapiserver.domain.user.service.UserService;
import com.pangapiserver.infrastructure.common.dto.BaseResponse;
import com.pangapiserver.infrastructure.security.token.TokenProvider;
import com.pangapiserver.infrastructure.security.token.enumeration.TokenType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class AuthUseCase {
    private final TokenProvider provider;
    private final UserService service;
    private final BCryptPasswordEncoder encoder;

    public BaseResponse<TokenResponse> login(LoginRequest request) {
        UserEntity user = service.getByUsername(request.id());
        if (!encoder.matches(request.password(), user.getPassword())) throw new UserPasswordIncorrectException();
        return BaseResponse.ok(HttpStatus.OK, generateTokens(user));
    }

    private TokenResponse generateTokens(UserEntity user) {
        return new TokenResponse(
            provider.generate(TokenType.ACCESS, user),
            provider.generate(TokenType.REFRESH, user)
        );
    }

    public void register(RegisterRequest request) {
        service.validateByUsernameAndEmail(request.id(), request.email());
        service.create(request.toEntity(encoder.encode(request.password())));
    }
}
