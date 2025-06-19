package com.pangapiserver.infrastructure.common.exception;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.web.filter.OncePerRequestFilter;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.domain.common.exception.StatusCode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pangapiserver.infrastructure.common.dto.ErrorResponse;
import com.pangapiserver.domain.common.exception.GlobalExceptionStatusCode;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            setErrorResponse(response, GlobalExceptionStatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void setErrorResponse(HttpServletResponse response, StatusCode errorCode) throws IOException {
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json; charset=UTF-8");

        Response errorResponse = ErrorResponse.builder()
            .status(errorCode.getHttpStatus())
            .message(errorCode.getMessage())
            .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonResponse = mapper.writeValueAsString(errorResponse);
        response.getWriter()
            .write(jsonResponse);
    }
}
