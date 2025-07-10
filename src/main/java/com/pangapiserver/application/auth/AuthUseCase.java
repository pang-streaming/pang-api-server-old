package com.pangapiserver.application.auth;

import com.pangapiserver.application.auth.data.LoginRequest;
import com.pangapiserver.application.auth.data.RefreshRequest;
import com.pangapiserver.application.auth.data.RegisterRequest;
import com.pangapiserver.application.auth.data.TokenResponse;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.exception.ExpiredTokenException;
import com.pangapiserver.domain.user.exception.InvalidTokenTypeException;
import com.pangapiserver.domain.user.exception.UserPasswordIncorrectException;
import com.pangapiserver.domain.user.service.UserService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.token.TokenParser;
import com.pangapiserver.infrastructure.security.token.TokenProvider;
import com.pangapiserver.infrastructure.security.token.enumeration.TokenType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class AuthUseCase {
    private final TokenProvider provider;
    private final TokenParser parser;
    private final UserService service;
    private final BCryptPasswordEncoder encoder;

    public DataResponse<TokenResponse> login(LoginRequest request) {
        UserEntity user = service.getByUsername(request.id());
        if (!encoder.matches(request.password(), user.getPassword())) throw new UserPasswordIncorrectException();
        return DataResponse.ok("로그인 성공", generateTokens(user));
    }

    private TokenResponse generateTokens(UserEntity user) {
        return new TokenResponse(
            provider.generate(TokenType.ACCESS, user),
            provider.generate(TokenType.REFRESH, user)
        );
    }

    public Response register(RegisterRequest request) {
        service.validateByUsernameAndEmail(request.id(), request.email());
        service.create(request.toEntity(encoder.encode(request.password())));
        return Response.ok("register successful");
    }

    public DataResponse<TokenResponse> refresh(RefreshRequest request) {
        String refreshToken = request.refreshToken();

        if (parser.findType(refreshToken) != TokenType.REFRESH) {
            throw new InvalidTokenTypeException();
        }

        if (parser.findExpiration(refreshToken).before(new Date())) {
            throw new ExpiredTokenException();
        }

        UserEntity user = service.getByUsername(parser.findUsername(refreshToken));
        return DataResponse.ok("재발급 성공", generateTokens(user));
    }
}
