package com.pangapiserver.infrastructure.common.exception;

import com.pangapiserver.infrastructure.common.dto.BaseResponse;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    private ResponseEntity<BaseResponse> handleAll(Exception e) {
        BaseResponse response = BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
