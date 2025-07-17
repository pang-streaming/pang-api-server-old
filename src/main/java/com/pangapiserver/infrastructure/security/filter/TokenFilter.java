package com.pangapiserver.infrastructure.security.filter;

import com.pangapiserver.domain.user.entity.UserPrincipal;
import com.pangapiserver.domain.user.exception.UserNotFoundException;
import com.pangapiserver.domain.user.repository.UserRepository;
import com.pangapiserver.infrastructure.security.token.TokenExtractor;
import com.pangapiserver.infrastructure.security.token.TokenParser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
    private final UserRepository repository;
    private final TokenExtractor tokenExtractor;
    private final TokenParser parser;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        Enumeration<String> headers = request.getHeaders(HttpHeaders.AUTHORIZATION);
        String token = tokenExtractor.extract(headers);

        if(!token.isEmpty()) {
            setAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String token) {
        SecurityContextHolder.getContext().setAuthentication(
                createAuthentication(token)
        );
    }

    private Authentication createAuthentication(String token) {
        UserPrincipal principal = getMemberDetails(token);
        return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
    }

    private UserPrincipal getMemberDetails(String token) {
        return new UserPrincipal(repository.findByUsername(parser.findUsername(token)).orElseThrow(UserNotFoundException::new));
    }
}
