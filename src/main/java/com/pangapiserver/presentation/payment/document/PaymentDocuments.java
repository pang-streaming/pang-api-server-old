package com.pangapiserver.presentation.payment.document;

import com.pangapiserver.application.payment.data.CardDto;
import com.pangapiserver.application.payment.data.RegisterCardRequest;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Tag(name = "결제 Api", description = "결제 api")
public interface PaymentDocuments {

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "카드 등록", description = "카드를 등록합니다.")
    Response registerPayment(
        @RequestBody RegisterCardRequest request
    );

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "등록된 카드 가져오기", description = "등록된 카드 목록을 조회합니다.")
    DataResponse<List<CardDto>> getCards();
}