package com.pangapiserver.infrastructure.common.exception;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.pangapiserver.domain.user.exception.UserAleadyExistException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (UserAleadyExistException e) {
            setErrorResponse(e.getStatusCode().getHttpStatus(), response, e.getMessage());
        } catch (Exception e) {
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, response, e.getMessage());
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json;charset=UTF-8");
        String json = String.format("{\"status\": %d, \"message\": \"%s\"}", status.value(), message);
        response.getWriter().write(json);
    }
}
