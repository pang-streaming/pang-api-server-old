package com.pangapiserver.application.donation;

import com.pangapiserver.infrastructure.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DonationUseCase {
    public Response createDonation() {
        return Response.ok("도네이션 생성을 완료하였습니다.");
    }
}
