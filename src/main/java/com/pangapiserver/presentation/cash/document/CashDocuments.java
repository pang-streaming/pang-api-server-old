package com.pangapiserver.presentation.cash.document;

import com.pangapiserver.application.cash.data.CashChargeRequest;
import com.pangapiserver.application.cash.data.CashResponse;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "펑(캐시) Api", description = "펑(캐시) api")
public interface CashDocuments {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "펑(캐시) 거래내역 조회", description = "펑(캐시)의 상세 거래내역을 조회합니다.")
    DataResponse<CashResponse> getCash();

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "펑(캐시) 결제", description = "카드로 펑(캐시)를 결제합니다.")
    Response chargeCash(@RequestBody CashChargeRequest request);
}