package com.pangapiserver.presentation.donation.document;

import com.pangapiserver.application.donation.data.DonationRequest;
import com.pangapiserver.infrastructure.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Donation API", description = "도네이션 api")
public interface DonationDocuments {
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "도네이션 api", description = "도네이션을 합니다")
    Response createDonation(DonationRequest request);
}
