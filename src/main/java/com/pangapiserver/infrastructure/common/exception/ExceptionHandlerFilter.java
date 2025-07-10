package com.pangapiserver.infrastructure.common.exception;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import com.pangapiserver.domain.common.exception.StatusCode;
import com.pangapiserver.domain.common.exception.BasicException;
import com.pangapiserver.infrastructure.common.dto.ErrorResponse;
import com.pangapiserver.domain.common.exception.GlobalExceptionStatusCode;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BasicException e) {
            setErrorResponse(response, e.getStatusCode());
        }
    }

    private void setErrorResponse(HttpServletResponse response, StatusCode errorCode) throws IOException {
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter()
            .write(ErrorResponse.setErrorBody(GlobalExceptionStatusCode.INTERNAL_SERVER_ERROR));
    }
}
