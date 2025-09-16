package com.pangapiserver.application.donation;

import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.common.exception.PangInternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DonationUseCase {
    public Response createDonation() {
        // TODO : 도네이션 저장로직 구현 필요
        throw new PangInternalServerException();
//        return Response.ok("도네이션 생성을 완료하였습니다.");
    }
}
