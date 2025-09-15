package com.pangapiserver.presentation.donation.document;

import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Donation API", description = "도네이션 api")
public interface DonationDocuments {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "펑(캐시) 거래내역 조회", description = "펑(캐시)의 상세 거래내역을 조회합니다.")
    Response createDonation();
}
