package com.pangapiserver.presentation.donation;

import com.pangapiserver.application.donation.DonationUseCase;
import com.pangapiserver.infrastructure.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {
    private final DonationUseCase useCase;

    @PostMapping
    public Response createDonation() {
        return useCase.createDonation();
    }
}
