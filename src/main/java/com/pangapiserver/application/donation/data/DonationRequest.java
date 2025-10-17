package com.pangapiserver.application.donation.data;

import jakarta.validation.constraints.Positive;

public record DonationRequest(
        String username, // 스트리머

        @Positive(message = "기부 금액은 0보다 커야 합니다.")
        int amount
) {

}
